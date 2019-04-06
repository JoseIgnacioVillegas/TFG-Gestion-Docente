package es.upm.dit.tfg.webLab.dao;

import es.upm.dit.tfg.webLab.model.GrupoClase;

public interface GrupoClaseDAO {
	public void createGrupoClase(GrupoClase grupo);
	public void deleteGrupoClase(GrupoClase grupo);
	public GrupoClase readGrupoClase(int id);
	public void updateGrupoClase(GrupoClase grupo);
}
