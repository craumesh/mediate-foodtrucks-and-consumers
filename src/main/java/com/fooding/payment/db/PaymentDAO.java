package com.fooding.payment.db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.fooding.cart.db.CartDTO;
import com.fooding.member.db.MemberDTO;




public class PaymentDAO {
	
	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// ==== 디비 연결 메서드 ====
	
	private Connection getCon() throws Exception{
		// Connection Pool을 사용한 디비 연결
		
		// 프로젝트의 정보를 확인(JNDI)
		Context initCTX = new InitialContext();  //컨텍스트= 프로젝트, init = 초기화, CTX = 컨텍스트
		// 프로젝트안에 작성된 디비 연결정보(context.xml)를 불러오기
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/mvc"); // 불러오기
		// DB 연결 수행
		con = ds.getConnection();
//		System.out.println(" DAO : 디비연결 성공! (커넥션풀)");
		return con; }

	
	//================    디비 연결(자원) 해제 메서드    ====================
	public void CloseDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//================    디비 연결(자원) 해제 메서드    ====================
	//=============     /////////////    =======================
	
	// 0-0 사전등록하기 위해서는 상품아이디를 만들어야함
	public int createOrderNum() {
	    LocalDate date = LocalDate.now();
	    String day = String.format("%02d", date.getDayOfMonth());

	    int orderNum = Integer.parseInt(day);
	    Random rand = new Random();
	    for (int i = 0; i < 6; i++) {
	        orderNum += rand.nextInt(10);
	    }
	    return orderNum; // 총 8자리 숫자
	}
	
	//0-1. getCartID(ArrayList<CartDTO> arr)
	public ArrayList<Integer> cartList(ArrayList<CartDTO> arr){
		ArrayList<Integer> cartIdList = new ArrayList<Integer>();

		for (CartDTO cartDTO : arr) {
		    int cartId = cartDTO.getCart_id(); 
		    cartIdList.add(cartId);
		}
		System.out.println("Payment DAO : 장바구니 번호 CartDTO");
		return cartIdList;
	}
	
	
	
