package py.edu.ucsa.rest.api.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.ucsa.rest.api.core.dao.DepartamentoDao;
import py.edu.ucsa.rest.api.core.model.Departamento;
import py.edu.ucsa.rest.api.core.services.DepartamentoService;

@Service("departamentoService")
@Transactional
public class DepartamentoServiceImpl implements DepartamentoService {
	
	@Autowired
	private DepartamentoDao dao;

	@Override
	public Departamento getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public Departamento getByCodigo(String cod) {
		return dao.getByCodigo(cod);
	}

	@Override
	public void guardarDepartamento(Departamento dpto) {
		if(dpto.getId() == null) {
			dao.insertar(dpto);
		}else {
			dao.actualizar(dpto);
		}
	}

	@Override
	public List<Departamento> listarDepartamentos() {
		return dao.listarTodos();
	}

	@Override
	public boolean isExisteDepartamento(Departamento dpto) {
		return dao.getByCodigo(dpto.getCodigo()) != null;
	}

	@Override
	public void eliminarTodos() {
		List<Departamento> todos = dao.listarTodos();
		if(todos != null) {
			for(Departamento w: todos) {
				dao.eliminar(w);
			}
		}
	}

	@Override
	public void eliminarById(Integer id) {
		Departamento dpto = dao.getById(id);
		dao.eliminar(dpto);
	}

}
