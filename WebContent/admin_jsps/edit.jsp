<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
	<h1><a href="admin-view">首页</a></h1>
		<form action="admin-${action }" method="post" enctype="multipart/form-data">
		书名:<input name="bname" type="text" value="${book.bname }"/><br/>
		作者:<input name="author" type="text" value="${book.author}"/><br/>
		当前价:<input name="currPrice"  type="text" value="${book.currPrice}"/><br/>
		折扣:<input name="discount"  type="text" value="${book.discount}"/><br/>
		出版社:<input name="press"  type="text" value="${book.press}"/><br/>
		出版时间:<input name="publishtime"  type="text" value="${book.printtime}"/><br/>
		版次:<input name="edition"  type="text" value="${book.edition}"/><br/>
		页数:<input name="pageNum"  type="text" value="${book.pageNum}"/><br/>
		字数:<input name="wordNum"  type="text" value="${book.wordNum}"/><br/>
		开售时间:<input name="printtime"  type="text" value="${book.printtime}"/><br/>
		书的大小:<input name="booksize"  type="text" value="${book.booksize}"/><br/>
		分类:
		<select name="cid">
			<c:forEach items="${categorys }" var="category">
				<option value="${category.cid }">${category.cname }</option>
			</c:forEach>
		</select>
		<br/>
		大图:<input name="image_ww" type="file"/><br/>
		<img alt="" width="150" height="150" src="jsps/${book.image_w}">
		<br/>
		小图:<input name="image_bb"  type="file"/>
		<br/>
		<input type="submit"/>
	</form> 
	<br/>
		<h1><font color="red"><label>${msg }</label></font></h1>
	</div>
</body>
</html>