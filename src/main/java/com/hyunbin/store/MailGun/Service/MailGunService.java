package com.hyunbin.store.MailGun.Service;

import com.hyunbin.store.MailGun.Client.MailGunClient;
import com.hyunbin.store.MailGun.Model.SendMailForm;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailGunService {
    private final MailGunClient mailGunClient;

    public String sendmail(){
        SendMailForm sendMailForm = SendMailForm.builder()
                .from("tjrhk01@gmail.com")
                .to("qortjr83@naver.com")
                .subject("11")
                .text("11")
                .build();


        return mailGunClient.sendEmail(sendMailForm).getBody();
    }
}
