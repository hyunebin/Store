package com.hyunbin.store.MailGun.Service;

import com.hyunbin.store.Exception.CustomException;
import com.hyunbin.store.Exception.ErrorCode;
import com.hyunbin.store.MailGun.Client.MailGunClient;
import com.hyunbin.store.MailGun.Model.SendMailForm;
import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Model.SignUp;
import com.hyunbin.store.User.Service.CustomerService;
import com.hyunbin.store.User.Service.CustomerServiceImp;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailGunServiceImp implements MailGunService{

    public final MailGunClient mailGunClient;
    public final CustomerService customerService;


    @Override
    public String customerSignUp(SignUp form) {
        if(customerService.isEmailExist(form.getEmail())){
            //유저가 존재한다면
            throw new CustomException(ErrorCode.ALREADY_REG_USER);
        }else{
            CustomerEntity customerEntity = customerService.signUp(form);
            LocalDateTime localDateTime = LocalDateTime.now();

            String code = getRandomCode();

            mailGunClient.sendEmail(SendMailForm.builder()
                    .text(getVerificationEmailBody(form.getEmail(), form.getName(), code))
                    .subject("verification Email")
                    .to(form.getEmail())
                    .from("qortjr83@naver.com")
                    .build());

            customerService.changeCustomerValidateEmail(customerEntity.getId(),code);

            return "회원 가입 완료";
        }
    }


    private String getRandomCode(){
        return RandomStringUtils.random(10,true,true);
    }

    private String getVerificationEmailBody(String email, String name, String code){
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("hello")
                            .append(name)
                            .append("! Plz Click Link for verification" + "\n")
                            .append("http://localhost:8080/")
                            .append(email)
                            .append("&code=")
                            .append(code)
                            .toString();
    }

    public void customerVerify(String email, String code){
        customerService.verifyEmail(email, code);
    }
}
