<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Users</title>

<link href="myCSS/allusers.css" rel="stylesheet">
</head>
<body>

	view users here

	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone</th>
			<th>Email</th>
		</tr>

		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.firstname }</td>
				<td>${user.lastname }</td>
				<td>${user.phone }</td>
				<td>${user.email }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>