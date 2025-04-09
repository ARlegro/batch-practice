package com.bus.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
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

import static org.springframework.batch.core.ExitStatus.*;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfigV6 {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    // JobFlow 예시 구현해보기

    @Bean
    public Job flowBuild2() {
        return new JobBuilder("flowBuild2", jobRepository)
                .start(flowStep())
                .next(multiFlowStep1())
                .incrementer(new CustomJobParameterIncrementer()) // JobBuilder 반환
                .build();
//                .start(flowVV1())
//                    .on(COMPLETED.getExitCode()).to(flowVV2())
//                .from(flowVV1())
//                    .on("*").to(flowVV3())
//                .end()
//                .build();
    }

    @Bean
    public Flow flowVV1(){
        return new FlowBuilder<Flow>("flow1")
                .start(multiFlowStep1())
                .next(multiFlowStep2())
                .end(); // flow 객체 생성
    }

    @Bean
    public Flow flowVV2(){
        return new FlowBuilder<Flow>("flow2")
                .start(flowVV3())
                .next(multiFlowStep4())
                .end(); // flow 객체 생성
    }

    @Bean
    public Flow flowVV3(){
        return new FlowBuilder<Flow>("flow3")
                .start(multiFlowStep5())
                .next(multiFlowStep6())
                .end(); // flow 객체 생성
    }

    @Bean
    @JobScope
    public Step flowStep(){
        return new StepBuilder("flowStep", jobRepository)
                .flow(flowVV1())  // Step 내에서 실행 될 Flow 설정
                .build();        // FlowStep 객체 생성
    }


    @Bean
    public Step multiFlowStep1(){
        return new StepBuilder("flowStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step1 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step multiFlowStep2(){
        return new StepBuilder("flowStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step2 입니다");
                    //contribution.setExitStatus(FAILED);
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step multiFlowStep3(){
        return new StepBuilder("flowStep3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step3 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }


    @Bean
    public Step multiFlowStep4(){
        return new StepBuilder("flowStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step4 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    public Step multiFlowStep5(){
        return new StepBuilder("flowStep5", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step5 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }


    @Bean
    public Step multiFlowStep6(){
        return new StepBuilder("flowStep6", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step6 입니다");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}
