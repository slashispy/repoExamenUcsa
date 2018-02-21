package py.edu.ucsa.rest.api.core.dao;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Departamento;

public interface DepartamentoDao {
	Departamento getById(Integer id);
	Departamento getByCodigo(String cod);
	void insertar(Departamento dpto);
	void actualizar(Departamento dpto);
	List<Departamento> listarTodos();
	void eliminar(Departamento dpto);
}
