package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	public SqlSession sql;

	/* 내 블로그 */
	public BlogVo selectOne(String id) {

		System.out.println("[Blog Dao]: selectOne(String id) 실행");

		System.out.println("[Blog Dao]: " + id);

		return sql.selectOne("blog.selectOne", id);
	}

	/* 회원가입하면서 blog db 테이블에도 동시에 데이터가 입력되도록 */
	public void insert(BlogVo bVo) {

		System.out.println("[Blog Dao]: insert(BlogVo bVo) 실행");

		System.out.println("[Blog Dao]: " + bVo);

		sql.insert("blog.insert", bVo);

	}

	/* 내 블로그 관리 */
	public void update(BlogVo bVo) {

		System.out.println("[Blog Dao]: update(BlogVo bVo) 실행");

		System.out.println("[Blog Dao]: " + bVo);

		sql.update("blog.update", bVo);

	}

}
