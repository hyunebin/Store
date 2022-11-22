package com.hyunbin.store.User.Controller;

import com.hyunbin.store.MailGun.Service.MailGunService;
import com.hyunbin.store.User.Model.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final MailGunService mailGunService;

    @PostMapping()
    public ResponseEntity<String> customerSignUp(@RequestBody SignUp form){
        return ResponseEntity.ok(mailGunService.customerSignUp(form));
    }
}
