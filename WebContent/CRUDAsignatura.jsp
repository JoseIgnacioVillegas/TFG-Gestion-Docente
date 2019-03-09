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
	table{
		font-size:12px;
	}
	table, td{
		border:solid 1px;
		padding:2px 4px;
		border-collapse:collapse;
		white-space: nowrap;
	}
	table tr:first-child{
		text-align:center;
		font-weight:bold;
		vertical-align: top;
	}
 
	.multiselect {
		position:relative;
	}
 
	.selectBox {
		position: relative;
	}
 
	.selectBox select {
		text-align:center;
		width: 100%;
		font-weight: bold;
	}
 
 
	.overSelect {
		position: absolute;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
	}
 
	.checkboxes {
		display: none;
		border: 1px #dadada solid;
		background-color:white;
		overflow-y: auto;
		max-height:200px;
		position:absolute;
		box-sizing: border-box;
		min-width:100%;
		white-space: nowrap;
	}
 
	.checkboxes label {
	  display: block;
	  text-align:left;
	}
 
	.checkboxes label:hover {
	  background-color: #1e90ff;
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
	
	<h2>Gestión de Asignaturas</h2>

	


	
	<p name="mensaje"></p>
	
	<div class="w3-row"><br>

		

		<form action="CrearAsignatura.jsp">Para crear una nueva asignatura pincha aquí: <button type="submit" >Crear una asignatura nueva</button></form>		
		<br>
		<br>
		<form action="GestorServlet">Para asignaturas de la API pincha aquí:<button type="submit" value="importarapi" name="importarapi">Importar asignaturas</button></form>
		<br>
		<br>
	
	
	
	

<table id="datos" style="margin: 0 auto;text-align: left;">
	<tr>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>CODIGO</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol1"></div>
				</div>
				<div class="checkboxes" id="chksCol1ss"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>NOMBRE</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol2"></div>
				</div>
				<div class="checkboxes" id="chksCol2"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>ACRONIMO</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol3"></div>
				</div>
				<div class="checkboxes" id="chksCol3"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>CURSO</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol4"></div>
				</div>
				<div class="checkboxes" id="chksCol4"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>SEMESTRE</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol5"></div>
				</div>
				<div class="checkboxes" id="chksCol5"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>CREDITOS</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol6"></div>
				</div>
				<div class="checkboxes" id="chksCol6"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>TIPO</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol7"></div>
				</div>
				<div class="checkboxes" id="chksCol7"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>HORAS APOLO</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol8"></div>
				</div>
				<div class="checkboxes" id="chksCol8"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>HORAS LABORATORIO</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol9"></div>
				</div>
				<div class="checkboxes" id="chksCol9"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>HORAS TEORIA</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol10"></div>
				</div>
				<div class="checkboxes" id="chksCol10"></div>
			</div>
		</td>
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>NUMERO DE ALUMNOS</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol11"></div>
				</div>
				<div class="checkboxes" id="chksCol11"></div>
			</div>
		</td>
		<td><a>COMENTARIOS</a></td>
		<td><a>EDITAR</a></td>
		<td><a>BORRAR</a></td>
		<td><a>Asignar Docentes</a></td>
	</tr>
		
<c:forEach items="${planesActuales}" var="plan">
	<tr>
			<th>${plan.codigo}</th>
			<th>${plan.nombre}</th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
	</tr>
	
	
	<c:forEach items="${plan.asignaturas}" var="asignatura">
		<tr>
			<td>${asignatura.codigo}</td>
			<td>${asignatura.nombre}</td>
			<td>${asignatura.acronimo}</td>
			<td>${asignatura.curso}</td>
			<td>${asignatura.semestre}</td>
			<td>${asignatura.ects}</td>
			<td>${asignatura.tipo}</td>
			<td>${asignatura.horasApolo}</td>
			<td>${asignatura.horasTeoria}</td>
			<td>${asignatura.horasLab}</td>
			<td>${asignatura.numeroAlumnos}</td>
			<td><p title="${asignatura.comentario}" style="height:20px;width:100px; overflow:hidden!important;">${asignatura.comentario}</p></td>
			
			<td>
			<form action="EditarAsignatura.jsp">
			<input type="hidden" value="${asignatura.comentario}" name="comentario">
				<input type="hidden" value="${asignatura.nombre}" name="nombre">
				<input type="hidden" value="${asignatura.codigo}" name="codigo">
				<input type="hidden" value="${plan.codigo}" name="codigoPlan">
				<input type="hidden" value="${asignatura.acronimo}" name="acronimo">
				<input type="hidden" value="${asignatura.tipo}" name="tipo">
				<input type="hidden" value="${asignatura.curso}" name="curso">
				<input type="hidden" value="${asignatura.semestre}" name="semestre">
				<input type="hidden" value="${asignatura.ects}" name="ects">
				<input type="hidden" value="${asignatura.horasTeoria}" name="horasTeoria">
				<input type="hidden" value="${asignatura.horasLab}" name="horasLab">
				<input type="hidden" value="${asignatura.numeroAlumnos}" name="numeroAlumnos">
				<input type="hidden" value="${asignatura.horasApolo}" name="horasApolo">
				<button type="submit" >Editar asignatura</button></form>
			</td>
			
			<td>
				<form action="BorrarAsignaturaServlet">
				<input type="hidden" value="${asignatura.codigo}" name="codigo">
				<button type="submit" >Borrar asignatura</button></form>
			</td>
			
			<td>
				<form action="ObtenerDocentesServlet">
				<input type="hidden" value="${asignatura.codigo}" name="codigo">
				<button type="submit" >Asignar docentes</button></form>
			</td>
			
		</tr>
	</c:forEach>
</c:forEach>

</table>


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








aFiltros = [];
aFiltro  = [];
aStack   = [];
window.onload = function(){
	x = document.querySelectorAll(".overSelect")
	for(let y of x){
		y.addEventListener("click",showCheckboxes);
	}
}

function showCheckboxes() {
	checkboxes = document.getElementById(this.dataset.checkboxes);
	x = document.querySelectorAll(".overSelect");
	for(i=0, t=x.length; i<t; i++ ){
		if (x.item(i)==this){
			n=i;
			break;
		}
	}

	td = this.parentElement.parentElement.parentElement;
	tr = td.parentElement;
	for(i=0, t=tr.children.length; i<t; i++ ){
		if( tr.children[i]==td ){
			m=i;
			break;
		}
	}

	if(checkboxes.style.display=='none' || checkboxes.style.display==""){
		x= document.querySelectorAll(".checkboxes");
		for(i=0, t=x.length; i<t; i++ ){
			document.querySelectorAll(".checkboxes")[i].style.display="none";
		}
		if( aFiltros[n]==undefined ){
			aFiltros[n]=[];
		}

		table = document.getElementById("datos");
		for(i=1, t=table.rows.length; i<t; i++){
			text = table.rows[i].cells[n].innerHTML;
			if(!aFiltros[n].includes(text)){
				aFiltros[n].push(text);

				lbl = document.createElement("label");
				txt = document.createTextNode(text);
				chk = document.createElement("input");
				chk.setAttribute("type", "checkbox");
				lbl.appendChild(chk);
				lbl.appendChild(txt);
				checkboxes.appendChild(lbl);
			}
		}
		checkboxes.style.display="block";
	}else{
		checkboxes.style.display='none';
	}

	x = document.querySelectorAll("input[type=checkbox]");
	for(let y of x){
		y.removeEventListener("click",setFiltro);
		y.addEventListener("click",setFiltro);
	}
}

function setFiltro(){
	x=document.querySelectorAll("div.multiselect");
	div=this.parentElement.parentElement.parentElement ;
	for(i=0, t=x.length; i<t; i++ ){
		if (x.item(i)==div){
			n=i;
			break;
		}
	}


	if(aFiltro[n]==undefined){
		aFiltro[n]=[];
	}

	text = this.nextSibling.textContent;
	if( this.checked ){
		aFiltro[n].push(text);
	}else{
		aFiltro[n].splice(aFiltro[n].indexOf(text),1);
	}

	if(aFiltro[n].length){
		if(!aStack.includes(n)){
			aStack.push(n);
		}
	}else{
		aStack.splice(aStack.indexOf(n),1);
	}

	filtrar();
}


function filtrar(){
	table = document.getElementById("datos");
	for(i=1, t=table.rows.length; i<t; i++){
		table.rows[i].style.display="";
	}

	if(aStack.length){
		for(n of aStack){
			for(i=1, t=table.rows.length; i<t; i++){
				if(table.rows[i].style.display!="none"){
					text = table.rows[i].cells[n].innerHTML;
					if(aFiltro[n].length>0){
						if(!aFiltro[n].includes(text) ){
							table.rows[i].style.display="none"
						}
					}
				}
			}
		}
	}
}

</script>





</body>



</html>