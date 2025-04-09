package com.bus.springbatch.config;


import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Configuration
//public class CustomJobParameter {
//
//    @Bean
//    public JobParameters customJobParameter() {
//        return new JobParametersBuilder()
//                .addLocalDateTime("dateTime", LocalDateTime.now())
//                .toJobParameters();
//    }
//}
