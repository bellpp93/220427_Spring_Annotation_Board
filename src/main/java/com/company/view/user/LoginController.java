package com.company.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.annotation.user.UserDAO;
import com.company.annotation.user.UserDO;

@Controller
public class LoginController {
	
	@RequestMapping("/login.do")
	public String login(UserDO userDO, UserDAO userDAO, HttpSession session) {
		UserDO user = userDAO.getUser(userDO);
		
		if(user != null) {
			session.setAttribute("userName", user.getName());
			System.out.println("로그인 성공");
			return null;
			//return "get BoardList.do";
		} else {
			System.out.println("로그인 실패");
			return "login.jsp";
		}
	}
}
