package es.upm.dit.tfg.webLab.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.util.*;


import es.upm.dit.tfg.webLab.dao.GrupoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PermisoDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlanEstudiosDAOImplementation;
import es.upm.dit.tfg.webLab.dao.PlazaDAOImplementation;
import es.upm.dit.tfg.webLab.dao.ProfesorDAOImplementation;
import es.upm.dit.tfg.webLab.dao.UsuarioDAOImplementation;
import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.Permiso;
import es.upm.dit.tfg.webLab.model.Plaza;
import es.upm.dit.tfg.webLab.model.Profesor;
import es.upm.dit.tfg.webLab.model.Usuario;
import es.upm.dit.tfg.webLab.model.PlanEstudios;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.json.*;

import com.microsoft.schemas.office.excel.*;
import com.microsoft.schemas.office.office.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;





@WebServlet("/GestorServlet")
public class GestorServlet extends HttpServlet{
	
	public static List<Asignatura> todasAsignaturas = new ArrayList<Asignatura>();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().removeAttribute("mensaje");
		Subject currentUser = (Subject) req.getSession().getAttribute("currentUser");
		
		
		//Botones para gestionar usuarios
		String BotonCRUDProfesor = req.getParameter("CRUDProfesor");
		String BotonGestionNoDocentes = req.getParameter("NoDocentes");
		
		//Botones para gestionar la docencia 
		String BotonCRUDPlan = req.getParameter("CRUDPlan");
		String BotonCRUDAsignaturas = req.getParameter("CRUDAsignatura");
		
		
		String grupo = req.getParameter("CRUDGrupo");
		String plaza = req.getParameter("CRUDPlaza");
		String api = req.getParameter("importarapi");
		
		
		
		
		
		String asignar = req.getParameter("AsignarUsuarios");
		
		String Backup = req.getParameter("Backup");
		String Export = req.getParameter("Export");
		
		
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar usuarios 
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			
			// Gestion de los profesores 
			if(BotonCRUDProfesor!=null && BotonCRUDProfesor.equals("CRUDProfesor")) {
				
				List<Profesor> listaProfesores = ProfesorDAOImplementation.getInstance().readProfesores();
				List<Grupo> listaGrupos = GrupoDAOImplementation.getInstance().readGrupos();
				
				List<Plaza> listaPlazas = PlazaDAOImplementation.getInstance().readPlazas();
				List<Permiso> listaPermisos = PermisoDAOImplementation.getInstance().readPermisos();
				
				req.getSession().setAttribute("permisos", listaPermisos);
				req.getSession().setAttribute("profesores", listaProfesores);
				req.getSession().setAttribute("grupos", listaGrupos);
				req.getSession().setAttribute("plazas", listaPlazas);
				
				getServletContext().getRequestDispatcher("/CRUDProfesor.jsp").forward(req, resp);
			}
			
			
			
			//Gestion de los usuarios no docentes 
			if(BotonGestionNoDocentes!=null && BotonGestionNoDocentes.equals("NoDocentes")) {

				List<Usuario> todosUsuarios = UsuarioDAOImplementation.getInstance().readUsuarios();
				List<Usuario> usuarios =new ArrayList();
				
				for (int i=0; i<todosUsuarios.size(); i++) {
					Profesor profesor = todosUsuarios.get(i).getProfesor();
					if(profesor==null) usuarios.add(todosUsuarios.get(i));
				}
				
				req.getSession().setAttribute("usuarios", usuarios);
				getServletContext().getRequestDispatcher("/CRUDPAS.jsp").forward(req, resp);
			}  
			

			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
		

		
		
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar docencia
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestionusuarios")){
			
			//Gestion de planes de estudio
			if(BotonCRUDPlan!=null && BotonCRUDPlan.equals("CRUDPlan")) {
				List<PlanEstudios> listaPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();

				req.getSession().setAttribute("planesActuales", listaPlanes);
		
				
				resp.sendRedirect(req.getContextPath() + "/CRUDPlan.jsp");
			}
			
