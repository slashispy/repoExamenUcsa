package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Departamento;

public interface DepartamentoService {
	Departamento getById(Integer id);
	Departamento getByCodigo(String cod);
	void guardarDepartamento(Departamento dpto);
	List<Departamento> listarDepartamentos();
	boolean isExisteDepartamento(Departamento dpto);
	void eliminarTodos();
	void eliminarById(Integer id);
	
}
