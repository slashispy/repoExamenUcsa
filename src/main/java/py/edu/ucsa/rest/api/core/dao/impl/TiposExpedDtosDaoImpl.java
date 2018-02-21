package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.TiposExpendDtosDao;
import py.edu.ucsa.rest.api.core.model.Departamento;
import py.edu.ucsa.rest.api.core.model.TiposExped;
import py.edu.ucsa.rest.api.core.model.TiposExpedDtos;

@Repository("tiposExpedDtosDao")
public class TiposExpedDtosDaoImpl extends AbstractDao<Integer, TiposExpedDtos> implements TiposExpendDtosDao {

	@Override
	public TiposExpedDtos getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public TiposExpedDtos getByDepartamento(Departamento dpto) {
		try {
			return (TiposExpedDtos) getEntityManager()
					.createNamedQuery("TiposExpedDtos.findByDepartamento")
					.setParameter("id", dpto)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public TiposExpedDtos getByTipoExped(TiposExped tExp) {
		try {
			return (TiposExpedDtos) getEntityManager()
					.createNamedQuery("TiposExpedDtos.findByTipoExped")
					.setParameter("id", tExp)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public void insertar(TiposExpedDtos tExpDpto) {
		super.persistir(tExpDpto);

	}

	@Override
	public void actualizar(TiposExpedDtos tExpDpto) {
		super.actualizar(tExpDpto);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TiposExpedDtos> listarTodos() {
		List<TiposExpedDtos> tExpeds = getEntityManager()
				.createNamedQuery("TiposExpedDtos.findAll")
				.getResultList();
		return tExpeds;
	}

	@Override
	public void eliminar(TiposExpedDtos TExpDpto) {
		super.eliminar(TExpDpto);

	}

	@Override
	public TiposExpedDtos getByUnique(TiposExped id_tipo_exped, Departamento id_departamento, String sentido) {
		try {
			return (TiposExpedDtos) getEntityManager()
					.createNamedQuery("TiposExpedDtos.findUnique")
					.setParameter("id_tipo_exped", id_tipo_exped)
					.setParameter("id_departamento", id_departamento)
					.setParameter("sentido", sentido)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

}
