<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="jsps/css/style.css" />
</head>
<body>
	<div id="wrap">

		<!-- 包含 head.jsp -->
		<jsp:include page="files/head.jsp"></jsp:include>

		<div class="center_content">
			<div class="left_content">
				<div class="title">
					<span class="title_icon"><img src="jsps/images/bullet1.gif"
						alt="" title="" />最热图书</span>
				</div>

				<!-- 最热图书 -->
				<c:forEach items="${books }" var="book">
					<div class="feat_prod_box">
						<div class="prod_img">
							<a href="view-Price?bid=${book.bid }"><img width="130"
								height="150" src="jsps/${book.image_b }" alt="${book.bname }"
								title="${book.bname }" border="0" /></a>
						</div>
						<div class="prod_det_box">
							<div class="box_top"></div>
							<div class="box_center">
								<div class="prod_title">Product name</div>
								<p class="details">${book.bname }</p>
								<a href="view-Price?bid=${book.bid }" class="more">- more
									details -</a>
								<div class="clear"></div>
							</div>
							<div class="box_bottom"></div>
						</div>
						<div class="clear"></div>
					</div>
				</c:forEach>

				<div class="title">
					<span class="title_icon"><img src="jsps/images/bullet2.gif"
						alt="" title="" /></span>New books
				</div>
				<div class="new_products">

					<!-- 新书列表 -->
					<c:forEach items="${newBooks }" var="newBook">
						<div class="new_prod_box">
							<div class="new_prod_bg">
								<span class="new_icon"><img
									src="jsps/images/new_icon.gif" alt="${newBook.bname }"
									title="${newBook.bname }" /></span> <a
									href="view-Price?bid=${newBook.bid }"><img width="120" height="120"
									src="jsps/${newBook.image_b }" alt="${newBook.bname }"
									title="${newBook.bname }" class="thumb" border="0" /></a>
							</div>
						</div>
					</c:forEach>

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
	<div style="text-align: center;">
		<p>
			&#26469;&#28304;:<a href="http://www.mycodes.net/" target="_blank">&#28304;&#30721;&#20043;&#23478;</a>
		</p>
	</div>
</body>
</html>
