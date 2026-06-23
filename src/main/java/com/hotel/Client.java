package com.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Client {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
