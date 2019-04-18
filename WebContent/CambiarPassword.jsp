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
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Cambiar contraseña </h1>
          </div>

      
      <p>
      

      
      </p>
    
          <!-- Content Row -->
          <div class="row" >		
			<div class="card mb-4 py-3 border-bottom-primary" style="width:60%;align:center;margin-left:auto;margin-right:auto;">
				<div class="card-body">
				 <form class="user" action="CambiarPasswordServlet">
				 <input type="hidden" name="usuario" value="<shiro:principal/>" />
                    <div class="form-group"><input type="password" class="form-control form-control-user" name="passAntigua" id="passAntigua" placeholder="Contraseña actual"></div>
                    <div class="form-group"><input type="password" class="form-control form-control-user" name="passNueva" id="passNueva"  placeholder="Contraseña nueva"></div>
                    <div class="form-group"><input type="password" class="form-control form-control-user" name="passNueva2" id="passNueva2" placeholder="Repite la nueva contraseña" onkeyup="comprobarClave()"></div>
                    <button type="submit" class="btn btn-primary btn-user btn-block" id="boton" disabled="true">Cambiar contraseña</button>
                    <hr>
                 </form>			
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
<script>
function comprobarClave(){
	var clave1 = document.getElementById('passNueva').value;
	var clave2 = document.getElementById('passNueva2').value;
	var boton = document.getElementById('boton');
	if(clave1==clave2)boton.disabled=false;
}


</script>
</html>
