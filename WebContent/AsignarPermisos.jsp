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
          <h1 class="h3 mb-2 text-gray-800">Asignar permisos</h1>

            
<form action="AsignarPermisosServlet" class="user">
	<input type="hidden" value="${usuario.id}" name="id" >

<!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Seleccione o deseleccione los permisos del usuario <b> ${usuario.nombre} ${usuario.apellidos} </b></h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
              
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Permiso</th>
                      <th>Descripción</th>
                      <th>Seleccionar/Deseleccionar</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  <c:forEach items="${permisosUsuario}" var="permiso1">
		<tr>
			<td>${permiso1.permiso } </td>
			<td>${permiso1.descripcion } </td>
			<td><input type="checkbox" value="${permiso1.id}" name="permisos" checked></td>
		</tr>
	</c:forEach>
	

	<c:forEach items="${todosPermisos}" var="permiso">
		<tr>
			<td>${permiso.permiso } </td>
			<td>${permiso.descripcion } </td>
			<td><input type="checkbox" value="${permiso.id}" name="permisos"></td>
		</tr>
	</c:forEach>
                  </tbody>
                </table>
               
              </div>
            </div>
          </div>
          
           <button type="submit"class="btn btn-primary btn-user btn-block" style="width:40%;">Guardar cambios</button>
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

    <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>



