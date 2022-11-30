package com.hyunbin.store.User.Model;

import com.hyunbin.store.User.Entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class CustomerDto {
    Long Id;
    String email;

    public static CustomerDto of(CustomerEntity customerEntity){
        return new CustomerDto(customerEntity.getId(),customerEntity.getEmail());
    }
}