			//Gestion de las asignaturas
			if( BotonCRUDAsignaturas!=null &&  BotonCRUDAsignaturas.equals("CRUDAsignatura")) {
				
				
				List<PlanEstudios> listaPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
				List<Profesor> listaProfesores = ProfesorDAOImplementation.getInstance().readProfesores();

				req.getSession().setAttribute("planesActuales", listaPlanes);
				req.getSession().setAttribute("listaProfesores", listaProfesores);
				
				resp.sendRedirect(req.getContextPath() + "/CRUDAsignatura.jsp");
			}
			
			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
		
		      
		
		
		
		
		
		
		/*
		 * Solo puede entrar aquí si es administrador o si tiene el rol para gestionar docencia
		 */
		if (currentUser.hasRole("administrador") || currentUser.hasRole("gestiondatos")){
			
			//Para realizar el Backup
			if(Backup!=null && Backup.equals("Backup")) getServletContext().getRequestDispatcher("/GestorBBDD.jsp").forward(req, resp);

			//Para poder exportar datos
			if(Export!=null && Export.equals("Export")) getServletContext().getRequestDispatcher("/ExportarDatos.jsp").forward(req, resp);
			
		}else {
			getServletContext().getRequestDispatcher("/NoPermitido.jsp").forward(req, resp);
		}
		
		
		
		
		
		
			                      
		  
		
		
		
		/*
		 * ¿DEBERIA ESTO DE ESTAR EN ESTE SERVLET???????????????????????
		 */
		
		if(asignar!=null && asignar.equals("AsignarUsuarios")) {
			
			List<PlanEstudios> listaPlanes = PlanEstudiosDAOImplementation.getInstance().readTodosPlanesEstudios();
			List<Profesor> listaProfesores = ProfesorDAOImplementation.getInstance().readProfesores();

			req.getSession().setAttribute("planesActuales", listaPlanes);
			req.getSession().setAttribute("listaProfesores", listaProfesores);
			
			resp.sendRedirect(req.getContextPath() + "/AsignarUsuarios.jsp");
			
		}
		
		
		
		
		
	
		
		
		
		
		
		/*
		 * ¿DEBERIA ESTO DE ESTAR EN ESTE SERVLET???????????????????????
		 */
		if(api!=null && api.equals("importarapi")) {
			String anio = hora();
			
			List<PlanEstudios> listaPlanes = descargarPlanes();
			
			req.getSession().setAttribute("listaPlanes", listaPlanes);
			req.getSession().setAttribute("listaAsignaturas", todasAsignaturas);
			
			req.getSession().setAttribute("anio", anio);
			resp.sendRedirect(req.getContextPath() + "/ImportarAsignaturasAPI.jsp");
		}
		
		
		
		
		/*
		 * ¿DEBERIA ESTO DE ESTAR EN ESTE SERVLET???????????????????????
		 */
		
		if(grupo!=null && grupo.equals("CRUDGrupo")) {
			List<Grupo> todosGrupos = GrupoDAOImplementation.getInstance().readGrupos();
			req.getSession().setAttribute("grupos", todosGrupos);
			resp.sendRedirect(req.getContextPath() + "/CRUDGrupo.jsp");
		}
		
		
		/*
		 * ¿DEBERIA ESTO DE ESTAR EN ESTE SERVLET???????????????????????
		 */
		
