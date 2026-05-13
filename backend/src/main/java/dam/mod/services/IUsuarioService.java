package dam.mod.services;

import java.util.List;

import dam.mod.models.Usuario;

public interface IUsuarioService {
    List<Usuario> findAll();
    Usuario findById(int id);
    boolean create(Usuario usuario);
    boolean update(Usuario usuario);
    boolean delete(int id);
}