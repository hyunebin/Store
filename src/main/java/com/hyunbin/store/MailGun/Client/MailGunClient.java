package com.hyunbin.store.MailGun.Client;

import com.hyunbin.store.MailGun.Model.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Qualifier("mailgun")
@FeignClient(name = "mailgun" ,url = "https://api.mailgun.net/v3/")
public interface MailGunClient {

    @PostMapping("sandbox6621d2754f3a48b9bab13e98838aefce.mailgun.org/messages")
    ResponseEntity<String> sendEmail(@SpringQueryMap SendMailForm form);
}
