package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	public PostDao pDao;

	/* 포스팅 */
	public void write(PostVo pVo) {

		System.out.println("[Blog Service]: write(PostVo pVo) 연결");

		pDao.insert(pVo);

	}

}
