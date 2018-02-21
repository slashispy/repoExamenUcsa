package py.edu.ucsa.rest.api.core.dao;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.TiposExped;

public interface TiposExpedDao {
	TiposExped getById(Integer id);
	TiposExped getByCodigo(String cod);
	void insertar(TiposExped dpto);
	void actualizar(TiposExped dpto);
	List<TiposExped> listarTodos();
	void eliminar(TiposExped dpto);
}
