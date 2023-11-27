package com.fooding.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * MemberDAO : DB관련 처리를 모두 수행하는 객체 (Data Access Object)
 *
 */
	
public class MemberDAO {

	// 공통변수
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";
		
	/////////////////////////// 디비 연결 메서드////////////////////////////////
	private Connection getCon() throws Exception {
		/*
		* // 디비연결정보 final String DRIVER = "com.mysql.cj.jdbc.Driver"; final String
		* DBURL = "jdbc:mysql://localhost:3306/jspdb"; final String DBID = "root";
		* final String DBPW = "1234";
		* 
		* // 1. 드라이버 로드 Class.forName(DRIVER);
		* System.out.println(" DAO : 드라이버 로드 성공!"); // 2. 디비 연결 con =
		* DriverManager.getConnection(DBURL, DBID, DBPW);
		* System.out.println(" DAO : 디비 연결 성공! "); System.out.println(" DAO : " + con);
		*/
		
		// Connection Pool을 사용한 디비 연결
		
		// 프로젝트의 정보를 확인(JNDI)
		Context initCTX = new InitialContext();
		// 프로젝트안에 작성된 디비 연결정보(context.xml)를 불러오기
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc");
		// 디비 연결 수행
		con = ds.getConnection();
		System.out.println(" DAO : 디비연결 성공! (커넥션풀) ");
		System.out.println(" DAO : "+con);		
		
		return con;
	}
		/////////////////////////// 디비 연결 메서드////////////////////////////////
		
		/////////////////////////// 디비 연결(자원) 해제 메서드////////////////////////
		public void CloseDB() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
		
				System.out.println(" DAO : 디비 자원해제 완료! ");			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/////////////////////////// 디비 연결(자원) 해제 메서드/////////////////////////
	

	// 로그인 체크 메서드 - loginMember(dto)
	public int loginMember(MemberDTO dto) {
		int result = -1; // -1 0 1
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select pw from member where id = ? AND activation = 'Y'";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {// 회원정보가 있음
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 비밀번호 동일
					result = 1;
				}else {
					// 비밀번호 다름
					result = 0;
				}
				
			}else {// 회원정보 없음
				result = -1;
			}

			System.out.println(" DAO : 로그인 처리 완료 ("+result+")");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return result;
	}
	// 로그인 체크 메서드 - loginMember(dto)	

	// 회원가입 메서드 - insertMember(dto)
	public void insertMember(MemberDTO dto) {
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql 작성 & pstmt 객체
			sql = "insert into member (id,pw,name,phone,email,regdate) "
					+ "values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setTimestamp(6, dto.getRegdate());
			//4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 회원가입 성공! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// 회원가입 메서드 - insertMember(dto)
	
	
	
	
	// id 중복확인 -----------------------------------------
	public List<MemberDTO> memberList() {
		
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		try {
			// 1+2 디비연결
			con = getCon();
			
			// 3 sql & pstmt
			sql = "select * from member";
			pstmt = con.prepareStatement(sql);
			// ???
			
			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5 데이터 처리
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				
				memberList.add(dto);
			}
			
			System.out.println("DAO : 회원리스트 저장완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	return memberList;
	
	}// id 중복확인

	
	// id 찾기 -----------------------------------------
	
	public String idFindMember(MemberDTO dto) {
		
		String id = null;
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select id from member where name = ? and email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			// 4. sql 실행
			rs = pstmt.executeQuery();

			
			// 5. 데이터 처리
			if(rs.next()) { // 회원정보가 있음
				System.out.println("DAO : 회원정보 있음");
				id = rs.getString("id");
			}
			else {// 회원정보 없음
				System.out.println("DAO : 회원정보 없음");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return id;
	}
	
	// id 찾기 -----------------------------------------
	
	
	// pw 찾기 -----------------------------------------
	
	public int pwFindMember(MemberDTO dto) {
		int result = -1; // -1 0 1
		
		try {
			// 1.2. 디비연결
		con = getCon();
		// 3. sql 작성 & pstmt 객체
		sql = "select pw from member where id = ? and email = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getEmail());
		// 4. sql 실행
		rs = pstmt.executeQuery();
		// 5. 데이터 처리
		if (rs.next()) { // 회원 정보가 있음
			System.out.println("DAO : 비밀번호 있음");
			result = 1; // 비밀번호 있음
		} 
		
		else {
			System.out.println("DAO : 비밀번호 없음");
		    result = -1; // 비밀번호 없음
		}
		System.out.println(" DAO : 비밀번호 확인 완료 ("+result+")");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return result;
		}
		
	// pw 찾기 -----------------------------------------
	
	// 임시 비밀번호 DB에 저장 -------------------------------
	
	public boolean saveTemporaryPassword(MemberDTO dto, String temporaryPassword) {
	    boolean success = false;
	
	    try {
	        con = getCon(); // getCon() 메서드로 데이터베이스 연결 가져오기
	
	        // SQL 쿼리 작성
	         sql = "update member set pw = ? where email = ?";
	        pstmt = con.prepareStatement(sql);
	
	        // 임시 비밀번호를 데이터베이스에 저장
	        pstmt.setString(1, temporaryPassword);
	        pstmt.setString(2, dto.getEmail());
	
	        int rowsUpdated = pstmt.executeUpdate();
	
	        if (rowsUpdated > 0) {
	            success = true;
	        }
	        System.out.println(" DAO :  ("+rowsUpdated+")");
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	CloseDB();
	    }
	
	    return success;
	}
	// 임시 비밀번호 DB에 저장 -------------------------------
	
}