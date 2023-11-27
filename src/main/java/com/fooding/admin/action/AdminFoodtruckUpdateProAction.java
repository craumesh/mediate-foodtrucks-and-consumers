package com.fooding.admin.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.admin.db.AdminDTO;
import com.fooding.profile.review.db.ProfileReviewDAO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminFoodtruckUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : AdminFoodtruckUpdateProAction_execute() 호출 ");
		
		// 세션 제어
		HttpSession session = request.getSession();
//		session.setAttribute("id", "admin"); // 이 부분만 삭제하면 됨
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}else if(id != null && !id.equals("admin")) {
			forward.setPath("./FtMainAction.man"); // main 주소
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글 처리 (배포 서술자 자동)
		
		// 업로드 폴더 생성 - realPath
		String realPath = request.getRealPath("/upload/foodtruck");
		System.out.println("realPath : " + realPath);
		
		// 크기 제한
		int maxSize = 5 * 1024 * 1024;
		
		// 파일 업로드 [MultipartRequest]
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		
		// 전달 정보 저장
		AdminDTO dto = new AdminDTO();
		String pageNum = multi.getParameter("pageNum");
		dto.setFoodtruck_id(Integer.parseInt(multi.getParameter("foodtruck_id")));
		int foodtruck_id = Integer.parseInt(multi.getParameter("foodtruck_id"));
		dto.setFoodtruck_name(multi.getParameter("foodtruck_name"));
		dto.setInfo(multi.getParameter("info"));
		dto.setImage(multi.getFilesystemName("image"));
		String newImage = multi.getFilesystemName("image");
		
		// 정보 수정 메서드 호출
		AdminDAO dao = new AdminDAO();
		
		// 먼저 기존 이미지 파일 이름 저장
		String image = dao.adminFoodtruckImage(foodtruck_id);
		System.out.println("이미지 파일명 : " + image);
		int result = 0;
		if(newImage != null) { // 새로 업로드 하는 게 있음
				result = dao.adminFoodtruckUpdate1(dto);
				// 기존 이미지 삭제
				File file = new File(realPath + "/" + image);
				file.delete();
//			}
		}else { // 새로 업로드 하는 게 없음 (기존 이미지 삭제하면 안 됨)
			result = dao.adminFoodtruckUpdate2(dto);
		}
				
		if(result == 1) {
			JSMoveFunction.alertLocation(response, "푸드트럭 정보가 수정되었습니다.", 
				"./AdminFoodtruckContent.adm?foodtruck_id=" + foodtruck_id + "&pageNum=" + pageNum);
			}else {
				JSMoveFunction.alertHistory(response, "수정이 실패했습니다.");
			}
		return null;
	}
}
