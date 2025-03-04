package com.login.model;



import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "daily_attendance")
public class DailyAttendance {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;  // Primary key for the table

    @Column(name = "EMPLY_CD")
    private Long empId;

    @Column(name = "ATTENDANCE_DAY")
    private String attendanceDay;

    @Column(name = "ATTENDANCE_DATE")
//    @Temporal(TemporalType.DATE) 
//    private LocalDateTime attendanceDate;
    private String attendanceDate;

    @Column(name = "IN_TIME")
    private String inTime;

    @Column(name = "OUT_TIME")
    private String outTime;

    @Column(name = "APP_STATUS")
    private String appStatus;

    @Column(name = "ATTENDANCE_INFO")
    private String attendanceInfo;

	

    // Getters and setters
    // ...


}
