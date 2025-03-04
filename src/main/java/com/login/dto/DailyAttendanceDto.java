package com.login.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DailyAttendanceDto {
    private Long empId;
    private String attendanceDay;
    private String attendanceDate;
    private String inTime;
    private String outTime;
    private String appStatus;
    private String attendanceInfo;
    
//    private Long empId;
//    private String attendanceDay;
//    private LocalDate attendanceDate;  // ✅ Use LocalDate (for DATE type)
//    private LocalTime inTime;          // ✅ Use LocalTime (for TIME type)
//    private LocalTime outTime;         // ✅ Use LocalTime (for TIME type)
//    private String appStatus;
//    private String attendanceInfo;


    // Getters and setters
    // ...
}

