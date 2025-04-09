package com.bus.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import static com.bus.springbatch.config.CustomFlowStatus.*;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfigV4 {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job deciderJob() {
        return new JobBuilder("FlowJobDecider", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(decideStep1())
                .next(is5highDecider())
                .from(is5highDecider()).on(FIVE_HIGH.name()).to(decideStep2())
                .from(is5highDecider()).on(FIVE_LOW.name()).to(decideStep3())
                .end()
                .build();
    }

    @Bean
    public Step decideStep1(){
        return new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("start Step 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step decideStep2(){
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("5보다 크니까 Step2");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step decideStep3(){
        return new StepBuilder("step3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("5보다 작으니까 Step3");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public JobExecutionDecider is5highDecider(){
        return new fiveHighDecider();
    }

    public static class fiveHighDecider implements JobExecutionDecider {

        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {

            int randomNumber = (int) (Math.random() * 10);
            return (randomNumber >= 5)
                    ? new FlowExecutionStatus(FIVE_HIGH.name())
                    : new FlowExecutionStatus(FIVE_LOW.name());
        }
    }
}
