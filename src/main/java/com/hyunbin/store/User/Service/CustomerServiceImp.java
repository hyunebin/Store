package com.hyunbin.store.User.Service;

import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Model.SignUp;
import com.hyunbin.store.User.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerEntity signUp(SignUp form){

        CustomerEntity customerEntity = CustomerEntity.from(form);

        return customerRepository.save(customerEntity);
    }


}
