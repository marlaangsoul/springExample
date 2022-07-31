// 7.6.
package com.edu.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.edu.member.vo.MemberVO;

//----------------------------------------------------------------------
// public class MemberDAOImpl implements MemberDAO
//----------------------------------------------------------------------
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
    //----------------------------------------------------------------------
	// 회원 가입 처리
	// sqlSession.insert(사용할 mapper의 id , 저장할 값) 
	//----------------------------------------------------------------------	
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		
		System.out.println("MemberDAOImpl에서 받은 memberVO ==>" + memberVO);
		int result = sqlSession.insert("mapper.member.addMember", memberVO);
		return result;
		
		
	} // END - public int addMember(MemberVO memberVO)

	//---------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 가져오기
	//---------------------------------------------------------------------------------------------------------
	@Override
	public List selectAllMemberList() throws DataAccessException {
		
		System.out.println("MemberDAOImpl selectAllMemberList().....");
		List<MemberVO> memberLists = null;
		
		memberLists = sqlSession.selectList("mapper.member.selectAllMemberList");
		
		
		return memberLists;
	}
	//---------------------------------------------------------------------------------------------------------
  	// 아이디에 해당하는 회원 정보 가져오기 (7.7)
  	//---------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO selectMember(String id) throws DataAccessException {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.selectMember", id);
		return memberVO;
	}

    //---------------------------------------------------------------------------------------------------------
  	// 아이디에 해당하는 회원 정보 수정하기 (7.7)
  	//---------------------------------------------------------------------------------------------------------
	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.update("mapper.member.updateMember", memberVO);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public MemberVO loginByID(MemberVO memberVO) throws DataAccessException {
		MemberVO memVO = sqlSession.selectOne("mapper.member.loginByID", memberVO);
		return memVO;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사(Ajax) (7.11)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberVO memberVO) throws DataAccessException {

		System.out.println("MemberDAOImpl idCheck() id : " + memberVO.getId());
		
		return sqlSession.selectOne("mapper.member.idCheck", memberVO);
	}
	
} // end - public class MemberDAOImpl implements MemberDAO
