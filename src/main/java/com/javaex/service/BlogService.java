package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	public BlogDao bDao;

	@Autowired
	public FileUploadService fS;

	@Autowired
	public CategoryDao cDao;

	@Autowired
	public PostDao pDao;

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

	/* 카테고리 관리(ajax) */
	/* 카테고리 리스트 */
	public List<CategoryVo> cateList(CategoryVo cVo) {

		System.out.println("[Blog Service]: cateList(CategoryVo cVo) 연결");

		List<CategoryVo> cList = cDao.selectList(cVo);

		return cList;
	}

	/* 카테고리 추가 */
	public CategoryVo cateAdd(CategoryVo cVo) {

		System.out.println("[Blog Service]: cateAdd(CategoryVo cVo) 연결");

		System.out.println("[Blog Service]: " + cVo);
		cDao.insert(cVo);
		System.out.println("[Blog Service]: " + cVo);

		int cateNo = cVo.getCateNo();

		return cDao.selectOne(cateNo);
	}

	/* 카테고리 데이터 가져오기 */
	public List<CategoryVo> cateName(String id) {

		System.out.println("[Blog Service]: cateName(String id) 연결");

		return cDao.cateSelect(id);
	}

	/* 카테고리 삭제 */
	public int remove(int cateNo) {

		System.out.println("[Blog Service]: remove(int cateNo) 연결");

		return cDao.delete(cateNo);
	}

	/* 메인화면 */
	public Map<String, Object> side(String id, int cateNo, int postNo) {

		System.out.println("[Blog Service]: Map<String, Object> side(String id, int cateNo, int postNo) 연결");

		Map<String, Object> bMap = new HashMap<String, Object>();

		/* #1. 카테고리 */
		List<CategoryVo> cList = cDao.cateSelect(id);

		/* 로딩시 기본값은 가장 상단의 카테고리 가 선택되어 집니다. */
		if (cateNo == 0) {

			cateNo = cList.get(0).getCateNo();

		}

		/* #2. 각 카테고리의 포스트 */
		List<PostVo> pList = pDao.selectList(cateNo);

		/* 기본값으로 #5영역의 가장 최근 포스트가 출력됩니다. */
		if (pList.size() == 0) {

			if (postNo == 0) {

				postNo = pList.get(0).getPostNo();

			}
		}

		/* #3. 포스트 조회 */
		PostVo pVo = pDao.selectOne(postNo);

		bMap.put("CateList", cList);
		bMap.put("PostList", pList);
		bMap.put("PostVo", pVo);

		System.out.println(bMap);

		return bMap;
	}

}
