//package com.bus.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.JobRegistry;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class SampleJob extends DefaultBatchConfiguration {
//
//    @Bean
//    public Job firstJob(JobRepository jobRepository, Step firstStep, Step secondStep) {
//        return new JobBuilder("firstJob", jobRepository)
//                .start(firstStep)
//                .next(secondStep)
//                .build();
//    }
//
//    @Bean
//    public Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("firstStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("Hello, Spring Batch!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step secondStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("secondStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("Hello, I'm SecondStep!");
//                    return RepeatStatus.FINISHED;
//                }, transactionManager)
//                .build();
//    }
//
//    private Tasklet fileDeletingTaskletV1() {
//        return (contribution, chunkContext) -> {
//            // Logic to delete files
//            System.out.println("Files deleted successfully.");
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//
//    private Tasklet fileDeletingTaskletV2() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                // Logic to delete files
//                System.out.println("Files deleted successfully.");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
//}
