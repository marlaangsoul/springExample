package com.edu.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.board.dto.BoardDTO;
import com.edu.board.service.BoardService;

//------------------------------------------------------------------------------------------------------
// public class BoardController
//------------------------------------------------------------------------------------------------------
@Controller // Bean의 대상으로 인식시키기 위해서 servlet-context.xml에 등록한다.
@RequestMapping(value="/board/*")
public class BoardController {
	
	@Inject
	private BoardService boardService;

	//------------------------------------------------------------------------------------------------------
	// 게시글 목록
	//------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public void boardList(Locale locale, Model model) throws Exception {
		
		System.out.println("BoardController boardList() 시작");
		
		List<BoardDTO> boardList = boardService.boardList();
		System.out.println("BoardController boardList() data ==> " + boardList);
		model.addAttribute("boardList", boardList);
	}
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 쓰기 화면
	//------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/boardRegisterForm.do", method = RequestMethod.GET)
	public String boardRegisterForm(Locale locale, Model model) throws Exception {
		
		System.out.println("BoardController boardRegisterForm()");
		return "/board/boardRegisterForm";
	}
		
	//------------------------------------------------------------------------------------------------------
	// 게시글 내용 등록하기 (7.14)
	//------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardRegister", method = RequestMethod.POST)
	public String boardRegister(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println("BoardController boardRegister() 시작");
		
		// 게시글 등록일자에 넣을 데이터를 등록일자 형식(년월일시분초)으로 만든다. 
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setReg_date(format.format(date));
		
		if(boardService.boardRegister(boardDTO) == 1) { // 게시글 등록 완료
			return "Y";
		} else { // 게시글 등록 실패
			return "N";
		}
	
	}
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 상세 조회 GET(7.14)
	//------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/boardDetail", method = RequestMethod.GET)
	public String boardDetail(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		// 게시글 번호에 해당하는 게시글 데이터를 가져온다.
		BoardDTO boardDTO = boardService.boardDetail(Integer.parseInt((String) request.getParameter("seq")));
		
		model.addAttribute("boardDetail", boardDTO);
		return "/board/boardDetail";	
	}
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 수정 화면(7.15-18)
	//------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/boardUpdateForm", method = RequestMethod.POST)
	public String boardUpdateForm(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println("BoardController boardUpdateForm() seq " + request.getParameter("seq"));
		
		// 게시글 번호에 해당하는 정보를 찾아온다.
		BoardDTO boardDTO = boardService.boardDetail(Integer.parseInt((String)request.getParameter("seq")));
		model.addAttribute("boardDetail", boardDTO);
		return "/board/boardUpdate";
	
	}
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 수정 하기(7.18)
	//------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(Locale locale, Model model, BoardDTO boardDTO) throws Exception {
		
		System.out.println("BoardController boardUpdate() BoardDTO => " + boardDTO);
		if(boardService.boardUpdate(boardDTO) == 1) {
			return "Y";
		} else {
			return "N";
		}

	}
	
	//------------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글 삭제(7.15)
	//------------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/boardDelete", method = RequestMethod.POST)
	public String boardDelete(Locale locale, Model model, HttpServletRequest request) throws Exception {
		System.out.println("BoardController boardDelte() seq" + request.getParameter("seq"));
		
		if(boardService.boardDelete(Integer.parseInt((String)request.getParameter("seq"))) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}
	
} // end - public class BoardController
