<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Edit user using this form</h1>
	<form action="/edituser" method="post">
	
	Username <input type="text" name="username" value="${user.username}"> <br>
	Password <input type="text" name="password" value="${user.password}"> <br>
	Firstname <input type="text" name="firstname" value="${user.firstname}"> <br>
	LastName <input type="text" name="lastname" value="${user.lastname}"> <br>
	Phone <input type="text" name="phone" value="${user.phone}"> <br>
	Email <input type="text" name="email" value="${user.email}"> <br> 
	
	<input type="submit" value="Edit">
	</form>

</body>
</html>