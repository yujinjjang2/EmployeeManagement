package edu.kh.emp.model.vo;

public class EmployeeTest {

	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;
	private String jobCode;
	private String salLevel;
	private int salary;
	private Double bonus;
	private String mangerId;
	
	private String deptTitle;
	private int sumSalary;
	
	private String jobName;
	private int avgSalary;
	
	private String deptCode;
	
	public EmployeeTest() {}

	public EmployeeTest(String empId, String empName, String empNo, String email, String phone, String deptCode, String jobCode,
			String salLevel, int salary, Double bonus, String mangerId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bonus = bonus;
		this.mangerId = mangerId;
	}



	public EmployeeTest(String empId, String empName, String empNo, String email, String phone, String jobCode,
			String salLevel, int salary, Double bonus, String mangerId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.salary = salary;
		this.bonus = bonus;
		this.mangerId = mangerId;
	}
	
	public EmployeeTest(String deptTitle, int sumSalary) {
		this.deptTitle = deptTitle;
		this.sumSalary = sumSalary;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getAvgSalary() {
		return avgSalary;
	}

	public void setAvgSalary(int avgSalary) {
		this.avgSalary = avgSalary;
	}

	public String getDeptTitle() {
		return deptTitle;
	}

	public void setDeptTitle(String deptTitle) {
		this.deptTitle = deptTitle;
	}

	public int getSumSalary() {
		return sumSalary;
	}

	public void setSumSalary(int sumSalary) {
		this.sumSalary = sumSalary;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getSalLevel() {
		return salLevel;
	}

	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public String getMangerId() {
		return mangerId;
	}

	public void setMangerId(String mangerId) {
		this.mangerId = mangerId;
	}
	
	public String getDeptCode() {
		return deptCode;
	}
	
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Override
	public String toString() {
		return "EmployeeTest [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
				+ ", phone=" + phone + ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", salary=" + salary
				+ ", bonus=" + bonus + ", mangerId=" + mangerId + "]";
	}
	
	public String toString1() {
		return "EmployeeTest [deptTitle=" + deptTitle + ", sumSalary=" + sumSalary + "]"; 
	}
	
	public String toString2() {
		return "EmployeeTest [jobName=" + jobName + ", avgSalary=" + avgSalary + "]"; 
	}
	
	public String toString3() {
		return "EmployeeTest [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
				+ ", phone=" + phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", salary=" + salary
				+ ", bonus=" + bonus + ", mangerId=" + mangerId + "]";
	}

}
