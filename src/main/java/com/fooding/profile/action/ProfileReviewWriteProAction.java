package com.fooding.profile.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.profile.review.db.ProfileReviewDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProfileReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileReviewWriteProAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
				if(id == null) {
					forward.setPath("./FtMainAction.man"); // main 주소
					forward.setRedirect(true);
					return forward;
				}

		// 한글 처리 (배포 서술자 자동)
		
		// 전달 정보 저장 [닉 / 제 / 본 / 비]
//		int purchaseid = Integer.parseInt(request.getParameter("purchaseid"));
//		String pageNum = request.getParameter("pageNum");
//		System.out.println(purchaseid);
		
		// 업로드 폴더 생성 [upload -> realPath]
		String realPath = request.getRealPath("/upload/review");
		System.out.println("realPath : " + realPath);
		
		// 크기 제한 설정 [maxSize]
		int maxSize = 5 * 1024 * 1024; // 5MB
		
		// 파일 업로드 [MultipartRequest 객체 생성]
		MultipartRequest multi = new MultipartRequest(
					request,
					realPath,
					maxSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
				);
		// 전달 정보 저장 (purchaseid)
		int purchaseid = Integer.parseInt(multi.getParameter("purchaseid"));
		
		// 전달 정보 저장 (dto)
		ProfileReviewDTO dto = new ProfileReviewDTO();
		dto.setNickname(multi.getParameter("nickname"));
		dto.setTitle(multi.getParameter("title"));
		dto.setScore(multi.getParameter("score"));
		dto.setContent(multi.getParameter("content"));
		dto.setPw(multi.getParameter("pw"));
		dto.setImage(multi.getFilesystemName("image"));
		
//		dto.setNickname(request.getParameter("nickname"));
//		dto.setTitle(request.getParameter("title"));
//		dto.setScore(request.getParameter("score"));
//		dto.setContent(request.getParameter("content"));
//		dto.setPw(request.getParameter("pw"));
		
		// 메서드 호출 (DAO)
		ProfileReviewDAO dao = new ProfileReviewDAO();
		dao.profileInsertReview(purchaseid, dto);
		
		int review_id = dao.profileReviewId(purchaseid);
		
		JSMoveFunction.alertLocation(response, "리뷰가 정상적으로 작성되었습니다.", 
				"./ProfileReviewContent.pro?review_id=" + review_id);
		return null;
	}
}
