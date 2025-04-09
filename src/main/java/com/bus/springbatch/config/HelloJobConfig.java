//package com.bus.springbatch.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.job.DefaultJobParametersValidator;
//import org.springframework.batch.core.job.SimpleJob;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class HelloJobConfig {
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager transactionManager;
//    private final ExecutionContextTasklet1 executionContextTasklet1;
//    private final ExecutionContextTasklet2 executionContextTasklet2;
//    private final ExecutionContextTasklet3 executionContextTasklet3;
//    private final ExecutionContextTasklet4 executionContextTasklet4;
//
//    @Bean
//    public Job helloJob() {
//        return new JobBuilder("helloJob", jobRepository)
//                .start(helloStep1())
//                .next(helloStep2())
//                .next(helloStep3())
//                .next(helloStep4())
//                //.preventRestart()
//                //.validator(new DefaultJobParametersValidator(new String[]{"name", "date"}, new String[]{"count"}))
//                //.validator(new CustomJobParametersValidator())
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }
//
//
//    @Bean
//    public Step helloStep1() {
//        return new StepBuilder("Step1", jobRepository)
//                .tasklet(executionContextTasklet1, transactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step helloStep2() {
//        return new StepBuilder("Step2", jobRepository)
//                .tasklet(executionContextTasklet2, transactionManager)
//
//                .build();
//    }
//    @Bean
//    public Step helloStep3() {
//        return new StepBuilder("Step3", jobRepository)
//                .tasklet(executionContextTasklet3, transactionManager)
//                .build();
//    }
//    @Bean
//    public Step helloStep4() {
//        return new StepBuilder("Step4", jobRepository)
//                .tasklet(executionContextTasklet4, transactionManager)
//                .build();
//    }
//
//    private static class CustomJobParametersValidator implements JobParametersValidator {
//
//        @Override
//        public void validate(JobParameters parameters) throws JobParametersInvalidException {
//            // validator는 JobParameters를 전달받을 수 있다.
//            // 이는 Job이 갖고 있는 parameters이다. (key-value 쌍으로 전달됨)
//            // 이를 활용하여 JobParameters를 검증할 수 있다.
//            if (parameters.getString("name") == null) {
//                throw new JobParametersInvalidException("name parameter is not found");
//            }
//        }
//    }
//}
