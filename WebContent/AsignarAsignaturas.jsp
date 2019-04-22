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
            <h1 class="h3 mb-0 text-gray-800">Asignar asignaturas al docente  ${profesor.usuario.nombre } ${profesor.usuario.apellidos } </h1>
          </div>


<form action="AsignarAsignaturasServlet" class="user">
<input type="hidden" value="${profesor.id}" name="id">

	<!-- Content Row -->
    <div class="row" >	
    	<!-- Celda superior izquierda, asignaturas en las que participa -->
		<div class="col-xl-6 col-md-6 mb-4">
			<!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
            	<!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                  <h6 class="m-0 font-weight-bold text-primary">Asignaturas en las que participa</h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                	<div class="card-body">
					<!-- AQUI APARECEN LAS ASIGNATURAS EN LAS QUE PARTICIPA PARA PODER ELIMINARLAS -->
					<c:set var = "cod3" value="${longitud}"/>
					<c:set var = "cod4" value="<%=0%>"/>
					<c:if test = "${cod3 eq cod4}"><p>Este docente no participa en ninguna asignatura</p></c:if>
					<c:if test = "${cod3 != cod4}">
						<c:forEach items="${asignaturasParticipa}" var="asignatura">
							<p>
								<input type="checkbox" style="visibility:hidden;" value="${asignatura.codigo}" name="asignaturasParticipaBorradas" id="${asignatura.codigo}">
								${asignatura.codigo} - ${asignatura.nombre}
								<a href="#" onclick="ponerValor(this,'${asignatura.codigo}');">Eliminar esta asignatura</a>
							</p>
						</c:forEach>
						</c:if>                  
                  	</div>
                </div>
              </div>
            </div>


			<!-- Celda superior superior derecha, aparecen las asignaturas que coordina -->
          <div class="col-xl-6 col-md-6 mb-4">
          	<!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
            	<!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                  <h6 class="m-0 font-weight-bold text-primary">Asignaturas que coordina</h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                	<div class="card-body">
						<!-- AQUI APARECE LAS ASIGNATURA QUE COORDINA PARA PODER ELIMINARLAS -->
						<c:set var = "cod1" value="${asignaturaCoordina}"/>
						<c:set var = "cod2" value="<%=null%>"/>
						
						<c:if test = "${cod1 eq cod2}"><p>Este docente no coordina ninguna asignatura</p></c:if>
						<c:if test = "${cod1 != cod2}">
							<p>
								<input type="hidden" name="asignaturasCoordinaBorradas" value="${asignaturaCoordina.codigo}">
								${asignaturaCoordina.codigo} - ${asignaturaCoordina.nombre}
								<a href="#" onclick="eliminarAsignatura(this);">Eliminar esta asignatura</a>
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
          
          
			<!-- Celda inferior izquierda, añadir asignaturas en las que participa -->             
          	<div class="col-xl-6 col-md-6 mb-4">
				<div class="card mb-4 py-3 border-bottom-primary" >
					<div class="card-body">
						<p>Añadir nuevas asignaturas en las que participe, para ello puede buscar el nombre en el cuadro de busqueda</p>
						<div id="participa1">
							<p>
								<input type="text" id="buscar" onKeyUp="buscarSelect(this)" placeholder="Buscar asignatura">
								<select id="elementos" name="participa">
									<option selected value="0">Seleccionar la asignatura </option>
									<c:forEach items="${todasAsignaturas}" var="asignatura">
										<option value="${asignatura.codigo}" >${asignatura.nombre}</option>
									</c:forEach>
								</select>
							</p>
						</div>
						<div id="participa"></div>
						<br>
						<a  href="#" onClick="addAsignaturaParticipar()" >Añadir otra asignatura en la que participe</a>
					</div>
				</div>
			</div>
              
           <!-- Celda inferior derecha, añadir asignatuas que coordine -->   
           <div class="col-xl-6 col-md-6 mb-4">   
	           	<div class="card mb-4 py-3 border-bottom-primary" >
					<div class="card-body">
	              		<p>Añadir asignatura que coordine</p>
						<c:if test = "${cod1 eq cod2}">
							<div id="coordina1">
								<p>
									<select name="coordina">
										<option selected>Seleccionar Asignatura que coordina</option>
										<c:forEach items="${todasAsignaturas}" var="asignatura" >
											<option value="${asignatura.codigo}">${asignatura.codigo} - ${asignatura.nombre} </option>
										</c:forEach>
									</select>
								</p>
							</div>		
						</c:if>
						<c:if test = "${cod1 != cod2}"><p>Este Docente ya coordina una asignatura</p></c:if>
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




















function addAsignaturaParticipar(){
    var div = document.createElement('div');
    div.setAttribute('class', 'form-inline');
        div.innerHTML = document.getElementById('participa1').outerHTML;
        document.getElementById('participa').appendChild(div);
}

















function eliminarAsignatura(obj){

	var elemento = obj.parentNode;
	
	elemento.style.display = "none";
	

}







function marcarAsignatura(source) {
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
	console.log(input);
	input.checked= true;
	var elemento = obj.parentNode;
	elemento.style.display = "none";
}


















function addAsignaturaCoordinar(){
    var div = document.createElement('div');
    div.setAttribute('class', 'form-inline');
        div.innerHTML = document.getElementById('coordina1').outerHTML;
        document.getElementById('coordina').appendChild(div);
}


function mostrarAsignaturas(source,codigo){
	elements = source.parentNode.getElementsByTagName('select');

	elements[1].style.visibility = "";
}

function mostrarGrupos(source){
	element = document.getElementById('grupos');
	element.style.visibility = "";
}







</script>
</html>



