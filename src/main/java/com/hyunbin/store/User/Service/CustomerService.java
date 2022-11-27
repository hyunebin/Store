package com.hyunbin.store.User.Service;

import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Model.SignUp;

import java.time.LocalDateTime;

public interface CustomerService {
    public CustomerEntity signUp(SignUp form);

    public boolean isEmailExist(String email);

    public LocalDateTime changeCustomerValidateEmail(Long customerId, String verificationCode);

    public void verifyEmail(String email, String code);

}
