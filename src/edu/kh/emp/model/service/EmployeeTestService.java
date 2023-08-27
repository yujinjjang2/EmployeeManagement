package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.emp.common.JDBCTemplate.*;

import edu.kh.emp.model.dao.EmployeeTestDAO;
import edu.kh.emp.model.vo.EmployeeTest;

public class EmployeeTestService {

	private EmployeeTestDAO dao = new EmployeeTestDAO();

	public List<EmployeeTest> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<EmployeeTest> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

	public int insertEmployee(String empId, String empName, String empNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertEmployee(conn, empId, empName, empNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public EmployeeTest selectEmpId(String empId) throws Exception{
		
		Connection conn = getConnection();
		
		EmployeeTest empt = dao.selectEmpId(conn, empId);
		
		close(conn);
		
		return empt;
	}

	public int updateEmployee(String empId, String empName, String empNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateEmployee(conn, empId, empName, empNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<EmployeeTest> selectDeptTotalSalary() throws Exception{
		
		Connection conn = getConnection();
		
		List<EmployeeTest> list = dao.selectDeptTotalSalary(conn);
		
		close(conn);
		
		return list;
	}

	public List<EmployeeTest> selectJobAvgSalary() throws Exception{
		
		Connection conn = getConnection();
		
		List<EmployeeTest> list = dao.selectJobAvgSalary(conn);
		
		close(conn);
		
		return list;
	}

	public int deleteEmployee(String empId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteEmployee(conn, empId);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<EmployeeTest> selectDeptEmp(String deptTitle) throws Exception{
		
		Connection conn = getConnection();
		
		List<EmployeeTest> list = dao.selectDeptEmp(conn, deptTitle);
		
		close(conn);
		
		return list;
	}

	public List<EmployeeTest> selectSalaryEmp(int salary) throws Exception{
		
		Connection conn = getConnection();
		
		List<EmployeeTest> list = dao.selectSalaryEmp(conn, salary);
		
		close(conn);
		
		return list;
	}

	public EmployeeTest selectEmpNo(String empNo) throws Exception{
		
		Connection conn = getConnection();
		
		EmployeeTest empt = dao.selectEmpNo(conn, empNo);
		
		close(conn);
		
		return empt;
	}
}
