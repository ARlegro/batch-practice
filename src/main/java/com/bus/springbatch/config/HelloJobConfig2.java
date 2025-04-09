package com.bus.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@RequiredArgsConstructor
public class HelloJobConfig2 {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job helloJobTest() {
        return new JobBuilder("helloFlowJob2", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(helloStep1())
                    .on("FAILED").to(helloStep2())
                .from(helloStep1())
                    .on("*").to(helloStep1())
                .end()
                .build();
    }


    @Bean
    public Step helloStep1() {
        return new StepBuilder("Step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello Step1 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step helloStep2() {
        return new StepBuilder("Step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello Step2 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}
