package com.bus.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import static com.bus.springbatch.config.CustomFlowStatus.FIVE_HIGH;
import static com.bus.springbatch.config.CustomFlowStatus.FIVE_LOW;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfigV5 {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Primary
    @Bean
    public Job flowBuild() {
        return new JobBuilder("flowBuild", jobRepository)
                .incrementer(new RunIdIncrementer()) // JobBuilder 반환
                .start(flowV1()) // JobFlowBuilder 반환 : Flow 기반 Job을 구성할 수 있도록 도와주는 중간 빌더
                .end()           // FlowJobBuilder 반환 : 최종적으로 Job을 생성할 수 있는 빌더
                .build();        // FlowJob 반환 : Flow 객체 기반으로 실행 순서를 제어하는 Job
    }

    @Bean
    public Flow flowV1(){
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");

        return flowBuilder.start(flowStep1())
                .next(flowStep2())
                .end(); // flow 객체 생성
    }

    @Bean
    public Step flowStep1(){
        return new StepBuilder("flowStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("start flowStep 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step flowStep2(){
        return new StepBuilder("flowStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("flowStep2 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step noFlowStep3(){
        return new StepBuilder("noFlowStep3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("flowStep 없는 STEP3 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
