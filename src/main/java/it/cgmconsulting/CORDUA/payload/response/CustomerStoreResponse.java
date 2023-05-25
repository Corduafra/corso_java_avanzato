package it.cgmconsulting.CORDUA.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter @AllArgsConstructor
public class CustomerStoreResponse {

    private String storeName;
    private long totalCustomer;

}
