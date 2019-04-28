
package es.upm.dit.tfg.webLab.servlets;


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
import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;





@WebServlet("/ObtenerDocentesServlet")

public class ObtenerDocentesServlet extends HttpServlet{

	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, java.io.IOException {
		
		req.getSession().removeAttribute("mensaje");
		String crear = req.getParameter("crear");
		String BotonDocentesPorGrupo = req.getParameter("docentesGrupo");
		String BotonDocentesPorPlaza = req.getParameter("docentesPlaza");
		String BotonDocentesPorAsignatura = req.getParameter("docentesAsignatura");
		
		List<Profesor> todosProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
		
		
		
		
		
		
		if(crear!=null&&crear.equals("crear")) {
			req.getSession().setAttribute("todosProfesores", todosProfesores);
			getServletContext().getRequestDispatcher("/CrearAsignatura.jsp").forward(req, resp);
		}else if(BotonDocentesPorGrupo!=null && BotonDocentesPorGrupo.equals("docentesGrupo")) {
			String grupo = req.getParameter("grupo");
			Grupo GrupoInvestigacion = GrupoDAOImplementation.getInstance().readGrupo(grupo);
			
			List<Profesor> profesores = null;
			
			try {
			profesores = GrupoInvestigacion.getProfesores();
			}catch(Exception e) {
				
			}
			
			req.getSession().setAttribute("profesores", profesores);
			req.getSession().setAttribute("grupo", grupo);
			getServletContext().getRequestDispatcher("/VerProfesores.jsp").forward(req, resp);
			
		}else if(BotonDocentesPorPlaza!=null && BotonDocentesPorPlaza.equals("docentesPlaza")) {
			String plazaId = req.getParameter("plaza");
			Plaza PlazaProfesor = PlazaDAOImplementation.getInstance().readPlaza(Integer.parseInt(plazaId,10));
			
			List<Profesor> profesores = null;
			
			try {
			profesores = PlazaProfesor.getProfesores();
			}catch(Exception e) {
				
			}
			
			req.getSession().setAttribute("profesores", profesores);
			req.getSession().setAttribute("plaza", PlazaProfesor.getPlaza());
			getServletContext().getRequestDispatcher("/VerProfesores.jsp").forward(req, resp);
		
		}else if(BotonDocentesPorAsignatura!=null && BotonDocentesPorAsignatura.equals("docentesAsignatura")){
			
			String asignaturaCodigo = req.getParameter("asignatura");
			Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(asignaturaCodigo);
			
			List<Profesor> profesores = null;
			Profesor coordi = null;
			try {
			profesores = asignatura.getProfesores();
			coordi = asignatura.getCoordinador();
			}catch(Exception e) {
				
			}
			
			Usuario coordinador = null;
			try {
				coordinador = coordi.getUsuario();
			}catch(Exception e) {
					
			}
			
		
			
			req.getSession().setAttribute("profesores", profesores);
			req.getSession().setAttribute("coordinador", coordinador);
			req.getSession().setAttribute("asignatura", asignatura);
			getServletContext().getRequestDispatcher("/VerProfesores.jsp").forward(req, resp);
			
			
		}else{
		String codigoAsignatura = req.getParameter("codigo");
		
		Asignatura asignatura = AsignaturaDAOImplementation.getInstance().readAsignatura(codigoAsignatura);
		
		List<Profesor> profesores = asignatura.getProfesores();

		req.getSession().setAttribute("coordinador", asignatura.getCoordinador());
		req.getSession().setAttribute("todosProfesores", todosProfesores);
		req.getSession().setAttribute("profesoresPorAsignatura", profesores);
		req.getSession().setAttribute("nombre", asignatura.getNombre());
		req.getSession().setAttribute("codigo", asignatura.getCodigo());
		req.getSession().setAttribute("acronimo", asignatura.getAcronimo());
		
		req.getSession().setAttribute("longitud", profesores.size());

		getServletContext().getRequestDispatcher("/AsignarDocentes.jsp").forward(req, resp);
		
		}
		
	}
}
		