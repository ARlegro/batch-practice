package com.bus.springbatch.chunk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Item {

    private final String isin;
    private final int quantity;
    private final double price;
    private final String customer;

    @JsonCreator
    public Item(@JsonProperty("isin") String isin,
                @JsonProperty("quantity") int quantity,
                @JsonProperty("price") double price,
                @JsonProperty("customer") String customer) {
        this.isin = isin;
        this.quantity = quantity;
        this.price = price;
        this.customer = customer;
    }
}
