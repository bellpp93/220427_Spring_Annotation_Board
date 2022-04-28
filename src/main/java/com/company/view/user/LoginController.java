package com.company.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.annotation.user.UserDAO;
import com.company.annotation.user.UserDO;

/*
 * POJO(Plain Old Java Object)란?
 * 		=>	객체 지향적인 원리에 충실하면서 환경과 기술에 종속되지 않고
 * 			필요에 따라 재활용될 수 있는 방식으로 설계된 오브젝트를 말한다.
 * 
 * Command 객체란?
 * 		=>	즉, userDO 객체를 의미한다.
 * 			클라이언트가 보내주는 파라미터가 자동으로 담겨서 반환되는 객체를 말한다.
 * 			클라이언트가 전달해주는 파라미터 데이터를 주입 받기 위해 사용되는 객체. 
 * 			이는 자동 객체 변환이라고도 한다. 이 개념이 MVC 패턴에서 가장 큰 핵심이다.
 */
@Controller
public class LoginController {  // POJO 클래스 => Plain Old Java Object (경량화 => 가볍다.)
	
	@RequestMapping("/login.do")
	public String login(UserDO userDO, UserDAO userDAO, HttpSession session) {  // DI => 커맨드 객체 형태로 객체를 주입 시킨다.
		UserDO user = userDAO.getUser(userDO);
		
		if(user != null) {
			session.setAttribute("userName", user.getName());
			// System.out.println("로그인 성공");
			return "getBoardList.do";
		} else {
			// System.out.println("로그인 실패");
			return "login.jsp";
		}
	}
}
