package com.hyunbin.store.User.Controller;

import Config.JwtAuthenticationProvider;
import Config.common.UserVO;
import com.hyunbin.store.Exception.CustomException;
import com.hyunbin.store.Exception.ErrorCode;
import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Model.ChangeBalanceForm;
import com.hyunbin.store.User.Model.CustomerDto;
import com.hyunbin.store.User.Repository.CustomerRepository;
import com.hyunbin.store.User.Service.CustomerBalanceService;
import com.hyunbin.store.User.Service.CustomerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final CustomerBalanceService customerBalanceService;
    private final JwtAuthenticationProvider provider;
    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo(@RequestHeader("X-AUTH-TOKEN") String token){
        UserVO userVO = provider.getUserVo(token);
        CustomerEntity customerEntity = customerRepository.findByIdAndEmail(userVO.getId(), userVO.getEmail()).orElseThrow(
                ()->new CustomException(ErrorCode.NOT_EXIST_USER));

        CustomerDto customerDto = CustomerDto.of(customerEntity);

        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping("/balance")
    public ResponseEntity<?> changeBalance(@RequestHeader("X-AUTH-TOKEN") String token, @RequestBody ChangeBalanceForm form){
        UserVO userVO = provider.getUserVo(token);

        return ResponseEntity.ok(customerBalanceService.changeBalance(userVO.getId(), form).getCurrentMoney());
    }


}
