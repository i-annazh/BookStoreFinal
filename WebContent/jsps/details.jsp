<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Store</title>
<link rel="stylesheet" type="text/css" href="jsps/css/style.css" />
<link rel="stylesheet" href="jsps/css/lightbox.css" type="text/css"
	media="screen" />
<script src="jsps/js/prototype.js" type="text/javascript"></script>
<script src="jsps/js/scriptaculous.js?load=effects"
	type="text/javascript"></script>
<script src="jsps/js/lightbox.js" type="text/javascript"></script>
<script type="text/javascript" src="jsps/js/java.js"></script>
<script type="text/javascript">
	var tabber1 = new Yetii({
		id : 'demo'
	});
</script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="files/head.jsp" />
		<div class="center_content">
			<div class="left_content">
				<div class="crumb_nav">
					<a href="view-IndexPage">home</a> &gt;&gt; product name
				</div>
				<div class="title">
					<span class="title_icon"><img src="jsps/images/bullet1.gif"
						alt="" title="" /></span>Product name
				</div>
				<div class="feat_prod_box_details">
					<div class="prod_img">
						<a href="view-Price?bid=${book.bid }"><img width="140"
							height="170" src="jsps/${book.image_w }" alt="${book.bname }"
							title="${book.bname }" border="0" /></a> <br /> <br /> <a
							href="jsps/${book.image_w }" rel="lightbox"><img
							src="jsps/images/zoom.gif" alt="${book.bname }"
							title="${book.bname }" border="0" /></a>
					</div>
					<div class="prod_det_box">
						<div class="box_top"></div>
						<div class="box_center">
							<div class="prod_title">Details</div>
							<p class="details">
								<c:out value="${book.bname }"></c:out>
							</p>
							<div class="price">
								<strong>PRICE:</strong> <span class="red"><c:out
										value="${book.price }"></c:out> $</span>
							</div>
							<div class="price">
								<strong>COLORS:</strong> <span class="colors"><img
									src="jsps/images/color1.gif" alt="" title="" border="0" /></span> <span
									class="colors"><img src="jsps/images/color2.gif" alt=""
									title="" border="0" /></span> <span class="colors"><img
									src="jsps/images/color3.gif" alt="" title="" border="0" /></span>
							</div>
							<a href="cart-InsertBook?bid=${book.bid }" class="more"><img
								src="jsps/images/order_now.gif" alt="" title="" border="0" /></a>
							<div class="clear"></div>
						</div>
						<div class="box_bottom"></div>
					</div>
					<div class="clear"></div>
				</div>
				<div id="demo" class="demolayout">
					<ul id="demo-nav" class="demolayout">
						<li><a class="" href="#tab2">Related books</a></li>
					</ul>
					<div class="tabs-container">
						<div style="display: block;" class="tab" id="tab1">

							<c:forEach items="${ relativeBooks}" var="relativeBook">

								<div class="new_prod_box">
									<div class="new_prod_bg">
										<a href="view-Price?bid=${relativeBook.bid }"><img
											width="140" height="160" src="jsps/${relativeBook.image_b }"
											alt="${relativeBook.bname }" title="${relativeBook.bname }"
											class="thumb" border="0" /></a>
									</div>
								</div>
							</c:forEach>
						</div>

					</div>
				</div>
				<div class="clear"></div>
			</div>
			<!--end of left content-->
			<jsp:include page="files/right.jsp" />
			<!--end of right content-->
			<div class="clear"></div>
		</div>
		<!--end of center content-->
		<jsp:include page="files/foot.jsp"></jsp:include>
	</div>
</body>

</html>
