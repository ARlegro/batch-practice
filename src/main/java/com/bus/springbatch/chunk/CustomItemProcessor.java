package com.bus.springbatch.chunk;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer item) {
        item.setName(item.getName().toUpperCase());
        return item;
    }
}
