//package com.login.repo;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.login.model.DailyAttendance;
//import com.login.model.Employee;
//
//public interface EmployeeRepository extends JpaRepository<Employee, Long>{
//	Optional<Employee> findByEmail(String email);
//	Optional<Employee> findByName(String name);
//	List<DailyAttendance> findByEmpId(Long empId);
//}
