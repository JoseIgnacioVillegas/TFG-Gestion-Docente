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

<%
	String nom = request.getParameter("nombre");
	String codigo = request.getParameter("codigo");
%>



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
            <h1 class="h3 mb-0 text-gray-800">Editar plan de estudios</h1>
          </div>

      
          <!-- Content Row -->
          <div class="row">
			<div class="col-xl-3 col-md-6 mb-4"></div>
            	<div class="col-xl-6 col-md-6 mb-4" style="text-align:center;">
            	<form class="user" action="EditarPlanServlet">
            		<input type="hidden" value="<%=codigo%>" name="codigo1">
					
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="codigo" value="<%=codigo%>"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="nombre" value="<%=nom%>"></div>

                     <button type="submit" class="btn btn-primary btn-user btn-block">Guardar cambios</button>

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

