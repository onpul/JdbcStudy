/*=====================
	MemberDTO.java
=====================*/

/*
EMP_ID     NOT NULL NUMBER(5)    
EMP_NAME            VARCHAR2(30) 
SSN                 CHAR(14)     
IBSADATE            VARCHAR2(10) 
CITY_NAME           VARCHAR2(10) 
TEL                 VARCHAR2(20) 
BUSEO_NAME          VARCHAR2(20) 
JIKWI_NAME          VARCHAR2(20) 
BASICPAY            NUMBER(10)   
SUDANG              NUMBER(10)   
PAY                 NUMBER       
*/

package com.test;

public class MemberDTO
{
	// 주요 속성 구성
	//-- 사번, 이름, 주민번호, 입사일, 지역, 전화번호
	//  , 부서, 직위, 기본급, 수당, 급여
	private int empId, basicPay, sudang, pay;
	private String empName, ssn, cityName, tel, buseoName, jikwiName;
	private String ibsaDate;	
	// ※ 입사일은 오라클에서 날짜 형식으로 처리되지만 문자열로 구성 (실무에서도 주로 이렇게 함)
	
	// getter / setter 구성
	public int getEmpId()
	{
		return empId;
	}
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	public int getBasicPay()
	{
		return basicPay;
	}
	public void setBasicPay(int basicPay)
	{
		this.basicPay = basicPay;
	}
	public int getSudang()
	{
		return sudang;
	}
	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}
	public int getPay()
	{
		return pay;
	}
	public void setPay(int pay)
	{
		this.pay = pay;
	}
	public String getEmpName()
	{
		return empName;
	}
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	public String getSsn()
	{
		return ssn;
	}
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	public String getCityName()
	{
		return cityName;
	}
	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public String getBuseoName()
	{
		return buseoName;
	}
	public void setBuseoName(String buseoName)
	{
		this.buseoName = buseoName;
	}
	public String getJikwiName()
	{
		return jikwiName;
	}
	public void setJikwiName(String jikwiName)
	{
		this.jikwiName = jikwiName;
	}
	public String getIbsaDate()
	{
		return ibsaDate;
	}
	public void setIbsaDate(String ibsaDate)
	{
		this.ibsaDate = ibsaDate;
	}
}
