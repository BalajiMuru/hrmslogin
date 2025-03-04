package com.login.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.login.dto.DailyAttendanceDto;
import com.login.model.DailyAttendance;

public interface DailyAttendanceRepository extends JpaRepository<DailyAttendance, Long> {
	List<DailyAttendance> findByEmpId(Long empId);




@Query(nativeQuery = true, value = "SELECT "
        + "    EMPLY_CD, "
        + "    ATTENDANCE_DATE, "
        + "    ATTENDANCE_DAY, "
        + "    IN_TIME, "
        + "    OUT_TIME, "
        + "    APP_STATUS, "
        + "    ATTENDANCE_INFO "
        + "FROM daily_attendance "
        + "WHERE CAST(ATTENDANCE_DATE AS DATE) BETWEEN TRUNC(SYSDATE, 'MM') AND LAST_DAY(SYSDATE) "
        + "AND EMPLY_CD = :empId")
List<Object[]> findAttendanceByEmpId(@Param("empId") Long empId);
		


	    @Query(value = "SELECT * FROM daily_attendance " +
                "WHERE EMPLY_CD = :empId " +
                "AND EXTRACT(MONTH FROM ATTENDANCE_DATE) = :month " +
                "AND EXTRACT(YEAR FROM ATTENDANCE_DATE) = EXTRACT(YEAR FROM CURRENT_DATE)", 
        nativeQuery = true)
 List<Object[]> findByEmpIdAndMonth(@Param("empId") Long empId, @Param("month") int month);
}