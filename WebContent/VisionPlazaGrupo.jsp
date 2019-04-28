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
          <h1 class="h3 mb-2 text-gray-800">Vision de los grupos de investigación y las plazas de profesor</h1>
          
	

            


<!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Grupos de investigación</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Nombre</th>
                      <th>Acrónimo</th>
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <c:forEach items="${grupos}" var="grupo">

	
		<tr>
			<td>${grupo.nombre}</td>
			<td>${grupo.acronimo}</td>
			<td>
			<div class="row" style="padding-left:5px;">
			<form action="ObtenerDocentesServlet">
			<input type="hidden" name="docentesGrupo" value="docentesGrupo"/>
			<input type="hidden" value="${grupo.nombre}" name="grupo">
			<button type="submit" class="btn btn-info btn-icon-split">
		       <span class="icon text-white-50"><img  style="width:25px;height:25px;"src="./img/detalles.png" /></span>
		       <span class="text">Ver Docentes</span>
		    </button>
			</form></div>
			</td>
		</tr>
		
		</c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          
          
          
          
          docentesPlaza
          
          
          
          
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Plazas de profesor</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Nombre</th>
                      <th>Descripción</th>
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <c:forEach items="${plazas}" var="plaza">

	
		<tr>
		<td>${plaza.plaza}</td>
		<td><p title="${plaza.descripcion}" style="height:20px;width:100px; overflow:hidden!important;">${plaza.descripcion}</p></td>
		<td>
		
		<div class="row" style="padding-left:5px;">
					<form action="ObtenerDocentesServlet">
			<input type="hidden" name="docentesPlaza" value="docentesPlaza"/>
			<input type="hidden" value="${plaza.id}" name="plaza">
			<button type="submit" class="btn btn-info btn-icon-split">
		       <span class="icon text-white-50"><img  style="width:25px;height:25px;"src="./img/detalles.png" /></span>
		       <span class="text">Ver Docentes</span>
		    </button>
			</form>
			</div>
			</td>
		

			
			
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

</body>

</html>



