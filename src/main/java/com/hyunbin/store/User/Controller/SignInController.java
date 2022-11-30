package com.hyunbin.store.User.Controller;

import com.hyunbin.store.User.Model.SignIn;
import com.hyunbin.store.User.Service.CustomerService;
import com.hyunbin.store.User.Service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signIn")
@RequiredArgsConstructor
public class SignInController {
    private final CustomerService customerService;
    private final SellerService sellerService;
    @PostMapping("/customer")
    public ResponseEntity<?> signInCustomer(@RequestBody SignIn signIn){
        return ResponseEntity.ok(customerService.customerLoginToken(signIn));
    }

    @PostMapping("/seller")
    public ResponseEntity<?> signInSeller(@RequestBody SignIn signIn){
        return ResponseEntity.ok(sellerService.sellerLoginToken(signIn));
    }
}
