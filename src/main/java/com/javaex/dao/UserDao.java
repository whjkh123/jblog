package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	public SqlSession sql;

	/* 회원가입 */
	public void insert(UserVo uVo) {

		System.out.println("[User Dao]: insert(UserVo uVo) 실행");

		sql.insert("user.insert", uVo);

	}

	/* 로그인 */
	public UserVo selectOne(UserVo uVo) {

		System.out.println("[User Dao]: selectOne(UserVo uVo) 실행");

		return sql.selectOne("user.selectOne", uVo);

	}

}
