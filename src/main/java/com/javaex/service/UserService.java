package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	public UserDao uDao;

	@Autowired
	public BlogDao bDao;

	/* 회원가입 */
	/* 회원가입하면서 blog db 테이블에도 동시에 데이터가 입력되도록 */
	public void join(UserVo uVo) {

		System.out.println("[User Service]: join(UserVo uVo) 연결");

		/* 회원가입.. */
		uDao.insert(uVo);

		/* 하면서 동시에 blog db 테이블도 작성.. */
		String id = uVo.getId();
		String blogTitle = uVo.getUserName();

		BlogVo bVo = new BlogVo(id, blogTitle, null);// INSERT INTO blog VALUES('test', '홍길동의 블로그 입니다.', null);

		bDao.insert(bVo);

	}

	/* 로그인 */
	public UserVo login(UserVo uVo) {

		System.out.println("[User Service]: login(UserVo uVo) 연결");

		return uDao.selectOne(uVo);

	}

}
