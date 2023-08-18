package edu.kh.emp.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.vo.Employee;

// 화면용 클래스
public class EmployeeView {
	
	private Scanner sc = new Scanner(System.in);
	
	// Service 객체 생성
	private EmployeeService service = new EmployeeService();
	
	// 메인메뉴
	public void displayMenu() {
		
		int input = 0;
		
		do {
			
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- 사원 관리 프로그램 -----");
				System.out.println("1. 전체 사원 정보 조회");
				System.out.println("2. 새로운 사원 추가");
				System.out.println("3. 사번이 일치하는 사원 정보 조회");
				System.out.println("4. 사번이 일치하는 사원 정보 수정");
				System.out.println("5. 사번이 일치하는 사원 정보 삭제");
				
				
				// 추가 (6~10)
				System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
				// selectDeptEmp()
				
				System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
				// selectSalaryEmp()
				
				System.out.println("8. 부서별 급여 합 전체 조회");
				// selectDeptTotalSalary()
				// DB 조회 결과를 HashMap<String, Integer>에 옮겨 담아서 반환
				// 부서코드, 급여 합 조회
				
				System.out.println("9. 주민등록번호가 일치하는 사원 정보 조회");
				
				System.out.println("10. 직급별 급여 평균 조회");
				// selectJobAvgSalary()
				// DB 조회 결과를 HashMap<String, Double>에 옮겨 담아서 반환
				// 직급명, 급여 평균(소수점 첫째자리) 조회
				
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); //  추가!
				
				
				System.out.println();				
				
				
				switch(input) {
				case 1:  selectAll();   break;
				case 2:  insertEmployee();  break;
				case 3:  selectEmpId();   break;
				case 4:  updateEmployee();   break;
				case 5:  deleteEmployee();   break;
				case 6:  selectDeptEmp();   break;
				case 7:  selectSalaryEmp();  break;
				case 8:  selectDeptTotalSalary();  break;
				case 9:  selectEmpNo();  break;
				case 10: selectJobAvgSalary();  break;
				case 0:  System.out.println("프로그램을 종료합니다...");   break;
				default: System.out.println("메뉴에 존재하는 번호만 입력하세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요.");
				input = -1; // 반복문 첫 번째 바퀴에서 잘못 입력하면 종료되는 상황을 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못 입력된 문자열 제거해서
							   // 무한 반복 방지
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while(input != 0);
	}
	
	// 주 기능 메서드

	/** 사번이 일치하는 사원 정보 삭제
	 * 
	 */
	private void deleteEmployee() throws Exception{
		
		System.out.println("<사번이 일치하는 사원 정보 삭제>");
		
		int empId = inputEmpId();
		
		System.out.print("정말 삭제 하시겠습니까? (Y/N) : ");
		char input = sc.next().toUpperCase().charAt(0);
		// Y/N 대소문가 구분없이 입력
		
		if(input == 'Y') {
			// 삭제 수행 서비스 호출
			int result = service.deleteEmployee(empId);
			
			if(result > 0) {
				System.out.println("삭제되었습니다.");
			} else {
				System.out.println("사번이 일치하는 사원이 존재하지 않습니다.");
			}
			
		} else {
			System.out.println("취소되었습니다.");
		}
		
	}

	/** 사번이 일치하는 사원정보 수정(이메일, 전화번호, 급여)
	 * 
	 */
	private void updateEmployee() throws Exception{
		
		System.out.println("<사번이 일치하는 사원 정보 수정>");
		
		int empId = inputEmpId();
		
		System.out.print("이메일 : ");
		String email = sc.next();
		
		System.out.print("전화번호(-제외) : ");
		String phone = sc.next();
		
		System.out.print("급여 : ");
		int salary = sc.nextInt();
		
		Employee emp = new Employee();
		
		emp.setEmpId(empId);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setSalary(salary);
		
		int result = service.updateEmployee(emp);
		
		if(result > 0) {
			System.out.println("사원 정보가 수정되었습니다.");
		} else {
			System.out.println("사번이 일치하는 직원이 존재하지 않습니다.");
		}
		
		
	}

	/** 사번이 일치하는 사원 정보 조회
	 * 
	 */
	private void selectEmpId() throws Exception{
		System.out.println("<사번이 일치하는 사원 정보 조회>");
		
		// 사번 입력 받기
		int empId = inputEmpId();
		/*
		 * System.out.println("사번 입력 : ");
			int empId = sc.nextInt();
			sc.nextLine();
			return empId;
		 * 
		 * */
		
		Employee emp = service.selectEmpId(empId);
		
		printOne(emp);
		
	}
	
//	/** 입력 받은 부서와 일치하는 모든 사원 정보 조회
//	 * 
//	 */
//	private void selectDeptEmp() throws Exception{
//		System.out.println("<입력 받은 부서와 일치하는 모든 사원 정보 조회>");
//		
//		System.out.print("부서명 입력 : ");
//		String deptTitle = sc.nextLine();
//		
//		List<Employee> empList = service.selectDeptEmp(deptTitle);
//		
//		printAll(empList);
//		
//	}
	
	/** 입력 받은 부서와 일치하는 모든 사원 정보 조회
	 * 
	 */
	private void selectDeptEmp() throws Exception{
		System.out.println("<입력받은 부서와 일치하는 모든 사원 정보 조회>");
		
		System.out.print("부서명 : ");
		String departmentTitle = sc.nextLine();
		
		printAll( service.selectDeptEmp(departmentTitle) );
	}
	
//	/** 입력 받은 급여 이상을 받는 모든 사원 정보 조회
//	 * 
//	 */
//	private void selectSalaryEmp() throws Exception{
//		System.out.println("<입력 받은 급여 이상을 받는 모든 사원 정보 조회>");
//		
//		System.out.print("급여 입력 : ");
//		int salary = sc.nextInt();
//		
//		List<Employee> empList = service.selectSalaryEmp(salary);
//		
//		printAll(empList);
//		
//	}
	
	/** 입력 받은 급여 이상을 받는 모든 사원 정보 조회
	 * 
	 */
	private void selectSalaryEmp() throws Exception {
		System.out.println("<입력받은 급여 이상을 받는 모든 사원 정보 조회>");
		
		System.out.print("급여 : ");
		int salary = sc.nextInt();
		
		printAll( service.selectSalaryEmp(salary) );
		
	}
	
//	/** 부서별 급여 합 전체 조회
//	 * 
//	 */
//	private void selectDeptTotalSalary() throws Exception{
//		System.out.println("<부서별 급여 합 전체 조회>");
//		
//		List<Employee> empList = service.selectDeptTotalSalary();
//		
//		printDeptSalary(empList);
//		
//	}
	
	/** 부서별 급여 합 전체 조회
	 * 
	 */
	private void selectDeptTotalSalary() throws Exception{
		System.out.println("<부서별 급여 합 전체 조회>");
		
		// D1 : 8000000 원
		Map<String, Integer> map = service.selectDeptTotalSalary();
		
		for( String key : map.keySet() ) {
			
			System.out.println( key + " : " + map.get(key) );
			
		}
		
	}
	
//	/** 주민등록번호가 일치하는 사원 정보 조회
//	 * 
//	 */
//	private void selectEmpNo() throws Exception{
//		System.out.println("<주민등록번호가 일치하는 사원 정보 조회>");
//		
//		System.out.print("주민등록번호 입력 : ");
//		String empNo = sc.nextLine();
//		
//		Employee emp = service.selectEmpNo(empNo);
//		
//		printOne(emp);
//		
//	}
	
	/** 주민등록 번호가 일치하는 사원 정보 조회
	 * 
	 */
	private void selectEmpNo() throws Exception{
		System.out.println("<주민등록 번호가 일치하는 사원 정보 조회>");
		System.out.print("주민등록번호 입력 : ");
		String empNo = sc.next();
		
		Employee emp = service.selectEmpNo(empNo);
		printOne(emp);
		
//		printOne(service.selectEmpNo(empNo))
	}
	
//	/** 직급별 급여 평균 조회
//	 * 
//	 */
//	private void selectJobAvgSalary() throws Exception{
//		System.out.println("<직급별 급여 평균 조회>");
//		
//		List<Employee> empList = service.selectJobAvgSalary();
//		
//		printJobSalary(empList);
//		
//	}
	
	/** 직급별 급여 평균 조회
	 * 
	 */
	private void selectJobAvgSalary() throws Exception{
		System.out.println("<직급별 급여 평균 조회>");
		
		Map<String, Double> map = service.selectJobAvgSalary();
		
		for(String key : map.keySet()) {
			System.out.println( key + " : " + map.get(key) + "원");
		}
		
	}

	/** 사원 정보 추가
	 * @throws
	 */
	private void insertEmployee() throws Exception{
		System.out.println("<사원 정보 추가>");
		
		// 사번
		int empId = inputEmpId();
		
		System.out.print("이름 : ");
		String empName = sc.next();
		
		System.out.print("주민등록번호 : ");
		String empNo = sc.next();
		
		System.out.print("이메일 : ");
		String email = sc.next();
		
		System.out.print("전화번호 : ");
		String phone = sc.next();
		
		System.out.print("부서코드(D1~D9) : ");
		String deptCode = sc.next();
		
		System.out.print("직급코드(J1~J7) : ");
		String jobCode = sc.next();
		
		System.out.print("급여등급(S1~S6) : ");
		String salLevel = sc.next();
		
		System.out.print("급여 : ");
		int salary = sc.nextInt();
		
		System.out.print("보너스 : ");
		double bonus = sc.nextDouble();
		
		System.out.print("사수번호 : ");
		int managerId = sc.nextInt();
		
		
		Employee emp = new Employee(empId, empName, empNo, email, phone, salary,
									deptCode, jobCode, salLevel,
									bonus, managerId);
		
		int result = service.insertEmployee(emp);
		
		if(result > 0) {
			System.out.println("사원 정보 추가 성공");
		} else {
			System.out.println("사원 정보 추가 실패!");
		}
	}

	/** 전체 사원 정보 조회
	 * 
	 */
	private void selectAll() throws Exception{
		System.out.println("<전체 사원 정보 조회>");
		
		List<Employee> empList = service.selectAll();
		
		printAll(empList);
		
	}
	
	
	// 보조 메서드
	
	/** 전달받은 사원 List 모두 출력
	 * 
	 */
	public void printAll(List<Employee> empList) {
		
		if(empList.isEmpty()) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
						emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
						emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
			}
		
		}
		
