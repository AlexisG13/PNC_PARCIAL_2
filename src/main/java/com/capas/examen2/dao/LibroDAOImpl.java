package com.capas.examen2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.capas.examen2.domain.Categoria;
import com.capas.examen2.domain.Libro;

@Repository
public class LibroDAOImpl implements LibroDAO {

	@PersistenceContext(unitName = "capas")
	private EntityManager entityManager;

	@Override
	public List<Libro> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_libro");
		Query query = entityManager.createNativeQuery(sb.toString(), Libro.class);
		List<Libro> resultset = query.getResultList();
		return resultset;
	}

	@Override
	public void save(Libro libro) throws DataAccessException {
		try {
			if (libro.getC_libro() == null)
				entityManager.persist(libro);
			else {
				entityManager.merge(libro);
				entityManager.flush();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
