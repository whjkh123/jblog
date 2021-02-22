package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {

	@Autowired
	public BlogService bS;

	@Autowired
	public PostService pS;

	/* 내 블로그 */
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String myBlog(@PathVariable("id") String id, Model model,
			@RequestParam(value = "cateNo", required = false, defaultValue = "0") int cateNo,
			@RequestParam(value = "postNo", required = false, defaultValue = "0") int postNo) {

		/* @PathVariable: 요청창(주소창)을 'localhost:8088/jblog/사용자 id'로 고정 */

		/*
		 * @RequestParam(value = "", required = false, defaultValue = ""): value값이 있을 수도
		 * 있고, 없을 수도 있다..
		 */

		System.out.println("[Blog Ctrl]: myBlog 진입");

		BlogVo bVo = bS.myBlog(id);

		if (bVo != null) {
			/* 'bVo' not null >> 'bVo'가 존재한다. >> 'myBlog'가 생성됐다. */

			Map<String, Object> bMap = bS.side(id, cateNo, postNo);

			/* BlogVo, bMap 데이터를 jsp파일에 뿌려준다.. */
			model.addAttribute("BlogVo", bVo);
			model.addAttribute("bMap", bMap);

			return "blog/blog-main";

		} else {
			return "redirect:/";
		}

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

	/* 카테고리 관리 폼 */
	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String cateForm(@PathVariable("id") String id, Model model) {

		System.out.println("[Blog Ctrl]: cateForm 진입");

		BlogVo bVo = bS.myBlog(id);

		model.addAttribute("BlogVo", bVo);

		return "blog/admin/blog-admin-cate";

	}

	/* 카테고리 관리(ajax) */
	/* 카테고리 리스트 */
	/* 포스트 수 카운트 추가 */
	@ResponseBody
	@RequestMapping(value = "admin/cateList", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> cList(@ModelAttribute CategoryVo cVo) {

		System.out.println("[Blog Ctrl]: cateList 진입");

		return bS.cateList(cVo);

	}

	/* 카테고리 추가 */
	@ResponseBody
	@RequestMapping(value = "admin/cateAdd", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo cateAdd(@ModelAttribute CategoryVo cVo) {

		System.out.println("[Blog Ctrl]: cateAdd 진입");

		CategoryVo cateAdd = bS.cateAdd(cVo);

		return cateAdd;

	}

	/* 포스트 폼 */
	@RequestMapping(value = "/{id}/admin/postForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String postForm(@PathVariable("id") String id, Model model) {

		System.out.println("[Blog Ctrl]: postForm 진입");

		/* 내 블로그 데이터 가져오기 */
		BlogVo bVo = bS.myBlog(id);

		model.addAttribute("BlogVo", bVo);
		System.out.println("[Blog Ctrl]: " + bVo);

		/* 카테고리 데이터 가져오기 */
		List<CategoryVo> cList = bS.cateName(id);
		model.addAttribute("CateList", cList);

		return "blog/admin/blog-admin-write";

	}

	/* 포스팅 */
	@RequestMapping(value = "/admin/posting", method = { RequestMethod.GET, RequestMethod.POST })
	public String posting(@ModelAttribute PostVo pVo, @RequestParam("id") String id) {

		System.out.println("[Blog Ctrl]: posting 진입");

		System.out.println(id);

		pS.write(pVo);

		return "redirect:/" + id + "/admin/postForm";

	}

	/* 카테고리 삭제 */
	@ResponseBody
	@RequestMapping(value = "/admin/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public int remove(@RequestParam("cateNo") int cateNo) {

		System.out.println("[Blog Ctrl]: remove 진입");

		return bS.remove(cateNo);

	}

}
