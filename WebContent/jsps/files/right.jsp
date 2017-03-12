<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div class="right_content">
	<div class="languages_box">
		<span class="red">Languages:</span> <a href="#" class="selected"><img
			src="jsps/images/gb.gif" alt="" title="" border="0" /></a> <a href="#"><img
			src="jsps/images/fr.gif" alt="" title="" border="0" /></a> <a href="#"><img
			src="jsps/images/de.gif" alt="" title="" border="0" /></a>
	</div>
	<div class="currency">
		<span class="red">Currency: </span> <a href="#">GBP</a> <a href="#">EUR</a>
		<a href="#" class="selected">USD</a>
	</div>
	<div class="cart">
		<div class="title">
			<span class="title_icon"><img src="jsps/images/cart.gif"
				alt="" title="" /></span>My cart
		</div>
		<div class="home_cart_content">
			<c:out value="${Cart.count }"></c:out>
			items | <span class="red">TOTAL: <c:out value="${Cart.total }"></c:out>$
			</span>
		</div>
		<a href="view-Cart" class="view_cart">view cart </a><a
			class="view_cart"><font color="red"><c:out
					value="${sessionScope.User.loginname }">
				</c:out></font></a>
	</div>
	<div class="title">
		<span class="title_icon"><img src="jsps/images/bullet3.gif"
			alt="" title="" /></span>About Our Store
	</div>
	<div class="about">
		<p>
			<img src="jsps/images/about.gif" alt="" title="" class="right" />
			Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
			eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
			ad minim veniam, quis nostrud.
		</p>
	</div>
	<div class="right_box">
		<div class="title">
			<span class="title_icon"><img src="jsps/images/bullet4.gif"
				alt="" title="" /></span>打折图书
		</div>

		<c:forEach items="${discountBooks}" var="book">
			<div class="new_prod_box">
				<div class="new_prod_bg">
					<span class="new_icon"><img src="jsps/images/promo_icon.gif"
						alt="${book.bname }" title="${book.bname }" /></span> <a
						href="view-Price?bid=${book.bid }"><img width="100"
						height="100" src="jsps/${book.image_b }" alt="" ${book.bname }
						title="${book.bname }" class="thumb" border="0" /></a>
				</div>
			</div>
		</c:forEach>

	</div>
	<div class="right_box">
		<div class="title">
			<span class="title_icon"><img src="jsps/images/bullet5.gif"
				alt="" title="" /></span>图书分类
		</div>
		<ul class="list">

			<c:forEach items="${categorys }" var="category">
				<li><a href="view-Books?cid=${category.cid }">${category.cname }</a></li>
			</c:forEach>

		</ul>
	</div>
</div>