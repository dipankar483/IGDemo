package pojo;

import java.util.List;

public class APIResponse {

	private String status;
	private List<EmployeeData> employeedata;
	private String message;

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public List<EmployeeData> getEmployeedata() {
		return employeedata;
	}

	public void setEmployeedata(List<EmployeeData> employeedata) {
		this.employeedata = employeedata;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
