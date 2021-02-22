package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	public SqlSession sql;

	/* 카테고리 관리(ajax) */
	/* 카테고리 리스트 */
	public List<CategoryVo> selectList(CategoryVo cVo) {

		System.out.println("[Category Dao]: selectList(CategoryVo cVo) 실행");

		return sql.selectList("category.selectList", cVo);
	}

	/* 카테고리 추가 */
	public void insert(CategoryVo cVo) {

		System.out.println("[Category Dao]: insert(CategoryVo cVo) 실행");

		sql.insert("category.insert", cVo);

	}

	/* 카테고리 조회 */
	public CategoryVo selectOne(int cateNo) {

		System.out.println("[Category Dao]: selectOne(int cateNo) 실행");

		return sql.selectOne("category.selectOne", cateNo);
	}

	/* 카테고리 데이터 가져오기 */
	public List<CategoryVo> cateSelect(String id) {

		System.out.println("[Category Dao]: cateSelect(String id) 실행");

		return sql.selectList("category.cateSelect", id);
	}

	/* 카테고리 삭제 */
	public int delete(int cateNo) {

		System.out.println("[Category Dao]: delete(int cateNo) 실행");

		return sql.delete("category.delete", cateNo);
	}

}
