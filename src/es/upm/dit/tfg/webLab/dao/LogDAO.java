package es.upm.dit.tfg.webLab.dao;

import java.util.List;

import es.upm.dit.tfg.webLab.model.Log;

public interface LogDAO {
	public void createLog(Log log);
	public void deleteLog(Log log);
	public Log readLog(int id);
	public List<Log> readTodosLog();

}
