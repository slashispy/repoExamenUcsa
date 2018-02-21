package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.TiposExpedDtos;

public interface TiposExpedDtosService {
	TiposExpedDtos getById(Integer id);
	List<TiposExpedDtos> listarTodos();
	void guardarTiposExpedDtos(TiposExpedDtos tExpDto);
	boolean isExisteTipoExpedDtos(TiposExpedDtos tExpDto);
}
