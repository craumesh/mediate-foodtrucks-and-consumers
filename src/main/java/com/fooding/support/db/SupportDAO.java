package com.fooding.support.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.collections4.Get;

public class SupportDAO {

	// 공통 변수 선언
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";

		// DB 연결 메서드 (getCon)
		private Connection getCon() throws Exception {

			// 커넥션 풀 이용
			Context initCTX = new InitialContext();
			DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
			con = ds.getConnection();
			return con;
		}
		// DB 연결 메서드

		// DB 연결 해제 메서드 (closeDB)
		public void closeDB() {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// DB 연결 해제 메서드
		
		// 공지사항 개수 메서드 (supportNoticeCount() 매개변수 X, 리턴타입 int)
		public int supportNoticeCount() {
			int count = 0;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT COUNT(*) FROM admintable WHERE class = '공지'";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가 [없음]
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				System.out.println("\t\t DAO : supportNoticeCount() 호출 성공 ");		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return count;
		}
		// 공지사항 개수 메서드 (supportNoticeCount() 매개변수 X, 리턴타입 int)
		
		// 공지사항 목록 메서드 (supportNoticeList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
		public ArrayList supportNoticeList(int startRow, int pageSize) {
			ArrayList list = new ArrayList();
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT * FROM admintable WHERE class = '공지' ORDER BY bno DESC LIMIT ?, ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, startRow - 1);
				pstmt.setInt(2, pageSize);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()) {
					SupportDTO dto = new SupportDTO();
					dto.setTable_id(rs.getInt("table_id"));
					dto.setBno(rs.getInt("bno"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getDate("date"));
					list.add(dto);
				}
				System.out.println("\t\t DAO : supportNoticeList() 호출 성공 ");			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return list;
		}
		// 공지사항 목록 메서드 (supportNoticeList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
		
		// FAQ 개수 메서드 (supportFaqCount() 매개변수 X, 리턴타입 int)
		public int supportFaqCount() {
			int count = 0;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT COUNT(*) FROM admintable WHERE class = 'FAQ'";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가 [없음]
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				System.out.println("\t\t DAO : supportFaqCount() 호출 성공 ");		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return count;
		}
		// FAQ 개수 메서드 (supportFaqCount() 매개변수 X, 리턴타입 int)
		
