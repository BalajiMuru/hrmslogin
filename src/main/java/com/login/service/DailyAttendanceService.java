package com.login.service;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.dto.DailyAttendanceDto;
import com.login.dto.RequestDto;
import com.login.dto.ResponseDto1;
import com.login.repo.DailyAttendanceRepository;

@Service
public class DailyAttendanceService {
    
	
    @Autowired
    private  DailyAttendanceRepository dailyAttendanceRepository;

//    public List<DailyAttendanceDto> getAttendanceByEmpId(Long empId) {
//       List<DailyAttendance> attendanceList = dailyAttendanceRepository.findAttendanceByEmpId(empId);
////        List<DailyAttendance> attendanceList = attendanceRepository.findByEmpId(empId);
//        // Convert model entities to DTO
//        em.close();
//        return attendanceList.stream().map(this::convertToDto).collect(Collectors.toList());
//    }
    
    public List<DailyAttendanceDto> getAttendanceByEmpId(Long empId) {
        List<Object[]> attendanceList = dailyAttendanceRepository.findAttendanceByEmpId(empId);

        // Convert List<Object[]> to List<DailyAttendanceDto>
        return attendanceList.stream()
                .map(this::convertArrayToDto)  // Convert each Object[] to DailyAttendanceDto
                .collect(Collectors.toList());
    }

  
    private DailyAttendanceDto convertArrayToDto(Object[] obj) {
        DailyAttendanceDto dto = new DailyAttendanceDto();

        // Handling possible null values
        dto.setEmpId(((Number) obj[0]).longValue());
        dto.setAttendanceDate(((Timestamp) obj[1]).toLocalDateTime().toLocalDate().toString()); // If ATTENDANCE_DATE is not null
        dto.setAttendanceDay((String) obj[2]);

        // Check if IN_TIME is not null before conversion
        if (obj[3] != null) {
            dto.setInTime(((Timestamp) obj[3]).toLocalDateTime().toLocalTime().toString());
        } else {
            dto.setInTime(null);  // or any default value
        }

        // Check if OUT_TIME is not null before conversion
        if (obj[4] != null) {
            dto.setOutTime(((Timestamp) obj[4]).toLocalDateTime().toLocalTime().toString());
        } else {
            dto.setOutTime(null);  // or any default value
        }

        dto.setAppStatus((String) obj[5]);
        dto.setAttendanceInfo((String) obj[6]);

        return dto;
    }

    


    // Convert Timestamp to Date (YYYY-MM-DD)
    private String formatDate(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toLocalDateTime().toLocalDate().toString(); // Convert to "YYYY-MM-DD"
    }

    // Convert Timestamp to Time-Only (HH:mm:ss)
    private String formatTime(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toLocalDateTime().toLocalTime().toString(); // Convert to "HH:mm:ss"
    }


    

    public ResponseDto1 getAttendanceListByMonth(RequestDto req) {
        ResponseDto1 resp = new ResponseDto1();
        
        // Convert String to Long and String to int
        Long empId = req.getEmpId(); 
        int month = Integer.parseInt(req.getMonth()); 
        
        // Fetch attendance list from the repository (returns List<Object[]>)
        List<Object[]> attendanceList = dailyAttendanceRepository.findByEmpIdAndMonth(empId, month);

        if (attendanceList.isEmpty()) {
            resp.setStatus("F");
            resp.setMessage("No data found for the given month.");
            return resp; // If no data found, return an appropriate message
        }

        // Convert List<Object[]> to List<DailyAttendanceDto>
        List<DailyAttendanceDto> attendanceDtoList = attendanceList.stream()
            .map(this::convertArrayToDto1)  // Convert each Object[] to DailyAttendanceDto
            .collect(Collectors.toList());

        // Set the converted list into the response
        resp.setData(attendanceDtoList); 
        resp.setStatus("S");
        resp.setMessage("Data fetched.");

        return resp; // Return the response with the converted data
    }

    public DailyAttendanceDto convertArrayToDto1(Object[] attendanceData) {
        DailyAttendanceDto dto = new DailyAttendanceDto();

        // Assume columns are in correct order
//        dto.setAttendanceDate((Timestamp) attendanceData[0]);  // Correct cast for Timestamp
//        dto.setAttendanceDate(((Timestamp) attendanceData[0]).toLocalDateTime().toLocalDate().toString()); 
        dto.setAttendanceDay((String) attendanceData[1]);
//        dto.setEmpId((Long) attendanceData[2]);
        
        BigDecimal empIdBigDecimal = (BigDecimal) attendanceData[2];
        dto.setEmpId(empIdBigDecimal.longValue()); 
//        dto.setInTime((Timestamp) attendanceData[3]); // Adjust according to your field type
//        dto.setOutTime((Timestamp) attendanceData[4]); // Adjust according to your field type
        
        // Check if IN_TIME is not null before conversion
        if (attendanceData[3] != null) {
            dto.setInTime(((Timestamp) attendanceData[3]).toLocalDateTime().toLocalTime().toString());
        } else {
            dto.setInTime(null);  // or any default value
        }

        // Check if OUT_TIME is not null before conversion
        if (attendanceData[4] != null) {
            dto.setOutTime(((Timestamp) attendanceData[4]).toLocalDateTime().toLocalTime().toString());
        } else {
            dto.setOutTime(null);  // or any default value
        }
        dto.setAppStatus((String) attendanceData[5]);
        dto.setAttendanceInfo((String) attendanceData[6]);

        return dto;
    }

}
