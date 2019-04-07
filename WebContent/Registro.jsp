<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en" >

<head>
<style>
/* Bordered form */
form {
  border: 3px solid #909090;
  width: 50%;
}
/* Full-width inputs */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}
/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}
/* Center the avatar image inside this container */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}
/* Add padding to containers */
.container {
  padding: 16px;
}
/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }
}
</style>
<title>Login - Gestion de departamento</title>
<link rel="icon" type="image/gif" href="ditupm.gif" />

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body >
 
<div align="center" style="padding-top: 10%;">
<c:if test="${not empty error}"><div>${error}</div></c:if>
	<c:if test="${not empty message}"><div>${message}</div></c:if>


<!-- LoginServlet -->
<form name='loginform' action="RegistroServlet" >


  <div class="imgcontainer">
    <img src="./img/ditupm.gif" alt="Icono"  style="width:150px;height:150px;">
  </div>



  <div class="container" >
  <label for="uname"><b>Nombre</b></label>
    <input type="text" placeholder="Introduzca su nombre" name="nombre" required>
    
    <label for="uname"><b>Apellidos</b></label>
    <input type="text" placeholder="Introduzca sus apellidos" name="apellidos" required>
    
    <label for="uname"><b>Email</b></label>
    <input type="text" placeholder="Introduzca Email" name="email" required>

    <label for="psw"><b>Contraseña</b></label>
    <input type="password" placeholder="Introduzca contraseña" name="password" required>


    <button type="submit" class="w3-button w3-large w3-teal">Registrar</button>

  </div>


</form>

</div>

</body>

</html>