	// 1. String[] 으로 오는 장바구니 번호 ArrayList<Integer>로 변환하기
	public ArrayList stringToArrayList(String[] arr){
		ArrayList cart_ids = new ArrayList<>();
		
		for(String eachArr : arr) {
			try {
				cart_ids.add(Integer.parseInt(eachArr));
			} catch (NumberFormatException e) {
				System.out.println(eachArr + " is not a valid number.");
			}
		}
		System.out.println("Payment DAO : 전달받은 장바구니 번호 변환 완료 ");
		return cart_ids;
	} // 1. 끝
	
	
	//4. 카트랑 프로덕트 합치기
	public ArrayList getPurchase(ArrayList<Integer> cart_id) {
		ArrayList purchasetList = new ArrayList();
		
		try {con=getCon();
		
		sql = "SELECT *, f.name As foodTruckName "
				+ "FROM cart c "
				+ "JOIN product p ON c.product_id = p.product_id "
				+ "JOIN foodtruck f ON f.foodtruck_id = p.foodtruck_id "
				+ "WHERE c.cart_id = ?";
		pstmt = con.prepareStatement(sql);
		
		for(int cartId : cart_id) {
			pstmt.setInt(1, cartId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PaymentDTO payDto = new PaymentDTO();
				// 카트테이블 전부
				payDto.setCart_id(rs.getInt("cart_id"));
				payDto.setMember_id(rs.getInt("member_id"));
				payDto.setProduct_id(rs.getInt("product_id"));
				payDto.setQuantity(rs.getInt("quantity"));
				payDto.setDate(rs.getString("date"));
				payDto.setAddress(rs.getString("address"));
				payDto.setStoptime(rs.getString("stoptime"));
				// 상품 이름과 가격, 이미지 주소
				payDto.setName(rs.getString("name"));
				payDto.setPrice(rs.getInt("price"));
				payDto.setImage(rs.getString("image"));
				// 푸드트럭 이름
				payDto.setFoodtruckName(rs.getString("foodTruckName"));
				payDto.setFoodtruck_id(rs.getInt("foodtruck_id"));
				
				purchasetList.add(payDto);
			}
		}
		System.out.println("Payment DAO : (cart+product+time)상품 정보 조회 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseDB();
		}
		return purchasetList;
	}//4.끝
	
	// 5. 아이디정보로 회원정보 조회하기 getMember()
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		// 1.2.  디비연결
		try {
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
			sql = "select * from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 4. sql 실행 
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리 (DB에 저장된 정보(rs)를 DTO로 저장)
			if(rs.next()) {
				dto = new MemberDTO();
				//rs => dto 저장
				dto.setMember_id(rs.getInt("member_id"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getTimestamp("regdate"));
			}
			
			System.out.println("DAO : 회원정보 조회 완료!"+dto.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		
		return dto;
	}
	
	// 이름 합치기 combinedName(PurchaseList)
	public String combinedName(ArrayList<PaymentDTO> purchaseList) {
	    StringBuilder nameString = new StringBuilder();
	    int nameCount = 0;

	    for (PaymentDTO payDTO : purchaseList) {
	        if (nameCount == 0) {
	            nameString.append(payDTO.getName());
	        } else if (nameCount == 1) {
	            nameString.append(", ").append(payDTO.getName());
	        } else {
	            break; // 2개 이상의 상품이 있는 경우 루프를 종료
	        }
	        nameCount++;
	    }

	    if (nameCount > 1 && purchaseList.size() - 2 > 0) {
	        nameString.append(" 외 ").append(purchaseList.size() - 2);
	    }

	    String result = nameString.toString();
	    System.out.println("DAO : 이름합치기 완료 : " + result);
	    return result;
	}
	

	//AFter=================================================================================
	//1. 
	public ArrayList stringToArrayList(String[] purchase,String[] member,
						String[] product,String[] quantity,String[] address){
		ArrayList<PaymentDTO> purchaseList = new ArrayList<>();
		for (int i = 0; i < purchase.length; i++) {
		    PaymentDTO dto = new PaymentDTO();
		    dto.setPurchaseid(Integer.parseInt(purchase[i]));
		    dto.setMember_id(Integer.parseInt(member[i]));
		    dto.setProduct_id(Integer.parseInt(product[i]));
		    dto.setQuantity(Integer.parseInt(quantity[i]));
		    dto.setAddress(address[i]);
		    purchaseList.add(dto);
		}
		System.out.println("Payment DAO : 전달받은 String[] 하나로 합치기 완료 ");
		return purchaseList;
	} // 1.끝 
	
	// 장바구니 정보 arraylist로 만들기
	public ArrayList getPurchaseList(ArrayList<Integer> cart_ids) {
		ArrayList PurchaseList = new ArrayList();
		
		try {
			con = getCon();
			for (Integer cart_id : cart_ids) {
			sql = "select * from cart where cart_id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cart_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				PaymentDTO dto = new PaymentDTO();
				dto.setMember_id(rs.getInt("member_id"));
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setQuantity(rs.getInt("quantity"));
				dto.setDate(rs.getString("date"));
				dto.setAddress(rs.getString("address"));
				dto.setStoptime(rs.getString("stoptime"));
				PurchaseList.add(dto);
			
			}
				
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			CloseDB();
		}
		return PurchaseList;
	}
	
	// 카트정보객체와 주문번호를 가지고 insert 하기
	public void insertPurchase(ArrayList<PaymentDTO> cartList, int purchase_id) {
		  try {
		      con = getCon();
		      //쿼리에서 date 삭제하기
		      sql = "INSERT INTO purchase (purchaseid, member_id, product_id, quantity, orderdate, date, address, stoptime) VALUES (?,?,?,?,now(),?,?,?)";
		      pstmt = con.prepareStatement(sql);

		      for (PaymentDTO dto : cartList) {
		         pstmt.setInt(1, purchase_id);
		         pstmt.setInt(2, dto.getMember_id());
		         pstmt.setInt(3, dto.getProduct_id());
		         pstmt.setInt(4, dto.getQuantity());
		         pstmt.setString(5, dto.getDate());//date
		         pstmt.setString(6, dto.getAddress());//address
		         pstmt.setString(7, dto.getStoptime());//stoptime

		         pstmt.executeUpdate();
		      }
		      System.out.println(" DAO : 데이터 입력 완");
		   } catch (Exception e) {
		      e.printStackTrace();
		   } finally {
		      CloseDB();
		   }
	}
	//2. insertPurchase(ArrayList arr) 만들기
	//		=> DB업데이트
	public void insertPurchase(ArrayList<PaymentDTO> arr) {
		   try {
		      con = getCon();
		      //쿼리에서 date 삭제하기
		      sql = "INSERT INTO purchase (purchaseid, member_id, product_id, quantity, orderdate, date, address, stoptime) VALUES (?,?,?,?,date(now()),?,?,?)";
		      pstmt = con.prepareStatement(sql);

		      for (PaymentDTO dto : arr) {
		         pstmt.setInt(1, dto.getPurchaseid());
		         pstmt.setInt(2, dto.getMember_id());
		         pstmt.setInt(3, dto.getProduct_id());
		         pstmt.setInt(4, dto.getQuantity());
		         pstmt.setString(5, dto.getDate());//date
		         pstmt.setString(6, dto.getAddress());//address
		         pstmt.setString(7, dto.getStoptime());//stoptime

		         pstmt.executeUpdate();
		      }
		      System.out.println(" DAO : 데이터 입력 완");
		   } catch (Exception e) {
		      e.printStackTrace();
		   } finally {
		      CloseDB();
		   }
		}// 2. 끝
	
	//3. deleteCart(ArrayList arr)
	public int deleteMember(ArrayList<Integer> arr) {
		int result=-1;  // -1, 0, 1
		ArrayList cartIds = new ArrayList<>();
		
		try {
			// select 해서 지워야할 데이터 찾기
			con=getCon();
			for (int dto : arr) {
				sql = "DELETE FROM cart WHERE cart_id = ?";
				try(PreparedStatement pstmt = con.prepareStatement(sql)){
					pstmt.setInt(1, dto);
					 int deletedRows = pstmt.executeUpdate();
	                    if (deletedRows > 0) {
	                    	result = -1;
	                        System.out.println("cart_id " + dto + "의 레코드를 삭제했습니다.");
	                    } else {
	                    	result=1;
	                        System.out.println("cart_id " + dto + "를 찾을 수 없습니다.");
	                    }
	                }
	            }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		System.out.println("DAO : 회원정보 삭제("+result+")");

		return result;
}
	// 글 정보 목록을 가져오는 메서드 - getBoardList(int startRow, int pageSize)
	public PaymentDTO getBoard() {
		PaymentDTO dto = null;
		try {
			con = getCon();
			
			sql="select * from purchase";
			pstmt=con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new PaymentDTO();
				
				dto.setDetail_id(rs.getInt("detail_id"));
				dto.setMember_id(rs.getInt("member_id"));
				dto.setProduct_id(rs.getInt("product_id"));
				dto.setPurchaseid(rs.getInt("purchaseid"));
				dto.setQuantity(rs.getInt("quantity"));
				dto.setAddress(rs.getString("address"));
				
			} // if
			
			System.out.println("DAO : 글 정보 조회 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
		return dto;
	}
		
		
	//---------------------- refund
	
	// 환불요청을 위해 주문번호를 기준으로 가격과 수량 구하기 makeCheckSum(int mid)
	public int makeCheckSum(int mid) {
		int result = 0;
		try {
			con = getCon();
			
			sql="select pd.price as price, p.quantity from purchase p join product pd "
				+ "on p.product_id=pd.product_id where purchaseid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mid);
			rs = pstmt.executeQuery();
	        while(rs.next()){
	            // 가정: 가격이 'price' 컬럼에, 수량이 'quantity' 컬럼에 저장되어 있다.
	            int price = rs.getInt("price");
	            int quantity = rs.getInt("quantity");
	            result += price * quantity;  // 가격과 수량을 곱한 값을 result에 더함
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		System.out.println("DAO : 현재 "+mid+"의 총 가격 = "+result);
		return result;
	}
	// 회원번호 찾기? GetMember_id(String id)
	// 5. 아이디정보로 회원정보 조회하기 getMember()
		public int getMember_id(String id) {
			int result = 0;
			// 1.2.  디비연결
			try {
				con = getCon();
				// 3. sql 작성(select) & pstmt 객체
				sql = "select * from member where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				// 4. sql 실행 
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리 (DB에 저장된 정보(rs)를 DTO로 저장)
				if(rs.next()) {
					//rs => dto 저장
					result = rs.getInt("member_id");
					
				}
				
				System.out.println("DAO : 회원번호 조회 완료!"+result);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			
			return result;
		}
		// 5. detail_id로 회원정보 조회하기 getMember() 
		public int getMember_id(int detail_id) {
			int result = 0;
			// 1.2.  디비연결
			try {
				con = getCon();
				// 3. sql 작성(select) & pstmt 객체
				sql = "select * from purchase where detail_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, detail_id);
				
				// 4. sql 실행 
				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리 (DB에 저장된 정보(rs)를 DTO로 저장)
				if(rs.next()) {
					
					result = rs.getInt("member_id");
					
				}
				
				System.out.println("DAO : 회원번호 조회 완료!"+result);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			
			return result;
		}
		
		// 데이터 지우기 전 인덱스 가져오기
		public int returnIdxs(int mid, String address) {
			int result = 0;
			try {
				con = getCon();
				
				sql="select * from purchase p join product pd "
						+ "on p.product_id = pd.product_id where purchaseid = ? and address = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mid);
				pstmt.setString(2, address);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("detail_id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			return result;
		}
		
		// 환불 요청 후 환불한 데이터 지우기
		public int deletePurchase(int member_id, int mid,int detail_id) {
			int result = -1 ; // -1(실패), 0(비번오류), 1(정상처리
			try {
				con=getCon();
				
				sql="select * from purchase "
						+ "where member_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, member_id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					sql = "delete from purchase "
							+ "where member_id = ? "
							+ "and purchaseid = ? "
							+ "and detail_id = ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, member_id);
					pstmt.setInt(2, mid);
					pstmt.setInt(3, detail_id);
					
					result = pstmt.executeUpdate();
					System.out.println("DAO : "+mid+"데이터 삭제 완료("+result+")");
					
				}else {
					result = 0;
					System.out.println("DAO : "+mid+"데이터 삭제 실패("+result+")");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return result;
		}
		
		// 결제검증 실패 시 결제 데이터 지우기
		public int deletePurchase(int mid) {
			int result = -1 ; // -1(실패), 0(비번오류), 1(정상처리
			try {
				con=getCon();
				
				sql="select * from purchase "
						+ "where purchaseid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mid);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					sql = "delete from purchase where purchaseid = ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, mid);
					
					result = pstmt.executeUpdate();
					System.out.println("DAO : "+mid+"데이터 삭제 완료("+result+")");
					
				}else {
					result = 0;
					System.out.println("DAO : "+mid+"데이터 삭제 실패("+result+")");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return result;
		}// 환불 데이터 지우기

		// 이메일 얻기 (인덱스로, 손님 이메일 get)
		public String getEmailForCustomer(int mid) {
			String result = null;
			try {
				con = getCon();
				
				sql ="select detail_id, email from purchase p "
						+ "join member m on p.member_id = m.member_id "
						+ "where purchaseid = ? limit 1";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getString("email");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			System.out.println(" 고객의 이메일 : " + result);
			return result;
		}


		public void sendSomeRefundEmail(String email, int detail_id, int mid) {
			String productName = null;
			try {
				con= getCon();
				
				sql="select detail_id, name from purchase pc join product p "
						+ "on pc.product_id = p.product_id "
						+ "where detail_id = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, detail_id);
	
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					productName = rs.getString("name");
				}
				System.out.println("상품이름 : "+productName);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			
			MailThread thread = new MailThread(email, detail_id, mid, productName);
			thread.start();
		}


		public void sendAllRefundEmail(String email, int detail_id, int mid) {
			MailThread thread = new MailThread(email, detail_id, mid);
			thread.start();
			
		}
		
	
}//dao end
