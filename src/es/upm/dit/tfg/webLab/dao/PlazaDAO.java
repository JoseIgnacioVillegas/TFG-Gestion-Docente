package es.upm.dit.tfg.webLab.dao;


import java.util.List;
import es.upm.dit.tfg.webLab.model.Plaza;

public interface PlazaDAO {
	public void createPlaza(Plaza plaza);
	public void deletePlaza(Plaza plaza);
	public Plaza readPlaza(int id);
	public List<Plaza> readPlazas();
	public void updatePlaza(Plaza plaza);
	
}
