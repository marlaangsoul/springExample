package com.edu.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.board.dao.BoardDAO;
import com.edu.board.dto.BoardDTO;

//------------------------------------------------------------------------------------------------------
// public class BoardServiceImpl implements BoardService
//------------------------------------------------------------------------------------------------------
@Service   // Bean으로 인식 시키기 위해서 사용한다.
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO boardDAO;
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 목록
	//------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardDTO> boardList() throws Exception {

		System.out.println("BoardServiceImpl boardList() 시작");
		return boardDAO.boardList();
	}
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 등록 처리 (7.14)
	//------------------------------------------------------------------------------------------------------
	@Override
	public int boardRegister(BoardDTO boardDTO) throws Exception {
		
		System.out.println("BoardServiceImpl boardRegister() 시작");
		
		if(boardDAO.getMaxSeq() == null) { // 게시글이 하나도 존재하지 않았을 때(맨 처음으로 게시글을 등록할 때)
			boardDTO.setSeq(1);			   // 게시글 번호는 1로 한다.
 		} else { // 게시글이 하나라도 존재한다면 최대값에 1을 더한 값을 게시글 번호로 한다.
			boardDTO.setSeq(boardDAO.getMaxSeq() + 1); // 최대값에 1을 더한 값을 게시글 번호로 한다.
		}
		return boardDAO.boardRegister(boardDTO);
	}


	//------------------------------------------------------------------------------------------------------
	// 게시글 상세 조회 GET (7.14-15)
	//------------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO boardDetail(int seq) throws Exception {
		// 게시글 번호에 해당하는 게시글의 자료를 가져오기 전에 조회수를 1증가 시킨다.
		boardDAO.updateReadCount(seq);
		// 게시글 번호에 해당하는 게시글의 자료를 가져온다.
		return boardDAO.boardDetail(seq);
	}

	//------------------------------------------------------------------------------------------------------
	// 게시글 삭제 (7.15)
	//------------------------------------------------------------------------------------------------------
	@Override
	public int boardDelete(int seq) {

		System.out.println("boardServiceImpl boardDelte(" + seq + ") 시작");
		return boardDAO.boardDelete(seq);
		
	}
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 수정 하기(7.18)
	//------------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardDTO boardDTO) {
		System.out.println("BoardServiceImpl boardUpdate() 시작");
		return boardDAO.boardUpdate(boardDTO);
	}
	

} // end - public class BoardServiceImpl implements BoardService
