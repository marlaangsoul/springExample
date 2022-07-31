package com.edu.movie.service;


import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.movie.dao.MovieDAO;
import com.edu.movie.dto.SeatDTO;

//--------------------------------------------------------------------------------------------------------
// public class MovieServiceImpl
//--------------------------------------------------------------------------------------------------------
@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
	
	@Inject
	MovieDAO movieDAO;
	
	//--------------------------------------------------------------------------------------------------------
	// 좌석 목록 가져오기(7.25)
	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<SeatDTO> getSeatList(int movieID) throws Exception {
		
		logger.info("MovieServiceImpl getSeatList(int movieID)");
		return movieDAO.getSeatList(movieID);
		
		
	} // end - public List<SeatDTO> getSeatList(int movieID)

	//--------------------------------------------------------------------------------------------------------
	// 좌석 예약 처리
	//--------------------------------------------------------------------------------------------------------
	@Override
	public int insertSeatID(int seatID) throws Exception {
		logger.info("MovieServiceImpl insertSeatID(int seatID)");
		return movieDAO.insertSeatID(seatID);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 좌석 예약 취소 처리(7.28)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int cancelSeatID(int seatID) throws Exception {
		logger.info("MovieServiceImpl cancelSeatID(int seatID)");
		return movieDAO.cancelSeatID(seatID);
	}

	
} // end - public class MovieServiceImpl
