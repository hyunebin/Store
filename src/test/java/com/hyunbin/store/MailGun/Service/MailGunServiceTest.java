package com.hyunbin.store.MailGun.Service;

import com.hyunbin.store.MailGun.Client.MailGunClient;
import com.hyunbin.store.MailGun.Config.FeignConfig;
import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailGunServiceTest {
    @Autowired(required = true)
    private MailGunService mailGunService;

    @Test
    public void mailTest(){
        String response = mailGunService.sendmail();
        System.out.println("a");
    }
}