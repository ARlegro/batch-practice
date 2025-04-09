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
public class ChunkJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job chunkJob() {
        return new JobBuilder("chunkJob", jobRepository)
                .incrementer(new CustomJobParameterIncrementer())
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep(){
        return new StepBuilder("chunkStep", jobRepository)
                .<Customer, Customer>chunk(3, transactionManager)
                //.reader(itemRead())
                .reader(new JsonItemReader<>())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemReader<Customer> itemReader(){
        List<Customer> customerList = new ArrayList<Customer>();
        for (int i = 0; i < 20; i++) {
            customerList.add(new Customer("customer" + i));
        }
        return new CustomItemReader(customerList);
    }

    @Bean
    public ItemProcessor<? super Customer, ? extends Customer> itemProcessor(){
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<? super Customer> itemWriter(){
        return new CustomItemWriter();
    }

}

