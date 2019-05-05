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
	<%@ include file="sidebarDocente.jsp" %> 
	
	
	
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- En este archivo .jsp esta definida superior -->
		<%@ include file="topbar.jsp" %> 

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          	          <h1 class="h3 mb-2 text-gray-800">Grupos de clase de la asignatura ${asignatura.codigo } - ${asignatura.nombre } </h1>
          	
	
	
	<div class="row" >	
	<!-- Celda superior izquierda, asignaturas en las que participa -->
		<div class="col-xl-12 col-md-6 mb-4">
			<!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
            
            
            
            <c:forEach items="${gruposClase}" var="grupo">
                	<div class="card border-left-primary shadow h-100 py-2" id="profesor1">
                		<div class="card-body">
                  			<div class="row no-gutters align-items-center">
                    			<div class="col mr-2">
                      				<div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:15px;">Grupo ${grupo.nombre}</div>
									Número de alumnos del grupo: ${grupo.numeroAlumnos}<br>
									Decripción: ${grupo.descripcion}<br>
									Profesores:
									<c:forEach items="${grupo.profesores}" var="asociacion">
									<p >${asociacion.profesor.usuario.nombre } ${asociacion.profesor.usuario.apellidos } - 
									
									Horas de teoría: ${asociacion.hTeoria } - Horas de laboratorio: ${asociacion.hLaboratorio } - Horas de practicas: ${asociacion.hPracticas }</p>
									</c:forEach>

                    			</div>
                  			</div>
                		</div>
              		</div>
                	
</c:forEach>
            
            
            

              </div>
            </div>
            
            
            
            
</div>


          
          
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

</html>



