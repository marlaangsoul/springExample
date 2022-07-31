package com.edu.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.member.service.MemberService;
import com.edu.member.vo.MemberVO;

//-----------------------------------------------------------------------------------------------------------
//public class MemberControllerImpl implements MemberController
//-----------------------------------------------------------------------------------------------------------
@Controller("memberController")
@RequestMapping("/member") // url에서 /member로 시작하는 것들을 처리하는 컨트롤러
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private MemberService memberService;
	// == MemeberService memberService = new MemberService();
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/memberForm.do", method=RequestMethod.GET)
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberForm");
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("memberVO")MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("MemberController에서 받은 memberVO ==>" + memberVO);
		
		int result = 0;
		// 사용자가 입력한 정보를 서비스에게 넘겨주어 처리하게 한다.
		result = memberService.addMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		return mav;
		
	} // END - public ModelAndView addMember

	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 조회(7.6)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		// 회원 전체 목록 자료를 가져온다.
		List memberLists = memberService.listMembers();
		System.out.println("회원 전체 목록 ==> " + memberLists);
		
		// 회원 전체 목록 화면을 보여준다.
		ModelAndView mav = new ModelAndView("/member/listMembers");
		// mav.setViewName("/member/listMembers");
		mav.addObject("memberLists", memberLists);
		
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/updateMemberForm.do", method=RequestMethod.GET)
	public ModelAndView updateMemberForm(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("MemberController 회원 정보 조회 id ==> " + id);
		
		// 회원 전체 리스트 화면에서 요청한 id에 해당하는 정보를 찾는 일을 서비스에게 부탁한다.
		memberVO = memberService.selectMember(id);
		System.out.println("MemberController 회원 정보 조회 ==> " + memberVO);
		
		// 찾아온 데이터를 가지고 개인 정보 수정화면으로 넘어간다.
		ModelAndView mav = new ModelAndView("/member/updateMemberForm");
		mav.addObject("member", memberVO);
		
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기(7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="modifyMember.do", method=RequestMethod.POST)
	public ModelAndView modifyMember(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("MemberController 회원 정보 수정하기 memberVO ==> " + memberVO);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int result = memberService.modifyMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제하기 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="removeMember.do", method=RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		int result = memberService.removeMember(id);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 화면 띄우기 (7.7)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/loginForm.do", method=RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/member/loginForm"); // == new ModelAndView("/member/loginForm");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리 (7.7)
	//-----------------------------------------------------------------------------------------------------------
  	@Override
  	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		//로그인 한 정보를 가지고 데이터 베이스에 존재하는지 처리를 하고, 그 결과를 가져온다.
		memberVO = memberService.login(member);
		
		// 로그인 정보가 데이터베이스에 존재하는지에 따라 다르게 처리한다.
		if(memberVO != null) { // 로그인 정보에 해당하는 자료가 존재한다면,
			
			if(member.getPwd().equals(memberVO.getPwd())) {
			// 아이디와 비밀번호가 일치한다면 세션을 발급한다.
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			// mav.setViewName("redirect:/member/listMembers.do");
			mav.setViewName("redirect:/main.do"); // 메인 화면으로 이동(7.12)
		} else {
			rAttr.addAttribute("result", "PasswordFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
			
		} else {  // 로그인 정보에 해당하는 자료가 존재하지 않는다면
			// 로그인 실패 메시지를 가지고 로그인 화면으로 이동한다.
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그아웃 처리 (7.8)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("redirect:/member/loginForm.do");
		mav.setViewName("redirect:/main.do"); // 메인화면으로 가기(7.12)
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 화면(Ajax) (7.11)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/registerAjaxForm", method = RequestMethod.GET)
	public String getRegisterAjaxForm() throws Exception {
		
		System.out.println("MemberController 회원 가입 화면(Ajax) 요청");
		
		return "/member/registerAjax";
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디 중복 검사(Ajax) (7.11)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@ResponseBody
	@RequestMapping(value = "/idCheck", method=RequestMethod.POST)
	public int idCheck(MemberVO memberVO) throws Exception {
		
		System.out.println("MemberController 아이디 중복 검사(Ajax) id => " + memberVO.getId());
		
		int result = memberService.idCheck(memberVO);
		
		System.out.println("result : " + result);
		return result;
	}

	
} // End - public class MemberControllerImpl implements MemberController
