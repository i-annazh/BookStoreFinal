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
		<jsp:include page="files/head.jsp"></jsp:include>
		<div class="center_content">
			<div class="left_content">
				<div class="title">
					<span class="title_icon"><img src="jsps/images/bullet1.gif"
						alt="" title="" /></span>My account
				</div>
				<div class="feat_prod_box_details">
					<p class="details">Lorem ipsum dolor sit amet, consectetur
						adipisicing elit, sed do eiusmod tempor incididunt ut labore et
						dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem
						ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
						tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
						minim veniam, quis nostrud.</p>
					<c:if test="${sessionScope.User != null }">
						<h1>
							<font color="red"> <c:out
									value="${sessionScope.User.loginname}"></c:out>
							</font> 登录成功<br />
							<br />
							<br /> <a
								href="user-Cancle?loginname=${sessionScope.User.loginname}">注销</a>
						</h1>
					</c:if>
					<c:if test="${sessionScope.User == null }">
						<div class="contact_form">
							<div class="form_subtitle">login into your account</div>
							<form name="register" action="user-Login" method="post">
								<div class="form_row">
									<label class="contact"><strong>Username:</strong></label> <input
										type="text" class="contact_input" name="loginname" />
								</div>
								<div class="form_row">
									<label class="contact"><strong>Password:</strong></label> <input
										type="password" class="contact_input" name="loginpass" />
								</div>
								<div class="form_row">
									<div class="terms">
										<input type="checkbox" name="terms" /> Remember me
									</div>
								</div>
								<div class="form_row">
									<c:out value="${ErrorMsg }"></c:out>
									<input type="submit" class="register" value="login" />
								</div>
							</form>
						</div>
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
