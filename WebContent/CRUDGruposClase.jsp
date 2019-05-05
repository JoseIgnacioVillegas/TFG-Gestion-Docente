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
            <h1 class="h3 mb-0 text-gray-800">Gestionar Grupos de Clase de la asignatura ${asignatura.codigo} - ${asignatura.nombre}</h1>
          </div>



      <form action="CRUDGruposClaseServlet" class="user">
<input type="hidden" name="asignatura" value="${asignatura.codigo}">
      
      <!-- Content Row -->
    <div class="row" >	
    	<!-- Celda superior izquierda, asignaturas en las que participa -->
		<div class="col-xl-12 col-md-6 mb-4">
			<!-- Collapsable Card Example -->
            <div class="card shadow mb-4">
            	<!-- Card Header - Accordion -->
                <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
                  <h6 class="m-0 font-weight-bold text-primary">Grupos de clase de esta asignatura</h6>
                </a>
                <!-- Card Content - Collapse -->
                <div class="collapse show" id="collapseCardExample">
                	<div class="card-body">
                	<c:forEach items="${gruposClase}" var="grupo">
                	<div class="card border-left-primary shadow h-100 py-2" id="profesor1">
                		<div class="card-body">
                  			<div class="row no-gutters align-items-center">
                    			<div class="col mr-2">
                      				<div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:15px;">Grupo ${grupo.nombre}</div>
									Número de alumnos del grupo: ${grupo.numeroAlumnos}<br>
									Decripción: ${grupo.descripcion}<br>
									Profesores:
									<c:forEach items="${grupo.profesores}" var="asociacion">
									<p >${asociacion.profesor.usuario.nombre } ${asociacion.profesor.usuario.apellidos } - 
									
									Horas de teoría: ${asociacion.hTeoria } - Horas de laboratorio: ${asociacion.hLaboratorio } - Horas de practicas: ${asociacion.hPracticas }</p>
									</c:forEach>
									<input type="checkbox" style="visibility:hidden;" value="${grupo.id}" name="gruposBorrados" id="${grupo.id}">
									<a href="#" style="width:50%;" onclick="borrarGrupo(this,${grupo.id})">Borrar Grupo de Clase</a>

                    			</div>
                  			</div>
                		</div>
              		</div>
                	
