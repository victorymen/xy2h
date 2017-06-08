<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
	<title>管理员登录</title>

	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div style="width:30%">
	<%--<form action="/loginController.jsp" method="post" align="center" class="form-signin">--%>
	<form action="/login.action" method="post" align="center" class="form-signin">
		<h2 align="center">登  录</h2>
		<input type="text" class="form-control" name="username" placeholder="UserName" required autofocus><br>
		<input type="password" class="form-control" name="password" placeholder="Password" required><br>
		<button class="btn btn-lg btn-primary btn-block" type="submit">登  录</button>
	</form>
</div>
</body>
</html>