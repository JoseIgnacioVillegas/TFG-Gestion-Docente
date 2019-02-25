<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
	html {
	  min-height: 100%;
	  position: relative;
	}
	body {
	  margin: 0;
	  margin-bottom: 40px;
	}
	footer {
	  background-color: black;
	  position: absolute;
	  bottom: 0;
	  width: 100%;
	  height: 200px;
	  color: white;
	}
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Gestion de departamento</title>
<link rel="icon" type="image/gif" href="./img/ditupm.gif"/>
</head>

<body id="myPage">

<!-- Sidebar on click -->

<div class="w3-sidebar w3-bar-block w3-white w3-card w3-animate-left " style="position:absolute;z-index:2" id="mySidebar">
  <br>
  <br>
    <button  class="w3-bar-item w3-button" name="gestUsuarios" value="gestUsuarios" onclick="desplegarMenu(this)">Gestion Usuarios</button>
    <div id="gestUsuarios" style="display:none;padding-left:30px;">
	    <shiro:hasRole name="gestor"><form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDProfesor" name="CRUDProfesor">Docentes</button></form> </shiro:hasRole>
	   	<shiro:hasRole name="gestor"><form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="NoDocentes" name="NoDocentes">No Docentes</button></form></shiro:hasRole>
	    <shiro:hasAnyRoles name="asignarpermisos"><form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="Permisos" name="Permisos">Permisos</button></form></shiro:hasAnyRoles>
    </div>
    
    <!--  div id="gestUsuarios" style="display:none;padding-left:30px;">
	    <shiro:hasAnyRoles name="crearprofesor,borrarprofesor,editarprofesor,creargrupo,editargrupo,borrargrupo,crearplaza,editarplaza,borrarplaza"><form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDProfesor" name="CRUDProfesor">Docentes</button></form> </shiro:hasAnyRoles>
	   	<shiro:hasAnyRoles name="crearusuario,borrarusuario,editarusuario,leerusuario"><form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="NoDocentes" name="NoDocentes">No Docentes</button></form></shiro:hasAnyRoles>
	    <shiro:hasAnyRoles name="asignarpermisos"><form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="Permisos" name="Permisos">Permisos</button></form></shiro:hasAnyRoles>
    </div-->
   
    
    <button  class="w3-bar-item w3-button"  name="gestDocencia" value="gestDocencia" onclick="desplegarMenu(this)">Gestion Docencia</button>
    <div id="gestDocencia" style="display:none;padding-left:30px;">
    	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDPlan" name="CRUDPlan">Plan de Estudios</button></form>
   	  	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDAsignatura" name="CRUDAsignatura">Asignaturas</button></form>
  	</div>
  	<form action="GestorBBDD.jsp" ><button type="submit" class="w3-bar-item w3-button" value="Backup" name="Backup">Gestionar copias de seguridad</button></form>
  	<form action="ExportarDatos.jsp" ><button type="submit" class="w3-bar-item w3-button" value="Backup" name="Backup">Exportar datos</button></form>
</div>



 
 <!-- Navbar -->
<div class="w3-top" style="position:absolute;z-index:3">
 <div class="w3-bar w3-theme-d2 w3-left-align">
  <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
  <form action="VistaInicial.jsp"><button class="w3-bar-item w3-button w3-teal"><i class="fa fa-home w3-margin-right"></i>Inicio</button></form>
  
  <shiro:hasRole name="profesor">
  <form action="ProfesorServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white">Profesor</button></form>
  </shiro:hasRole>
  
  <shiro:hasRole name="coordinador">
  <form action="CoordinadorServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white">Coordinador</button></form>
  </shiro:hasRole>
  
  <shiro:hasRole name="gestor">
  <form action="PasoGestorServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white">Gestor</button></form>
  </shiro:hasRole>
  
  <form action="LogoutServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white w3-right">Logout</button></form>
 </div>
</div>
 



<!-- Team Container -->
<div class="w3-container w3-padding-64 w3-center" id="team">
<br>
	<h1>Bienvenido a la pantalla de gestión</h1>
	<h2>Selecciona lo que quieres hacer</h2>

</div>

<div class="w3-display-container w3-animate-opacity" style="text-align:center;">
  <img src="./img/ditupm.gif" alt="icono" style="width:20%;height:20%;" >
</div>






<!-- Footer -->
<footer class="w3-padding-32 w3-center" >
  <h4>Enlaces de interés</h4>
  <a class="w3-button w3-large w3-teal" href="https://www.dit.upm.es/" title="DIT"><img src="./img/ditupm.gif" style="width:30px;height:30px;"></a>
  <a class="w3-button w3-large w3-teal" href="http://www.etsit.upm.es/" title="ETSIT"><img src="./img/etsit.gif" style="width:30px;height:30px;"></a>
  <a class="w3-button w3-large w3-teal" href="https://moodle.upm.es/" title="MOODLE"><img src="./img/moodle.gif" style="width:30px;height:30px;"></a>
  <a class="w3-button w3-large w3-teal" href="http://www.upm.es/" title="UPM"><img src="./img/upm.gif" style="width:30px;height:30px;"></a>
  <p>TFG Gestión docente - 2019</p>
  <!--  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>  -->
</footer>






<script>
function desplegarMenu(obj){ 	
   	if(obj.value == "gestUsuarios" && document.getElementById('gestUsuarios').style.display=="none" )	{
   		document.getElementById('gestUsuarios').style.display="";
   	}else if(obj.value == "gestUsuarios" && document.getElementById('gestUsuarios').style.display==""){
		document.getElementById('gestUsuarios').style.display="none";
   	}
   	
	if(obj.value == "gestDocencia" && document.getElementById('gestDocencia').style.display=="none"){
		document.getElementById('gestDocencia').style.display="";
   	}else if(obj.value == "gestDocencia" && document.getElementById('gestDocencia').style.display==""){
   		document.getElementById('gestDocencia').style.display="none";
   	}
} 



// Script for side navigation




</script>
</body>
</html>