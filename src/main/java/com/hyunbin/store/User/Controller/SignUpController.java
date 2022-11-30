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

    @PostMapping("/customer")
    public ResponseEntity<String> customerSignUp(@RequestBody SignUp form){
        return ResponseEntity.ok(mailGunService.customerSignUp(form));
    }


    @GetMapping("/customer/verify")
    public ResponseEntity<String> verifyCustomer(String email, String code){
        mailGunService.customerVerify(email,code);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }

    @PostMapping("/seller")
    public ResponseEntity<String> sellerSignUp(@RequestBody SignUp form){
        return ResponseEntity.ok(mailGunService.sellerSignUp(form));
    }


    @GetMapping("/seller/verify")
    public ResponseEntity<String> verifySeller(String email, String code){
        mailGunService.sellerVerify(email,code);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }
}
