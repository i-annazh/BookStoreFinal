<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						alt="" title="" /></span>Contact Us
				</div>
				<div class="feat_prod_box_details">
					<p class="details">Lorem ipsum dolor sit amet, consectetur
						adipisicing elit, sed do eiusmod tempor incididunt ut labore et
						dolore magna aliqua. Ut enim ad minim veniam, quis nostrud. Lorem
						ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
						tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
						minim veniam, quis nostrud.</p>
					<div class="contact_form">
						<div class="form_subtitle">all fields are requierd</div>
						<div class="form_row">
							<label class="contact"><strong>Name:</strong></label> <input
								type="text" class="contact_input" />
						</div>
						<div class="form_row">
							<label class="contact"><strong>Email:</strong></label> <input
								type="text" class="contact_input" />
						</div>
						<div class="form_row">
							<label class="contact"><strong>Phone:</strong></label> <input
								type="text" class="contact_input" />
						</div>
						<div class="form_row">
							<label class="contact"><strong>Company:</strong></label> <input
								type="text" class="contact_input" />
						</div>
						<div class="form_row">
							<label class="contact"><strong>Message:</strong></label>
							<textarea class="contact_textarea"></textarea>
						</div>
						<div class="form_row">
							<a href="#" class="contact">send</a>
						</div>
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
