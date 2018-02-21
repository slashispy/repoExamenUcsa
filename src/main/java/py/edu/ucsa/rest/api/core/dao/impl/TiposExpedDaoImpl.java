package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.TiposExpedDao;
import py.edu.ucsa.rest.api.core.model.TiposExped;

@Repository("tiposExpedDao")
public class TiposExpedDaoImpl extends AbstractDao<Integer, TiposExped> implements TiposExpedDao {

	@Override
	public TiposExped getById(Integer id) {
		return super.getById(id);
	}

	@Override
	public TiposExped getByCodigo(String cod) {
		try {
			return (TiposExped) getEntityManager()
					.createNamedQuery("TiposExped.findByCodigo")
					.setParameter("codigo", cod)
					.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public void insertar(TiposExped tExp) {
		super.persistir(tExp);

	}

	@Override
	public void actualizar(TiposExped tExp) {
		super.actualizar(tExp);

	}


	@Override
	@SuppressWarnings("unchecked")
	public List<TiposExped> listarTodos() {
		List<TiposExped> tExpeds = getEntityManager()
				.createNamedQuery("TiposExped.findAll")
				.getResultList();
		return tExpeds;
	}

	@Override
	public void eliminar(TiposExped tExp) {
		super.eliminar(tExp);

	}

}
