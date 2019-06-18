package es.upm.dit.tfg.webLab.servlets;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;

import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;





@WebServlet("/AsignarPermisosServlet")

public class AsignarPermisosServlet extends HttpServlet{

	private final static Logger log = Logger.getLogger(AsignarPermisosServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		int UsuarioId = 0;
		try{UsuarioId = Integer.parseInt(req.getParameter("id"));}catch(Exception e){log.error(e);}
		System.out.println("EL DI "+UsuarioId);
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			System.out.println("id del usuario "+UsuarioId);
			Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario(UsuarioId);
			
			//Saco el array de los id de los profesores borrados
			String todosPermisosId[]; 
			todosPermisosId = req.getParameterValues("permisos");
			
			List<Permiso> nuevosPermisos = new ArrayList<Permiso>();
			

			try {
				for (int i = 0; i< todosPermisosId.length; i++) {
					System.out.println("EL ID "+todosPermisosId[i]);
					Permiso permiso = PermisoDAOImplementation.getInstance().readPermiso(Integer.parseInt(todosPermisosId[i]));
					System.out.println("EL PERMISO "+permiso.getPermiso());
					nuevosPermisos.add(permiso);
					
					//log.info("El usuario "+currentUser.getPrincipal().toString()+" ha asignado nuevos permisos al usuario " + usuario.getNombre()+" "+ usuario.getApellidos());
				}
			}catch(Exception e) {
				System.out.println("error "+e);
				log.error(e);
			}
			
			System.out.println("longitudd "+nuevosPermisos.size());
			try {usuario.setPermisos(nuevosPermisos);}catch(Exception e) {
				System.out.println(e);
				log.error(e);}
			UsuarioDAOImplementation.getInstance().updateUsuario(usuario);
			
			Profesor profe =null;
			try {profe = usuario.getProfesor();}catch(Exception e) {log.error(e);}
			
			if(profe==null) {
				getServletContext().getRequestDispatcher("/CRUDPAS.jsp").forward(req, resp);
			}else {
				
				getServletContext().getRequestDispatcher("/CRUDProfesor.jsp").forward(req, resp);
			}
			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
	}
}
		
