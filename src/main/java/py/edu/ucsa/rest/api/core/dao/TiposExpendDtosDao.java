package py.edu.ucsa.rest.api.core.dao;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Departamento;
import py.edu.ucsa.rest.api.core.model.TiposExped;
import py.edu.ucsa.rest.api.core.model.TiposExpedDtos;

public interface TiposExpendDtosDao {
	TiposExpedDtos getById(Integer id);
	TiposExpedDtos getByDepartamento(Departamento dpto);
	TiposExpedDtos getByTipoExped(TiposExped tExp);
	void insertar(TiposExpedDtos tExpDpto);
	void actualizar(TiposExpedDtos tExpDpto);
	List<TiposExpedDtos> listarTodos();
	void eliminar(TiposExpedDtos TExpDpto);
	TiposExpedDtos getByUnique(TiposExped id_tipo_exped, Departamento id_departamento, String sentido);
}
