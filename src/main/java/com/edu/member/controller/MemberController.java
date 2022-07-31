package com.edu.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.member.vo.MemberVO;

//-----------------------------------------------------------------------------------------------------------
// public interface MemberController
//-----------------------------------------------------------------------------------------------------------
public interface MemberController {

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView addMember(@ModelAttribute("member") MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 조회 (7.6)
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView updateMemberForm(@RequestParam("id") String id, HttpServletRequest request, 
			                             HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView modifyMember(@ModelAttribute("info") MemberVO memberVO, 
			                         HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, 
			                                    HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 폼 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리 (7.7)
	//-----------------------------------------------------------------------------------------------------------
    public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
    		HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃 처리 (7.8)
	//-----------------------------------------------------------------------------------------------------------
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 화면(Ajax) (7.11)
	//-----------------------------------------------------------------------------------------------------------
    public String getRegisterAjaxForm() throws Exception;
    
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사(Ajax) (7.11)
	//-----------------------------------------------------------------------------------------------------------
    public int idCheck(MemberVO memberVO) throws Exception;
    
    
} // end - public interface MemberController
