<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function searchClick() {
		var serachObj = document.getElementById("search");
		var bookNameObj = document.getElementById("bookName");
		serachObj.setAttribute("href", "admin-view?bname=" + bookNameObj.value);
	}
</script>
</head>
<body>


	<div align="center">

		<br /> <br /> <br /> <input id="bookName" width="200px" type="text"
			name="bname" /><a id="search" onclick="searchClick()" href="">
			查询</a> <a href="admin-add">增加</a> <br /> <br /> <br />

		<table cellspacing="0" cellpadding="0" border="2px">
			<thead>
				<tr>
					<td>图片</td>
					<td>书名</td>
					<td>作者</td>
					<td>当前价</td>
					<td>折扣</td>
					<td>出版时间</td>
					<td>修改</td>
					<td>删除</td>
				</tr>
			</thead>

			<c:forEach items="${books }" var="book">
				<tr>
					<td><img title="${book.bname}" alt="${book.bname}" width="100"
						height="100" src="jsps/${book.image_b }" /></td>
					<td>${book.bname }</td>
					<td>${book.author }</td>
					<td>${book.currPrice }</td>
					<td>${book.discount }</td>
					<td>${book.publishtime }</td>
					<td><a href="admin-update?bid=${book.bid }">修改</a></td>
					<td><a href="admin-delete?bid=${book.bid }">删除</a></td>
				</tr>
			</c:forEach>

		</table>
		<br> <br> 第<label> ${page.currPage } </label>页
		<c:if test="${page.currPage-1 > 0 }">
			<a href="admin-view?currPage=${page.currPage-1 }">上一页</a>
		</c:if>

		<c:forEach begin="1" end="5" var="aaa">
			<c:if test="${page.currPage + aaa < page.total}">
				<a href="admin-view?currPage=${page.currPage + aaa }"><label>${page.currPage + aaa}</label></a>
			</c:if>
		</c:forEach>

		<c:if test="${page.currPage+1 <= page.total }">
			<a href="admin-view?currPage=${page.currPage+1 }">下一页</a>
		</c:if>

		总<label> ${page.total }</label>页
	</div>
</body>
</html>