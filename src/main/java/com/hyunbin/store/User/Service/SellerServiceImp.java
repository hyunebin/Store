package com.hyunbin.store.User.Service;

import Config.JwtAuthenticationProvider;
import Config.common.UserType;
import com.hyunbin.store.Exception.CustomException;
import com.hyunbin.store.Exception.ErrorCode;
import com.hyunbin.store.Exception.SellerException;
import com.hyunbin.store.User.Entity.SellerEntity;
import com.hyunbin.store.User.Model.SignIn;
import com.hyunbin.store.User.Model.SignUp;
import com.hyunbin.store.User.Repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImp implements SellerService{
    private final SellerRepository sellerRepository;
    private final JwtAuthenticationProvider provider;


    @Override
    public SellerEntity signUp(SignUp signUp) {
        SellerEntity sellerEntity = SellerEntity.from(signUp);

        return sellerRepository.save(sellerEntity);
    }

    @Override
    public boolean isEmailExist(String email) {
        return sellerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    @Override
    public LocalDateTime changeCustomerValidateEmail(Long sellerId, String verificationCode) {
        SellerEntity sellerEntity = sellerRepository.findById(sellerId).orElseThrow(()->new SellerException(ErrorCode.NOT_EXIST_USER));

        sellerEntity.setVerificationCode(verificationCode);
        sellerEntity.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));

        return sellerEntity.getVerifyExpiredAt();
    }

    @Override
    public void verifyEmail(String email, String code) {
        SellerEntity sellerEntity = sellerRepository.findByEmail(email).orElseThrow(()->new SellerException(ErrorCode.NOT_EXIST_EMAIL));


        if(sellerEntity.isVerify()){
            throw new SellerException(ErrorCode.ALREADY_REG_USER);
        }

        if(!sellerEntity.getVerificationCode().equals(code)){
            throw new CustomException(ErrorCode.CODE_NOT_EQ);
        }


        if(sellerEntity.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new SellerException(ErrorCode.AUTHENTICATION_TIMEOUT);
        }

        sellerEntity.setVerify(true);
        sellerRepository.save(sellerEntity);

    }

    @Override
    public String sellerLoginToken(SignIn signIn) {
        Optional<SellerEntity> optionalSellerEntity = sellerRepository.findByEmail(signIn.getEmail()).stream().filter(
                seller -> seller.getPassword().equals(signIn.getPassword()) && seller.isVerify()).findFirst();

        SellerEntity sellerEntity = optionalSellerEntity.orElseThrow(()->new SellerException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.creatToken(sellerEntity.getEmail(), sellerEntity.getId(), UserType.SELLER);
    }
}
