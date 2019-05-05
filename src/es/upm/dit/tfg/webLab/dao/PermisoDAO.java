package es.upm.dit.tfg.webLab.dao;

import java.util.List;

import es.upm.dit.tfg.webLab.model.Permiso;



public interface PermisoDAO {
	
	public void createPermiso(Permiso permiso);
	public Permiso readPermiso(int id);
	public void deletePermiso(Permiso permiso);
	public List<Permiso> readPermisos();
	public void updatePermiso(Permiso permiso);
	public Permiso readPermisoPorNombre(String nombre);
}
