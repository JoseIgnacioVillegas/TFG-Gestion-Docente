<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
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
<html>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<body id="myPage">

<!-- Sidebar on click -->

<div class="w3-sidebar w3-bar-block w3-white w3-card w3-animate-left " style="position:absolute;z-index:2" id="mySidebar">
  <br>
  <br>
    <button  class="w3-bar-item w3-button" name="gestUsuarios" value="gestUsuarios" onclick="desplegarMenu(this)">Gestion Usuarios</button>
    <div id="gestUsuarios" style="display:none;">
	    <form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDProfesor" name="CRUDProfesor">Docentes</button></form>
	    <form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="NoDocentes" name="NoDocentes">No Docentes</button></form>
	    <form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="Permisos" name="Permisos">Permisos</button></form>
    </div>
    
    <button  class="w3-bar-item w3-button"  name="gestDocencia" value="gestDocencia" onclick="desplegarMenu(this)">Gestion Docencia</button>
    <div id="gestDocencia" style="display:none;">
    	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDPlan" name="CRUDPlan">Plan de Estudios</button></form>
   	  	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="CRUDAsignatura" name="CRUDAsignatura">Asignaturas</button></form>
  	</div>
</div>




<!-- Navbar -->


 
<div class="w3-bar w3-theme-d2 w3-left-align" style="position:absolute;z-index:3">
  
<a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2" href="javascript:void(0);" onclick="openNav()"><i class="fa fa-bars"></i></a>
  <form action="VistaInicial.jsp"><button class="w3-bar-item w3-button w3-teal"><i class="fa fa-home w3-margin-right"></i>Inicio</button></form>
  <form action="ProfesorServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white">Profesor</button></form>
  <form action="CoordinadorServlet"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white">Coordinador</button></form>
  <form action="VistaGestor.jsp"><button class="w3-bar-item w3-button w3-hide-small w3-hover-white">Gestor</button></form>
  <form action="LogoutServlet"><button  type="submit" class="w3-bar-item w3-button w3-hide-small w3-hover-white w3-right">Logout</button>	</form>
 
</div>

 


<!-- Image Header -->

<div class="w3-display-container w3-animate-opacity" >
  
<!--  img src="" alt="boat" style="width:30%;" -->
</div>








<!-- Team Container -->

<div class="w3-container w3-padding-64 w3-center" id="team">
	<p style="color:#FF0000";>${mensaje}</p>
	<h2>Gestionar Usuarios</h2>


	<p name="mensaje"></p>
	<div class="w3-row"><br>

		<form action="CRUDPermisos.jsp">Para gestionar los permisos haz click aqui: <button type="submit" >Gestionar permisos</button></form>		
		<br>
		<br>
		
		<table id="datos" style="margin: 0 auto;text-align: left;">
	<tr>
		
		<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>NOMBRE</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol1"></div>
				</div>
				<div class="checkboxes" id="chksCol1"></div>
			</div>
			</td>
			<td>
			<div class="multiselect">
				<div class="selectBox" >
					<select id="lstOS" >
						<option>APELLIDOS</option>
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
						<option>CORREO</option>
					</select>
					<div class="overSelect" data-checkboxes="chksCol4"></div>
				</div>
				<div class="checkboxes" id="chksCol4"></div>
			</div>
		</td>
		<td><a>Asignar permisos</a></td>
	</tr>
		
		<c:forEach items="${usuarios}" var="usuario">

	
		<tr>
			<td>${usuario.nombre}</td>
			<td>${usuario.apellidos}</td>
			<td>${usuario.correo}</td>

			
			<td><form action="">
			<input type="hidden" value="${usuario.id}" name="id">
			<input type="hidden" value="${usuario.nombre}" name="nombre">
			<input type="hidden" value="${usuario.apellidos}" name="apellidos">
			<input type="hidden" value="${usuario.correo}" name="correo">
			<button type="submit" >Asignar permisos</button></form>
			</td>
			

		</tr>
		
		</c:forEach>
		
		</table>
		
	</div>
</div>











<!-- Footer -->

<footer class="w3-container w3-padding-32 w3-theme-d1 w3-center" >
  
<h4>Follow Us</h4>
  <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Facebook"><i class="fa fa-facebook"></i></a>
  <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Twitter"><i class="fa fa-twitter"></i></a>
  <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Google +"><i class="fa fa-google-plus"></i></a>
  <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Google +"><i class="fa fa-instagram"></i></a>
  <a class="w3-button w3-large w3-teal w3-hide-small" href="javascript:void(0)" title="Linkedin"><i class="fa fa-linkedin"></i></a>
<p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>

  

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
	for(y of x){
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