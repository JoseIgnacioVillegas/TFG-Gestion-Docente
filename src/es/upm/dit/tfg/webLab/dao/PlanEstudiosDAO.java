package es.upm.dit.tfg.webLab.dao;

import java.util.List;

import es.upm.dit.tfg.webLab.model.Asignatura;
import es.upm.dit.tfg.webLab.model.Grupo;
import es.upm.dit.tfg.webLab.model.PlanEstudios;


public interface PlanEstudiosDAO {
	public void createPlanEstudios(PlanEstudios plan);
	public void deletePlanEstudios(PlanEstudios plan) ;
	public PlanEstudios readPlanEstudios(String codigo);
	public List<PlanEstudios> readTodosPlanesEstudios();

}
