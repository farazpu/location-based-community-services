<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html dir="ltr" lang="en-US">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title>Login</title>

	<link rel="stylesheet" href="../css/style-login.css" type="text/css" />

	</head>

	<body>
		<div id="container">
			<form action="redirect-to-main.jsp" method="post">
				<div class="login">LOGIN</div>
				<div class="username-text">Username:</div>
				<div class="password-text">Password:</div>
				<div class="username-field">
					<input type="text" name="user" />
				</div>
				<div class="password-field">
					<input type="password" name="password" />
				</div>
				<input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">Remember me</label>
				<div class="forgot-usr-pwd"></div>
				<input type="submit" name="submit" value="GO" />
			</form>
		</div>
		<div id="footer">
			Location Based Community Service - Administration
		</div>
	</body>
</html>
