package com.hyunbin.store.User.Service;

import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Model.SignUp;
import com.hyunbin.store.User.Repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Provider;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class CustomerServiceImpTest {

    @Autowired
    private CustomerService serviceImp;

    private CustomerRepository customerRepository;

    @Test
    void signUp() {
        SignUp form  = SignUp.builder()
                .birth(LocalDate.now())
                .email("이메일")
                .name("이름")
                .password("비밀번호")
                .phone("전화번호")
                .build();

        CustomerEntity customerEntity = serviceImp.signUp(form);


        Assertions.assertNotNull(customerEntity.getId());
        Assertions.assertNotNull(customerEntity.getName());
        Assertions.assertNotNull(customerEntity.getBirth());
        Assertions.assertNotNull(customerEntity.getPhone());
        Assertions.assertNotNull(customerEntity.getPassword());


    }

}