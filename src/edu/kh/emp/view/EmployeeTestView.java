package edu.kh.emp.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeTestService;
import edu.kh.emp.model.vo.EmployeeTest;

public class EmployeeTestView {

	private Scanner sc = new Scanner(System.in);
	// Service 객체 생성
	private EmployeeTestService service = new EmployeeTestService();
	
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

		private void selectEmpNo() throws Exception{
			System.out.println("9. 주민등록번호가 일치하는 사원 정보 조회");
			
			System.out.print("주민등록번호 입력 : ");
			String empNo = sc.next();
			
			EmployeeTest empt = service.selectEmpNo(empNo);
			
			if(empt.equals("")) {
				System.out.println("주민등록번호가 일치하는 사원 정보가 없습니다.");
			} else {
				
				System.out.println(empt);
			}
			
		}

		private void selectSalaryEmp() throws Exception{
			System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
			
			System.out.print("급여 입력 : ");
			int salary = sc.nextInt();
			
			List<EmployeeTest> list = service.selectSalaryEmp(salary);
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			} else {
				
				for(EmployeeTest empt : list) {
					System.out.println(empt.toString3());
				}
			}
			
		}

		private void selectDeptEmp() throws Exception{
			System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
			
			System.out.print("부서입력 : ");
			String deptTitle = sc.next();
			
			List<EmployeeTest> list = service.selectDeptEmp(deptTitle);
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			} else {
				
				for(EmployeeTest empt : list) {
					System.out.println(empt.toString3());
				}
			}
		}

		private void deleteEmployee() throws Exception{
			System.out.println("5. 사번이 일치하는 사원 정보 삭제");
			
			System.out.print("사번 입력 : ");
			String empId = sc.next();
			
			int result = service.deleteEmployee(empId);
			
			if(result > 0) {
				System.out.println("사번이 일치하는 사원 정보 삭제 성공");
			} else {
				System.out.println("사번이 일치하는 사원 정보 삭제 실패");
			}
			
		}

		private void selectJobAvgSalary() throws Exception{
			System.out.println("10. 직급별 급여 평균 조회");
			
			List<EmployeeTest> list = service.selectJobAvgSalary();
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			}
			
			for(EmployeeTest empt : list) {
				System.out.println(empt.toString2());
			}
		}

		private void selectDeptTotalSalary() throws Exception{
			System.out.println("8. 부서별 급여 합 전체 조회");
			
			List<EmployeeTest> list = service.selectDeptTotalSalary();
			
			if(list.isEmpty()) {
				System.out.println("조회된 내역이 없습니다.");
			}
			
			for(EmployeeTest empt : list) {
				System.out.println(empt.toString1());
			}
			
		}

		private void updateEmployee() throws Exception{
			System.out.println("4. 사번이 일치하는 사원 정보 수정");
			
			System.out.print("사번 입력 : ");
			String empId = sc.next();
			
			System.out.print("이름 입력 : ");
			String empName = sc.next();
			
			System.out.print("주민등록번호 입력 : ");
			String empNo = sc.next();
			
			int result = service.updateEmployee(empId, empName, empNo);
			
			if(result > 0) {
				System.out.println("사번이 일치하는 사원 정보 수정 성공");
			} else {
				System.out.println("사번이 일치하는 사원 정보 수정 실패");
			}
			
		}

		private void selectEmpId() throws Exception{
			System.out.println("3. 사번이 일치하는 사원 정보 조회");
			
			System.out.print("사번 입력 : ");
			String empId = sc.next();
			
			EmployeeTest empt = service.selectEmpId(empId);
			
			if(empt.getEmpId().equals("")) {
				System.out.println("사번이 일치하는 사원 정보가 없습니다.");
			} else {
				System.out.println(empt);
			}
			
		}

		private void insertEmployee() throws Exception{
			System.out.println("2. 새로운 사원 추가");
			
			System.out.print("사원번호 입력 : ");
			String empId = sc.next();
			
			System.out.print("사원이름 입력 : ");
			String empName = sc.next();
			
			System.out.print("사원주민번호 입력 : ");
			String empNo = sc.next();
			
			int result = service.insertEmployee(empId, empName, empNo);
			
			if(result > 0) {
				System.out.println("사원 추가 성공");
			} else {
				System.out.println("사원 추가 실패");
			}
			
		}

		private void selectAll() throws Exception{
			System.out.println("1. 전체 사원 정보 조회");
			
			List<EmployeeTest> list = service.selectAll();
			
			if(list.isEmpty()) {
				System.out.println("조회 결과가 없습니다.");
			}
			
			for(EmployeeTest empt : list) {
				System.out.println(empt);
			}
			
		}
}
