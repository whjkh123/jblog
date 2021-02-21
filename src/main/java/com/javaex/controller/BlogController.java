package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {

	@Autowired
	public BlogService bS;

	/* 내 블로그 */
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String myBlog(@PathVariable("id") String id, Model model) {

		// @PathVariable: 요청창(주소창)을 'localhost:8088/jblog/사용자 id'로 고정

		System.out.println("[Blog Ctrl]: myBlog 진입");

		BlogVo bVo = bS.myBlog(id);

		model.addAttribute("BlogVo", bVo);
		System.out.println("[Blog Ctrl]: " + bVo);

		return "blog/blog-main";

	}

	/* 내 블로그 관리 폼 */
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminBasic(@PathVariable("id") String id, Model model) {

		System.out.println("[Blog Ctrl]: adminBasic 진입");

		/* 내 블로그 데이터 가져오기 */
		BlogVo bVo = bS.myBlog(id);

		model.addAttribute("BlogVo", bVo);
		System.out.println("[Blog Ctrl]: " + bVo);

		return "blog/admin/blog-admin-basic";

	}

	/* 내 블로그 관리 */
	@RequestMapping(value = "/setting", method = { RequestMethod.GET, RequestMethod.POST })
	public String setting(@ModelAttribute BlogVo bVo, @RequestParam("file") MultipartFile file) {

		System.out.println("[Blog Ctrl]: setting 진입");

		bS.setting(bVo, file);

		System.out.println("[Blog Ctrl]: " + bVo);

		return "redirect:/" + bVo.getId() + "/admin/basic";

	}

}
