<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>

<head>
    <title>Login Page</title>
</head>

<body>
<font color="red">${errorMessage}</font>
<form action="LoginServlet" method="post">
    Name : <input type="text" name="name" />
    Password : <input type="password" name="password" />
    <input type="submit" value="Login" />
</form>
</body>

</html>