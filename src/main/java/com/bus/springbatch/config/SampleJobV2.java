//package com.bus.springbatch.config;
//
//import com.bus.springbatch.service.FileDeletingTaskletV2;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class SampleJobV2{
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager transactionManager;
//
//    @Autowired
//    private FileDeletingTaskletV2 fileDeletingTaskletV2;
//
//    @Bean
//    public Job firstJobV2(@Qualifier("firstStepV2") Step firstStep2,
//                          @Qualifier("secondStepV2") Step secondStep2) {
//        return new JobBuilder("firstJobV2", jobRepository)
//                .start(firstStep2)
//                .next(secondStep2)
//                .build();
//    }
//
//    @Bean
//    public Step firstStepV2() {
//        return new StepBuilder("firstStepV2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("Hello, Spring Batch!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step secondStepV2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("secondStepV2", jobRepository)
//                .tasklet(fileDeletingTaskletV2, transactionManager)
//                .build();
//    }
//}
