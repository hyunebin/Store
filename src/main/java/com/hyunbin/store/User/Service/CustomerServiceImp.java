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
        customerEntity.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));

        return customerEntity.getVerifyExpiredAt();

    }


    public void verifyEmail(String email, String code){
        CustomerEntity customerEntity = customerRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_EMAIL));

        if(customerEntity.isVerify()){
            throw new CustomException(ErrorCode.ALREADY_ALLOW_USER);
        }

        if(!customerEntity.getVerificationCode().equals(code)){
            throw new CustomException(ErrorCode.CODE_NOT_EQ);
        }

        if(customerEntity.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new CustomException(ErrorCode.AUTHENTICATION_TIMEOUT);
        }

         customerEntity.setVerify(true);
    }

}
