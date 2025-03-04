package com.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	private Long empid;
	private String email;
	private String name ;
    private String password;
    
    private String month;
    private String confirmPassword;
    private String newPassword;
}
