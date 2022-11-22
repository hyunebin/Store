package com.hyunbin.store.User.Service;

import com.hyunbin.store.Exception.CustomException;
import com.hyunbin.store.Exception.ErrorCode;
import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Model.SignUp;
import com.hyunbin.store.User.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerEntity signUp(SignUp form){

        CustomerEntity customerEntity = CustomerEntity.from(form);

        return customerRepository.save(customerEntity);
    }

    public boolean isEmailExist(String email){
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }


    @Override
    @Transactional
    public LocalDateTime changeCustomerValidateEmail(Long customerId, String verificationCode) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXIST_USER));

        customerEntity.setVerificationCode(verificationCode);
        customerEntity.setVerifyExpiredAt(LocalDateTime.now());

        return customerEntity.getVerifyExpiredAt();

    }
}
