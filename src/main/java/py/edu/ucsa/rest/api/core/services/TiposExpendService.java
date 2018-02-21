package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.TiposExped;

public interface TiposExpendService {
	TiposExped getById(Integer id);
	TiposExped getByCodigo(String cod);
	void guardarTipoExped(TiposExped tExp);
	List<TiposExped> listarTiposExped();
	boolean isExisteTiposExped(TiposExped tExp);
	void eliminarTodos();
	void eliminarById(Integer id);
}
