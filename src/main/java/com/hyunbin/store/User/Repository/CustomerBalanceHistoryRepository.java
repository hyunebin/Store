package com.hyunbin.store.User.Repository;

import com.hyunbin.store.User.Entity.CustomerBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface CustomerBalanceHistoryRepository extends JpaRepository<CustomerBalanceHistory, Long> {
    Optional<CustomerBalanceHistory> findFirstByCustomerEntity_IdOrderByIdDesc(@RequestParam("customerEntity_id") Long customerId);
}
