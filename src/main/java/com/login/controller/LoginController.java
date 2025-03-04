package com.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.dto.DailyAttendanceDto;
import com.login.dto.LoginRequest;
import com.login.dto.ResponseDto;
import com.login.dto.UserDto;
import com.login.model.DailyAttendance;

import com.login.model.User;

import com.login.repo.UserRespository;
import com.login.service.DailyAttendanceService;

import com.login.service.UserService;
import com.login.util.Helper;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	Helper helper;
//    @Autowired
//    private EmployeeService employeeService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRespository userRespository;

//    @Autowired
//    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DailyAttendanceService dailyAttendanceService;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody User employee) {
//        employeeService.registerEmployee(employee);
//        return ResponseEntity.ok("Employee registered successfully!");
//    }

  
//    @PostMapping("/login")
//    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequest loginRequest) {
//        Long empId = loginRequest.getEmpid();
//        String password = loginRequest.getPassword();
//
//        if (empId == null || password == null) {
//            return ResponseEntity.status(400).body(
//                    new ResponseDto("error", "Employee ID and password are required.", null,null,null)
//            );
//        }
//
//        return userRespository.findByUserId(empId)
//                .map(emp -> {
//                    if (userService.verifyPassword(password, emp.getPassword())) {
//
//                        User responseUser = new User();
//                        responseUser.setUserId(emp.getUserId());
//                        responseUser.setUserName(emp.getUserName());
//                        responseUser.setEmailId(emp.getEmailId());
//                        
//                        // Fetch attendance details
//                  List<DailyAttendanceDto> attendanceList = dailyAttendanceService.getAttendanceByEmpId(empId);
//                  
//
//                  
//                  String[] months = { "January", "February", "March", "April", "May", "June", 
//                          "July", "August", "September", "October", "November", "December" };
//                         String monthName = null;
//
//						for (DailyAttendanceDto d : attendanceList) {
//							String dateStr = d.getAttendanceDate(); // Example: "2025-02-28T18:30:00.000+00:00"
//							int monthIndex = Integer.parseInt(dateStr.substring(5, 7)) - 1; // Extract month number and
//																							// convert to index
//							 monthName = months[monthIndex];
//							System.out.println("Month: " + monthName);
//						}
//                        // Construct response with attendance details
//                        return ResponseEntity.ok(new ResponseDto(
//                                "success",
//                                "Login successful! Welcome, " + emp.getUserName(),
//                                responseUser,
//                                attendanceList, // Adding attendance data
//                                monthName
//                        ));
//                    } else {
//                        return ResponseEntity.status(401).body(
//                                new ResponseDto("error", "Invalid credentials", null,null,null)
//                        );
//                    }
//                })
//                .orElse(ResponseEntity.status(404).body(
//                        new ResponseDto("error", "Employee not found", null,null,null)
//                ));
//    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequest loginRequest) {
        Long empId = loginRequest.getEmpid();
//        String password = helper.decrypt(loginRequest.getPassword());
        String password = loginRequest.getPassword();

        if (empId == null || password == null) {
        	System.out.println(empId);
        	System.out.println(password);
            return ResponseEntity.status(400).body(
                    new ResponseDto("error", "Employee ID and password are required.", null, null, null)
            );
        }

        return userRespository.findByUserId(empId)
                .map(emp -> {
                    try {
                        // Initialize Helper class for decryption
                        Helper helper = new Helper(""); // Replace with your actual secret key
                        
                        // Decrypt stored password
                        String decryptedPassword = helper.decrypt(emp.getPassword());

                        // Verify password
                        if (password.equals(decryptedPassword)) {
                            UserDto responseUser = new UserDto();
                            responseUser.setUserId(emp.getUserId());
                            responseUser.setUserName(emp.getUserName());
                            responseUser.setEmailId(emp.getEmailId());
                            responseUser.setAddedOn(emp.getAddedOn());
                            responseUser.setReporting1(emp.getReporting1());
                            responseUser.setReporting2(emp.getReporting2());
                            responseUser.setReporting3(emp.getReporting3());
                            responseUser.setUserRole(emp.getUserRole());
                            responseUser.setIsActive(emp.getIsActive());
                            responseUser.setUserFullName(emp.getUserFullName());
//                            responseUser.setPassword("*********");

                            // Fetch attendance details
                            List<DailyAttendanceDto> attendanceList = dailyAttendanceService.getAttendanceByEmpId(empId);

                            // Extract month name from attendance date
                            String[] months = {"January", "February", "March", "April", "May", "June",
                                    "July", "August", "September", "October", "November", "December"};
                            String monthName = null;
                            
                            
                            for (DailyAttendanceDto d : attendanceList) {
                                String dateStr = d.getAttendanceDate(); // Example: "2025-02-28T18:30:00.000+00:00"
                                int monthIndex = Integer.parseInt(dateStr.substring(5, 7)) - 1; // Extract month number
                                monthName = months[monthIndex];
                               
                            }

                            // Construct response with attendance details
                            return ResponseEntity.ok(new ResponseDto(
                                    "success",
                                    "Login successful! Welcome, " + emp.getUserName(),
                                    responseUser,
                                    attendanceList,
                                    monthName
                            ));
                        } else {
                            return ResponseEntity.status(401).body(
                                    new ResponseDto("error", "Invalid credentials", null, null, null)
                            );
                        }

                    } catch (Exception e) {
                        return ResponseEntity.status(500).body(
                                new ResponseDto("error", "Error during authentication: " + e.getMessage(), null, null, null)
                        );
                    }
                })
                .orElse(ResponseEntity.status(404).body(
                        new ResponseDto("error", "Employee not found", null, null, null)
                ));
    }

  
    

}
