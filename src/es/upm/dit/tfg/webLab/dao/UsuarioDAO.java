package es.upm.dit.tfg.webLab.dao;


import java.util.List;


import es.upm.dit.tfg.webLab.model.Usuario;




public interface UsuarioDAO {
	public void createUsuario(Usuario usuario);
	public Usuario readUsuario(int id);
	public void deleteUsuario(Usuario usuario);
	public List<Usuario> readUsuarios(); 
	public Usuario readUsuarioPorCorreo(String email);
	public void updateUsuario(Usuario usuario);
}