		if(plaza!=null && plaza.equals("CRUDPlaza")) {
			
			List<Plaza> todasPlazas = PlazaDAOImplementation.getInstance().readPlazas();
			
			for (int i=0; i<todasPlazas.size(); i++) {
				todasPlazas.get(i).getDescripcion();
			}
			
			req.getSession().setAttribute("plazas", todasPlazas);
			resp.sendRedirect(req.getContextPath() + "/CRUDPlaza.jsp");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
			
			
			
			
			
			
			
			
			
			
	// Aqui empiezan los metodos para descargar asignaturas 
	private static List<Asignatura> descargarAsignaturas(String codigo){
		List<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();
		try {
			String anio = hora();
			//Cuando funcione lo del año en la API lo único que habría que añadir al final del siguiente URL sería ?anio="+anio
			JSONObject miJSON = jsonGetRequestObject("https://www.upm.es/wapi_upm/academico/comun/index.upm/v2/departamento.json/D520/"+codigo+"/asignaturas");
		    
			JSONArray nombres = miJSON.names();

		    for (int i=0; i< nombres.length(); i++) {
				String codigoAsignatura = nombres.getString(i);
				JSONObject detalles = (JSONObject) miJSON.get(codigoAsignatura);
				
				String nombre = detalles.getString("nombre");
				String curso = detalles.getString("curso");
				String tipo = detalles.getString("nombre_tipo_asignatura");
				
				double ects =0.0;
				try {
					ects = Double.parseDouble(detalles.getString("credects"));
				}catch(Exception e){
					
					 e.printStackTrace();
				}finally {
					
				}
				
				String semestre ="";
				try {
					JSONObject imparticion = detalles.getJSONObject("imparticion");
					JSONArray codigosImp = imparticion.names();
					String codigoImparticion = codigosImp.getString(0);
					JSONObject detallesImparticion = (JSONObject) imparticion.get(codigoImparticion);
					semestre = detallesImparticion.getString("nombre_duracion");
				}catch(Exception e){
					//ects = "";
					 e.printStackTrace();
				}finally {
					
				}
				
				Asignatura asignatura = new Asignatura();
				asignatura.setCodigo(nombres.getString(i));
				asignatura.setNombre(nombre);
				asignatura.setCurso(curso);
				asignatura.setTipo(tipo);
				asignatura.setEcts(ects);
				asignatura.setSemestre(semestre);
				listaAsignaturas.add(asignatura);
				todasAsignaturas.add(asignatura);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
	    }
		return listaAsignaturas;
	}
	
	
	
	
	
	
	private static List<PlanEstudios> descargarPlanes(){
		List<PlanEstudios> listaPlanes = new ArrayList<PlanEstudios>();
		try {
			String anio = hora();
			//Cuando funcione lo del año en la API lo único que habría que añadir al final del siguiente URL sería ?anio="+anio
			JSONArray miJSON = jsonGetRequestArray("https://www.upm.es/wapi_upm/academico/comun/index.upm/v2/departamento.json/D520/planes");
			System.out.println("weee"+anio);
			System.out.println("eeeepa"+miJSON);
			for (int i=0; i<miJSON.length(); i++) {
				String codigo =  (String) miJSON.getJSONObject(i).get("codigo");
				String nombre =  (String) miJSON.getJSONObject(i).get("nombre");
				List<Asignatura> asignaturas = descargarAsignaturas(codigo);
			
				
				PlanEstudios plan = new PlanEstudios();
				plan.setCodigo(codigo);
				plan.setNombre(nombre);
				plan.setAsignaturas(asignaturas);
				listaPlanes.add(plan);
				
				
		
				for (int j=0; j<asignaturas.size(); j++) {
				asignaturas.get(j).setPlanEstudios(plan);
				
				}
				
				
				
			}
		} catch(Exception e) {
			e.printStackTrace();
	    }
		return listaPlanes;
	}
	
	
	
	
	
	

	public static JSONArray jsonGetRequestArray(String urlQueryString) throws JSONException {
		JSONArray jsonArray = null;
	    try {
	      URL url = new URL(urlQueryString);
	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      connection.setDoOutput(true);
	      connection.setInstanceFollowRedirects(false);
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Content-Type", "application/json");
	      connection.setRequestProperty("charset", "utf-8");
	      connection.connect();
	      
	      InputStream inStream = connection.getInputStream();

	      BufferedReader streamReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
	      StringBuilder responseStrBuilder = new StringBuilder();
	      String inputStr;
	      while ((inputStr = streamReader.readLine()) != null)
	          responseStrBuilder.append(inputStr);

	      jsonArray = new JSONArray(responseStrBuilder.toString());
			
	    } catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    return jsonArray;
	  }
	
	
	
	
	public static JSONObject jsonGetRequestObject(String urlQueryString) throws JSONException {
		JSONObject jsonObject = null;
	    try {
	      URL url = new URL(urlQueryString);
	      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	      connection.setDoOutput(true);
	      connection.setInstanceFollowRedirects(false);
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Content-Type", "application/json");
	      connection.setRequestProperty("charset", "utf-8");
	      connection.connect();
	      
	      InputStream inStream = connection.getInputStream();

	      BufferedReader streamReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
	      StringBuilder responseStrBuilder = new StringBuilder();
	      String inputStr;
	      while ((inputStr = streamReader.readLine()) != null)
	          responseStrBuilder.append(inputStr);

	      jsonObject = new JSONObject(responseStrBuilder.toString());
			
	    } catch (IOException ex) {
	      ex.printStackTrace();
	    }
	    return jsonObject;
	  }
	
	
	
	
	private static String hora(){
		String fechaEnviar = "";

        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;

        
        if (1<mes && mes<7) {
        	int anioPrimerCuatri = anio-1;
        	String anioPrimerCuatriString = Integer.toString(anioPrimerCuatri);
        	String[] separado = anioPrimerCuatriString.split("0");
        	String dosNumeros = separado[1];
        	String anioSegundoCuatriString = Integer.toString(anio);
        	fechaEnviar =  anioSegundoCuatriString + dosNumeros;
        	
        }else {
        	int anioSegundoCuatri = anio+1;
        	String anioSegundoCuatriString = Integer.toString(anioSegundoCuatri);
        	String[] separado = anioSegundoCuatriString.split("0");
        	String dosNumeros = separado[1];
        	String anioPrimerCuatriString = Integer.toString(anio);
        	fechaEnviar = anioPrimerCuatriString+dosNumeros;
        }
        return fechaEnviar;
        		
    }
	
}
