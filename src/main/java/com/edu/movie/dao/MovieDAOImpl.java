package com.edu.movie.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.movie.dto.SeatDTO;


//--------------------------------------------------------------------------------------------------------
// public class MovieDAOImpl
//--------------------------------------------------------------------------------------------------------
@Repository
public class MovieDAOImpl implements MovieDAO {

	private static final Logger logger = LoggerFactory.getLogger(MovieDAOImpl.class);
	
	@Inject  // 의존관계 주입 (Defendencty Injection, DI)
	SqlSession sqlSession;
	
	// Namespace
	private static String namespace = "com.edu.movie.mappers.movieMapper";
	
	//--------------------------------------------------------------------------------------------------------
	// 좌석 목록 가져오기(7.25)
	//--------------------------------------------------------------------------------------------------------
	@Override
	public List<SeatDTO> getSeatList(int movieID) throws Exception {
		
		logger.info("MovieDAOImpl getSeatList(int movieID)");
		List<SeatDTO> seatList = sqlSession.selectList(namespace + ".seatList", movieID);
		logger.info(seatList.toString());
		return seatList;
	} // end - public List<SeatDTO> getSeatList(int movieID)


	//--------------------------------------------------------------------------------------------------------
	// 좌석 예약 처리
	//--------------------------------------------------------------------------------------------------------
	@Override
	public int insertSeatID(int seatID) throws Exception {
		logger.info("MovieDAOImpl insertSeatID(int seatID)");
		return sqlSession.insert(namespace + ".insertSeatID", seatID);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 좌석 예약 취소 처리(7.28)
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int cancelSeatID(int seatID) throws Exception {
		logger.info("MovieDAOImpl cancelSeatID(int seatID)");
		return sqlSession.delete(namespace + ".cancelSeatID", seatID);
	}
	
	

} // end - public class MovieDAOImpl
