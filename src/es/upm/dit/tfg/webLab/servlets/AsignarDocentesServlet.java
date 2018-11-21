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


import com.itextpdf.io.IOException;

import es.upm.dit.tfg.webLab.dao.AsignaturaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Profesor;





@WebServlet("/AsignarDocentesServlet")

public class AsignarDocentesServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		String codAsignatura = req.getParameter("codigoAsignatura");
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codAsignatura);
		
		List<Profesor> profesoresParticipan = asignatura.getProfesores();
		
		//Saco el array de los id de los profesores borrados
		String profesoresBorradosId[]; 
		profesoresBorradosId = req.getParameterValues("profesoresBorrados");
		
		
		try {
			for (int i = 0; i< profesoresBorradosId.length; i++) {
				for (int j = 0; j< profesoresParticipan.size(); j++) {
					if(Integer.parseInt(profesoresBorradosId[i])==profesoresParticipan.get(j).getId()) profesoresParticipan.remove(j);
				}
			}
			}catch(Exception e) {
				System.out.println(e);
			}finally {
					
			}
		
		
		
		
		String coordinadorBorradoId = req.getParameter("coordinadorBorrado");
	
		Profesor coordinadorBorrado = new Profesor();
		try {
			
			ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(coordinadorBorradoId));
			asignatura.setCoordinador(coordinadorBorrado);
		}catch(Exception e) {
			System.out.println(e);
		}finally {
				
		}
		
		
		
		
		
		
		String profesoresId[]; 
		profesoresId = req.getParameterValues("profesor");
		
		
		
		
		
		String coordinadorId = req.getParameter("coordinador");
		Profesor coordinador =null;
		
		try {
			
			coordinador = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(coordinadorId));
			}catch(Exception e) {
				System.out.println(e);
			}finally {
				
			}

		
		try {
		for (int i = 0; i< profesoresId.length; i++) {
			Profesor profe = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(profesoresId[i]));
			profesoresParticipan.add(profe);
		}
		asignatura.setProfesores(profesoresParticipan);
		
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			
		}
		asignatura.setCoordinador(coordinador);
		
		List<Asignatura> asignaturasCoordina = coordinador.getAsignaturaCoordina();
		asignaturasCoordina.add(asignatura);
		try {
			coordinador.setAsignaturaCoordina(asignaturasCoordina);
			}catch(Exception e) {
				System.out.println(e);
			}finally {
				
			}
		

		ProfesorDAOImplementation.getInstance().updateProfesor(coordinador);
		AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
		
		String msj = "Usuarios asignados con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDAsignatura.jsp");
	}
}
		