</c:forEach>
						
						
						
						
						                
                  	</div>
                </div>
              </div>
            </div>
            
            </div>
            
            
            
            
            
            
            
            
            
            
          <!-- Content Row -->
   
          
          
          
          <div class="row" id="grupo">
			<div class="card mb-4 py-3 border-bottom-primary" style="width:100%" >
				<div class="card-body" style="width:100%;">		
					<div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="font-size:15px;">Añadir nuevo grupo de clase</div>
					
					<div class="row">
					<div class="col-xl-6 col-md-6 mb-4">
					<input type="hidden" value="" name=""/>
					<div class="form-group" style="padding-top:20px;"><input type="text" class="form-control form-control-user" onKeyUp="setName(this)" name="nombreGrupoNuevo" placeholder="Introduce el nombre del grupo"></div>
					<div class="form-group" style="padding-top:20px;"><input type="text" class="form-control form-control-user" name="numeroAlumnosGrupoNuevo" placeholder="Introduce el numero de alumnos"></div>
					</div>
					
					<div class="col-xl-6 col-md-6 mb-4">
					
					Añadir descripción(max 140 caracteres):<br>
		 			<textarea type="text" name="descripcionGrupoNuevo" maxlength="250" style="width:400px;height:130px;vertical-align: top;" cols="35" rows="10" wrap="soft"></textarea>
					</div>
					</div>
					<br><br>
					
					<!-- Empieza la selección de los profesores -->
					<div id="invisible" style="display:none">
					<div class="card border-left-primary shadow h-100 py-2" id="profesor1">
                		<div class="card-body">
                  			<div class="row no-gutters align-items-center">
                    			<div class="col mr-2">
                      				<div class="text-xs font-weight-bold text-primary text-uppercase mb-1">seleccionar profesor y asignarle horas lectivas</div>
									
									<div class="row">
									<input style="height:50px;"  type="text"  onKeyUp="buscarSelect(this)" value="Buscar Profesor" onClick="this.value=''" onBlur="this.value='Buscar Profesor'">
									<select name="docentes"  style="height:50px;">
										<option selected value="0">Seleccionar la Docente </option>
										<c:forEach items="${todosProfesores}" var="profesor">
											<option value="${profesor.id}" >${profesor.usuario.nombre } ${profesor.usuario.apellidos }</option>
										</c:forEach>
									</select><br>
									<div class="form-group"><input type="text" class="form-control form-control-user" name="hPracticasNuevo" style="width:230px;" value="0" placeholder="Introduce las horas de prácticas"></div>
									<div class="form-group"><input type="text" class="form-control form-control-user" name="hLabNuevo" style="width:230px;"  value="0" placeholder="Introduce las horas de laboratorio"></div>
									<div class="form-group"><input type="text" class="form-control form-control-user" name="hTeoriaNuevo" style="width:230px;"  value="0" placeholder="Introduce las horas de teoria"></div>
                    				<a  href="#" onClick="deleteProfesor(this)" >Eliminar profesor</a>
                    			</div>
                    			</div>
                  			</div>
                		</div>
              		</div>
              		
              		
              		
              		
					<div id="profesor"  class="h-100 py-2" style="display:none;"></div>
					<a  href="#" onClick="addProfesor(this)" >Añadir profesor</a>
					</div>
					<a  href="#" onClick="addFirstProfesor(this)" id="FirstBoton" >Añadir profesor</a>
				</div>
			</div>
          </div>
          
          		
		  <div id="grupo1"  class="h-100 py-2"  ></div>
		  <a  href="#" onClick="addGrupo()" >Añadir grupo de clase</a><br><br>
		<button type="submit" class="btn btn-primary btn-user btn-block" style="width:42%;align:center;margin-left:auto;margin-right:auto;">Guardar Cambios</button>
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
function addFirstProfesor(obj){
	document.getElementById('invisible').style.display="";
	document.getElementById('FirstBoton').style.display="none";
	var input = obj.parentNode.getElementsByTagName('input')[0];
	input.setAttribute("value",1);
}



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









function deleteProfesor(obj){
	var parent = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	var input = parent.parentNode.parentNode.parentNode.getElementsByTagName('input')[0];
	var longitud = input.value.length;
	if(longitud == 1){
		var valor = "";
		input.setAttribute("value",valor);
	}else{
		var valor = input.value.substr(0,longitud-1);
		input.setAttribute("value",valor);
	}
	var child = obj.parentNode.parentNode.parentNode.parentNode.parentNode;
	parent.removeChild(child);
	
}

































function setName(obj){
	console.log(obj.parentNode.parentNode.getElementsByTagName('input')[0]);
	var input = obj.parentNode.parentNode.getElementsByTagName('input')[0];
	console.log(obj.value);
	input.setAttribute("name",obj.value);
	
	//obj.setAttribute("name",obj.parentNode.parentNode.getElementsByTagName('input')[0].value);
	//console.log(obj.parentNode.parentNode.getElementsByTagName('input')[0].value);
}





function addProfesor(obj){
	
	console.log(obj.parentNode);
	
	
    var div = document.createElement('div');
    
        div.innerHTML = document.getElementById('profesor1').outerHTML;
        document.getElementById('profesor').style.display="";
        document.getElementById('profesor').appendChild(div);
        
        
        var input = obj.parentNode.parentNode.getElementsByTagName('input')[0];
        input.setAttribute("value",input.value+1);
        console.log(input);
}



function addGrupo(){
    var div = document.createElement('div');
    
    div.innerHTML = document.getElementById('grupo').outerHTML;
    document.getElementById('grupo1').style.display="";
    document.getElementById('grupo1').appendChild(div);
}



function borrarGrupo(obj,id){ 
	obj.parentNode.style.display="none"; 
	
	var input = document.getElementById(id);
	input.checked= true;
} 










</script>
</html>