		// FAQ 목록 메서드 (supportFaqList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
		public ArrayList supportFaqList(int startRow, int pageSize) {
			ArrayList list = new ArrayList();
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT * FROM admintable WHERE class = 'FAQ' ORDER BY bno DESC LIMIT ?, ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, startRow - 1);
				pstmt.setInt(2, pageSize);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()) {
					SupportDTO dto = new SupportDTO();
					dto.setTable_id(rs.getInt("table_id"));
					dto.setBno(rs.getInt("bno"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getDate("date"));
					list.add(dto);
				}
				System.out.println("\t\t DAO : supportFaqList() 호출 성공 ");			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return list;
		}
		// FAQ 목록 메서드 (supportFaqList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
		
		// FAQ 작성 메서드 (supportFaqInsert() 매개변수 dto, 리턴타입 void)
		public void supportFaqInsert(SupportDTO dto) {
			int bno = 0;
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT MAX(bno) FROM admintable WHERE class = 'FAQ'";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가 [없음]
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					bno = rs.getInt(1) + 1;
					
					// 3. SQL 구문 작성 및 pstmt 객체 생성
					sql = "INSERT INTO admintable (bno, title, content, date, class) "
							+ "VALUES (?, ?, ?, NOW(), 'FAQ')";
					pstmt = con.prepareStatement(sql);
					
					// ? 정보 추가
					pstmt.setInt(1, bno);
					pstmt.setString(2, dto.getTitle());
					pstmt.setString(3, dto.getContent());
					
					// 4. SQL 구문 실행
					pstmt.executeUpdate();
				}
				System.out.println("\t\t DAO : supportFaqInsert() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		// FAQ 작성 메서드 (supportFaqInsert() 매개변수 dto, 리턴타입 void)
		
		// FAQ 작성 메서드 (supportNoticeInsert() 매개변수 dto, 리턴타입 void)
		public void supportNoticeInsert(SupportDTO dto) {
			int bno = 0;
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT MAX(bno) FROM admintable WHERE class = '공지'";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가 [없음]
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					bno = rs.getInt(1) + 1;
					
					// 3. SQL 구문 작성 및 pstmt 객체 생성
					sql = "INSERT INTO admintable (bno, title, content, date, class) "
							+ "VALUES (?, ?, ?, NOW(), '공지')";
					pstmt = con.prepareStatement(sql);
					
					// ? 정보 추가
					pstmt.setInt(1, bno);
					pstmt.setString(2, dto.getTitle());
					pstmt.setString(3, dto.getContent());
					
					// 4. SQL 구문 실행
					pstmt.executeUpdate();
				}
				System.out.println("\t\t DAO : supportNoticeInsert() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		// 공지사항 작성 메서드 (supportNoticeInsert() 매개변수 dto, 리턴타입 void)
		
		// 공지사항 출력 메서드 (supportNoticeContent() 매개변수 table_id, 리턴타입 dto)
		public SupportDTO supportNoticeContent(int table_id) {
			SupportDTO dto = null;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성
				sql = "SELECT * FROM admintable WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, table_id);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					dto = new SupportDTO();
					dto.setTable_id(table_id);
					dto.setBno(rs.getInt("bno"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getDate("date"));
					dto.setClassify(rs.getString("class"));
				}
				System.out.println("\t\t DAO : supportNoticeContent() 호출 성공 ");					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return dto;
		}
		// 공지사항 출력 메서드 (supportNoticeContent() 매개변수 table_id, 리턴타입 dto)

		// FAQ 출력 메서드 (supportFaqContent() 매개변수 table_id, 리턴타입 dto)
		public SupportDTO supportFaqContent(int table_id) {
			SupportDTO dto = null;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성
				sql = "SELECT * FROM admintable WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, table_id);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					dto = new SupportDTO();
					dto.setTable_id(table_id);
					dto.setBno(rs.getInt("bno"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getDate("date"));
					dto.setClassify(rs.getString("class"));
				}
				System.out.println("\t\t DAO : supportFaqContent() 호출 성공 ");					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return dto;
		}
		// FAQ 출력 메서드 (supportFaqContent() 매개변수 table_id, 리턴타입 dto)
		
		// 공지사항 수정 메서드 (supportNoticeUpdate() 매개변수 dto, 리턴타입 int)
		public int supportNoticeUpdate(SupportDTO dto) {
			int result = 0;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "UPDATE admintable SET title = ?, content = ?, updatedate = NOW() WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getTable_id());
				
				// 4. SQL 구문 실행
				result = pstmt.executeUpdate();
				System.out.println("\t\t DAO : supportNoticeUpdate() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return result;
		}
		// 공지사항 수정 메서드 (supportNoticeUpdate() 매개변수 dto, 리턴타입 int)
		
		// FAQ 수정 메서드 (supportFaqUpdate() 매개변수 dto, 리턴타입 int)
		public int supportFaqUpdate(SupportDTO dto) {
			int result = 0;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "UPDATE admintable SET title = ?, content = ?, updatedate = NOW() WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getTable_id());
				
				// 4. SQL 구문 실행
				result = pstmt.executeUpdate();
				System.out.println("\t\t DAO : supportFaqUpdate() 호출 성공 ");					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return result;
		}
		// FAQ 수정 메서드 (supportFaqUpdate() 매개변수 dto, 리턴타입 int)
		
		// 공지사항 삭제 메서드 (supportNoticeDelete() 매개변수 table_id, 리턴타입 int)
		public int supportNoticeDelete(int table_id) {
			int result = -1;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "DELETE FROM admintable WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, table_id);
				
				// 4. SQL 구문 실행
				result = pstmt.executeUpdate();
				
				System.out.println("\t\t DAO : supportNoticeDelete() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;
		}
		
		// 공지사항 삭제 메서드 (supportNoticeDelete() 매개변수 table_id, 리턴타입 int)
		
		// FAQ 삭제 메서드 (supportFaqDelete() 매개변수 table_id, 리턴타입 int)
		public int supportFaqDelete(int table_id) {
			int result = -1;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "DELETE FROM admintable WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, table_id);
				
				// 4. SQL 구문 실행
				result = pstmt.executeUpdate();
				
				System.out.println("\t\t DAO : supportFaqDelete() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;
		}
		
		// FAQ 삭제 메서드 (supportFaqDelete() 매개변수 table_id, 리턴타입 int)
		
		// 이벤트 개수 메서드 (supportEventCount() 매개변수 X, 리턴타입 int)
		public int supportEventCount() {
			int count = 0;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT COUNT(*) FROM admintable WHERE class = '이벤트'";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가 [없음]
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				System.out.println("\t\t DAO : supportEventCount() 호출 성공 ");		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return count;
		}
		// 이벤트 개수 메서드 (supportEventCount() 매개변수 X, 리턴타입 int)
		
		// 이벤트 목록 메서드 (supportEventList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
		public ArrayList supportEventList(int startRow, int pageSize) {
			ArrayList list = new ArrayList();
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT * FROM admintable WHERE class = '이벤트' ORDER BY bno DESC LIMIT ?, ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, startRow - 1);
				pstmt.setInt(2, pageSize);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()) {
					SupportDTO dto = new SupportDTO();
					dto.setTable_id(rs.getInt("table_id"));
					dto.setBno(rs.getInt("bno"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getDate("date"));
					list.add(dto);
				}
				System.out.println("\t\t DAO : supportEventList() 호출 성공 ");			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return list;
		}
		// 이벤트 목록 메서드 (supportEventList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
		
		// 이벤트 작성 메서드 (supportEventInsert() 매개변수 dto, 리턴타입 void)
		public void supportEventInsert(SupportDTO dto) {
			int bno = 0;
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT MAX(bno) FROM admintable WHERE class = '이벤트'";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가 [없음]
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					bno = rs.getInt(1) + 1;
					
					// 3. SQL 구문 작성 및 pstmt 객체 생성
					sql = "INSERT INTO admintable (bno, title, content, date, class) "
							+ "VALUES (?, ?, ?, NOW(), '이벤트')";
					pstmt = con.prepareStatement(sql);
					
					// ? 정보 추가
					pstmt.setInt(1, bno);
					pstmt.setString(2, dto.getTitle());
					pstmt.setString(3, dto.getContent());
					
					// 4. SQL 구문 실행
					pstmt.executeUpdate();
				}
				System.out.println("\t\t DAO : supportEventInsert() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		// 이벤트 작성 메서드 (supportEventInsert() 매개변수 dto, 리턴타입 void)
		
		// 이벤트 출력 메서드 (supportEventContent() 매개변수 table_id, 리턴타입 dto)
		public SupportDTO supportEventContent(int table_id) {
			SupportDTO dto = null;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성
				sql = "SELECT * FROM admintable WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, table_id);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					dto = new SupportDTO();
					dto.setTable_id(table_id);
					dto.setBno(rs.getInt("bno"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setDate(rs.getDate("date"));
					dto.setClassify(rs.getString("class"));
				}
				System.out.println("\t\t DAO : supportEventContent() 호출 성공 ");					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return dto;
		}
		// 이벤트 출력 메서드 (supportEventContent() 매개변수 table_id, 리턴타입 dto)
		
		// 이벤트 수정 메서드 (supportEventUpdate() 매개변수 dto, 리턴타입 int)
		public int supportEventUpdate(SupportDTO dto) {
			int result = 0;
					
			try {
				// 1. 2. DB 연결
				con = getCon();
						
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "UPDATE admintable SET title = ?, content = ?, updatedate = NOW() WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
						
				// ? 정보 추가
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getTable_id());
						
				// 4. SQL 구문 실행
				result = pstmt.executeUpdate();
				System.out.println("\t\t DAO : supportEventUpdate() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return result;
		}
		// 이벤트 수정 메서드 (supportEventUpdate() 매개변수 dto, 리턴타입 int)
		
		// 이벤트 삭제 메서드 (supportEventDelete() 매개변수 table_id, 리턴타입 int)
		public int supportEventDelete(int table_id) {
			int result = -1;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "DELETE FROM admintable WHERE table_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, table_id);
				
				// 4. SQL 구문 실행
				result = pstmt.executeUpdate();
				
				System.out.println("\t\t DAO : supportEventDelete() 호출 성공 ");	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;
		}
		
		// 이벤트 삭제 메서드 (supportEventDelete() 매개변수 table_id, 리턴타입 int)
}