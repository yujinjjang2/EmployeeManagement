package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.emp.common.JDBCTemplate.*;

import edu.kh.emp.model.vo.EmployeeTest;

public class EmployeeTestDAO {

	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public EmployeeTestDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("test-query.xml") );
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<EmployeeTest> selectAll(Connection conn) throws Exception{
		
		List<EmployeeTest> list = new ArrayList<EmployeeTest>();
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				Double bonus = rs.getDouble("BONUS");
				String managerId = rs.getString("MANAGER_ID");
				
				list.add( new EmployeeTest(empId, empName, empNo, email,
						phone, jobCode, salLevel, salary, bonus, managerId) );
				
			}
			
		} finally {
		
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}

	public int insertEmployee(Connection conn, String empId, 
								String empName, String empNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertEmployee");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empId);
			pstmt.setString(2, empName);
			pstmt.setString(3, empNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	public EmployeeTest selectEmpId(Connection conn, String empId) throws Exception{
		
		EmployeeTest empt = null;
		
		try {
			
			String sql = prop.getProperty("selectEmpId");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String empId2 = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				Double bonus = rs.getDouble("BONUS");
				String managerId = rs.getString("MANAGER_ID");
				
				empt = new EmployeeTest(empId2, empName, empNo, email,
						phone, jobCode, salLevel, salary, bonus, managerId);
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return empt;
	}

	public int updateEmployee(Connection conn, String empId,
								String empName, String empNo) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateEmployee");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empName);
			pstmt.setString(2, empNo);
			pstmt.setString(3, empId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		return result;
	}

	public List<EmployeeTest> selectDeptTotalSalary(Connection conn) throws Exception{
		
		List<EmployeeTest> list = new ArrayList<EmployeeTest>();
		
		try {
			String sql = prop.getProperty("selectDeptTotalSalary");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String deptTitle = rs.getString("DEPT_TITLE");
				int sumSalary = rs.getInt("SUM_SALARY");
				
				list.add( new EmployeeTest(deptTitle, sumSalary) );
				
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}

	public List<EmployeeTest> selectJobAvgSalary(Connection conn) throws Exception{
		List<EmployeeTest> list = new ArrayList<EmployeeTest>();
		
		try {
			
			String sql = prop.getProperty("selectJobAvgSalary");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String jobName = rs.getString("JOB_NAME");
				int avgSalary = rs.getInt("AVG_SALARY");
				
				EmployeeTest empt = new EmployeeTest();
				
				empt.setJobName(jobName);
				empt.setAvgSalary(avgSalary);
				
				list.add(empt);
			}
			 
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		return list;
	}

	public int deleteEmployee(Connection conn, String empId) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteEmployee");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		return result;
	}

	public List<EmployeeTest> selectDeptEmp(Connection conn, String deptTitle) throws Exception{
		
		List<EmployeeTest> list = new ArrayList<EmployeeTest>();
		
		try {
			
			String sql = prop.getProperty("selectDeptEmp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deptTitle);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				Double bonus = rs.getDouble("BONUS");
				String managerId = rs.getString("MANAGER_ID");
				
				list.add( new EmployeeTest(empId, empName, empNo, email, phone,
						deptCode, jobCode, salLevel, salary, bonus, managerId) );
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}

	public List<EmployeeTest> selectSalaryEmp(Connection conn, int salary) throws Exception{
		List<EmployeeTest> list = new ArrayList<EmployeeTest>();
		
		try {
			
			String sql = prop.getProperty("selectSalaryEmp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, salary);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary1 = rs.getInt("SALARY");
				Double bonus = rs.getDouble("BONUS");
				String managerId = rs.getString("MANAGER_ID");
				
				list.add( new EmployeeTest(empId, empName, empNo, email, phone,
						deptCode, jobCode, salLevel, salary1, bonus, managerId) );

			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}

	public EmployeeTest selectEmpNo(Connection conn, String empNo) throws Exception{
		EmployeeTest empt = null;
		
		try {
			
			String sql = prop.getProperty("selectEmpNo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo1 = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary1 = rs.getInt("SALARY");
				Double bonus = rs.getDouble("BONUS");
				String managerId = rs.getString("MANAGER_ID");
				
				empt = new EmployeeTest(empId, empName, empNo1, email, phone,
						deptCode, jobCode, salLevel, salary1, bonus, managerId);
	
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return empt;
	}
}
