package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	public SqlSession sql;

	/* 포스팅 */
	public void insert(PostVo pVo) {

		System.out.println("[Category Dao]: insert(PostVo pVo) 실행");

		sql.insert("post.insert", pVo);

	}

	/* 각 카테고리의 포스트 */
	public List<PostVo> selectList(int cateNo) {

		System.out.println("[Category Dao]: selectList(int cateNo) 실행");

		return sql.selectList("post.selectList", cateNo);
	}

	/* 포스트 조회 */
	public PostVo selectOne(int postNo) {

		System.out.println("[Category Dao]: selectOne(int postNo) 실행");

		return sql.selectOne("post.selectOne", postNo);
	}

}
