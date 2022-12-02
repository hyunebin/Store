package com.hyunbin.store.User.Entity;

import com.hyunbin.store.User.Model.SignUp;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
@Builder
public class CustomerEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String phone;
    private LocalDate birth;

    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    @Column(columnDefinition = "int default 0")
    private Integer balance;
    public static CustomerEntity from(SignUp form){
        return CustomerEntity.builder()
                .phone(form.getPhone())
                .email(form.getEmail())
                .name(form.getName())
                .password(form.getPassword())
                .birth(form.getBirth())
                .verify(false)
                .build();
    }



}
