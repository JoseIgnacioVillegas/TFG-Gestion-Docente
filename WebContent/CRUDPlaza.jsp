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
          <h1 class="h3 mb-2 text-gray-800">Gestión de las plazas de profesor</h1>
          
          
           <form action="CrearPlaza.jsp">
           <button type="submit" >
            <div class="col-xl-9 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1 " style="font-size:15px;">Para crear una nueva plaza de profesor pincha aquí</div>
                    </div>
                    <div class="col-auto">
                      <img  style="width:30px;height:30px;"src="./img/gestionar.png" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </button>	
            </form>
            


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
		<form action="EditarPlaza.jsp" >
			<input type="hidden" value="${plaza.id}" name="id">
			<input type="hidden" value="${plaza.plaza}" name="nombre">
			<input type="hidden" value="${plaza.descripcion}" name="descripcion">
			<button  title="Editar" type="submit" class="btn btn-success btn-circle" >
					<img  style="padding-bottom:15px;padding-left:2px;width:160%;height:160%;"  src="./img/edit.svg" />
			</button>
			</form>
			
			
			<form action="BorrarPlazaServlet">
			<input type="hidden" value="${plaza.id}" name="id">
			<button title="Borrar" type="submit" class="btn btn-danger btn-circle">
				<img  style="padding-bottom:15px;width:160%;height:160%;"  src="./img/bin.svg" />
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
	<%@ include file="JavaScript.jsp" %> 

</body>

</html>



