package com.login.dto;

import java.util.List;


import com.login.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
//	private String status;
//	private String message;
//	private Object data;
	
	 private String status;
	 private String message;
	 private UserDto user;
	 private List<DailyAttendanceDto> attendanceList;
	 private String month;
//	 private List<Object> attendanceList;
	
}
