<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>




<html onLoad="cargarLista();">
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




.divTable{
	display: table;
	width: 70%;
	align:center;
	margin-right:auto;
	margin-left:auto;
}
.divTableRow {
	display: table-row;
}
.divTableHeading {
	background-color: #EEE;
	display: table-header-group;
}
.divTableCell, .divTableHead {
	border: 1px solid #999999;
	display: table-cell;
	padding: 3px 10px;
}
.divTableHeading {
	background-color: #EEE;
	display: table-header-group;
	font-weight: bold;
}
.divTableFoot {
	background-color: #EEE;
	display: table-footer-group;
	font-weight: bold;
}
.divTableBody {
	display: table-row-group;
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

<h2>Asignar Asignaturas</h2>
<h2>Al Docente ${profesor.usuario.nombre } ${profesor.usuario.apellidos }</h2>




<form action="AsignarAsignaturasServlet">
<input type="hidden" value="${profesor.id}" name="id">



<div class="divTable w3-container w3-padding-64 w3-center">

<div class="divTableBody">
<div class="divTableRow">
<div class="divTableCell">
<!-- AQUI APARECEN LAS ASIGNATURAS EN LAS QUE PARTICIPA PARA PODER ELIMINARLAS -->
<p>Participa en las asignaturas: </p>
	
	
	
	<c:forEach items="${asignaturasParticipa}" var="asignatura">
		<p>
			<input type="checkbox" style="visibility:hidden;" value="${asignatura.codigo}" name="asignaturasParticipaBorradas" id="${asignatura.codigo}">
			${asignatura.codigo} - ${asignatura.nombre}
			<a href="#" onclick="ponerValor(this,'${asignatura.codigo}');">Eliminar esta asignatura</a>
		</p>
	</c:forEach>





</div>
<div class="divTableCell">

<!-- AQUI APARECE LAS ASIGNATURA QUE COORDINA PARA PODER ELIMINARLAS -->
<p>Coordina la asignatura: </p>

<c:set var = "cod1" value="${asignaturaCoordina}"/>
<c:set var = "cod2" value="<%=null%>"/>


<c:if test = "${cod1 eq cod2}">
   				<p>Este docente no coordina ninguna asignatura</p>
</c:if>
<c:if test = "${cod1 != cod2}">
<p>
			<input type="hidden" name="asignaturasCoordinaBorradas" value="${asignaturaCoordina.codigo}">
			${asignaturaCoordina.codigo} - ${asignaturaCoordina.nombre}
			<a href="#" onclick="eliminarAsignatura(this);">Eliminar esta asignatura</a>
		</p>

</c:if>





		


</div>
</div>
<div class="divTableRow">
<div class="divTableCell">


<p>Añadir nuevas asignaturas en las que participe,</p>
<p> para ello puede buscar el nombre en el cuadro de busqueda</p>

	<div id="participa1">
	
	<p>
	
	<input type="text" id="buscar" onKeyUp="buscarSelect()" >
			
			<select id="elementos" name="participa">
				<option selected value="0">Seleccionar la asignatura </option>
				<c:forEach items="${todasAsignaturas}" var="asignatura">
					<option value="${asignatura.codigo}" >${asignatura.nombre}</option>
				</c:forEach>
			</select>
	
	</p>
	
	</div>

	<div id="participa"></div>
	
	<br>
	<a  href="#" onClick="addAsignaturaParticipar()" >Añadir otra asignatura en la que participe</a>
	


</div>
<div class="divTableCell">



<p>Añadir asignatura que coordine</p>



<c:if test = "${cod1 eq cod2}">
<div id="coordina1">
		<p>
			<select name="coordina">
				<option selected>Seleccionar Asignatura que coordina</option>
				<c:forEach items="${todasAsignaturas}" var="asignatura" >
					<option value="${asignatura.codigo}">${asignatura.codigo} - ${asignatura.nombre} </option>
				</c:forEach>
				
			</select>
		</p>

	</div>
   				
</c:if>
<c:if test = "${cod1 != cod2}">
<p>Este Docente ya coordina una asignatura:</p>
</c:if>



</div>
</div>
</div>
</div>


	
	
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

function buscarSelect(){

	// creamos un variable que hace referencia al select
	var select=document.getElementById("elementos") ;
 
	// obtenemos el valor a buscar
	var buscar=document.getElementById("buscar").value ;
 
	// recorremos todos los valores del select
	for(var i=1;i<select.length;i++){
		var x = select.options[i].text.substr(0,buscar.length);
		if(x.toLowerCase() ==buscar.toLowerCase() ){
			// seleccionamos el valor que coincide
			select.selectedIndex=i;
		}
		
		
		
	}
}




function eliminarAsignatura(obj){

	var elemento = obj.parentNode;
	
	elemento.style.display = "none";
	

}







function marcarAsignatura(source) {
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
	console.log(input);
	input.checked= true;
	var elemento = obj.parentNode;
	elemento.style.display = "none";
}















function addAsignaturaParticipar(){
        var div = document.createElement('div');
        div.setAttribute('class', 'form-inline');
            div.innerHTML = document.getElementById('participa1').outerHTML;
            document.getElementById('participa').appendChild(div);
}


function addAsignaturaCoordinar(){
    var div = document.createElement('div');
    div.setAttribute('class', 'form-inline');
        div.innerHTML = document.getElementById('coordina1').outerHTML;
        document.getElementById('coordina').appendChild(div);
}


function mostrarAsignaturas(source,codigo){
	elements = source.parentNode.getElementsByTagName('select');

	elements[1].style.visibility = "";
}

function mostrarGrupos(source){
	element = document.getElementById('grupos');
	element.style.visibility = "";
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