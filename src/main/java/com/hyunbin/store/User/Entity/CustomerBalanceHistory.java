package com.hyunbin.store.User.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerBalanceHistory extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = CustomerEntity.class, fetch = FetchType.LAZY)
    private CustomerEntity customerEntity;
    //변경금액
    private Integer changeMoney;
    //현재 잔액
    private Integer currentMoney;

    private String fromMessage;

    private String description;


}
