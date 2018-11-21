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
	
<%
	String nom = request.getParameter("nombre");
	String codigo = request.getParameter("codigo");
	String codigoPlan = request.getParameter("codigoPlan");
	System.out.println("hiiii"+codigoPlan);
	String acronimo = request.getParameter("acronimo");
	String tipo = request.getParameter("tipo");
	String curso = request.getParameter("curso");
	String semestre = request.getParameter("semestre");
	String ects = request.getParameter("ects");
	int horasTeoria = Integer.parseInt(request.getParameter("horasTeoria"));
	int horasLab = Integer.parseInt(request.getParameter("horasLab"));
	//Profesor coordinador = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(idCoordinador));
	String comentario = request.getParameter("comentario");
	double horasApolo = Double.parseDouble(request.getParameter("horasApolo"));
	int numeroAlumnos = Integer.parseInt(request.getParameter("numeroAlumnos"));
%>

	

<h2>EditarAsignatura</h2>



  
<form action="EditarAsignaturaServlet">
    
	<input type="hidden" value="<%=codigo%>" name="codigo1">
    
	<p>Codigo: <input type="text"name="codigo" value="<%=codigo%>"></p>

	<p>Nombre: <input type="text" name="nombre" value="<%=nom%>"></p>
    <p>Acronimo: <input type="text" name="acronimo" value="<%=acronimo%>"></p>
    
    <p>Plan de estudios: 
			
    <select name="planEstudios">
    <option value="">Seleccionar</option>
			<c:forEach items="${planesActuales}" var="plan">
			 
			<c:set var = "cod1" value="${plan.codigo}"/>
			<c:set var = "cod2" value="<%=codigoPlan%>"/>
			
			<c:if test = "${cod1 eq cod2}">
   				<option value="${plan.codigo}" selected >${plan.nombre}  </option>
			</c:if>
			<c:if test = "${cod1 != cod2}">
   			 	<option value="${plan.codigo}"  >${plan.nombre} </option>
			</c:if>
			
			
		
         </c:forEach>
			
		</select>
    
    
    </p>
    
    
    
    
    
    
    
    
    <p>Tipo: 
			
    <select name="tipo">
    <option value="">Seleccionar</option>
    		<c:set var = "tipo" value="<%=tipo%>"/>
			<c:set var = "obligatoria" value="OBLIGATORIA"/>
			<c:set var = "basica" value="BASICA"/>
			<c:set var = "optativa" value="OPTATIVA"/>
			<c:set var = "proyecto" value="PROYECTO FIN DE MASTER"/>
			
			<c:if test = "${tipo eq obligatoria}">
        	<option value="">Seleccionar</option>
			<option value="OBLIGATORIA" selected >OBLIGATORIA</option>
			<option value="BASICA">BASICA</option>
			<option value="OPTATIVA">OPTATIVA</option>
			<option value="PROYECTO FIN DE MASTER">PROYECTO FIN DE MASTER</option>
         </c:if>
         
         <c:if test = "${tipo eq basica}">
         <option value="">Seleccionar</option>
			<option value="OBLIGATORIA">OBLIGATORIA</option>
			<option value="BASICA" selected >BASICA</option>
			<option value="OPTATIVA">OPTATIVA</option>
			<option value="PROYECTO FIN DE MASTER">PROYECTO FIN DE MASTER</option>
         </c:if>
         
         
         <c:if test = "${tipo eq optativa}">
   
         <option value="">Seleccionar</option>
			<option value="OBLIGATORIA">OBLIGATORIA</option>
			<option value="BASICA">BASICA</option>
			<option value="OPTATIVA" selected >OPTATIVA</option>
			<option value="PROYECTO FIN DE MASTER">PROYECTO FIN DE MASTER</option>
         </c:if>
         
         
         <c:if test = "${tipo eq proyecto}">
         
         <option value="">Seleccionar</option>
			<option value="OBLIGATORIA">OBLIGATORIA</option>
			<option value="BASICA">BASICA</option>
			<option value="OPTATIVA">OPTATIVA</option>
			<option value="PROYECTO FIN DE MASTER" selected >PROYECTO FIN DE MASTER</option>
         </c:if>
         
         
      
			
			
		</select>
    
    
    </p>
    <p>Curso:  
    <select name="curso">
    <c:choose>
         
         <c:when test = "<%=curso.equals(\"1\")%>">
         <option value="">Seleccionar</option>
           	<option value="1" selected>Primero</option>
           	<option value="2">Segundo</option>
			<option value="3">Tercero</option>
			<option value="4">Cuarto</option>
         </c:when>
         <c:when test = "<%=curso.equals(\"2\")%>">
         <option value="">Seleccionar</option>
            <option value="1">Primero</option>
           	<option value="2" selected>Segundo</option>
			<option value="3">Tercero</option>
			<option value="4">Cuarto</option>
         </c:when>
         <c:when test = "<%=curso.equals(\"3\")%>">
         <option value="">Seleccionar</option>
            <option value="1">Primero</option>
           	<option value="2">Segundo</option>
			<option value="3" selected>Tercero</option>
			<option value="4">Cuarto</option>
         </c:when>
         
         <c:when test = "<%=curso.equals(\"4\")%>">
         <option value="">Seleccionar</option>
            <option value="1">Primero</option>
           	<option value="2">Segundo</option>
			<option value="3">Tercero</option>
			<option value="4" selected>Cuarto</option>
         </c:when>
         
         <c:otherwise>
            <option value="" selected>Seleccionar</option>
            <option value="1">Primero</option>
           	<option value="2">Segundo</option>
			<option value="3">Tercero</option>
			<option value="4">Cuarto</option>
         </c:otherwise>
      </c:choose>
    </select>
    
 
    <p>Selecione el semestre de impartición: 
		<select name="semestre">
			
			<c:choose>
         
         <c:when test = "<%=semestre.equals(\"Primer Semestre\")%>">
         <option value="">Seleccionar</option>
           	<option value="Primer Semestre" selected>Primer Semestre</option>
           	<option value="Segundo Semestre">Segundo Semestre</option>
			<option value="Anual">Anual</option>
         </c:when>
         
         <c:when test = "<%=semestre.equals(\"Segundo Semestre\")%>">
         <option value="">Seleccionar</option>
            <option value="Primer Semestre" >Primer Semestre</option>
           	<option value="Segundo Semestre" selected>Segundo Semestre</option>
			<option value="Anual">Anual</option>
         </c:when>
         
         <c:when test = "<%=semestre.equals(\"Anual\")%>">
         <option value="">Seleccionar</option>
            <option value="Primer Semestre" >Primer Semestre</option>
           	<option value="Segundo Semestre" >Segundo Semestre</option>
			<option value="Anual" selected>Anual</option>
         </c:when>
         
         <c:otherwise>
            <option value="" selected>Seleccionar</option>
            <option value="Primer Semestre" >Primer Semestre</option>
           	<option value="Segundo Semestre" >Segundo Semestre</option>
			<option value="Anual" >Anual</option>
         </c:otherwise>
      </c:choose>
      
			
			
		</select>
	</p>
    
    <p>Creditos: <input type="text" name="ects" value="<%=ects%>"></p>
    <p>Horas de teoría: <input type="text" name="horasTeoria" value="<%=horasTeoria%>"></p>
    <p>Horas de laboratorio: <input type="text" name="horasLab" value="<%=horasLab%>"></p>
    <p>Horas APOLO: <input type="text" name="horasApolo" value="<%=horasApolo%>"></p>
    <p>Número de alumnos: <input type="text" name="numeroAlumnos" value="<%=numeroAlumnos%>"></p>
    
	<p>Comentario(max 250 caracteres):<textarea type="text" name="comentario" maxlength="250" style="width:400px;height:130px;vertical-align: top;" cols="35" rows="10" wrap="soft"><%=comentario%></textarea></p>
    
	<input type="submit" value="Editar">
  
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