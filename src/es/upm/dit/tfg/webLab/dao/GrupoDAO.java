package es.upm.dit.tfg.webLab.dao;

import java.util.List;

import es.upm.dit.tfg.webLab.model.Grupo;


public interface GrupoDAO {
	public void createGrupo(Grupo grupo);
	public void deleteGrupo(Grupo grupo);
	public Grupo readGrupo(String nombre);
	public List<Grupo> readGrupos();
	public void updateGrupo(Grupo grupo) ;

}
