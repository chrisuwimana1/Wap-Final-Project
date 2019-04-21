<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	Hello you can add user using the following page

	<form action="login" method="post">
		<label for="uname"><b>Username</b></label> <input type="text"
			placeholder="Enter Username" name="uname" required> <label
			for="psw"><b>Password</b></label> <input type="password"
			placeholder="Enter Password" name="psw" required>

		<button type="submit">Login</button>
	</form>

</body>
</html>