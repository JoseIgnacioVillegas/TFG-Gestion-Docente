<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<style>
button {
  background: none;
  border: 0;
  color: inherit;
  /* cursor: default; */
  font: inherit;
  line-height: normal;
  overflow: visible;
  padding: 0;
  -webkit-user-select: none; /* for button */
   -webkit-appearance: button; /* for input */
     -moz-user-select: none;
      -ms-user-select: none;
      }

</style>

<!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="VistaInicial.jsp">
        <div class="sidebar-brand-icon ">
          
          <img  type="image/gif" src="./img/ditupm.gif" style="width:25px;height:25px;"/>
        </div>
        <div class="sidebar-brand-text mx-3">
        Administrador
        </div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="VistaInicial.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Inicio</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Gestión del departamento
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
       <shiro:hasAnyRoles name="administrador,gestionusuarios">
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          
          <span>Gestión Usuarios</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Opciones:</h6>
            
        <form action="GestorServlet"><button  type="submit" class="collapse-item" style="width:90%;text-align:left;" value="CRUDProfesor" name="CRUDProfesor">Docentes</button></form> 
	   	<form action="GestorServlet" ><button type="submit" class="collapse-item" style="width:90%;text-align:left;" value="NoDocentes" name="NoDocentes">No Docentes</button></form>

          </div>
        </div>
      </li>
	</shiro:hasAnyRoles>




      <!-- Nav Item - Utilities Collapse Menu -->
      <shiro:hasAnyRoles name="administrador,gestiondocencia">
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>Gestión Docencia</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Opciones:</h6>
		<form action="GestorServlet" ><button type="submit" class="collapse-item" style="width:90%;text-align:left;" value="CRUDPlan" name="CRUDPlan">Plan de Estudios</button></form>
   	  	<form action="GestorServlet" ><button type="submit" class="collapse-item" style="width:90%;text-align:left;" value="CRUDAsignatura" name="CRUDAsignatura">Asignaturas</button></form>
          </div>
        </div>
      </li>

</shiro:hasAnyRoles>
      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <shiro:hasAnyRoles name="administrador,gestiondatos">
      <div class="sidebar-heading">
        Gestión del sistema
      </div>

 

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <form action="GestorServlet" class="nav-link">
        <button type="submit"  value="Backup" name="Backup">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Copias de seguridad</span>
          </button>
         </form>
      </li>



      <!-- Nav Item - Tables -->
      <li class="nav-item">
      <form action="GestorServlet" class="nav-link">
      <button type="submit"  value="Export" name="Export">
          <i class="fas fa-fw fa-table"></i>
          <span>Exportar datos</span>
         </button></form>
      </li>
</shiro:hasAnyRoles>
      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->