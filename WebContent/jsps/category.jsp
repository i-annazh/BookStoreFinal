<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="jsps/css/style.css" />

<script type="text/javascript">
	function searchClick() {
		var serachObj = document.getElementById("search");
		var bookNameObj = document.getElementById("bookName");
		serachObj.setAttribute("href", "view-Books?bname=" + bookNameObj.value);
	}
</script>

</head>
<body>
	<div id="wrap">
		<jsp:include page="files/head.jsp"></jsp:include>
		<div class="center_content">
			<div class="left_content">
				<div class="crumb_nav">
					<a href="view-IndexPage">home</a> &gt;&gt; category name
				</div>
				<div class="crumb_nav">
					<input type="text" id="bookName" name="bookName" value="" /> <a
						onclick="searchClick()" id="search" href="view-Books"><span
						class="current">搜索</span></a>
				</div>
				<div class="title">
					<span class="title_icon"><img src="jsps/images/bullet1.gif"
						alt="" title="" /></span>Category books
				</div>
				<div class="new_products">

					<c:forEach items="${pageBooks }" var="book">
						<div class="new_prod_box">
							<div class="new_prod_bg">
								<a href="view-Price?bid=${book.bid }"><img width="110"
									height="120" src="jsps/${book.image_b }" alt="${book.bname }"
									title="${book.bname }" class="thumb" border="0" /></a>
							</div>
						</div>
					</c:forEach>

					<div class="pagination">
						<c:if test="${newPage.currPage == 1 }">
							<span class="disabled"> &lt;&lt; </span>
						</c:if>
						<c:if test="${newPage.currPage != 1 }">
							<a href="view-Books?currPage=0&bname=${newPage.bname}">&lt;&lt;</a>
						</c:if>

						<c:forEach varStatus="statue" begin="1"
							end="${newPage.total }">

							<c:if test="${newPage.currPage == statue.index -1 }">
								<span class="current"><c:out value="${statue.index}"></c:out>
								</span>
							</c:if>
							<c:if test="${newPage.currPage  != statue.index - 1 }">
								<a
									href="view-Books?currPage=${statue.index }&bname=${newPage.bname}">
									<c:out value="${statue.index} "></c:out>
								</a>
							</c:if>

						</c:forEach>

						<c:if test="${newPage.currPage == newPage.total }">
							<span class="disabled"> >> </span>
						</c:if>
						<c:if test="${newPage.currPage != newPage.total }">
							<a
								href="view-Books?currPage=${newPage.total}&bname=${newPage.bname}">>></a>
						</c:if>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<!--end of left content-->
			<jsp:include page="files/right.jsp"></jsp:include>
			<!--end of right content-->
			<div class="clear"></div>
		</div>
		<!--end of center content-->
		<jsp:include page="files/foot.jsp"></jsp:include>
	</div>
</body>
</html>
