package com.hyunbin.store.User.Service;

import com.hyunbin.store.User.Entity.CustomerBalanceHistory;
import com.hyunbin.store.User.Model.ChangeBalanceForm;

public interface CustomerBalanceService {
    CustomerBalanceHistory changeBalance(Long customerId, ChangeBalanceForm from);
}
