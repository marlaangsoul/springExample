package com.edu.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.edu.member.dao.MemberDAO;
import com.edu.member.vo.MemberVO;

//---------------------------------------------------------------------------------------------------------
// public class MemberServiceImpl implements MemberService
//---------------------------------------------------------------------------------------------------------
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
    //---------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//---------------------------------------------------------------------------------------------------------
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("MemberServiceImpl에서 받은 memberVO ==> " + memberVO);
		return memberDAO.addMember(memberVO);
		
	
	} // end - public int addMember(MemberVO memberVO)
	
	//---------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 가져오기
	//---------------------------------------------------------------------------------------------------------
	@Override
	public List listMembers() throws DataAccessException {
		
		List memberLists = null;
		memberLists = memberDAO.selectAllMemberList();
		return memberLists;
		
	} // end - public List listMembers() throws DataAccessException

	//---------------------------------------------------------------------------------------------------------
  	// 아이디에 해당하는 회원 정보 가져오기 (7.7)
  	//---------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO selectMember(String id) throws DataAccessException {
		MemberVO memberVO = memberDAO.selectMember(id);
		return memberVO;
	}

    //---------------------------------------------------------------------------------------------------------
  	// 아이디에 해당하는 회원 정보 수정하기 (7.7)
  	//---------------------------------------------------------------------------------------------------------
	@Override
	public int modifyMember(MemberVO memberVO) throws DataAccessException {
		
		return memberDAO.updateMember(memberVO);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int removeMember(String id) throws DataAccessException {
		
		return memberDAO.deleteMember(id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO login(MemberVO memberVO) throws DataAccessException {
		return memberDAO.loginByID(memberVO);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사(Ajax) (7.11)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("MemberService 아이디 중복 검사(Ajax)");
		int result = memberDAO.idCheck(memberVO);
		return result;
	}

} // end - public class MemberServiceImpl implements MemberService
