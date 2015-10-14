package com.avnet.ams.vto;

public class EmployeeDetailsOwnedby {
	private String employee_ID;
	private String employee_Name;
	
	
	public String getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}

	public EmployeeDetailsOwnedby(String emp_id,String name){
		this.employee_ID = emp_id;
		this.employee_Name = name;
	}
	
	public void setEmployee_Name(String employee_Name) {
		this.employee_Name = employee_Name;
	}
	public String getEmployee_Name() {
		return employee_Name;
	}

	
}
