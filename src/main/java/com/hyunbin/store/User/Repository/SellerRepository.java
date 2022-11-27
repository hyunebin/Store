package com.hyunbin.store.User.Repository;

import com.hyunbin.store.User.Entity.CustomerEntity;
import com.hyunbin.store.User.Entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    Optional<SellerEntity> findByEmail(String email);
    Optional<SellerEntity> findByIdAndEmail(Long Id, String Email);
}
