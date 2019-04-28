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
        Docente
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
        Departamento
      </div>




      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <form action="DocenteServlet" class="nav-link">
        <button type="submit"  value="planes" name="planes">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Planes y asignaturas</span>
          </button>
         </form>
      </li>
      
            <!-- Nav Item - Charts -->
      <li class="nav-item">
        <form action="DocenteServlet" class="nav-link">
        <button type="submit"  value="profesores" name="profesores">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Profesores</span>
          </button>
         </form>
      </li>
      
            <!-- Nav Item - Charts -->
      <li class="nav-item">
        <form action="DocenteServlet" class="nav-link">
        <button type="submit"  value="plazagrupo" name="plazagrupo">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Plazas y grupos </span>
          </button>
         </form>
      </li>
      
      





      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
 
      <div class="sidebar-heading">
        Datos Personales
      </div>

 

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <form action="DocenteServlet" class="nav-link">
        <input type="hidden" value="<shiro:principal/>" name="email"/>
        <button type="submit"  value="datos" name="datos">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Ver mis datos personales</span>
          </button>
         </form>
      </li>








      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->