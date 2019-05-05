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
          <h1 class="h3 mb-2 text-gray-800">Gestión de las asignaturas</h1>
          
   
          
          <shiro:hasAnyRoles name="administrador, gestiondocencia">
          
          <!-- Content Row -->
          <div class="row">
			<form action="GestorServlet" >
            <button type="submit" value="importarapi" name="importarapi">
            <div class="col-xl-12 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:15px;">Para importar asignaturas de la API pincha aquí</div>

                    </div>
                    <div class="col-auto">
                      <img  style="width:30px;height:30px;"src="./img/import.png" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
			</button>
			</form>
            
            
            <form action="ObtenerDocentesServlet">
            <input type="hidden" value="crear" name="crear"/>
            <button type="submit" value="CRUDPlaza" name="CRUDPlaza">
            <div class="col-xl-12 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1" style="font-size:15px;">Para crear una nueva asignatura pincha aquí</div>
                    </div>
                    <div class="col-auto">
                      <img  style="width:30px;height:30px;"src="./img/crear.png" />
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
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
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
			<th  class="${plan.codigo}">
				<a href="#" onclick="OcultarAsignaturas(this)" style="display:none;">Ocultar asignaturas</a>
		       	<a href="#" onclick="MostrarAsignaturas(this)" >Mostrar asignaturas</a>
			</th>
	</tr>
                  <c:forEach items="${plan.asignaturas}" var="asignatura">
		<tr class="${plan.codigo}" style="display:none;">
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
			<div class="row" style="padding-left:5px;">
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
				<button  title="Editar" type="submit" class="btn btn-success btn-circle" >
					<img  style="padding-bottom:15px;padding-left:2px;width:160%;height:160%;"  src="./img/edit.svg" />
				</button>
				</form>
			
				<form action="BorrarAsignaturaServlet">
				<input type="hidden" value="${asignatura.codigo}" name="codigo">
				<button title="Borrar" type="submit" class="btn btn-danger btn-circle">
				<img  style="padding-bottom:15px;width:160%;height:160%;"  src="./img/bin.svg" />
				</button>
				</form>
				</div>
				<br>
				<div class="row" style="padding-left:5px;">
				<form action="ObtenerDocentesServlet">
				<input type="hidden" value="${asignatura.codigo}" name="codigo">
				<button type="submit" class="btn btn-primary btn-icon-split">
		       	<span class="icon text-white-50"><img  src="./img/lock.svg" /></span>
		       	<span class="text">Asignar docentes</span>
		    	</button>
				</form>
				</div>
				<br>
				<div class="row" style="padding-left:5px;">
				<form action="ObtenerGruposServlet">
				<input type="hidden" value="${asignatura.codigo}" name="codigo">
				<button type="submit" class="btn btn-info btn-icon-split">
		       	<span class="icon text-white-50"><img  style="width:25px;height:25px;"src="./img/gestion.svg" /></span>
		       	<span class="text">Gestionar grupos</span>
		    	</button>
				</form>
				</div>
			</td>
			
		</tr>
	</c:forEach>
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
	
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>
  
  <!--  AQuí está el problema de que se muestren mal las asignaturas y los planes
  <script src="js/demo/datatables-demo.js"></script> 
-->
</body>
<script>
function OcultarAsignaturas(obj){
	var plan = obj.parentNode.getAttribute("class");
	var elementos = document.getElementsByClassName(plan);
	for (var i = 1; i < elementos.length; i++) {
		elementos[i].style.display = "none";
	}
	obj.parentNode.getElementsByTagName("a")[0].style.display = "none";
	obj.parentNode.getElementsByTagName("a")[1].style.display = "";
}

function MostrarAsignaturas(obj){
	var plan = obj.parentNode.getAttribute("class");
	var elementos = document.getElementsByClassName(plan);
	for (var i = 1; i < elementos.length; i++) {
		elementos[i].style.display = "";
	}
	obj.parentNode.getElementsByTagName("a")[0].style.display = "";
	obj.parentNode.getElementsByTagName("a")[1].style.display = "none";
}

</script>
</html>



