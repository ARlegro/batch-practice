//package com.bus.springbatch.controller;
//
//import jakarta.annotation.Resource;
//import jakarta.annotation.Resources;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.SyncTaskExecutor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;
//
//@RestController
//@RequiredArgsConstructor
//public class JobLauncherController {
//
//
//    private final Job job;
//    private final JobLauncher jobLauncher;
//
//    //@Resource(name = "asyncJobLauncher")
////    @Qualifier("asyncJobLauncher")
////    private final JobLauncher asyncJobLauncher;
//
//    @PostMapping("batch/sync")
//    public String launch(@RequestBody Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("id", member.getId())
//                .addLocalDate("data", LocalDate.now())
//                .toJobParameters();
//
//        jobLauncher.run(job, jobParameters);
//        return "Batch Completed Successfully";
//    }
//
//    @PostMapping("batch/async")
//    public String launchAsync(@RequestBody Member member,
//                              @Qualifier("asyncJobLauncher") JobLauncher asyncJobLauncher) throws Exception {
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("id", member.getId() + 3)
//                .addLocalDate("data", LocalDate.now())
//                .toJobParameters();
//
//        asyncJobLauncher.run(job, jobParameters);
//        return "Async Batch Completed Successfully";
//    }
//}
