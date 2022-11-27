package com.hyunbin.store.MailGun.Service;

import com.hyunbin.store.User.Model.SignUp;

public interface MailGunService {
    public String customerSignUp(SignUp form);
    public void customerVerify(String email, String code);
}
