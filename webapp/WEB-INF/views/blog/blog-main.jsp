<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">

					<c:choose>
						<c:when test="${BlogVo.logoFile == null }">
							<!-- 기본이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
						</c:when>

						<c:otherwise>
							<!-- 사용자업로드 이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/setting/${BlogVo.logoFile}">
						</c:otherwise>
					</c:choose>

					<div id="nick">${BlogVo.userName}(${BlogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${bMap.CateList }" var="cList">
							<li><a href="${pageContext.request.contextPath}/${BlogVo.id}?cateNo=${cList.cateNo}">${cList.cateName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->

			<div id="post_area">

				<c:choose>
					<c:when test="${bMap.PostVo != null}">
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left">
								<strong>${bMap.PostVo.postTitle}</strong>
							</div>
							<div id="postDate" class="text-left">
								<strong>${bMap.PostVo.regDate}</strong>
							</div>
							<div id="postNick">${BlogVo.userName}(${BlogVo.id})님</div>
						</div>
						<!-- //postBox -->
						<div id="post">${bMap.PostVo.postContent}</div>
					</c:when>
					<c:otherwise>
						<!-- 글이 없는 경우 -->
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left">
								<strong>등록된 글이 없습니다.</strong>
							</div>
							<div id="postDate" class="text-left">
								<strong></strong>
							</div>
							<div id="postNick"></div>
						</div>
						<div id="post"></div>
						<!-- //postBox -->
					</c:otherwise>
				</c:choose>

				<div id="list">
					<div id="listTitle" class="text-left">
						<strong>카테고리의 글</strong>
					</div>
					<table id="PostList">
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>

						<c:forEach items="${bMap.PostList}" var="pList">
							<tr>
								<td class="text-left"><a href="${pageContext.request.contextPath }/${BlogVo.id}?cateNo=${pList.cateNo}&postNo=${pList.postNo}">${pList.postTitle}</a></td>
								<td class="text-right">${pList.regDate}</td>
							</tr>
						</c:forEach>

					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->



		</div>
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>



	</div>
	<!-- //wrap -->
</body>
</html>