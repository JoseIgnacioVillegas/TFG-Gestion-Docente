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
          <h1 class="h3 mb-2 text-gray-800">Visión de las asignaturas</h1>
          
   
          
  
          
          
            


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
			<th class="${plan.codigo}">
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
				<form action="ObtenerDocentesServlet">
				<input type="hidden" name="docentesAsignatura" value="docentesAsignatura"/>
				<input type="hidden" value="${asignatura.codigo}" name="asignatura">
				
				<button type="submit" class="btn btn-info btn-icon-split">
		       	<span class="icon text-white-50"><img  style="width:30px;height:30px;" src="./img/detalles.png" /></span>
		       	<span class="text">Ver Docentes</span>
		    	</button>
				</form>
				</div>
				<br><br>
				<div class="row" style="padding-left:5px;">
				<form action="ObtenerGruposServlet">
				<input type="hidden" name="visionGrupos" value="visionGrupos"/>
				<input type="hidden" value="${asignatura.codigo}" name="codigo">
				
				<button type="submit" class="btn btn-info btn-icon-split">
		       	<span class="icon text-white-50"><img  style="width:30px;height:30px;" src="./img/detalles.png" /></span>
		       	<span class="text">Ver Grupos</span>
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



