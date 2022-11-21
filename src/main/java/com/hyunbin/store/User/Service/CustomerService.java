package com.hyunbin.store.User.Service;

import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Model.SignUp;

public interface CustomerService {
    public CustomerEntity signUp(SignUp form);
}
