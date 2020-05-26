package com.capas.examen2.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.capas.examen2.domain.Categoria;


public interface CategoriaService {
	
	public List<Categoria> findAll() throws DataAccessException;

	public void save(Categoria categoria) throws DataAccessException;
}
