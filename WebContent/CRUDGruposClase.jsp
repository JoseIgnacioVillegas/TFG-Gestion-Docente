<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

<!-- En este archivo .jsp esta definido el menu principal y la barra lateral -->
<%@ include file="menu.jsp" %> 


<!-- Team Container -->

<div class="w3-container w3-padding-64 w3-center" id="team">
	<p style="color:#FF0000">${mensaje}</p>
	<h2>Gestionar Grupos de Clase de la asignatura</h2>
	<h2>${asignatura.codigo} - ${asignatura.nombre}</h2>
	
	
	
	
	
	
	
	
<form action="CRUDGruposClaseServlet">
<input type="hidden" name="asignatura" value="${asignatura.codigo}">

	<p>Grupos de clase de esta asignatura </p>
	<c:forEach items="${gruposClase}" var="grupo">
	<div  style="border-style:groove;width:50%;align:center;margin-right:auto;margin-left:auto;">
		<p>
		Grupo: <input name="grupo" value="${grupo.nombre}" type="text" style="width:90px;">
		Numero de Alumnos: <input name="nalumnos" value="${grupo.numeroAlumnos}" type="text" style="width:90px;"></p>
		<p>Descripción: <textarea type="text"  name="descripcionGrupo" maxlength="100" style="width:150px;height:80px;vertical-align: top;" cols="35" rows="10" wrap="soft">${grupo.descripcion}</textarea>
		Profesores:


		 </p>
		 
		 
	<input type="checkbox" style="visibility:hidden;" value="${grupo.id}" name="gruposBorrados" id="${grupo.id}">
	<div type="button" style="width:50%;align:center;margin-right:auto;margin-left:auto;" onclick="borrarGrupo(this,${grupo.id})">Borrar Grupo de Clase</div>
	</div>
	<br>
	</c:forEach>

<br>



















	
	
	
	










<br>
<p>Añadir nuevos grupos de clase</p>



	<div id="grupo" style="border-style:groove;width:70%;align:center;margin-right:auto;margin-left:auto;">
	
	<br>
		<p> 
		Nombre del grupo:<input type="text" name="nombreGrupoNuevo" style="width:90px;">
		 Número de alumnos:<input type="text" name="numeroAlumnosGrupoNuevo" style="width:90px;">
		 </p>
		

		Descripción(max 140 caracteres): <textarea type="text" name="descripcionGrupoNuevo" maxlength="100" style="width:150px;height:80px;vertical-align: top;" cols="35" rows="10" wrap="soft"></textarea>

		Seleccionar profesores: 
		
		
		<br>
		
		<div id="profesor1">
		<input type="text"  onKeyUp="buscarSelect(this)" value="Buscar Profesor" onClick="this.value=''" onBlur="this.value='Buscar Profesor'">
			
			<select name="docentes">
				<option selected value="0">Seleccionar la Docente </option>
				<c:forEach items="${todosProfesores}" var="profesor">
					<option value="" >${profesor.usuario.nombre } ${profesor.usuario.apellidos }</option>
					
				</c:forEach>
			</select><br>
			Horas de prácticas:<input type="text" name="hPracticasNuevo" style="width:45px;" onKeyUp="setName(this)">
		 	Horas de laboratorio:<input type="text" name="hLabNuevo" style="width:45px;" onKeyUp="setName(this)">
		 	Horas de teoria:<input type="text" name="hTeoriaNuevo" style="width:45px;" onKeyUp="setName(this)">
			
		</div>
		
		<div id="profesor"></div>
		
		
		<a  href="#" onClick="addProfesor(this)" >Añadir profesor</a>


		</div>


	 <div id="grupo1"></div>
		<a  href="#" onClick="addGrupo()" >Añadir grupo de clase</a><br><br>

<input type="submit" value="Guardar Cambios">

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



function setName(obj){
	console.log(obj.parentNode.parentNode);
	//console.log(obj.parentNode.parentNode.getElementsByTagName('input')[0].value);
	obj.setAttribute("name",obj.parentNode.parentNode.getElementsByTagName('input')[0].value)
}





function addProfesor(obj){
    var div = document.createElement('div');
    div.setAttribute('class', 'form-inline');
        div.innerHTML = document.getElementById('profesor1').outerHTML;
        document.getElementById('profesor').appendChild(div);
}


function buscarSelect(obj){
	// creamos un variable que hace referencia al select
	var select = obj.parentNode.getElementsByTagName('select').item(0);
	// obtenemos el valor a buscar
	var buscar= obj.value ;
 
	// recorremos todos los valores del select
	for(var i=1;i<select.length;i++){
		var x = select.options[i].text.substr(0,buscar.length);
		if(x.toLowerCase() ==buscar.toLowerCase() ){
			// seleccionamos el valor que coincide
			select.selectedIndex=i;
		}
		
		
		
	}
}



function borrarGrupo(obj,id){ 
	obj.parentNode.style.display="none"; 
	
	var input = document.getElementById(id);
	input.checked= true;
} 

function mostrar(obj){ 
	console.log(obj.innerHTML);
	
	
	
	if(obj.innerHTML=="MOSTRAR PROFESORES"){
		elements = obj.parentNode.getElementsByTagName('div');
		elements[1].style.display="";
		
		obj.innerHTML="QUITAR PROFESORES";
	}else{
		elements = obj.parentNode.getElementsByTagName('div');
		elements[1].style.display="none";
		obj.innerHTML="MOSTRAR PROFESORES";
	}
   
} 


function addGrupo(){
    var div = document.createElement('div');
    div.setAttribute('class', 'form-inline');
    div.innerHTML = document.getElementById('grupo').outerHTML;
    document.getElementById('grupo1').appendChild(div);
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