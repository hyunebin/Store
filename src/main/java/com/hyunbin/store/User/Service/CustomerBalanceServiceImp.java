package com.hyunbin.store.User.Service;

import com.hyunbin.store.Exception.CustomException;
import com.hyunbin.store.Exception.ErrorCode;
import com.hyunbin.store.User.Entity.CustomerBalanceHistory;
import com.hyunbin.store.User.Model.ChangeBalanceForm;
import com.hyunbin.store.User.Repository.CustomerBalanceHistoryRepository;
import com.hyunbin.store.User.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;

@Service
@RequiredArgsConstructor
public class CustomerBalanceServiceImp implements CustomerBalanceService{
    private final CustomerRepository customerRepository;
    private final CustomerBalanceHistoryRepository customerBalanceHistoryRepository;

    @Transactional(noRollbackFor = {CustomException.class})
    public CustomerBalanceHistory changeBalance(Long customerId, ChangeBalanceForm from) throws CustomException{
        CustomerBalanceHistory customerBalanceHistory = customerBalanceHistoryRepository.findFirstByCustomerEntity_IdOrderByIdDesc(customerId)
                .orElse(
                            CustomerBalanceHistory.builder()
                            .changeMoney(0)
                            .currentMoney(0)
                            .customerEntity(customerRepository.findById(customerId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXIST_USER)))
                .build());

        if(customerBalanceHistory.getCurrentMoney() + from.getMoney() < 0){
            throw  new CustomException(ErrorCode.NOT_ENOUGH_BALANCE);
        }

        customerBalanceHistory = CustomerBalanceHistory.builder()
                .changeMoney(from.getMoney())
                .currentMoney(customerBalanceHistory.getCurrentMoney() + from.getMoney())
                .description(from.getMessage())
                .fromMessage(from.getFrom())
                .customerEntity(customerBalanceHistory.getCustomerEntity())
                .build();

        customerBalanceHistory.getCustomerEntity().setBalance(customerBalanceHistory.getCurrentMoney());

        return customerBalanceHistoryRepository.save(customerBalanceHistory);
    }
}
