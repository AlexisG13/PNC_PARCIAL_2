package com.capas.examen2.controller;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.capas.examen2.domain.Categoria;
import com.capas.examen2.domain.Libro;
import com.capas.examen2.service.CategoriaService;
import com.capas.examen2.service.LibroService;

@Controller
public class MainController {

	@Autowired
	LibroService libroService;

	@Autowired
	CategoriaService categoriaService;

	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mensaje","");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("ingresarCategoria")
	public ModelAndView ingresarCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("categoria");
			return mav;
		}
		try {
			categoriaService.save(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("mensaje","Categoria ingresada con exito!");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("ingresarLibro")
	public ModelAndView ingresarLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			List<Categoria> categorias = null;
			categorias = this.getcategorias();
			mav.addObject("categoriaCombo", categorias);
			mav.setViewName("libro");
			return mav;
		}
		Date date = new Date();
		try {
			libro.setF_ingreso(date);
			libroService.save(libro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("mensaje","Libro ingresado con exito!");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("libro")
	public ModelAndView formLibro() {
		ModelAndView mav = new ModelAndView();
		List<Categoria> categorias = null;
		categorias = this.getcategorias();
		mav.addObject("categoriaCombo", categorias);
		mav.addObject("libro", new Libro());
		mav.setViewName("libro");
		return mav;
	}

	@RequestMapping("categoria")
	public ModelAndView formCategoria() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoria", new Categoria());
		mav.setViewName("categoria");
		return mav;
	}

	@RequestMapping("listado")
	public ModelAndView listadoLibros() {
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = null;
		try {
			libros = libroService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("libros", libros);
		mav.setViewName("listado");
		return mav;
	}

	private List<Categoria> getcategorias() {
		List<Categoria> categorias = null;
		try {
			categorias = categoriaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorias;
	}

}
