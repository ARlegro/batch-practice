//package com.bus.springbatch.config;
//
//import jakarta.annotation.Resource;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobRunnerOld implements ApplicationRunner {
//    // ApplicationRunner : 스프링부트가 제공하는 인터페이스, 초기화되고 완료되면 가장 먼저 호출되는 타입의 클래스
//    private final JobLauncher jobLauncher;  // 스프링 부트가 실행 시 이미 초기화 시켜놓음
//
//    @Resource(name = "flowBuild2")
//    private final Job job;
//
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
////                .addString("name", "statusTest")
//                .addLong("time", System.currentTimeMillis())
////                .addLong("seq", 2L)
////                .addDouble("age", 16.5)
//                .toJobParameters();
//        jobLauncher.run(job, jobParameters);
//    }
//}
