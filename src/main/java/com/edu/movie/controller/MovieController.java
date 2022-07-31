package com.edu.movie.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.movie.dto.SeatDTO;
import com.edu.movie.dto.SeatStatusDTO;
import com.edu.movie.service.MovieService;

//-----------------------------------------------------------------------------------------------------------
// 영화 예매 (7.22)
// MovieController에 @Controller 어노테이션이 설정되어 있더라도 스프링에서 해당 패키지를 스캔하지 않으면, 스프링 빈으로 등록되지 않음
// 그래서 servlet-context.xml에 Bean을 등록해야 한다.
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/movie")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Inject
	MovieService movieService;
	
	//-----------------------------------------------------------------------------------------------------------
	// 영화관 좌석 화면
	// @ModelAttribute("movieID")는 주소창에서 msg라는 파라미터 값을 가져온다.
	// http://localhost:8088/movie/seatReservation?movieID=1
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/seatReservation", method = RequestMethod.GET)
	public String seatReservation(@ModelAttribute("movieID") String mID, Model model) throws Exception {
		
		logger.info("MovieController seatReservation() GET ==> " + mID);
		mID = "1";  // movieID를 VIEW에서 입력해서 넘어오는 부분이 없어서 임시로 사용한다.
		
		int reserveOK = 0;
		int reserveNO = 0;
		int movieID = 0;
		
		if(mID != null) {
			movieID = Integer.parseInt(mID);
		}
		if(movieID < 0) {
			logger.info("MovieController seatReservation() GET) ==> 유효하지 않는 값입니다.");
		} else {
			List<SeatDTO> list = movieService.getSeatList(movieID);
			
			ArrayList<SeatStatusDTO> result = new ArrayList<SeatStatusDTO>();
			
			// 영화관 좌석수가 200개로 설정한다. 좌석수가 변동이 되면 변수를 사용해야 한다.
			// 좌석예약 테이블(reservation_seat)에 값이 있으면 true, 아니면 false로 만든다.
			for(int i = 1; i <= 200; i++) {
				boolean findSeatID = false;
				for(int j = 0; j < list.size(); j++) {
					if(i == list.get(j).getSeatID()) {
						findSeatID = true;
						reserveOK++;
					}
				}
				if(findSeatID) result.add(new SeatStatusDTO(i, true));
				else result.add(new SeatStatusDTO(i, false));
			}
			// 예약이 안된 자릿수
			reserveNO = 200 - reserveOK;
			
			model.addAttribute("SeatList", result);  // 좌석 목록
			model.addAttribute("reserveOK", reserveOK); // 예약된 좌석 수
			model.addAttribute("reserveNO", reserveNO);  // 남아있는 좌석 수 
			
			logger.info("result =>" + result);
		}
		
		return "/movie/seatReservation";
		
	} // end - public String seatReservation(@ModelAttribute("movieID") String mID, Model model)
	
	//-----------------------------------------------------------------------------------------------------------
	// 좌석 예약 처리(7.25)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/seatReservation", method = RequestMethod.POST)
	@ResponseBody
	public int seatReservationOK(@RequestParam int seatID, Model model) throws Exception {
		System.out.println("11111111");
		logger.info("MovieController seatReservationOK(@RequestParam int seatID, Model model)");
		return movieService.insertSeatID(seatID);
		
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 좌석 예약 취소 처리(7.28)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/seatReservationCancel", method = RequestMethod.POST)
	@ResponseBody
	public int seatReservationCancel(@RequestParam int seatID, Model model) throws Exception {
		
		logger.info("MovieController seatReservationCancel(@RequestParam int seatID, Model model)");
		return movieService.cancelSeatID(seatID);
	}
	
} // end - public class MovieController
