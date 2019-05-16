package es.upm.dit.tfg.webLab.dao;

import java.util.List;

import es.upm.dit.tfg.webLab.model.Permiso;



public interface PermisoDAO {
	public Permiso readPermiso(int id);
	public List<Permiso> readPermisos();
	public Permiso readPermisoPorNombre(String nombre);
}
