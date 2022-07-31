package com.edu.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

//-----------------------------------------------------------------------------------------------------------
// public class MemberVO
// @Component 어노테이션을 이용하면 Bean Configuration 파일에 Bean으로 따로 등록하지 않아도 사용할 수 있다.
// @Component 어노테이션은 기본적으로 타입기반의 자동주입 어노테이션이다.
//-----------------------------------------------------------------------------------------------------------
@Component("memberVO")
public class MemberVO {

	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	
	public MemberVO() {}  // 기본 생성자
	
	public MemberVO(String id, String pwd, String name, String email) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joinDate=" + joinDate
				+ "]";
	}
	
} // END - public class MemberVO
