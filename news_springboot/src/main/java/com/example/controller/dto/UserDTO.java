package com.example.controller.dto;

import lombok.Data;

//接收前端注册登录
@Data
public class UserDTO {
    private String userName;
    private String passWord;
    private String role;
}
