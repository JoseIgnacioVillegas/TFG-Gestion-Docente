package es.upm.dit.tfg.webLab.dao;

import java.util.List;


import es.upm.dit.tfg.webLab.model.Asignatura;

public interface AsignaturaDAO {
	public void createAsignatura(Asignatura grupo);
	public void deleteAsignatura(Asignatura grupo);
	public Asignatura readAsignatura(String codigo);
	public List<Asignatura> readAsignaturas();
	public List<Asignatura> readAsignaturasPorPlan(String codigo);
	public void updateAsignatura(Asignatura asignatura);
}