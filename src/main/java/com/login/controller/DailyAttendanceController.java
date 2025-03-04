package com.login.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.dto.DailyAttendanceDto;
import com.login.dto.RequestDto;
import com.login.dto.ResponseDto;
import com.login.dto.ResponseDto1;
import com.login.service.DailyAttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class DailyAttendanceController {

    @Autowired
    private DailyAttendanceService dailyAttendanceService;

    @PostMapping("/employee/{empId}")
    public ResponseEntity<List<DailyAttendanceDto>> getAttendanceByEmpId(@PathVariable Long empId) {
        List<DailyAttendanceDto> attendanceList = dailyAttendanceService.getAttendanceByEmpId(empId);
        if (attendanceList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(attendanceList);
    }

//    @PostMapping("/getAttendance")
//    public List<DailyAttendanceDto> getAttendance(@RequestBody RequestDto req) {
//        return dailyAttendanceService.getAttendanceListByMonth(req);
//    }
    
    @PostMapping("/getAttendance")
    public ResponseDto1 getAttendance(@RequestBody RequestDto req) {
        return dailyAttendanceService.getAttendanceListByMonth(req);
    }

}
