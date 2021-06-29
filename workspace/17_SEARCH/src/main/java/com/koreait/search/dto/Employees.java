package com.koreait.search.dto;
// com.koreait.search.dto  == com.koreait.search.domain

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employees {

	// field
	private int employeeId;	 	// column : employees_id
	private String firstName;	// column : first_name
	private String lastName;	// column : last_name
	private String email;		// column : email
	private String phoneNumber;	// column : phone_number
	private Date hireDate;		// column : hire_date
	private String jobId;		// column : job_id
	private int salary;			// column : salary
	private int commissionPct;	// column : commission_pct
	private int managerId;		// column : manager_id
	private int departmentId;	// column : department_id
	
	
	
	
}
