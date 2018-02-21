package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.DepartamentoDao;
import py.edu.ucsa.rest.api.core.model.Departamento;

@Repository("departamentoDao")
public class DepartamentoDaoImpl extends AbstractDao<Integer, Departamento> implements DepartamentoDao {

	@Override
	public Departamento getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public Departamento getByCodigo(String cod) {
		try {
			return (Departamento) getEntityManager()
					.createNamedQuery("Departamento.findByCodigo")
					.setParameter("codigo", cod)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public void insertar(Departamento dpto) {
		super.persistir(dpto);

	}

	@Override
	public void actualizar(Departamento dpto) {
		super.actualizar(dpto);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Departamento> listarTodos() {
		List<Departamento> dptos = getEntityManager()
				.createNamedQuery("Departamento.findAll")
				.getResultList();
		return dptos;
	}
	
	@Override
	public void eliminar(Departamento dpto) {
		super.eliminar(dpto);
	}

}
