package com.solak.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solak.bookstore.domain.Livro;
import com.solak.bookstore.repositories.LivroRepository;
import com.solak.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não econtrado! id: " + id + ", Tipo: " + Livro.class.getName()));
	}

	public List<Livro> findAll(Integer id_cat) {
		// verificar se a catagoria existe
		categoriaService.findById(id_cat);
		return repository.findAllByCategoria(id_cat); // id_cat: id passado pelo user
	}
}
