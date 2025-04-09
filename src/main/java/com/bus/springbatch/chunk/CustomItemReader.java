package com.bus.springbatch.chunk;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class CustomItemReader implements ItemReader<Customer> {

    private final List<Customer> customList;

    public CustomItemReader(List<Customer> customList) {
        this.customList = customList;
    }

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(!customList.isEmpty()) {
            customList.remove(0);
        }
        return customList.isEmpty() ? null : customList.get(0);
    }
}
