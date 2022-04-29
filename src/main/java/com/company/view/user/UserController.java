package com.company.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.annotation.user.UserDAO;
import com.company.annotation.user.UserDO;

@Controller
public class UserController {  // POJO 클래스
	@RequestMapping("/insertUser.do")
	public String insertUser(UserDO userDO, UserDAO userDAO) {
		userDAO.insertUser(userDO);
		return "login.jsp";
	}
}
