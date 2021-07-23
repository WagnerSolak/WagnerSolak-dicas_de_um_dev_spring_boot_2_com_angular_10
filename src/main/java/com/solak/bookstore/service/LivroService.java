package com.solak.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solak.bookstore.domain.Categoria;
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

	public Livro update(Integer id, Livro obj) {
		//verificar se existe o livro
		Livro newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	//metodo para que quem chama não fique com muitas informações
	private void updateData(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setNome_autor(obj.getNome_autor());
		newObj.setTexto(obj.getTexto());
		
	}

	public Livro create(Integer id_cat, Livro obj) {
		// setar obj como nulo para nao ocorrer erro
		obj.setId(null);
		Categoria cat = categoriaService.findById(id_cat); // aqui temos a Categoria
		obj.setCategoria(cat);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		Livro obj = findById(id);
		repository.delete(obj);
		
	}
}
