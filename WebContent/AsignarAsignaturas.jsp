<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>





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
   	  	<form action="GestorServlet" ><button type="submit" class="w3-bar-item w3-button" value="Responsabilidades" name="Responsabilidades">Responsabilidades</button></form>
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

<h2>Asignar Asignaturas</h2>





<form action="AsignarAsignaturasServlet">
<input type="hidden" value="${profesor.id}" name="id">


<p>El Docente ${profesor.usuario.nombre } ${profesor.usuario.apellidos }</p>

	<p>Participa en las asignaturas: </p>
	
	<c:forEach items="${asignaturasParticipa}" var="asignatura">
		<p>
			<input type="checkbox" style="visibility:hidden;" value="${asignatura.codigo}" name="asignaturasParticipaBorradas" id="${asignatura.codigo}">
			${asignatura.codigo} - ${asignatura.nombre}
			<a href="#" onclick="ponerValor(this,'${asignatura.codigo}');">Eliminar esta asignatura</a>
		</p>
	</c:forEach>
	
	<div id="participa1">
		<p>
		
		
		
			<select name="participa" >
				<option selected>Seleccionar asignatura que participa</option>
				<c:forEach items="${todasAsignaturas}" var="asignatura">
					<option value="${asignatura.codigo}">${asignatura.codigo} - ${asignatura.nombre}</option>
				</c:forEach>
			</select>
			
			
			
		</p>

	</div>

	<div id="participa"></div>
	
	<br>
	<a  href="#" onClick="addAsignaturaParticipar()" >Añadir otra asignatura en la que participe</a>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<br><br><br><br><br><br><br><br>
	
	
	<p>Coordina las asignaturas: </p>

	
	
	<c:forEach items="${asignaturasCoordina}" var="asignatura">
		<p>
			<input type="checkbox" style="visibility:hidden;" value="${asignatura.codigo}" name="asignaturasCoordinaBorradas" id="${asignatura.codigo}">
			${asignatura.codigo} - ${asignatura.nombre}
			<a href="#" onclick="ponerValor(this,'${asignatura.codigo}');">Eliminar esta asignatura</a>
		</p>
	</c:forEach>
	
	
	
	
	
	<div id="coordina1">
		<p>
			<select name="coordina">
				<option selected>Seleccionar Asignatura que coordina</option>
				<c:forEach items="${todasAsignaturas}" var="asignatura">
					<option value="${asignatura.codigo}">${asignatura.codigo} - ${asignatura.nombre}</option>
				</c:forEach>
				
			</select>
		</p>

	</div>

	<div id="coordina"></div>
	
	<br>
	<a  href="#" onClick="addAsignaturaCoordinar()" >Añadir otra asignatura</a>
	
	
	
	<br><br><br><br>
	
	<button type="submit" >Guardar cambios</button>
</form>
	
	
	
	
	</div>





<!-- Footer -->

<footer class="w3-container w3-padding-32 w3-theme-d1 w3-center" >
  
<h4>Follow Us</h4>
  <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Facebook"><i class="fa fa-facebook"></i></a>
  <a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Twitter"><i class="fa fa-twitter"></i></a>
  

<a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Google +">
<i class="fa fa-google-plus"></i></a>
  
<a class="w3-button w3-large w3-teal" href="javascript:void(0)" title="Google +">
<i class="fa fa-instagram"></i></a>
  
<a class="w3-button w3-large w3-teal w3-hide-small" href="javascript:void(0)" title="Linkedin">
<i class="fa fa-linkedin"></i></a>

<p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>

  


</footer>






<script>

function marcarAsignatura(source) {
	elements = source.parentNode.getElementsByTagName('input');
	elements[0].checked = source.checked;
	
}

function setValue(source) {
	elements =source.parentNode.getElementsByTagName('input');
	elements[0].value=source.value;
	elements[1].value=source.value;
	console.log(elements[0]);
	console.log(elements[1]);
}


function ponerValor(obj,id){
	var input = document.getElementById(id);
	console.log(input);
	input.checked= true;
	var elemento = obj.parentNode;
	
	elemento.style.display = "none";
	
	console.log(input);
	
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