//package com.login.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "Employee")
//public class Employee {
//
//    @Id
//    @Column(name = "EMPID")  // Match the exact column name in the database
//    private Long empId;
//
//    private String name;
//
//    @Column(unique = true)
//    private String email;
//
//    private String password;
//
//    // Getters and Setters
//
//
//	public Long getEmpId() {
//		return empId;
//	}
//	public void setEmpId(Long empId) {
//		this.empId = empId;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//    // Getters and Setters
//     
//
//}
