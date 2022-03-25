/*==============================================
	MemeberProcess.java
	- 콘솔 기반 서브 메뉴 입출력 전용 클래스
==============================================*/

package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberProcess // 실무에서는 '서비스'라고 부르게 될 것.
{
	// 주요 속성 구성
	private MemberDAO dao;
	
	// 생성자 정의(사용자 정의 생성자)
	public MemberProcess()
	{
		dao = new MemberDAO();
	}
	
	// 직원 정보 입력 메소드 정의
	public void memberInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			
			// 지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder cityStr = new StringBuilder();
			for (String city : citys)
			{
				cityStr.append(city + "/");
			}
			// "강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/"
			
			
			// 부서 리스트 구성
			ArrayList<String> buseos = dao.searchBuseo();
			StringBuilder buseoStr = new StringBuilder();
			for (String buseo : buseos)
			{
				buseoStr.append(buseo + "/");
			}
			// "개발부/기획부/영업부/인사부/자재부/총무부/홍보부/"
			
			
			// 직위 리스트 구성
			ArrayList<String> jikwis = dao.searchJikwi();
			StringBuilder jikwiStr = new StringBuilder();
			for (String jikwi : jikwis)
			{
				jikwiStr.append(jikwi + "/");
			}
			//"사장/전무/상무/이사/부장/차장/과장/대리/사원/"
			
			
			// 사용자에게 보여지는 화면 처리
			/*
			직원 정보 입력 -------------------------------------------------------------------
			이름 : 김정용
			주민등록번호(yymmdd-nnnnnnn) : 960608-2234567
			입사일(yyyy-mm-dd) : 2019-06-08
			지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : 경기
			전화번호 : 010-2731-3153
			부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : 개발부
			직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/) : 대리
			기본급(최소 1800000 이상) : 5000000
			수당 : 2000000
			
			직원 정보 입력 완료~!!!
			------------------------------------------------------------------- 직원 정보 입력 
			*/
			
			System.out.println("직원 정보 입력 -------------------------------------------------------------------");
			System.out.print("이름 : ");
			String empName = sc.next();
			System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = sc.next();
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsaDate = sc.next();
			System.out.printf("지역(%s) : ", cityStr.toString());
			String cityName = sc.next();
			System.out.print("전화번호 : ");
			String tel = sc.next();
			System.out.printf("부서(%s) : ", buseoStr.toString());
			String buseoName = sc.next();
			System.out.printf("직위(%s) : ", jikwiStr.toString());
			String jikwiName = sc.next();
			System.out.printf("기본급(최소 %d원 이상) : ", dao.searchBasicPay(jikwiName));
			int basicPay = sc.nextInt();
			System.out.print("수당 : ");
			int sudang = sc.nextInt();
			System.out.println("");
			
			MemberDTO dto = new MemberDTO();
			dto.setEmpName(empName);
			dto.setSsn(ssn);
			dto.setIbsaDate(ibsaDate);
			dto.setCityName(cityName);
			dto.setTel(tel);
			dto.setBuseoName(buseoName);
			dto.setJikwiName(jikwiName);
			dto.setBasicPay(basicPay);
			dto.setSudang(sudang);
			
			int result = dao.add(dto);
			if (result > 0)
			{
				System.out.println("직원 정보 입력 완료~!!!");
			}
			System.out.println("------------------------------------------------------------------- 직원 정보 입력");
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.close();
			} catch (Exception e2)
			{
				System.out.println(e.toString());
			}
		}
	}// end memberInsert()
	
	
	// 직원 전체 출력 메소드 정의
	public void memberLists()
	{
		Scanner sc = new Scanner(System.in);
		
		// 서브 메뉴 출력(안내)
		System.out.println("");
		System.out.println("1. 사번 정렬");				// EMP_ID
		System.out.println("2. 이름 정렬");				// EMP_NAME
		System.out.println("3. 부서 정렬");				// BUSEO_NAME
		System.out.println("4. 직위 정렬");				// JIKWI_NAME
		System.out.println("5. 급여 내림차순 정렬");	// PAY DESC
		System.out.println(">> 항목 선택(1~5, -1종료)");
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu == -1)
			{
				return; // memberLists() 종료
			}
			
			String key = "";
			
			switch (menu)
			{
				case 1:
					key = "EMP_ID"; break;
				case 2:
					key = "EMP_NAME"; break;
				case 3:
					key = "BUSEO_NAME"; break;
				case 4:
					key = "JIKWI_NAME"; break;
				case 5:
					key = "PAY DESC"; break;
			}
			
			// 데이터베이스 연결
			dao.connection();
			
			// 직원 리스트 출력
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.memberCount());
			System.out.println("사번  이름  주민번호  입사일  지역  전화번호  부서  직위  기본급  수당  급여");
			ArrayList<MemberDTO> memList = dao.lists(key);
			
			for (MemberDTO dto : memList)
			{
				System.out.print("%5d %4s %14s %10s %4s %12s %4s %3s %8d %8d %8d\n"
						, dto.getEmpId(), dto.getEmpName()
						, dto.getSsn(), dto.getIbsaDate()
						, dto.getCityName(), dto.getTel(), dto.getBuseoName(), dto.getJikwiName()
						, dto.getBasicPay(), dto.getSudang(), dto.getPay());
			}
		} 
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e2)
			{
				System.out.println(e.toString());
			}
		}
	}// end memberLists()
	
	
	// 직원 검색 출력 메소드 정의
	public void memberSearch()
	{
		Scanner sc = new Scanner(System.in);
		
		// 서브 메뉴 구성
		System.out.println();
		System.out.println("1. 사번 검색");
		System.out.println("2. 이름 검색");
		System.out.println("3. 부서 검색");
		System.out.println("4. 직위 검색");
		System.out.print(">> 항목 선택(1~4, -1종료) : ");
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu == -1)
			{
				return;
			}
			
			String key = "";
			String value = "";
			
			switch (menu)
			{
			case 1:
				key = "EMP_ID";
				System.out.print("검색할 사원번호 입력 : ");
				value = sc.next();
				break;
			case 2:
				key = "EMP_NAME";
				System.out.print("검색할 사원이름 입력 : ");
				value = sc.next();
				break;
			case 3:
				key = "BUSEO_NAME";
				System.out.print("검색할 부서이름 입력 : ");
				value = sc.next();
				break;
			case 4:
				key = "JIKWI_NAME";
				System.out.print("검색할 직위이름 입력 : ");
				value = sc.next();
				break;
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
	
	
	
	
}