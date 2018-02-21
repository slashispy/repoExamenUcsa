package py.edu.ucsa.rest.api.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.ucsa.rest.api.core.dao.TiposExpendDtosDao;
import py.edu.ucsa.rest.api.core.model.TiposExpedDtos;
import py.edu.ucsa.rest.api.core.services.TiposExpedDtosService;

@Service("tipoExpedDtosService")
@Transactional
public class TiposExpedDtosServiceImpl implements TiposExpedDtosService {
	
	@Autowired
	private TiposExpendDtosDao dao;

	@Override
	public TiposExpedDtos getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<TiposExpedDtos> listarTodos() {
		return dao.listarTodos();
	}

	@Override
	public void guardarTiposExpedDtos(TiposExpedDtos tExpDto) {
		if(tExpDto.getId() == null) {
			dao.insertar(tExpDto);
		}else {
			dao.actualizar(tExpDto);
		}

	}

	@Override
	public boolean isExisteTipoExpedDtos(TiposExpedDtos tExpDto) {
		return dao.getByUnique(tExpDto.getTipoExped(), tExpDto.getDepartamento(), tExpDto.getSentido()) != null;
	}

}
