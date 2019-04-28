<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
       <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
       



<html lang="en">

<!-- En este archivo .jsp esta definida la cabecera -->
	<%@ include file="head.jsp" %> 


<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">


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
            <h1 class="h3 mb-0 text-gray-800">Vista general</h1>
          </div>







      
          <!-- Content Row -->
          <div class="row">
			<div class="col-xl-4 col-md-6 mb-4">
			<div class="card shadow mb-4">
                <!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                  <h6 class="m-0 font-weight-bold text-primary" style="font-size:20px">Descripción</h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                  <div class="card-body">
                  Bienvenido/a a la aplicación para la gestión del Departamento de Igeniería de Sistemas Telemáticos.<br>
                  En esta página tiene una visión general del departamento, navegando por las diferentes pantallas podrá realizar las acciones que tenga permitidas.
                  </div>
                </div>
              </div>
				
			</div>

            <div class="col-xl-4 col-md-6 mb-4" style="text-align:center;">
				<img src="./img/dit.gif" style="width:50%;height:auto;"/>   
            </div>

         <div class="col-xl-4 col-md-6 mb-4">
                 <div class="card border-left-primary shadow h-100 py-2">
	                <div class="card-body">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:20px">Número de profesores del departamento</div>
	                      <div class="h5 mb-0 font-weight-bold text-gray-800">${Numeroprofesores }</div>
	                    </div>
	                  </div>
	                </div>
              </div>      
         </div>           
     </div>


          <!-- Content Row -->
          <div class="row">
          
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1" style="font-size:20px">Número de asignaturas del departamento</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">${Numeroasignaturas }</div>
                    </div>

                  </div>
                </div>
              </div>
            </div>
            
			<div class="col-xl-4 col-md-6 mb-4">
				<div style="align:center;margin-left:auto;margin-right:auto;"><img src="./img/upmdit.gif" style="width:50%;height:auto;"/></div>
            </div>

            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1" style="font-size:20px">Número de planes de estudios del departamento</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${Numeroplanes }</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
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
