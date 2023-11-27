package com.fooding.profile.review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProfileReviewDAO {

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

	// 주문번호로 구매 상품 목록 받아오는 메서드 (profileReviewProductList() 매개변수 purchaseid 리턴타입 ArrayList)
	public ArrayList profileReviewProductList(int purchaseid) {
		ArrayList list = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT p.product_id, d.name, f.name, date "
					+ "FROM purchase p "
					+ "JOIN product d "
					+ "ON p.product_id = d.product_id "
					+ "JOIN foodtruck f "
					+ "ON d.foodtruck_id = f.foodtruck_id "
					+ "WHERE purchaseid = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, purchaseid);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				ProfileReviewDTO dto = new ProfileReviewDTO();
				dto.setProduct_id(rs.getInt("p.product_id"));
				dto.setName(rs.getString("d.name"));
				dto.setFoodtruck_name(rs.getString("f.name"));
				dto.setPdate(rs.getDate("date"));
				list.add(dto);
			}
			System.out.println("\t\t DAO : profileReviewProductList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	// 주문번호로 구매 상품 목록 받아오는 메서드 (profileReviewProductList() 매개변수 purchaseid 리턴타입 ArrayList)
	
	// 리뷰가 있는지 확인해주는 메서드 (profileHasReview() 매개변수 purchaseid, 리턴타입 int)
	public int profileHasReview(int purchaseid) {
		int result = 0;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT * FROM review WHERE purchaseid = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, purchaseid);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
			System.out.println("\t\t DAO : result - " + result);
			System.out.println("\t\t DAO : profileHasReview() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 리뷰가 있는지 확인해주는 메서드 (profileHasReview() 매개변수 purchaseid, 리턴타입 int)
	
	// 리뷰 입력 메서드 (profileInsertReview() 매개변수 purchaseid,, dto 리턴타입 void)
	public void profileInsertReview(int purchaseid, ProfileReviewDTO dto) {	
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT member_id, purchaseid FROM purchase WHERE purchaseid = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, purchaseid);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				dto.setMember_id(rs.getInt(1));
				dto.setPurchaseid(rs.getInt(2));
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "INSERT INTO review (member_id, purchaseid, nickname, title, score, content, pw, date, image) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?)";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, dto.getMember_id());
				pstmt.setInt(2, purchaseid);
				pstmt.setString(3, dto.getNickname());
				pstmt.setString(4, dto.getTitle());
				pstmt.setString(5, dto.getScore());
				pstmt.setString(6, dto.getContent());
				pstmt.setString(7, dto.getPw());
				pstmt.setString(8, dto.getImage());
				
				// 4. SQL 구문 실행
				pstmt.executeUpdate();
			}
			System.out.println("\t\t DAO : profileInsertReview() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// 리뷰 입력 메서드 (profileInsertReview() 매개변수 purchaseid 리턴타입 dto)
	
	// 리뷰 개수 조회 메서드 (profileReviewCount() 매개변수 X , 리턴타입 int) [전체 리뷰 개수]
		public int profileReviewCount() {
			int count = 0;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
					
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT COUNT(*) FROM review";
				pstmt = con.prepareStatement(sql);
					
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
					
				// 5. 데이터 처리
				if(rs.next()) {
					count = rs.getInt(1);
				}
					
				System.out.println("\t\t DAO : profileReviewCount() 호출 성공 ");
				System.out.println("\t\t DAO : count - " + count);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return count;
		}
// 리뷰 개수 조회 메서드 (profileReviewCount() 매개변수 X, 리턴타입 int) [전체 리뷰 개수]
	
// 리뷰 개수 조회 메서드 (profileReviewCount() 매개변수 id, 리턴타입 int) [내 리뷰 개수]
	public int profileReviewCount(String id) {
		int count = 0;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT member_id FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				int member_id = rs.getInt(1);
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT COUNT(*) FROM review WHERE member_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, member_id);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				System.out.println("\t\t DAO : profileReviewCount(id) 호출 성공 ");
				System.out.println("\t\t DAO : count - " + count);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return count;
	}
// 리뷰 개수 조회 메서드 (profileReviewCount() 매개변수 id, 리턴타입 int) [내 리뷰 개수]
	
// 전체 리뷰 목록 조회하는 메서드 (profileReviewList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)
	public ArrayList profileReviewList(int startRow, int pageSize) {
		ArrayList list = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();
				
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT review_id, title, score, date, image "
					+ "FROM review "
					+ "ORDER BY review_id DESC LIMIT ?, ?";
			pstmt = con.prepareStatement(sql);
					
			// ? 정보 추가
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
					
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
					
			// 5. 데이터 처리
			while(rs.next()) {
				ProfileReviewDTO dto = new ProfileReviewDTO();
				dto.setReview_id(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setScore(rs.getString(3));
				dto.setDate(rs.getDate(4));
				dto.setImage(rs.getString(5));
				list.add(dto);
			}
			System.out.println("\t\t DAO : profileReviewList() 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
// 전체 리뷰 목록 조회하는 메서드 (profileReviewList() 매개변수 startRow, pageSize, 리턴타입 ArrayList)

	
// 자신의 리뷰 목록 조회하는 메서드 (profileReviewList() 매개변수 id, startRow, pageSize, 리턴타입 ArrayList)
	public ArrayList profileReviewList(String id, int startRow, int pageSize) {
		ArrayList list = new ArrayList();
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT member_id FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				int member_id = rs.getInt(1);
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT review_id, title, score, date, image "
						+ "FROM review "
						+ "WHERE member_id = ? "
						+ "ORDER BY review_id DESC LIMIT ?, ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, member_id);
				pstmt.setInt(2, startRow - 1);
				pstmt.setInt(3, pageSize);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()) {
					ProfileReviewDTO dto = new ProfileReviewDTO();
					dto.setReview_id(rs.getInt(1));
					dto.setTitle(rs.getString(2));
					dto.setScore(rs.getString(3));
					dto.setDate(rs.getDate(4));
					dto.setImage(rs.getString(5));
					list.add(dto);
				}
			}
			System.out.println("\t\t DAO : profileReviewList(id) 호출 성공 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
// 자신의 리뷰 목록 조회하는 메서드 (profileReviewList() 매개변수 id, 리턴타입 ArrayList)
	
// 리뷰 보기 메서드 (profileReviewContent() 매개변수 review_id, 리턴타입 dto)
public ProfileReviewDTO profileReviewContent(int review_id) {
	ProfileReviewDTO dto = null;
	
	try {
		// 1. 2. DB 연결
		con = getCon();
		
		// 3. SQL 구문 작성 및 pstmt 객체 생성
		sql = "SELECT review_id, r.member_id, purchaseid, nickname, title, score, content, r.pw, m.id, image "
				+ "FROM review r "
				+ "JOIN member m "
				+ "ON r.member_id = m.member_id "
				+ "WHERE review_id = ?";
		pstmt = con.prepareStatement(sql);
		
		// ? 정보 추가
		pstmt.setInt(1, review_id);
		
		// 4. SQL 구문 실행
		rs = pstmt.executeQuery();
		
		// 5. 데이터 처리
		if(rs.next()) {
			dto = new ProfileReviewDTO();
			dto.setReview_id(rs.getInt(1));
			dto.setMember_id(rs.getInt(2));
			dto.setPurchaseid(rs.getInt(3));
			dto.setNickname(rs.getString(4));
			dto.setTitle(rs.getString(5));
			dto.setScore(rs.getString(6));
			dto.setContent(rs.getString(7));
			dto.setPw(rs.getString(8));
			dto.setId(rs.getString(9));
			dto.setImage(rs.getString(10));
		}
		System.out.println("\t\t DAO : profileReviewContent() 호출 성공 ");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		closeDB();
	}
	return dto;
}
// 리뷰 보기 메서드 (profileReviewContent() 매개변수 review_id, 리턴타입 dto)

// 구매 번호로 리뷰 번호 조회 (profileReviewId() 매개변수 purchaseid, 리턴타입 int)
public int profileReviewId(int purchaseid) {
	int review_id = -1;
	
	try {
		// 1. 2. DB 연결
		con = getCon();
		
		// 3. SQL 구문 작성 및 pstmt 객체 생성
		sql = "SELECT review_id FROM review WHERE purchaseid = ?";
		pstmt = con.prepareStatement(sql);
		
		// ? 정보 추가
		pstmt.setInt(1, purchaseid);
		
		// 4. SQL 구문 실행
		rs = pstmt.executeQuery();
		
		// 5. 데이터 처리
		if(rs.next()) {
			review_id = rs.getInt(1); 
		}
		System.out.println("\t\t DAO : profileReviewId() 호출 성공 ");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		closeDB();
	}
	return review_id;
}
// 구매 번호로 리뷰 번호 조회 (profileReviewId() 매개변수 purchaseid, 리턴타입 int)

// 멤버id 일치 여부 확인 메서드 (profileEqualMemberId())
	public int profileEqualMemberId(String id, int review_id) {
		int result = -1;
		int member_id = -1;
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT member_id FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				member_id = rs.getInt(1);
				
				// 3. SQL 구문 작성 및 pstmt 객체 생성
				sql = "SELECT member_id FROM review WHERE review_id = ?";
				pstmt = con.prepareStatement(sql);
				
				// ? 정보 추가
				pstmt.setInt(1, review_id);
				
				// 4. SQL 구문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(member_id == rs.getInt(1)) {
						result = 1;
					}else {
						result = 0;
					}
				}else {
					result = -1;
				}
			}
			System.out.println("\t\t DAO : profileEqualMemberId() 호출 성공 ");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
// 멤버id 일치 여부 확인 메서드 (profileEqualMemberId())	
	
// 리뷰 수정 메서드 (profileReviewUpdate1() 매개변수 dto, 리턴타입 int / 이미지 있음)
	public int profileReviewUpdate1(ProfileReviewDTO dto) {
		int result = -1;
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE review SET nickname = ?, title = ?, score = ?, content = ?, pw = ?, image = ?, updatedate = NOW() "
					+ "WHERE review_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, dto.getNickname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getScore());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPw());
			pstmt.setString(6, dto.getImage());
			pstmt.setInt(7, dto.getReview_id());
			
			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			System.out.println("\t\t DAO : profileReviewUpdate() 호출 성공 ");						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 리뷰 수정 메서드 (profileReviewUpdate1() 매개변수 dto, 리턴타입 int)
	
	// 리뷰 수정 메서드 (profileReviewUpdate2() 매개변수 dto, 리턴타입 int / 이미지 없음)
	public int profileReviewUpdate2(ProfileReviewDTO dto) {
		int result = -1;
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "UPDATE review SET nickname = ?, title = ?, score = ?, content = ?, pw = ?, updatedate = NOW() "
					+ "WHERE review_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setString(1, dto.getNickname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getScore());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPw());
			pstmt.setInt(6, dto.getReview_id());
			
			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			System.out.println("\t\t DAO : profileReviewUpdate() 호출 성공 ");						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 리뷰 수정 메서드 (profileReviewUpdate2() 매개변수 dto, 리턴타입 int)
	
	// 리뷰 삭제 메서드 (profileReviewDelete() 매개변수 review_id, 리턴타입 int)
	public int profileReviewDelete(int review_id) {
		int result = -1;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "DELETE FROM review WHERE review_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, review_id);
			
			// 4. SQL 구문 실행
			result = pstmt.executeUpdate();
			System.out.println("\t\t DAO : profileReviewDelete() 호출 성공 ");				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// 리뷰 삭제 메서드 (profileReviewDelete() 매개변수 review_id, 리턴타입 int)
	
	// 리뷰 번호에 해당하는 이미지 파일 조회 (profileReviewImage() 매개변수 review_id, 리턴타입 String)
	public String profileReviewImage(int review_id) {
		String image = "";
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			
			// 3. SQL 구문 작성 및 pstmt 객체 생성
			sql = "SELECT image FROM review WHERE review_id = ?";
			pstmt = con.prepareStatement(sql);
			
			// ? 정보 추가
			pstmt.setInt(1, review_id);
			
			// 4. SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				image = rs.getString(1);
			}
			System.out.println("\t\t DAO : profileReviewImage() 호출 성공 ");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return image;
	}
	// 리뷰 번호에 해당하는 이미지 파일 조회 (profileReviewImage() 매개변수 review_id, 리턴타입 String)
	
}
