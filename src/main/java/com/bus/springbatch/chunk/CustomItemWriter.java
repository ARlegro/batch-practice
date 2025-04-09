package com.bus.springbatch.chunk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class CustomItemWriter implements ItemWriter<Object> {

    @Override
    public void write(Chunk<?> items) throws Exception {
        items.forEach(System.out::println);
        log.info("커밋 완료");
    }
}
