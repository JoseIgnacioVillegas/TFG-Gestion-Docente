
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>


<html lang="en">

<!-- En este archivo .jsp esta definida la cabecera -->
	<%@ include file="head.jsp" %> 


<body id="page-top">

  



  <!-- Page Wrapper -->
  <div id="wrapper">

<!-- En este archivo .jsp esta definida superior -->
	<%@ include file="sidebar.jsp" %> 
	
	
	
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- En este archivo .jsp esta definida superior -->
	<%@ include file="topbar.jsp" %> 

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Crear Asignatura</h1>
          </div>

      <form class="user"  action="CrearAsignaturaServlet">
          <!-- Content Row -->
          <div class="row">
          
			<div class="col-xl-5 col-md-6 mb-4">
					<div class="form-group"><input type="text" class="form-control form-control-user" name="nombre" placeholder="Introduce el nombre de la asignatura"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="acronimo" placeholder="Introduce el acrónimo de la asignatura"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="codigo" placeholder="Introduce el código de la asignatura"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="ects" placeholder="Indica los créditos de la asignatura"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="horasTeoria" placeholder="Indica las horas de teoría de la asignatura"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="horasLab" placeholder="Indica las horas de laboratorio de la asignatura"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="horasApolo" placeholder="Indica las horas APOLO de la asignatura"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="numeroAlumnos" placeholder="Indica el número de alumnos de la asignatura"></div>
</div>
<div class="col-xl-5 col-md-6 mb-4" style="text-align:center;">


            <div class="form-group">
						<select name="tipo" class="form-control">
							<option selected value="">Seleccionar el tipo de asignatura</option>
							<option value="OBLIGATORIA">OBLIGATORIA</option>
							<option value="BASICA">BASICA</option>
							<option value="OPTATIVA">OPTATIVA</option>
							<option value="PROYECTO FIN DE MASTER">PROYECTO FIN DE MASTER</option>
						</select>
					</div>
					<div class="form-group">
						<select name="semestre" class="form-control">
							<option selected value="">Seleccionar el semestre de impartición</option>
							<option value="Primer Semestre">Primer Semestre</option>
							<option value="Segundo Semestre">Segundo Semestre</option>
							<option value="Anual">Anual</option>
						</select>
					</div>
					<div class="form-group">
						<select name="plan" class="form-control">
							<option selected value="">Seleccionar el plan al que pertenece</option>
							<c:forEach items="${planesActuales}" var="plan">
								<option value="${plan.codigo}">${plan.nombre}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<select name="curso" class="form-control">
							<option selected value="">Seleccionar el curso al que pertenece</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</div>
					
					

Añadir comentario(max 250 caracteres):<br>
		 <textarea type="text" name="comentario" maxlength="250" style="width:400px;height:130px;vertical-align: top;" cols="35" rows="10" wrap="soft"></textarea>
		 
<br><br>
                    
                    Marque los profesores/coordinadores que participan: 

			<a  href="#" onclick="mostrar(this)" id="profesores">MOSTRAR PROFESORES</a>
			<br>
			<br>
			
			<div style="display:none;" id="profesoressss">
			<c:forEach items="${listaProfesores}" var="profesor">
			
			<div  style="border-style:groove;">
			<p>${profesor.usuario.nombre}  ${profesor.usuario.apellidos} </p>
			
			<p>Profesor<input name="profesor" value="${profesor.id}" type="checkbox">
			Coordinador<input name="coordinador" value="${profesor.id}" type="checkbox" onclick="marcarProfesor(this);"></p>
			</div>
			
		</c:forEach>
		</div>       
                     

                    
                   
                   

                           
            	</div>
          </div>


<button type="submit" class="btn btn-primary btn-user btn-block" style="width:42%;align:center;margin-left:auto;margin-right:auto;">Crear</button>
<hr>

 </form>
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->






<footer class="sticky-footer bg-white" >
  <div class="container my-auto">
    <div class="copyright text-center my-auto">

  <p>TFG Gestión docente - 2019</p></div></div>
</footer>






    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->





  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  
<!-- En este archivo .jsp estan incluidos los enlances a los archivos JS -->
	<%@ include file="JavaScript.jsp" %> 

</body>
<script>

function marcarProfesor(source) {
	elements = source.parentNode.getElementsByTagName('input');
	elements[0].checked = source.checked;
}



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





</script>
</html>

