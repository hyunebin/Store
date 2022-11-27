package com.hyunbin.store.User.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUp {
    private String email;
    private String name;
    private String password;
    private String phone;
    private LocalDate birth;

}
