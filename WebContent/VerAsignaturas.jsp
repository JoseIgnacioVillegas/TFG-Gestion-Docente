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
          	
			
			<h1 class="h3 mb-2 text-gray-800">Asignaturas asignadas al profesor   ${profesor.nombre} - ${profesor.apellidos}</h1>
			
			
			
			<div class="card border-left-primary shadow h-100 py-2" style="width:100%">
	                <div class="card-body">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:20px">
	                      	<c:set var = "cod1" value="<%=null%>"/>
							<c:set var = "cod2" value="${asignaturasCoordina}"/>	
	                      <c:if test = "${cod1 eq cod2}"> Este profesor no coordina ninguna asignatura</c:if>
	                      <c:if test = "${cod1 != cod2}"> Este profesor coordina la asignatura de ${asignaturasCoordina.codigo} - ${asignaturasCoordina.nombre}</c:if>
	                      
	                      </div>

	                    </div>
	                  </div>
	                </div>
              </div> 
			<br><br>
			
			
			
			


<!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Asignaturas</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive	">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th style="font-size:15px;">Código</th>
                      <th>Nombre</th>
                      <th>Acrónimo</th>
                      <th>Curso</th>
                      <th>Semestre</th>
                      <th>Créditos</th>
                      <th>Tipo</th>
                      <th>Horas APOLO</th>
                      <th>Horas laboratorio</th>
                      <th>Horas teoría</th>
                      <th>Número de alumnos</th>
                      <th>Comentarios</th>
                    </tr>
                  </thead>
                  <tbody>

                  <c:forEach items="${asignaturasParticipa}" var="asignatura">
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
			
	
			
		</tr>
	</c:forEach>

                  </tbody>
                </table>
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



