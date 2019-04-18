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
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Crear Docente</h1>
          </div>

      
          <!-- Content Row -->
          <div class="row">
			<div class="col-xl-3 col-md-6 mb-4"></div>
            	<div class="col-xl-6 col-md-6 mb-4" style="text-align:center;">
            	

            	<form class="user"  action="CrearProfesorServlet">
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="nombre" placeholder="Introduce el nombre del docente"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="apellidos" placeholder="Introduce los apellidos del docente"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="acronimo" placeholder="Introduce el acrónimo del docente"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="correo" placeholder="Introduce el email del docente"></div>                 
                 	<div class="form-group">  
						<select name="grupo"id="grupo"  class="form-control">
							<option value="" selected>Selecciona el grupo de investigación al que pertenece </option>
							<c:forEach items="${grupos}" var="grupo">
								<option value="${grupo.nombre}">${grupo.acronimo}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<select name="dedicacion" id="dedicacion" class="form-control">
							<option value="" selected>Selecciona la dedicación</option>
							<option value="1h" >1h</option>
							<option value="2h">2h</option>
							<option value="3h">3h</option>
							<option value="4h">4h</option>
							<option value="5h">5h</option>
							<option value="6h">6h</option>
							<option value="7h">7h</option>
							<option value="8h">8h</option>
							<option value="9h">9h</option>
							<option value="10h">10h</option>
							<option value="11h">11h</option>
							<option value="12h">12h</option>	
						</select>
	   				</div>
            		<div class="form-group">         
						<select name="plaza" id="plaza" class="form-control">
							<option value="" selected>Selecciona el tipo de plaza</option>
								<c:forEach items="${plazas}" var="plaza">
									<option value="${plaza.id}"> ${plaza.plaza} </option>
								</c:forEach>
						</select>
					</div>
					<div class="form-group"> 
					<p class="form-control" style="height:70px;"> Selecciona los permisos que le quieres dar a este profesor<br>
					
					<c:forEach items="${permisos}" var="permiso">
						 ${permiso.permiso} <input type="checkbox" value="${permiso.id}"> 
					</c:forEach></p>
					</div>
					<br>
                  
                  
                  
                  
                  
                  
                  
                    
                     <button type="submit" class="btn btn-primary btn-user btn-block">Crear</button>

                    <hr>
                   
                  </form>  
                  
       
                           
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

