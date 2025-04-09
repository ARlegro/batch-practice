package com.bus.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static org.springframework.batch.core.ExitStatus.*;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfigV3 {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job helloJob() {
        return new JobBuilder("FlowJobTest", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                    .on("A")
                    .to(step2())
                    .on(FAILED.getExitCode())
                    .stop()

                .from(step2())
                    .on("*")
                    .to(step3())


                .from(step1())
                    .on("*").to(step3())
                    .next(step4())
                    .on("*").end()
                .end()
                .build();
    }

    @Bean
    public Step step1(){
        return new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step1 입니다");
                    throw new RuntimeException("Step1에서 예외 발생");
                    //return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step step2(){
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step2 입니다");
                    //contribution.setExitStatus(FAILED);
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step step3(){
        return new StepBuilder("step3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step3입니다.");
                    throw new RuntimeException("Step3 오류입니3다");
                    //return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step step4(){
        return new StepBuilder("step4", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step4입니다.");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
