package com.capas.examen2.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.capas.examen2.domain.Libro;

public interface LibroDAO {

	public List<Libro> findAll() throws DataAccessException;

	public void save(Libro libro) throws DataAccessException;

}
