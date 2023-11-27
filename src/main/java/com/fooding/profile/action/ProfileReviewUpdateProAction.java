package com.fooding.profile.action;

import java.io.File;

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

public class ProfileReviewUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t M : ProfileReviewUpdateProAction_execute() 호출 ");
		
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
		
		// 전달 정보 저장
		// 업로드 폴더 생성 [upload]
		String realPath = request.getRealPath("/upload/review");
		System.out.println("realPath : " + realPath);
		
		// 크기 제한 [maxSize]
		int maxSize = 5 * 1024 * 1024;
		
		// 파일 업로드 
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
				
		// 전달 정보 저장
		int review_id = Integer.parseInt(multi.getParameter("review_id"));
		String pageNum = multi.getParameter("pageNum");
		String search = multi.getParameter("search");
		
		ProfileReviewDTO dto = new ProfileReviewDTO();
		dto.setReview_id(Integer.parseInt(multi.getParameter("review_id")));
		dto.setNickname(multi.getParameter("nickname"));
		dto.setTitle(multi.getParameter("title"));
		dto.setScore(multi.getParameter("score"));
		dto.setContent(multi.getParameter("content"));
		dto.setPw(multi.getParameter("pw"));
		dto.setImage(multi.getFilesystemName("image"));
		String newImage = multi.getFilesystemName("image");
		
		// 정보 수정 메서드 호출
		ProfileReviewDAO dao = new ProfileReviewDAO();
		// 먼저 기존 이미지 파일 이름 저장
		String image = dao.profileReviewImage(review_id);
		System.out.println("이미지 파일명 : " + image);
		int result = 0;
		if(newImage != null) { // 새로 업로드 하는 게 있음
//			if(newImage.equals(image)) {
//				result = dao.profileReviewUpdate1(dto);
//			}else {
				result = dao.profileReviewUpdate1(dto);
				// 기존 이미지 삭제
				File file = new File(realPath + "/" + image);
				file.delete();
//			}
		}else { // 새로 업로드 하는 게 없음 (기존 이미지 삭제하면 안 됨)
			result = dao.profileReviewUpdate2(dto);
		}
		
		if(result == 1) {
			JSMoveFunction.alertLocation(response, "리뷰가 수정되었습니다.", 
					"./ProfileReviewContent.pro?review_id=" + review_id + "&pageNum=" + pageNum + "&search=" + search);
		}else {
			JSMoveFunction.alertHistory(response, "수정이 실패했습니다.");
		}
		return null;
	}
}
