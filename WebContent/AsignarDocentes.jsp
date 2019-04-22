<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <h1 class="h3 mb-0 text-gray-800">Asignar Usuarios para la asignatura ${codigo } - ${nombre } - ${acronimo } </h1>
          </div>


<form action="AsignarDocentesServlet" class="user">
<input type="hidden" value="${codigo}" name="codigoAsignatura">


	<!-- Content Row -->
    <div class="row" >	
    	<!-- Celda superior izquierda, profesores que participan en la asignatura -->
		<div class="col-xl-6 col-md-6 mb-4">
			<!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
            	<!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                  <h6 class="m-0 font-weight-bold text-primary">Profesores que participan en esta asignatura</h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                	<div class="card-body">
               			<c:set var = "cod3" value="${longitud}"/>
						<c:set var = "cod4" value="<%=0%>"/>
						<c:if test = "${cod3 eq cod4}"><p>Aún no hay profesores que participen en esta asignatura</p></c:if>
						<c:if test = "${cod3 != cod4}">
							<c:forEach items="${profesoresPorAsignatura}" var="profesor">
								<p>
								<input type="checkbox" style="visibility:hidden;" value="${profesor.id}"name="profesoresBorrados" id="${profesor.id}">
								${profesor.usuario.nombre} ${profesor.usuario.apellidos}
								<a href="#" onclick="ponerValor(this,'${profesor.id}');">Eliminar este profesor</a>
								</p>
							</c:forEach>
						</c:if>		                 
                  	</div>
                </div>
              </div>
            </div>


		<!-- Celda superior superior derecha, aparece el coordinador de la asignatura-->
          <div class="col-xl-6 col-md-6 mb-4">
          	<!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
            	<!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                  <h6 class="m-0 font-weight-bold text-primary">Coordinador de la asignatura</h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                	<div class="card-body">
						<c:set var = "cod1" value="${coordinador.usuario.nombre}"/>
						<c:set var = "cod2" value="<%=null%>"/>
						<c:if test = "${cod1 eq cod2}"><p>Aún no hay coordinadores asignados a esta asignatura</p></c:if>
						<c:if test = "${cod1 != cod2}">
							<p>
								<input type="checkbox" style="visibility:hidden;" value="${coordinador.id}" name="coordinadorBorrado" id="${coordinador.usuario.correo}">
								${coordinador.usuario.nombre} ${coordinador.usuario.apellidos}
								<a href="#" onclick="ponerValor(this,'${coordinador.usuario.correo}');">Eliminar coordinador</a>
							</p>
						</c:if>
                  	</div>
                </div>
              </div>
          </div>
        </div>
        <!-- End of Content Row -->
          

          <!-- Content Row -->
          <div class="row" >	
			<!-- Fila inferior, para añadir nuevos docentes -->             
          	<div class="col-xl-12 col-md-6 mb-4">
				<div class="card mb-4 py-3 border-bottom-primary" >
					<div class="card-body" style="align:center;margin-left:auto;margin-right:auto">ç
					
						<p>Añadir otro profesor o coordinador a la asignatura</p>
							<div id="profes1">
								<p>
								<input type="text" id="buscar" onKeyUp="buscarSelect(this)" placeholder="Buscar docente">
								<select name="profesores" onchange="setValue(this);">
									<option selected>Seleccionar docente</option>
									<c:forEach items="${todosProfesores}" var="profesor">
										<option value="${profesor.id}">${profesor.usuario.nombre} ${profesor.usuario.apellidos}</option>
									</c:forEach>
								</select>
								<a>
									Profesor<input name="profesor" type="checkbox">
									Coordinador<input name="coordinador" type="checkbox" onclick="marcarProfesor(this);"></a>
								</p>
							</div>
							
							<div id="profes"></div>
							<br>
							<a  href="#" onClick="addProfesor()" >Añadir otro profesor</a>
					</div>
				</div>
			</div>
        </div>
		<button type="submit" class="btn btn-primary btn-user btn-block" style="width:50%;align:center;margin-left:auto;margin-right:auto;">Guardar cambios</button>
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

  
<!-- En este archivo .jsp estan incluidos los enlances a los archivos JS -->
	<%@ include file="JavaScript.jsp" %> 

</body>
    
    

<script>

function buscarSelect(obj){
	var p = obj.parentNode;

	// creamos un variable que hace referencia al select
	var select = p.children[1];

	var input = p.children[0];
	
	// obtenemos el valor a buscar
	var buscar = input.value ;
 	
	// recorremos todos los valores del select
	for(var i=1;i<select.length;i++){
		var x = select.options[i].text.substr(0,buscar.length);
		if(x.toLowerCase() ==buscar.toLowerCase() ){
			// seleccionamos el valor que coincide
			select.selectedIndex=i;
		}
	}
}

function marcarProfesor(source) {
	elements = source.parentNode.getElementsByTagName('input');
	elements[0].checked = source.checked;
}

function setValue(source) {
	elements =source.parentNode.getElementsByTagName('input');
	elements[0].value=source.value;
	elements[1].value=source.value;
}


function ponerValor(obj,id){
	var input = document.getElementById(id);
	
	input.checked= true;
	var elemento = obj.parentNode;
	
	elemento.style.display = "none";
	
}



function addProfesor(){
        var div = document.createElement('div');
        div.setAttribute('class', 'form-inline');
            div.innerHTML = document.getElementById('profes1').outerHTML;
            document.getElementById('profes').appendChild(div);
}


</script>
</html>



