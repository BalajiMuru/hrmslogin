//package com.login.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.login.model.Employee;
//import com.login.repo.EmployeeRepository;
//
//@Service
//public class EmployeeService {
//	 @Autowired
//	 private EmployeeRepository employeeRepository;
//
//	    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	    
//	    public void registerEmployee(Employee employee) {
//	        // Encrypt password before saving
//	        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//	        employeeRepository.save(employee);
//	    }
////	    public boolean verifyPassword(String rawPassword, String encodedPassword) {
////	        // Check if raw password matches the encrypted password
////	        return passwordEncoder.matches(rawPassword, encodedPassword);
////	    }
//	    
//	    public boolean verifyPassword(String rawPassword, String storedPassword) {
//	        return rawPassword.equals(storedPassword); // Replace with encryption check if using encrypted passwords
//	    }
//	 
////	 private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////	    private static final String ALGORITHM = "AES";
////	    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // Ensure it is 16 bytes long
////
////	    // Register employee with AES encryption
////	    public void registerEmployee(Employee employee) {
////	        try {
////	            String encryptedPassword = encrypt(employee.getPassword());
////	            employee.setPassword(encryptedPassword);  // Save encrypted password
////	            employeeRepository.save(employee);
////	        } catch (Exception e) {
////	            throw new RuntimeException("Error encrypting password", e);
////	        }
////	    }
////
////	    // Verify raw password with encrypted one by decrypting it
////	    public boolean verifyPassword(String rawPassword, String encryptedPassword) {
////	        try {
////	            String decryptedPassword = decrypt(encryptedPassword);
////	            return rawPassword.equals(decryptedPassword); // Compare raw and decrypted password
////	        } catch (Exception e) {
////	            throw new RuntimeException("Error decrypting password", e);
////	        }
////	    }
////
////	    // AES Encryption
////	    private String encrypt(String data) throws Exception {
////	        Cipher cipher = Cipher.getInstance(ALGORITHM);
////	        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
////	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
////	        byte[] encryptedData = cipher.doFinal(data.getBytes());
////	        return Base64.getEncoder().encodeToString(encryptedData);
////	    }
////
////	    // AES Decryption
////	    private String decrypt(String encryptedData) throws Exception {
////	        Cipher cipher = Cipher.getInstance(ALGORITHM);
////	        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
////	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
////	        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
////	        return new String(decryptedData);
////	    }
//
////	    private static final String ALGORITHM = "AES";
////	    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // Must be 16 bytes long
////
////	    // Register employee with AES encryption
////	    public void registerEmployee(Employee employee) {
////	        try {
////	            String encryptedPassword = encrypt(employee.getPassword());
////	            employee.setPassword(encryptedPassword);  // Save encrypted password
////	            employeeRepository.save(employee);
////	        } catch (Exception e) {
////	            throw new RuntimeException("Error encrypting password", e);
////	        }
////	    }
////
////	    // Verify raw password with encrypted one by decrypting it
////	    public boolean verifyPassword(String rawPassword, String encryptedPassword) {
////	        try {
////	            String decryptedPassword = decrypt(encryptedPassword);
////	            return rawPassword.equals(decryptedPassword);
////	        } catch (Exception e) {
////	            throw new RuntimeException("Error decrypting password", e);
////	        }
////	    }
////
////	    // AES Encryption Method
////	    private String encrypt(String data) throws Exception {
////	        Cipher cipher = Cipher.getInstance(ALGORITHM);
////	        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
////	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
////	        byte[] encryptedData = cipher.doFinal(data.getBytes());
////	        return Base64.getEncoder().encodeToString(encryptedData);
////	    }
//
//	    // AES Decryption Method
////	    private String decrypt(String encryptedData) throws Exception {
////	        Cipher cipher = Cipher.getInstance(ALGORITHM);
////	        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
////	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
////	        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
////	        return new String(decryptedData);
////	    }
////	    private String decrypt(String encryptedData) throws Exception {
////	        if (!isValidBase64(encryptedData)) {
////	        	System.out.println("balaji");
////	            throw new RuntimeException("Invalid Base64 input");
////	        }
////	        Cipher cipher = Cipher.getInstance(ALGORITHM);
////	        SecretKeySpec secretKey = new SecretKeySpec(KEY, ALGORITHM);
////	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
////	        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
////	        return new String(cipher.doFinal(decodedData), "UTF-8");
////	    }
////
////	    private boolean isValidBase64(String data) {
////	        return data.matches("^[A-Za-z0-9+/=]+$") && (data.length() % 4 == 0);
////	    }
//	    
//	/*    public AttedanceDto getAttedanceList(RequestDto req) {
//	        Long empId = Long.parseLong(req.getEmpId());
//
//	        // Define start and end dates for the query
//	        LocalDate startDate = LocalDate.of(2025, 1, 1);
//	        LocalDate endDate = LocalDate.of(2025, 12, 31);
//
//	        // Fetch attendance list using LocalDate converted to java.sql.Date
//	        List<DailyAttendance> attendanceList = repo.findAttendanceByEmpIdAndDateRange(
//	                empId, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate)
//	        );
//	        em.clear();
//	        AttedanceDto dto = new AttedanceDto();
//	        dto.setAttendanceList(attendanceList);
//
//	        // Generate the list of months from January to the current month of the current year
//	        ArrayList<MonthDto> months = new ArrayList<>();
//	        LocalDate tempDate = LocalDate.of(startDate.getYear(), Month.JANUARY, 1);
//	        int key = 1;
//
//	        while (tempDate.getYear() == startDate.getYear() && tempDate.isBefore(LocalDate.now().plusMonths(1))) {
//	            MonthDto monthDto = new MonthDto();
//	            monthDto.setId(String.valueOf(key));
//	            monthDto.setMonth(tempDate.getMonth().name().toLowerCase() + "-" + tempDate.getYear());
//	            months.add(monthDto);
//	            tempDate = tempDate.plusMonths(1);
//	            key++;
//	        }
//
//	        // Set current month and list of months
//	        dto.setCurrentMonth(String.valueOf(LocalDate.now().getMonthValue()));
//	        dto.setMonths(months);
//	        em.clear();
//	        return dto;
//	        
//	    } */
//
//}
