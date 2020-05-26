package com.capas.examen2.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.capas.examen2.domain.Libro;


public interface LibroService {

	public List<Libro> findAll() throws DataAccessException;

	public void save(Libro libro) throws DataAccessException;
}
