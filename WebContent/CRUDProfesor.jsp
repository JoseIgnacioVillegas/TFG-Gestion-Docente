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
          <h1 class="h3 mb-2 text-gray-800">Gestión de los Docentes</h1>
          
   
          
          <shiro:hasAnyRoles name="administrador, gestionusuarios">
          
          <!-- Content Row -->
          <div class="row">

            <form action="CrearProfesor.jsp" >
            <input type="hidden" value="${permisos}" name="permisos">
            <button type="submit" >
            <div class="col-xl-12 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:15px;">Para crear un nuevo docente pincha aquí</div>

                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
			</button>
			</form>
            
            
            
            
            
            
            <form action="GestorServlet" >
            <button type="submit" value="CRUDPlaza" name="CRUDPlaza">
            <div class="col-xl-12 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1" style="font-size:15px;">Para gestionar las plazas de profesores pincha aquí</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
			</button>
			</form>
			
			
			<form action="GestorServlet" >
            <button type="submit" value="CRUDGrupo" name="CRUDGrupo">
            <div class="col-xl-12 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1" style="font-size:15px;">Para gestionar grupos de investigación pincha aquí</div>

                    </div>
                    <div class="col-auto">
                      <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </button>
            </form>

                    


          </div>
          
          
          </shiro:hasAnyRoles>
          
          
          
          
          
          
          
            


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
                      <th style="font-size:15px;">NOMBRE</th>
                      <th>Apellidos</th>
                      <th>Acrónimo</th>
                      <th>Correo</th>
                      <th>Grupo</th>
                      <th>Dedicación</th>
                      <th>Plaza</th>
                      <th>Acciones</th>
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
			
			<shiro:hasAnyRoles name="administrador, gestionusuarios">
			<td>
			<form action="EditarProfesor.jsp">
				<input type="hidden" value="${profesor.id}" name="id">
				<input type="hidden" value="${profesor.usuario.nombre}" name="nombre">
				<input type="hidden" value="${profesor.usuario.apellidos}" name="apellidos">
				<input type="hidden" value="${profesor.acronimo}" name="acronimo">
				<input type="hidden" value="${profesor.usuario.correo}" name="correo">
				<input type="hidden" value="${profesor.plaza.plaza}" name="plaza">
				<input type="hidden" value="${profesor.dedicacion}" name="dedicacion">
				<input type="hidden" value="${profesor.grupo.nombre}" name="grupo">
				<button  title="Editar" type="submit" class="btn btn-success btn-circle" >
					<img  style="padding-bottom:15px;padding-left:2px;width:160%;height:160%;"  src="./img/edit.svg" />
				</button>
			</form>
			
			


			<form action="BorrarProfesorServlet">
			<input type="hidden" value="${profesor.id}" name="idProfe">
			<input type="hidden" value="${profesor.usuario.id}" name="idUsuario">
			<button href="#" title="Borrar" type="submit" class="btn btn-danger btn-circle">
				<img  style="padding-bottom:15px;width:160%;height:160%;"  src="./img/bin.svg" />
			</button>
			</form>
			
			
			
			
			<form action="ObtenerAsignaturasServlet">
			<input type="hidden" value="${profesor.id}" name="idProfe">
			<button type="submit" class="btn btn-info btn-icon-split">
		       <span class="icon text-white-50"><img  style="width:25px;height:25px;"src="./img/gestion.svg" /></span>
		       <span class="text">Gestionar asignaturas</span>
		    </button>
			</form>
			
			<form action="ObtenerPermisosServlet">
			<input type="hidden" value="${profesor.usuario.id}" name="id">
			<button type="submit" class="btn btn-primary btn-icon-split">
		       <span class="icon text-white-50"><img  src="./img/lock.svg" /></span>
		       <span class="text">Asignar permisos</span>
		    </button>
			</form>
			</td>
			
			</shiro:hasAnyRoles>
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



