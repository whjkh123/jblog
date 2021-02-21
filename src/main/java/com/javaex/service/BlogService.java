package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	public BlogDao bDao;

	@Autowired
	public FileUploadService fS;

	/* 내 블로그 */
	public BlogVo myBlog(String id) {

		System.out.println("[Blog Service]: myBlog(String id) 연결");

		return bDao.selectOne(id);

	}

	/* 내 블로그 관리 */
	public void setting(BlogVo bVo, MultipartFile file) {

		System.out.println("[Blog Service]: setting(BlogVo bVo, MultipartFile file) 연결");

		fS.fileUpload(bVo, file);

		System.out.println("[Blog Service]: " + bVo);

		bDao.update(bVo);

	}

}