		return;

	}
	
	/** 부서별 급여합 출력
	 * 
	 */
	public void printDeptSalary(List<Employee> empList) {
		
		if(empList.isEmpty()) {
			System.out.println("조회된 정보가 없습니다.");
			
		} else {
			System.out.println("부서 | 급여(합)" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf("%s | %d\n",
						emp.getDepartmentTitle(), emp.getSalary());
			}
		
		}
		
		return;

	}	
	
	/** 직급별 급여평균 출력
	 * 
	 */
	public void printJobSalary(List<Employee> empList) {
		
		if(empList.isEmpty()) {
			System.out.println("조회된 정보가 없습니다.");
			
		} else {
			System.out.println("직급 | 급여(평균)" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Employee emp : empList) { 
				System.out.printf("%s | %d\n",
						emp.getJobName(), emp.getSalary());
			}
		
		}
		
		return;

	}
	
	
	/** 사원 1명 정보 출력
	 * @param emp
	 */
	public void printOne(Employee emp) {
		
		if(emp == null) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			
			System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
					emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(), 
					emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
		}


	}
	
	
	/** 사번을 입력받아 반환하는 메서드
	 * @return empId
	 */
	public int inputEmpId() {
		System.out.print("사번 입력 : ");
		int empId = sc.nextInt();
		sc.nextLine();
		return empId;
	}
	
	

}
