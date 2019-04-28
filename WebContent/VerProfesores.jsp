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
          	
			<c:set var = "cod1" value="<%=null%>"/>
			<c:set var = "cod2" value="${asignatura}"/>	
			<c:set var = "cod3" value="${grupo}"/>	
			<c:set var = "cod4" value="${plaza}"/>	
				
			<c:if test = "${cod1 eq cod2}">
			<c:if test = "${cod1 eq cod3}">
			<h1 class="h3 mb-2 text-gray-800">Docentes cuya plaza de profesor es ${plaza}</h1>
			</c:if>
			</c:if>
			
			<c:if test = "${cod1 eq cod2}">
			<c:if test = "${cod1 eq cod4}">
			<h1 class="h3 mb-2 text-gray-800">Docentes cuyo grupo de investigación es ${grupo}</h1>
			</c:if>
			</c:if>
			
			<c:if test = "${cod1 eq cod3}">
			<c:if test = "${cod1 eq cod4}">
			<h1 class="h3 mb-2 text-gray-800">Docentes que participan en la asignatura ${asignatura.codigo} - ${asignatura.nombre} - ${asignatura.acronimo}</h1>
			
			
			
			<div class="card border-left-primary shadow h-100 py-2" style="width:100%">
	                <div class="card-body">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:20px">El coordinador de esta asignatura es ${coordinador.nombre } ${coordinador.apellidos}</div>

	                    </div>
	                  </div>
	                </div>
              </div> 
			<br><br>
			
			</c:if>
			</c:if>
			


<!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Profesores</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive	">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Nombre</th>
                      <th>Apellidos</th>
                      <th>Acrónimo</th>
                      <th>Correo</th>
                      <th>Grupo</th>
                      <th>Dedicación</th>
                      <th>Plaza</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <c:forEach items="${profesores}" var="profesor">

	
		<tr>
			<td>${profesor.usuario.nombre}</td>
			<td>${profesor.usuario.apellidos}</td>
			<td>${profesor.acronimo}</td>
			<td>${profesor.usuario.correo}</td>
			<td>${profesor.grupo.acronimo}</td>
			<td>${profesor.dedicacion}</td>
			<td>${profesor.plaza.plaza}</td>
			
			
			
	
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



