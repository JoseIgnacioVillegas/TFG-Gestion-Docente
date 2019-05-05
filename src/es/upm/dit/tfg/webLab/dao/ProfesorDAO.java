package es.upm.dit.tfg.webLab.dao;

import java.util.List;

import es.upm.dit.tfg.webLab.model.Profesor;

public interface ProfesorDAO {
	public void createProfesor(Profesor profesor);
	public Profesor readProfesor(int id);
	public void deleteProfesor(Profesor profesor);
	public List<Profesor> readProfesores();
	public void updateProfesor(Profesor profesor);



}
