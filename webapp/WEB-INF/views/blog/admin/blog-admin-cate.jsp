<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/postForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->

						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="desc"></td>
					</tr>
				</table>

				<div id="btnArea">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->

		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>

	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	/* DOM 생성 */
	$("document").ready(function() {
		console.log("ready")

		var id = "${authUser.id}"
		console.log(id)

		/* 카테고리 리스트 */
		$.ajax({
			url : "${pageContext.request.contextPath }/admin/cateList",
			type : "post",
			//contentType : "application/json",
			data : {
				id : id
			},

			dataType : "json",
			success : function(cateList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(cateList)

				for (var i = 0; i < cateList.length; i++) {
					render(cateList[i], "down")
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error)
			}
		})

	})

	function render(categoryVo, updown) {
		var str = '';
		str += '<tr id="t-' + categoryVo.cateNo + '">';
		str += '	<td>' + categoryVo.cateNo + '</td>';
		str += '	<td>' + categoryVo.cateName + '</td>';
		str += '	<td>' + categoryVo.postCnt + '</td>';
		str += '	<td>' + categoryVo.description + '</td>';
		str += '	<td class="text-center">';
		str += '		<img data-cateno="' + categoryVo.cateNo + '" data-postcnt="' + categoryVo.postCnt + '" class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	</td>';
		str += '</tr>';

		if (updown == "up") {
			$("#cateList").prepend(str);
		} else if (updown == "down") {
			$("#cateList").append(str);
		} else {
			console.log("updown 미지정")
		}
	}

	/* 카테고리 추가 */
	$("#btnAddCate").on("click", function() {
		console.log("카테고리추가 클릭")

		var id = '${authUser.id}'
		console.log(id)

		var cateName = $("[name='name']").val()
		console.log(cateName)

		var description = $("[name='desc']").val()
		console.log(description)

		$.ajax({

			url : "${pageContext.request.contextPath}/admin/cateAdd",
			type : "post",
			//contentType : "application/json",		
			data : {
				cateName : cateName,
				description : description,
				id : id
			},
			dataType : "json",
			success : function(categoryVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(categoryVo)
				render(categoryVo, "up")

				/* 입력폼 리셋 */
				$("[name='name']").val("")
				$("[name='desc']").val("")

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error)
			}
		})
	})

	/* 카테고리 삭제 */
	/* 포스트가 있으면 삭제 불가 */
	$("#cateList").on("click", "img", function() {
		console.log("삭제아이콘 클릭")

		var cateNo = $(this).data("cateno")
		console.log(cateNo)

		var postCnt = $(this).data("postcnt")
		console.log(postCnt)

		if (postCnt > 0) {
			/* postCnt > 0 >> 포스트가 1개 이상 존재 >> 삭제 불가 */
			/* 삭제를 실패하면.. */
			alert("삭제할 수 없습니다.")
		} else {
			/* 삭제를 성공하면.. */
			$.ajax({

				url : "${pageContext.request.contextPath}/admin/remove",
				type : "post",
				//contentType : "application/json",
				data : {
					cateNo : cateNo
				},

				dataType : "json",
				success : function(result) {
					/*성공시 처리해야될 코드 작성*/
					/* 성공(result = 1) */
					console.log(result)

					$("#t-" + cateNo).remove()
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		}
	});
</script>

</html>