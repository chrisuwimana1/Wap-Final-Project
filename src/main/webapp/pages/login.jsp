<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="login" method="post">
		<div class="container">
			<label for="uname"><b>Username</b></label> 
			<input type="text" placeholder="Enter Username" name="uname" required>
			
			 
			<label for="psw"><b>Password</b></label> 
			<input type="password" placeholder="Enter Password" name="psw" required>

			<button type="submit">Login</button>
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form>
</body>
</html>