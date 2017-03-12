<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="jsps/css/style.css" />
</head>
<body>
	<div id="wrap">
		<jsp:include page="files/head.jsp"></jsp:include>
		<div class="center_content">
			<div class="left_content">
				<div class="title">
					<span class="title_icon"><img src="jsps/images/bullet1.gif"
						alt="" title="" /></span>Special gifts
				</div>

				<c:forEach items="${ specialBooks}" var="specialBooks">
					<div class="feat_prod_box">
						<div class="prod_img">
							<a href="view-Price?bid=${specialBooks.bid }"><img
								width="130px" height="150" src="jsps/${specialBooks.image_b }"
								alt="${specialBooks.bname }" title="${specialBooks.bname }"
								border="0" /></a>
						</div>
						<div class="prod_det_box">
							<span class="special_icon"><img
								src="jsps/images/promo_icon.gif" alt="" title="" /></span>
							<div class="box_top"></div>
							<div class="box_center">
								<div class="prod_title">Product name</div>
								<p class="details">
									<c:out value="${specialBooks.bname }"></c:out>
								</p>
								<a href="view-Price?bid=${specialBooks.bid }" class="more">-
									more details -</a>
								<div class="clear"></div>
							</div>
							<div class="box_bottom"></div>
						</div>
						<div class="clear"></div>
					</div>
				</c:forEach>

				<div class="pagination">
					<c:if test="${newPage.currPage == 0 }">
						<span class="disabled"> &lt;&lt; </span>
					</c:if>
					<c:if test="${newPage.currPage != 0 }">
						<a href="view-SpecialBooks?currPage=1">&lt;&lt;</a>
					</c:if>

					<c:forEach varStatus="statue" begin="1" end="${newPage.total }">

						<c:if test="${newPage.currPage == statue.index - 1 }">
							<span class="current"><c:out value="${statue.index  }"></c:out>
							</span>
						</c:if>
						<c:if test="${newPage.currPage  != statue.index - 1 }">
							<a href="view-SpecialBooks?currPage=${statue.index }"> <c:out
									value="${statue.index } "></c:out>
							</a>
						</c:if>

					</c:forEach>

					<c:if test="${newPage.currPage == newPage.total }">
						<span class="disabled"> >> </span>
					</c:if>
					<c:if test="${newPage.currPage != newPage.total }">
						<a href="view-SpecialBooks?currPage=${newPage.total  }">>></a>
					</c:if>
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
