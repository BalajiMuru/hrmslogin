package com.login.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	 
	    private Long userId;

	    
	    private String userName;

	   
	    private String emailId;

	    
	    private Integer isActive;

	    
	    private Date addedOn;

	    
	    private String userFullName;

	    private Long reporting1;

	    
	    private Long reporting2;

	    
	    private Long reporting3;

	    
	    private String userRole;
}
