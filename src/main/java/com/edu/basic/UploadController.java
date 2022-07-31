package com.edu.basic;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//---------------------------------------------------------------------------------------------------
// 파일 올리기 컨트롤러
//---------------------------------------------------------------------------------------------------
// servlet-context.xml에 component-scan을 등록한다.
@Controller
public class UploadController {

	//---------------------------------------------------------------------------------------------------
	// servlet-context.xml에서 선언한 파일 올리기 설정을 참조한다.
	// 자바 11부터는 @Resource를 사용하기 위해서
	// http://mvnrepository.com/artifact/javax.annotation/javax.annotation-api 를 pom.xml에 추가하여야 한다.
	//---------------------------------------------------------------------------------------------------
	@Resource(name="uploadPath")
	// String uploadPath = "c:/data/upload"; <= 직접 기술해도 된다.
	String uploadPath; // 파일 경로를 공통으로 사용하기 위해서 선언한다.
	
	//---------------------------------------------------------------------------------------------------
	// 파일 올리기 화면으로 이동
	//---------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/upload/uploadForm", method = RequestMethod.GET)
	public String uploadForm() {
		// view/upload/uploadForm.jsp
		return "upload/uploadForm";
	}
	
	//---------------------------------------------------------------------------------------------------
	// 업로드된 내용을 처리하기
	// MultipartFile file : 업로드한 파일이 저장됨(임시경로)
	//---------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/upload/uploadForm", method = RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		
		System.out.println("파일 이름 : " + file.getOriginalFilename());
		System.out.println("파일 크기 : " + file.getSize());
		System.out.println("컨텐트 타입 : " + file.getContentType());
		
		String savedName = file.getOriginalFilename();
		savedName = uploadFile(savedName, file.getBytes());
		
		mav.setViewName("upload/uploadResult");  // 뷰의 이름 : 처리가 끝나고 돌아갈 페이지
		mav.addObject("savedName", savedName);   // 전달할 데이터
		return mav; // uploadResult.jsp로 포워딩된다.
	}
	
	//---------------------------------------------------------------------------------------------------
	// 파일 이름이 중복되지 않도록 처리한다.
	//---------------------------------------------------------------------------------------------------
	String uploadFile(String originalName, byte[] fileData) throws Exception {
		
		// Universal Unique IDentifier (범용 식별자) => 코드를 랜덤으로 만들어 낸다.
		UUID uuid = UUID.randomUUID();
		
		//1680901a-da8b-4025-b302-0b0029c1b793_KakaoTalk_20220512_145246763
		String savedName = uuid.toString() + "_" + originalName;
		
		// new File(디렉토리, 파일이름)
		File target = new File(uploadPath, savedName);
		
		// 첨부파일을 임시 디렉토리에서 우리가 지정한 디렉토리로 복사한다.
		FileCopyUtils.copy(fileData, target);
		return savedName; // 복사한 파일의 이름을 되돌려 준다.
	}
	
}//END - public class UploadController
