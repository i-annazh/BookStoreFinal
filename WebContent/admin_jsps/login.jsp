<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function changeCode() {
		var img = document.getElementById("yzm");
		img.src = "valicodeAction?time=" + new Date().getTime();
	}
</script>


<style type="text/css">
/* 将登录表单居中 */
#login_div {
	margin-left: 600px;
	margin-top: 300px;
}
</style>



</head>
<body>
	<div id="login_div">
		<form action="admin-login" method="post">
			用户名: <input type="text" name="adminName" /><br /> 密&nbsp;码: <input
				type="password" name="adminPwd" /><br /> <br /> 验证码: <input
				type="text" name="vcode" /><img id="yzm" src="valicodeAction" /> <a
				href="javascript:changeCode()">换一张</a> <br /> <br /> <input
				type="submit" value="登录" /> <label><font color="red">${requestScope.loginMsg }</font></label>
		</form>
	</div>
</body>
</html>