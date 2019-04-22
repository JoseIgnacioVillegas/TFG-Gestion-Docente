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
          <h1 class="h3 mb-2 text-gray-800">Importar asignaturas</h1>

<form action="ImportarAsignaturasServlet">
<input type="hidden" name="anio" value="${anio}">
<input type="hidden" name="planes" value="${listaPlanes}">

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
                      <th>Importar todo <input type="checkbox" onclick="marcar(this)"></th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  
<c:forEach items="${listaPlanes}" var="plan">
		
		
		<tr>
			<th>${plan.codigo}</th>
			<th>${plan.nombre}</th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
			
			<th><input type="checkbox"  name="codigoPlan" value="${plan.codigo}" onclick="marcarAsignaturas(this,'${plan.codigo}');"></th>
	</tr>
		
		<c:forEach items="${plan.asignaturas}" var="asignatura">
		
		<tr>
			<td>${asignatura.codigo}</td>
			<td>${asignatura.nombre}</td>
			<td>${asignatura.acronimo}</td>
			<td>${asignatura.curso}</td>
			<td>${asignatura.semestre}</td>
			<td>${asignatura.ects}</td>
			<td>${asignatura.tipo}</td>
			
			<td><input name="${plan.codigo}" value="${asignatura.codigo}" type="checkbox"></td>
		</tr>
			
		</c:forEach>
		
		
	
	</c:forEach>
                  </tbody>
                </table>
                
                
              </div>
            </div>
          </div>
          <input type="submit" value="Importar">
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

  
<!-- En este archivo .jsp estan incluidos los enlances a los archivos JS -->
	<%@ include file="JavaScript.jsp" %> 

</body>
<script>

function marcar(source) {
	
	checkboxes=document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
	for(i=0;i<checkboxes.length;i++){
		if(checkboxes[i].type == "checkbox") {
			checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
		}
	}
}

function marcarAsignaturas(source, id) {
	console.log(id);
	checkboxes=document.getElementsByName(id); //obtenemos todos los controles del tipo Input
	
	for(i=0;i<checkboxes.length;i++){
		if(checkboxes[i].type == "checkbox") {
			checkboxes[i].checked=source.checked; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
		}
	}
}

</script>
</html>



