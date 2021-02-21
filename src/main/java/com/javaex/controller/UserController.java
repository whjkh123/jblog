package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	public UserService uS;

	/* 로그인 폼 */
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {

		System.out.println("[User Ctrl]: loginForm 진입");

		return "user/loginForm";

	}

	/* 회원가입 폼 */
	@RequestMapping(value = "joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {

		System.out.println("[User Ctrl]: joinForm 진입");

		return "user/joinForm";

	}

	/* 회원가입 */
	@RequestMapping(value = "join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo uVo) {

		System.out.println("[User Ctrl]: join 진입");

		uS.join(uVo);

		System.out.println("[User Ctrl]: " + uVo);

		return "user/joinSuccess";

	}

	/* 로그인 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo uVo, HttpSession session) {

		System.out.println("[User Ctrl]: login 진입");

		UserVo authUser = uS.login(uVo);

		if (authUser == null) {

			System.out.println("[User Ctrl]: login 실패");

			return "redirect:/user/loginForm?result=fail";

		} else {

			System.out.println("[User Ctrl]: login 성공");

			System.out.println("[User Ctrl]: " + authUser);

			session.setAttribute("authUser", authUser);

			return "redirect:/";

		}

	}

	/* 로그아웃 */
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {

		System.out.println("[User Ctrl]: logout 진입");

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";

	}

}
