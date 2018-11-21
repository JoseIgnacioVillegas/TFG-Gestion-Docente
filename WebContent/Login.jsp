<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
	
<title>Login - Gestion de departamento</title>
	
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body >
 
<div align="center">
<form action="LoginServlet">
								
	<p>Introduce el Email: <input name="email" id="email" value="">
	</p>							
	<p>Introduce la contraseña: <input name="pass" id="pass" value=""> </p>
								
	<button type="submit" style="background-color: rgb(45, 59, 121); border: none; color: white; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; margin: 4px 2px; cursor: pointer ">Login</button>
							 
</form>
<p style="color:#FF0000";>${error}</p>
	
</div>

</body>

</html>