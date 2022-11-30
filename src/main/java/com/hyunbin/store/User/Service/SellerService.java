package com.hyunbin.store.User.Service;

import com.hyunbin.store.User.Entity.SellerEntity;
import com.hyunbin.store.User.Model.SignIn;
import com.hyunbin.store.User.Model.SignUp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


public interface SellerService {

    public SellerEntity signUp(SignUp signUp);

    public boolean isEmailExist(String email);

    public LocalDateTime changeCustomerValidateEmail(Long sellerId, String verificationCode);

    public void verifyEmail(String email, String code);

    public String sellerLoginToken(SignIn signIn);

}
