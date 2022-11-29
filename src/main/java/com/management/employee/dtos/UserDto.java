package com.management.employee.dtos;

import lombok.Data;

@Data
public class UserDto {

    private String name;

    private String email;

    private String password;

    int cid;
}
