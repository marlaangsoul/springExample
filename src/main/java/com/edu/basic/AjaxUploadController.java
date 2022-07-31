package com.edu.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.util.MediaUtils;
import com.edu.util.UploadFileUtils;

//----------------------------------------------------------------------------------------------
// AJAX를 이용하여 파일 올리기 (7.20)
//----------------------------------------------------------------------------------------------
@Controller
public class AjaxUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);

	//업로드 할 경로(디렉토리)는 servlet-context.xml에 설정해 놓았음.
	@Resource(name = "uploadPath")
	String uploadPath;
	
	//----------------------------------------------------------------------------------------------
	// 파일 올리기(ajax) 화면 불러오기
	//----------------------------------------------------------------------------------------------
	@RequestMapping(value="/upload/uploadAjax", method = RequestMethod.GET)
	public String uploadAjax() {
		return "/upload/uploadAjax";
	}
	
	//----------------------------------------------------------------------------------------------
	// 파일을 드래그해서 업로드했을 경우 진행되는 메서드(ajax)
	// 업로드한 파일은 MultipartFile 변수에 저장된다. (7.22)
	// @ResponseBody : jsp로 넘어가는 것이 아니라 데이터 자체를 되돌려 주는 것이다.
	//----------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/upload/uploadAjax", method = RequestMethod.POST, 
					produces = "text/plain;charset=UTF-8")
	// ResponseEntity<String> ==> 메시지와 에러코드를 같이 돌려준다.
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
	
		// 파일 정보를 로그에 출력한다.
		logger.info("getOriginalFilename : " + file.getOriginalFilename());
		logger.info("getSize : " + file.getSize());
		logger.info("getContentType: " + file.getContentType());
		
		// 파일 정보를 출력한다.
		//System.out.println("getOriginalFilename : " + file.getOriginalFilename());
		//System.out.println("getSize : " + file.getSize());
		//System.out.println("getContentType: " + file.getContentType());
		
		// 업로드한 파일 정보와 Http 상태 코드를 함께 리턴
		//new ResponseEntity(데이터, 상태코드)
		//new ResponseEntity(업로드된 파일이름, 상태코드)
		//file.getBytes() : 파일을 Byte로 풀어서 올린다.
		//HttpStatus.OK : 파일이 올라가면 OK메시지가 떨어진다.
		
		return new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
				
	}
	
	//----------------------------------------------------------------------------------------------
	// 이미지 표시 기능을 하게 하는 메서드 (7.22)
	//----------------------------------------------------------------------------------------------
	@ResponseBody // view가 아닌 data를 리턴한다.
	@RequestMapping(value = "/upload/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		// 서버의 파일을 다운로드 하기 위한 스트림
		InputStream in = null;  // java.io
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 넘겨받은 파일이름에서 확장자를 검사한다.
			// 매개변수가 1개인 경우 : substring(10) => 매개변수 위치부터 뒤의 모든 것을 말한다.
			// 매개변수가 2개인 경우 : substring(1, 5) => 첫번째 매개변수 위치부터 두번째 매개변수 바로 전까지의 데이터를 말한다.
			String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(extensionName);
			
			// 헤더 구성 객체
			HttpHeaders headers = new HttpHeaders();
			
			// InputStream 생성
			in = new FileInputStream(uploadPath + fileName);
			
			// 이미지 파일인지 아닌지에 따라서 처리를 다르게 한다.
			if(mType != null) { // 이미지 파일이라면
				headers.setContentType(mType);
			} else { // 이미지 파일이 아닌 경우
				fileName = fileName.substring(fileName.indexOf("-") + 1);
				
				// 다운로드용 컨텐트 타입
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 컨텐트 타입
				
				// 파일이름에 한글이 들어간 경우 스트링.getBytes("언어셑") 스트링을 바이트 배열로 변환
				// new String(바이트 배열, "언어셑") 문자열의 인코딩 변경
				
				// 큰 따옴표 내부에 " \" "
				// 바이트배열을 스트링으로
				// iso-8859-1 서유럽 언어
				// new String(fileName.getBytes("utf-8"), "iso-8859-1");
				
				// 아래의 2줄과 동일하다.
				// headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + "\"");
				
				// fileName을 utf-8로 읽어서 서유럽언어 형식으로 바꾸어서 넘겨준다.
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + "\"");
				// == headers.add("Content-Disposition", "attachment; filename='" + fileName + "'");
			}
			
			// 바이트 배열, 헤더
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(in != null)
				in.close(); // 사용한 스트림 닫기
		}
		
		
		return entity;
		
	} // END - public ResponseEntity<byte[]> displayFile(String fileName)
	
	//----------------------------------------------------------------------------------------------
	// 파일 삭제하기 (7.22)
	//----------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value = "/upload/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		
		logger.info("fileName : " + fileName);
		
		// 확장자 검사
		// fileName에는 이미지 파일의 경우 썸네일 파일 이름이 넘어온다.
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(extensionName);
		
		///2022/07/22/4bdd0bfa-9de1-4276-aa7f-3a41716fcdf2_book.jpg
		// 이미지 파일이면 썸네일 이미지를 삭제한다
		if(mType != null) { // 이미지 파일이면
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			
			// File.separatorChar : 유닉스 / 윈도우즈 \
			// 썸네일 삭제
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}
		
		// 원본 파일을 삭제한다.
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
}// END - public class AjaxUploadController
