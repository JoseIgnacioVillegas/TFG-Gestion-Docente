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
		String apellidos = request.getParameter("apellidos");
		String acron = request.getParameter("acronimo");
		String correo = request.getParameter("correo");
		String grupo = request.getParameter("grupo");
		String dedicacion = request.getParameter("dedicacion");
		String plaza = request.getParameter("plaza");
		String[] dedicacionlista = {"1h","2h","3h","4h","5h","6h","7h","8h","9h","10h","11h","12h"};
		
		String id = request.getParameter("id");
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
            <h1 class="h3 mb-0 text-gray-800">Editar Docente</h1>
          </div>

      
          <!-- Content Row -->
          <div class="row">
			<div class="col-xl-3 col-md-6 mb-4"></div>
            	<div class="col-xl-6 col-md-6 mb-4" style="text-align:center;">
            	<form class="user" action="EditarProfesorServlet">
					<input type="hidden" value="<%=id%>" name="id">
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="nombre" value="<%=nom%>"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="apellidos" value="<%=apellidos%>"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="acronimo" value="<%=acron%>"></div>
                    <div class="form-group"><input type="text" class="form-control form-control-user" name="correo"  value="<%=correo%>"></div>                 
                 	<div class="form-group">  
						<select name="grupo" id="grupo"  class="form-control">
							<option value="">Seleccionar grupo de investigación</option>
							<c:forEach items="${grupos}" var="grupo">
								<c:choose>
					         		<c:when test ="${grupo.nombre} == <%=grupo%>">
										<option value="${grupo.nombre}" selected >${grupo.acronimo}  </option>
					         		</c:when>
					         
					         		<c:otherwise>
					            		<option value="${grupo.nombre}"  >${grupo.acronimo} </option>
					        		 </c:otherwise>
					          	</c:choose>
					         </c:forEach>
						</select>
					</div>
					<div class="form-group">
						<select name="dedicacion" id="dedicacion" class="form-control">

								<c:forEach items="<%=dedicacionlista%>" var="dedicacion1">
									<c:choose>
						         		<c:when test ="${dedicacion1} == <%=dedicacion%>">
											<option value="${dedicacion1}" selected >${dedicacion1}  </option>
						         		</c:when>
						         
						         		<c:otherwise>
						            		<option value="${dedicacion1}"  >${dedicacion1} </option>
						        		 </c:otherwise>
						          	</c:choose>
						         </c:forEach>
						</select>
	   				</div>
            		<div class="form-group"> 
            		<select name="plaza" id="plaza" class="form-control">
            			<option value="">Seleccionar plaza de profesor</option>
						<c:forEach items="${plazas}" var="plaza">
							<c:choose>
				         		<c:when test ="${plaza.plaza} == <%=plaza%>">
									<option value="${plaza.id}" selected >${plaza.plaza}  </option>
				         		</c:when>
				         
				         		<c:otherwise>
				            		<option value="${plaza.id}"  >${plaza.plaza} </option>
				        		 </c:otherwise>
				        		 
				        		 
				          	</c:choose>
				         </c:forEach>
					</select>
					</div>
					<br>
                  
                  
                  
                  
                  
                  
                  
                    
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

