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
						alt="" title="" /></span>My cart
				</div>
				<div class="feat_prod_box_details">
					<table class="cart_table">
						<tr class="cart_title">
							<td>Item pic</td>
							<td>Book name</td>
							<td>Quantity</td>
							<td>Unit price</td>
							<td>Delete</td>
						</tr>

						<c:forEach items="${cartItems}" var="item">
							<tr>
								<td><a href="view-Price?bid=${item.book.bid }"><img
										width="60" height="80" src="jsps/${item.book.image_b }"
										alt="${item.book.bname }" title="${item.book.bname }"
										border="0" class="cart_thumb" /></a></td>

								<td><c:out value="${item.book.bname }"></c:out></td>
								<td><c:out value="${item.quantity }"></c:out></td>
								<td><c:out value="${item.book.currPrice }"></c:out></td>
								<td><a href="cart-DeleteBook?bid=${item.book.bid }">删除</a></td>
							</tr>

						</c:forEach>
						<tr>
							<td colspan="4" class="cart_total"><span class="red">TOTAL:</span></td>
							<td><c:out value="${Cart.total }"></c:out>$</td>
						</tr>
					</table>
					<!-- 分页 -->
					<div class="pagination">
						<c:if test="${newPage.currPage == 0 }">
							<span class="disabled"> &lt;&lt; </span>
						</c:if>
						<c:if test="${newPage.currPage != 0 }">
							<a href="view-Cart?currPage=0">&lt;&lt;</a>
						</c:if>

						<c:if test="${newPage.total != 0}">
							<c:forEach varStatus="statue" begin="0"
								end="${newPage.total - 1 }">

								<c:if test="${newPage.currPage == statue.index }">
									<span class="current"><c:out
											value="${statue.index + 1 }"></c:out> </span>
								</c:if>
								<c:if test="${newPage.currPage  != statue.index }">
									<a href="view-Cart?currPage=${statue.index }"> <c:out
											value="${statue.index + 1 } "></c:out>
									</a>
								</c:if>

							</c:forEach>
						</c:if>

						<c:if test="${newPage.currPage == newPage.total - 1 }">
							<span class="disabled"> >> </span>
						</c:if>
						<c:if test="${newPage.currPage != newPage.total - 1 }">
							<a href="view-Cart?currPage=${newPage.total -1 }">>></a>
						</c:if>
					</div>
					<a href="view-Books" class="continue">&lt; continue</a> <a href="#"
						class="checkout">checkout &gt;</a>
				</div>
				<div class="clear"></div>
			</div>

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
