package com.bus.springbatch.chunk;

import com.bus.springbatch.config.CustomJobParameterIncrementer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JsonChunkJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Value("${batch.json.input}")
    String jsonPath;

    @Bean
    public Job jsonChunkJob() {
        return new JobBuilder("jsonChunkJob", jobRepository)
                .incrementer(new CustomJobParameterIncrementer())
                .start(jsonChunkStep())
                .build();
    }


    @Bean
    public Step jsonChunkStep() {
        return new StepBuilder("jsonChunkStep", jobRepository)
                .<Item, Item>chunk(3, transactionManager)
                .reader(jsonItemReader())
                .writer(new CustomItemWriter())
                .build();
    }


    @Bean
    public JsonItemReader<Item> jsonItemReader() {
        return new JsonItemReaderBuilder<Item>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Item.class))
                .resource(new ClassPathResource(jsonPath))
                .name("jsonItemReader")
                .build();
    }

//    @Bean
//    public ItemWriter<Item> itemWriter() {
//    }
}

