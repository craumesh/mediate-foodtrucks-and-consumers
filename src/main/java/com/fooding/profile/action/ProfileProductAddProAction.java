package com.fooding.profile.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.admin.db.AdminDTO;
import com.fooding.profile.db.ProfileDAO;
import com.fooding.profile.db.ProfileDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProfileProductAddProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : ProfileProductAddProAction_execute() 호출 ");
		
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
		
		// Upload 폴더 생성
		String realPath = request.getRealPath("/upload/product");
		System.out.println("realPath : " + realPath);
		
		// 크기 제한
		int maxSize = 5 * 1024 * 1024;
		
		// 파일 업로드 - MultipartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request, 
				realPath,
				maxSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		
		// 전달 정보 저장 (form -> action)
		ProfileDTO dto = new ProfileDTO();
		dto.setProduct_name(multi.getParameter("product_name"));
		dto.setPrice(Integer.parseInt(multi.getParameter("price")));
		dto.setInfo(multi.getParameter("info"));
		dto.setFoodtruck_id(Integer.parseInt(multi.getParameter("foodtruck_id")));
		dto.setImage(multi.getFilesystemName("image"));
		dto.setLargeclass(multi.getParameter("largeclass"));
		dto.setSmallclass(multi.getParameter("smallclass"));
		
		// 메서드 호출
		ProfileDAO dao = new ProfileDAO();
		dao.profileProductAdd(dto);
		
		// 페이지 이동
		forward.setPath("./ProfileProductList.pro");
		forward.setRedirect(true);
		return forward;
	}

}
