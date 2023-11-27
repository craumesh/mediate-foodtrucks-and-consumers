package com.fooding.ft.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.fooding.ft.db.FTDAO;
import com.fooding.ft.db.FTDTO;


public class FTDAO {

	// 공통 변수
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 공통 메서드 (기능)
	/////////////////////////////// 디비 연결 메서드 ///////////////////////////////
	private Connection getCon() throws Exception {
		
		// 프로젝트 정보 확인 (JNDI)
		Context initCTX = new InitialContext();
		// 프로젝트 간에 작성된 디비 연결정보(context.xml) 불러오기.
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc");
		// 디비 연결 수행
		con = ds.getConnection();
				
		return con;
	}
	/////////////////////////////// 디비 연결 메서드 ///////////////////////////////
	
	
	/////////////////////////////// 디비 연결(자원) 해제 메서드 ///////////////////////////////
	public void CloseDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	///////////////////////////////디비 연결(자원) 해제 메서드///////////////////////////////
	
	// 메인 페이지에서 조회 할 푸드트럭 정보 메서드
	public ArrayList getMainInfoFoodTrucks() {

	    ArrayList FtMainInfo = new ArrayList<>();

	    try {
	        // 1. 2. 디비 연결
	        con = getCon();
	        // 3. sql 작성(select) & pstmt 객체
	        String sql = "SELECT name as 'ftName', image as 'FImage', foodtruck_id "
	        		   + "FROM c7d2307t2.foodtruck "
	        		   + "WHERE activation = 'Y'";
	        pstmt = con.prepareStatement(sql);

	        // 4. sql 실행
	        rs = pstmt.executeQuery();

	        // 5. 데이터 처리
	        while (rs.next()) {
	        	FTDTO fdto = new FTDTO();
	        	
	        	fdto.setFtName(rs.getString("ftName"));
	        	fdto.setFImage(rs.getString("FImage"));
	        	fdto.setFoodtruck_id(rs.getInt("foodtruck_id"));

	            FtMainInfo.add(fdto); // FTDTO 객체 리스트에 추가
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }

	    return FtMainInfo;
	}
	// 메인 페이지에서 조회 할 푸드트럭 정보 메서드
	
	
	// 푸드트럭 페이지에서 푸드트럭 정보를 조회하는 메서드
	public ArrayList getInfoFoodTrucks() {

	    ArrayList FtInfo = new ArrayList<>();

	    try {
	        // 1. 2. 디비 연결
	        con = getCon();
	        // 3. sql 작성(select) & pstmt 객체
	        String sql = "SELECT name, image, foodtruck_id, info "
	        		 + "FROM c7d2307t2.foodtruck "
	        		 + "WHERE activation = 'Y'";
	        pstmt = con.prepareStatement(sql);

	        // 4. sql 실행
	        rs = pstmt.executeQuery();

	        // 5. 데이터 처리
	        while (rs.next()) {
	        	FTDTO fdto = new FTDTO();
	        	
	        	fdto.setFoodtruck_id(rs.getInt("foodtruck_id"));
	        	fdto.setFtName(rs.getString("name"));
	        	fdto.setFImage(rs.getString("image"));
	        	fdto.setFtInfo(rs.getString("info"));


	            FtInfo.add(fdto);  // FTDTO 객체를 리스트에 추가
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }

	    return FtInfo;
	}
	// 푸드트럭 페이지에서 푸드트럭 정보를 조회하는 메서드

	
	// 푸드트럭 개수 계산 메서드 - getFtCount()
	public int getFtCount() {
		int result = 0;

		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();

			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) "
					+ "from c7d2307t2.foodtruck "
					+ "WHERE activation = 'Y'";
			pstmt = con.prepareStatement(sql);

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 - 개수를 저장
			if (rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return result;
	}
	// 푸드트럭 개수 계산 메서드 - getFtCount()
	
	
	// 푸드트럭 개수 계산 메서드 (검색) - getFtCount(String search)
	public int getFtCount(String search) {
		int result = 0;
		
		try {
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			
			// 3. sql 작성(select) & pstmt 객체
			sql = "select count(*) from c7d2307t2.foodtruck "
					+ "where name like ? "
					+ "AND activation = 'Y'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 - 개수를 저장
			if (rs.next()) {
				result = rs.getInt(1);
				// result = rs.getInt("count(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// 푸드트럭 개수 계산 메서드 (검색) - getFtCount(String search)
	
	
	// 푸드트럭 정보 목록을 가져오는 메서드 - getFtList(int startRow, int pageSize)
	public ArrayList getFtList(int startRow, int pageSize) {
		// 글정보를 저장하는 배열
		ArrayList ftList = new ArrayList();
		try {
			// 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			// 3. SQL 작성(select) & pstmt 객체
			sql = "SELECT foodtruck_id, name, image, info "
					+ "FROM c7d2307t2.foodtruck "
					+ "AND activation = 'Y' "
					+ "LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			// ????
			pstmt.setInt(1, startRow - 1); // 시작행번호-1
			pstmt.setInt(2, pageSize); // 개수
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// BoardBean 객체 여러개 => ArrayList 저장
			while (rs.next()) {
				// 글 하나의 정보 => BoardBean저장
				FTDTO fdto = new FTDTO();

				fdto.setFoodtruck_id(rs.getInt("foodtruck_id"));
				fdto.setFtName(rs.getString("name"));
				fdto.setFImage(rs.getString("image"));
				fdto.setFtInfo(rs.getString("info"));

				// 글 하나의 정보를 배열의 한칸에 저장
				ftList.add(fdto);

			} // while

			System.out.println(" DAO : 게시판 글 목록 조회성공! ");
			System.out.println(" DAO : " + ftList.size());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return ftList;
	}
	// 푸드트럭 정보 목록을 가져오는 메서드 - getFtList(int startRow, int pageSize)
	
	
	// 푸드트럭 정보 목록을 가져오는 메서드 (검색) - getFtList(String search)
	public ArrayList getFtList(String search) {
		// 글정보를 저장하는 배열
		ArrayList ftList = new ArrayList();
		try {
			// 디비연결정보
			// 1. 드라이버 로드
			// 2. 디비 연결
			con = getCon();
			// 3. SQL 작성(select) & pstmt 객체
			sql = "SELECT * "
					+ "FROM c7d2307t2.foodtruck "
					+ "WHERE name LIKE ? "
					+ "AND activation = 'Y'";			
			pstmt = con.prepareStatement(sql);
			// ????
			pstmt.setString(1, "%"+search+"%"); // %검색어%
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			// 글정보 전부 가져오기
			// BoardBean 객체 여러개 => ArrayList 저장
			while (rs.next()) {
				// 글 하나의 정보 => BoardBean저장
				FTDTO fdto = new FTDTO();
				
				fdto.setFoodtruck_id(rs.getInt("foodtruck_id"));
				fdto.setFtName(rs.getString("name"));
				fdto.setFImage(rs.getString("image"));
				fdto.setFtInfo(rs.getString("info"));

				// 글 하나의 정보를 배열의 한칸에 저장
				ftList.add(fdto);
				
			} // while
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return ftList;
	}
	// 푸드트럭 정보 목록을 가져오는 메서드 (검색) - getFtList(String search)
}
