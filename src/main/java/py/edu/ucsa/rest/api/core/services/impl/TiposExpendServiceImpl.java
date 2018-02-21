package py.edu.ucsa.rest.api.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.ucsa.rest.api.core.dao.TiposExpedDao;
import py.edu.ucsa.rest.api.core.model.TiposExped;
import py.edu.ucsa.rest.api.core.services.TiposExpendService;

@Service("tiposExpendService")
@Transactional
public class TiposExpendServiceImpl implements TiposExpendService {

	@Autowired
	private TiposExpedDao dao;
	
	@Override
	public TiposExped getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public TiposExped getByCodigo(String cod) {
		return dao.getByCodigo(cod);
	}

	@Override
	public void guardarTipoExped(TiposExped tExp) {
		if(tExp.getId() == null) {
			dao.insertar(tExp);
		}else {
			dao.actualizar(tExp);
		}

	}

	@Override
	public List<TiposExped> listarTiposExped() {
		return dao.listarTodos();
	}

	@Override
	public boolean isExisteTiposExped(TiposExped tExp) {
		return dao.getByCodigo(tExp.getCodigo()) != null;
	}

	@Override
	public void eliminarTodos() {
		List<TiposExped> tExps = dao.listarTodos();
		for(TiposExped w: tExps) {
			dao.eliminar(w);
		}

	}

	@Override
	public void eliminarById(Integer id) {
		dao.eliminar(dao.getById(id));
	}

}
