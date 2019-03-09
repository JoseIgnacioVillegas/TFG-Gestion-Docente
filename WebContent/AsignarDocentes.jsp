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

<h2>Asignar Usuarios</h2>
<p> Para la asignatura </p>
<p>${codigo } - ${nombre } - ${acronimo }</p>




<form action="AsignarDocentesServlet">
<input type="hidden" value="${codigo}" name="codigoAsignatura">


<p>Profesores que participan en esta asignatura</p>
	<c:forEach items="${profesoresPorAsignatura}" var="profesor">
					<p >
					<input type="checkbox" style="visibility:hidden;" value="${profesor.id}"name="profesoresBorrados" id="${profesor.id}">
					${profesor.usuario.nombre} ${profesor.usuario.apellidos}
					<a href="#" onclick="ponerValor(this,'${profesor.id}');">Eliminar este profesor</a>
					</p>
		</c:forEach>
		
		
		
<p>Coordinador de la asignatura</p>
	<p><input type="checkbox" style="visibility:hidden;" value="${coordinador.id}"name="coordinadorBorrado" id="${coordinador.id}">
					${coordinador.usuario.nombre} ${coordinador.usuario.apellidos}
					<a href="#" onclick="ponerValor(this,'${coordinador.id}');">Eliminar coordinador</a>
					</p>




	<div id="profes1">
		<p>
			<select name="profesores" onchange="setValue(this);">
				<option selected>Seleccionar docente</option>
				<c:forEach items="${todosProfesores}" var="profesor">
					<option value="${profesor.id}">${profesor.usuario.nombre} ${profesor.usuario.apellidos}</option>
				</c:forEach>
				
			</select>
			<a>
			Profesor<input name="profesor" type="checkbox">
			Coordinador<input name="coordinador" type="checkbox" onclick="marcarProfesor(this);"></a>
		</p>

	</div>

	<div id="profes"></div>
	
	<br>
	<a  href="#" onClick="addProfesor()" >Añadir otro profesor</a>
	
	<button type="submit" >Guardar cambios</button>
</form>
	
	
	
	
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

function marcarProfesor(source) {
	elements = source.parentNode.getElementsByTagName('input');
	elements[0].checked = source.checked;
}

function setValue(source) {
	elements =source.parentNode.getElementsByTagName('input');
	elements[0].value=source.value;
	elements[1].value=source.value;
}


function ponerValor(obj,id){
	var input = document.getElementById(id);
	
	input.checked= true;
	var elemento = obj.parentNode;
	
	elemento.style.display = "none";
	
}



function addProfesor(){
        var div = document.createElement('div');
        div.setAttribute('class', 'form-inline');
            div.innerHTML = document.getElementById('profes1').outerHTML;
            document.getElementById('profes').appendChild(div);
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