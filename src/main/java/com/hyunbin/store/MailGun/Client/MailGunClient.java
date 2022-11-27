package com.hyunbin.store.MailGun.Client;

import com.hyunbin.store.MailGun.Model.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun" ,url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailGunClient {

    @PostMapping("sandbox9f55db226aac443cb415b17399de1ac5.mailgun.org/messages")
    ResponseEntity<String> sendEmail(@SpringQueryMap SendMailForm form);
}
