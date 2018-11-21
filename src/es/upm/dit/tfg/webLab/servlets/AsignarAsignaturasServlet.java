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





@WebServlet("/AsignarAsignaturasServlet")

public class AsignarAsignaturasServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		String docenteId = req.getParameter("id");
		Profesor profesor = ProfesorDAOImplementation.getInstance().readProfesor(Integer.parseInt(docenteId));
		
		
		String asignaturasParticipaBorradasCodigo[]; 
		asignaturasParticipaBorradasCodigo = req.getParameterValues("asignaturasParticipaBorradas");		
		
		
		String asignaturasCoordinaBorradasCodigo[]; 
		asignaturasCoordinaBorradasCodigo = req.getParameterValues("asignaturasCoordinaBorradas");
		

		
		//Saco el array de los id de los profesores borrados
		String codigoAsignaturasParticipa[]; 
		codigoAsignaturasParticipa = req.getParameterValues("participa");
		
		

		String codigoAsignaturasCoordina[]; 
		codigoAsignaturasCoordina = req.getParameterValues("coordina");
		
		
		//borrar las asignaturas de los profesores que participan, esta hecho 
		
			try {
				for (int j = 0; j< asignaturasParticipaBorradasCodigo.length; j++) {
				Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(asignaturasParticipaBorradasCodigo[j]);
				List<Profesor> listaProfesoresParticipan = asignatura.getProfesores();
				for (int i = 0; i< listaProfesoresParticipan.size(); i++) {
					if(listaProfesoresParticipan.get(i).getId()==profesor.getId())listaProfesoresParticipan.remove(i);
				}
				asignatura.setProfesores(listaProfesoresParticipan);
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
				}
			}catch(Exception e) {
				System.out.println(e);
			}finally {
			}
		
		
		
		
		
		//poner las asignaturas de los profesores que participan, esta hecho 
		for (int i = 0; i< codigoAsignaturasParticipa.length; i++) {
			try {
				Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignaturasParticipa[i]);
				List<Profesor> listaProfesoresParticipan = asignatura.getProfesores();
				listaProfesoresParticipan.add(profesor);
				asignatura.setProfesores(listaProfesoresParticipan);
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
			}catch(Exception e) {
				System.out.println(e);
			}finally {
			}
		}
		

		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////// A PARTIR DE AQUI SE HACE LO DEL COORDINADOR //////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

		
		//Poner asignaturas coordina esta hecho 
		for (int i = 0; i< codigoAsignaturasCoordina.length; i++) {
			Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignaturasCoordina[i]);
			try {
				asignatura.setCoordinador(profesor);
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
				}catch(Exception e) {
					System.out.println(e);
				}finally {
							
				}
		}
				
		
		
		//QUitar el coordinador de las asignaturas, hecho 
		for (int i = 0; i< asignaturasCoordinaBorradasCodigo.length; i++) {
			Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(asignaturasCoordinaBorradasCodigo[i]);
			try {
				asignatura.deleteCoordinador();
				AsignaturaDAOImplementation.getInstance().updateAsignatura(asignatura);
				}catch(Exception e) {
					System.out.println(e);
				}finally {
							
				}
		}
		
		String msj = "Usuarios asignados con éxito";
		req.getSession().setAttribute("mensaje", msj);
		resp.sendRedirect(req.getContextPath()+ "/CRUDProfesor.jsp");
	}
}
		