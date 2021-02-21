package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String jblog() {

		System.out.println("[Main Ctrl]: index 진입");

		return "/main/index";
	}

}
