<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<!-- Sidebar on click -->

<div class="w3-sidebar w3-bar-block w3-white w3-card w3-animate-left " style="position:absolute;z-index:2" id="mySidebar">
  <br>
  <br>
  
  
  <!-- Bototones para la gestión de usuarios -->
  <shiro:hasAnyRoles name="administrador,gestionusuarios">
    <button  class="w3-bar-item w3-button" name="gestUsuarios" value="gestUsuarios" onclick="desplegarMenu(this)">Gestión Usuarios</button>
    </shiro:hasAnyRoles>
    
    <div id="gestUsuarios" style="display:none;padding-left:30px;">
	    <form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDProfesor" name="CRUDProfesor">Docentes</button></form> 
	   	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="NoDocentes" name="NoDocentes">No Docentes</button></form>
	    <form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="Permisos" name="Permisos">Permisos</button></form>
    </div>
    

    <!-- Botones para las gestión de docencia -->
    <shiro:hasAnyRoles name="administrador,gestiondocencia">
    <button  class="w3-bar-item w3-button"  name="gestDocencia" value="gestDocencia" onclick="desplegarMenu(this)">Gestión Docencia</button>
    </shiro:hasAnyRoles>
    
    <div id="gestDocencia" style="display:none;padding-left:30px;">
    	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDPlan" name="CRUDPlan">Plan de Estudios</button></form>
   	  	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDAsignatura" name="CRUDAsignatura">Asignaturas</button></form>
  	</div>
  	
  	
  	<!-- Botones para gestión de datos -->
  	<shiro:hasAnyRoles name="administrador,gestiondatos">
  	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="Backup" name="Backup">Gestionar copias de seguridad</button></form>
  	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="Export" name="Export">Exportar datos</button></form>
  	</shiro:hasAnyRoles>
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
  
  <shiro:hasAnyRoles name="administrador,gestiondocencia,gestionusuarios,gestiondatos">
  
  <form action="PasoGestorServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white">Gestor</button></form>
  
  </shiro:hasAnyRoles>
  
  <form action="LogoutServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white w3-right">Logout</button></form>
 </div>
</div>