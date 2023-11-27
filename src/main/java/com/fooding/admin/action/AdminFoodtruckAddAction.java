package com.fooding.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fooding.admin.db.AdminDAO;
import com.fooding.admin.db.AdminDTO;
import com.fooding.util.Action;
import com.fooding.util.ActionForward;
import com.fooding.util.JSMoveFunction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminFoodtruckAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 출력문
		System.out.println("\t\t M : AdminFoodtruckAddAction_execute() 호출 ");
		
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
		
		// 가져온 아이디를 이용해서 member_id 조회
		AdminDAO dao = new AdminDAO();
		

		int member_id = dao.adminMemberidNum(multi.getParameter("id"));
		if(member_id == -1) { // 입력했는데 없을 때
			JSMoveFunction.alertHistory(response, "존재하지 않는 아이디입니다.");
			return null;
		}
		
		// 전달 정보 저장
		AdminDTO dto = new AdminDTO();
		dto.setFoodtruck_name(multi.getParameter("foodtruck_name"));
		dto.setInfo(multi.getParameter("info"));
		dto.setImage(multi.getFilesystemName("image"));
		dto.setMember_id(member_id);
		
		// 메서드 호출 (DAO)
		dao.adminFoodtruckAdd(dto);  
		
		// 페이지 이동
		forward.setPath("./Admin.adm");
		forward.setRedirect(true);
		return forward;
	}
}
