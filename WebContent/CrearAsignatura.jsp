<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>




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


<!-- En este archivo .jsp esta definido el menu principal y la barra lateral -->
<%@ include file="menu.jsp" %> 



<!-- Team Container -->


<div class="w3-container w3-padding-64 w3-center" id="team">
	
	<p style="color:#FF0000;">${mensaje}</p>
	
	<h2>Crear Asignatura</h2>

	


	
	<p name="mensaje"></p>
	
	<div class="w3-row"><br>

	
<form action="CrearAsignaturaServlet">
<table style="margin: 0 auto;text-align: center;">
<tr>
			<th>CREAR ASIGNATURA</th>
			<th></th>
		</tr>
		<tr>
			<td>Nombre:</td>
			<td><input type="text"name="nombre"></td>
		</tr>
		
		
		<tr>
			<td>Acronimo:</td>
			<td><input type="text"name="acronimo"></td>
			</tr>
			<tr>
			<td>Numero:</td>
			<td><input type="text"name="codigo"></td>
			</tr>
			
			<tr>
			<td>Selecione el tipo: </td>
			<td><select name="tipo">
			<option selected value="">Seleccionar</option>
			<option value="OBLIGATORIA">OBLIGATORIA</option>
			<option value="BASICA">BASICA</option>
			<option value="OPTATIVA">OPTATIVA</option>
			<option value="PROYECTO FIN DE MASTER">PROYECTO FIN DE MASTER</option>
			</select>
			</td>
			
		</tr>
		
		<tr>
			<td>Seleccione el curso al que pertenece:</td>
			<td><select name="curso">
			<option selected value="">Seleccionar</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			</select>
			</td>
		</tr>
	<tr>
			<td>Selecione el semestre de impartición:</td>
			<td><select name="semestre">
			<option selected value="">Seleccionar</option>
			<option value="Primer Semestre">Primer Semestre</option>
			<option value="Segundo Semestre">Segundo Semestre</option>
			<option value="Anual">Anual</option>
		</select></td>
		</tr>
		<tr>
			<td>Indique los creditos:</td>
			<td><input type="text"name="ects"></td>
		</tr>
		<tr>
			<td>Indique las horas de teoría:</td>
			<td><input type="text"name="horasTeoria"></td>
		</tr>
		<tr>
			<td>Indique las horas de laboratorio:</td>
			<td><input type="text"name="horasLab"></td>
		</tr>
		<tr>
			<td>Seleccione el plan al que pertenece:</td>
			<td><select name="plan">
			<option selected value="">Seleccionar</option>
			<c:forEach items="${planesActuales}" var="plan">
				<option value="${plan.codigo}">${plan.nombre}</option>
			</c:forEach>
		</select></td>
		</tr>
		<tr>
			<td>Marque los profesores/coordinadores que participan: </td>
			<td>
			<a  href="#" onclick="mostrar(this)" id="profesores">MOSTRAR PROFESORES</a>
			<br>
			<br>
			
			<div style="display:none;" id="profesoressss">
			<c:forEach items="${listaProfesores}" var="profesor">
			
			
			<p>${profesor.usuario.nombre}  ${profesor.usuario.apellidos} </p>
			
			<p>Profesor<input name="profesor" value="${profesor.id}" type="checkbox">
			Coordinador<input name="coordinador" value="${profesor.id}" type="checkbox"></p>
			
			
		</c:forEach>
		</div>
		</td>
		</tr>

		<tr>
			<td>Número de horas APOLO: </td>
			<td><input type="text"name="horasApolo"></td>
		</tr>
		<tr>
			<td>Número de alumnos: </td>
			<td><input type="text"name="numeroAlumnos"></td>
		</tr>
		<tr>
		<td>Añadir comentario(max 250 caracteres):</td>
		<td> <textarea type="text" name="comentario" maxlength="250" style="width:400px;height:130px;vertical-align: top;" cols="35" rows="10" wrap="soft"></textarea></td>
		</tr>
		<tr>
			<th><input type="submit" value="Crear"></th>
			<th></th>
		</tr>
	
	</table>	
</form>

	</div>
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


function mostrar(obj){ 
	console.log(obj.innerHTML);
	if(obj.innerHTML=="MOSTRAR PROFESORES"){
		
		document.getElementById('profesoressss').style.display="";
		
		obj.innerHTML="QUITAR PROFESORES";
	}else{
		document.getElementById('profesoressss').style.display="none";
		obj.innerHTML="MOSTRAR PROFESORES";
	}
   
} 



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


</script>
</body>
</html>