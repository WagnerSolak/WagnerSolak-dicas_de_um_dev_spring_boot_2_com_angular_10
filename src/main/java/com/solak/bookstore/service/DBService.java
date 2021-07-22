package com.solak.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solak.bookstore.domain.Categoria;
import com.solak.bookstore.domain.Livro;
import com.solak.bookstore.repositories.CategoriaRepository;
import com.solak.bookstore.repositories.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void instanciaBaseDeDados() {
		Categoria cat1 = new Categoria(null, "Informática", "Livros de TI");
		Categoria cat2 = new Categoria(null, "Ficção Cinetífica", "Livros de Ficção Cinetífica");
		Categoria cat3 = new Categoria(null, "Agricultura", "Livros de Agricultura");
		Categoria cat4 = new Categoria(null, "Suplementação Alimentar", "Livros de Suplementação");	
		
		Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
		Livro l2 = new Livro(null, "Ficção Científica", "Isaac Asimov", "Lorem ipsum", cat2);
		Livro l3 = new Livro(null, "Agricultura Sintrópica", "Ernst Götsch", "Lorem ipsum", cat3);
		Livro l4 = new Livro(null, "Suplementação", "Solak", "Lorem ipsum", cat4);
		Livro l5 = new Livro(null, "A Teia da vida", "Fritjof Capra", "Lorem ipsum", cat3);
		
		
		cat1.getLivros().addAll(Arrays.asList(l1));
		cat2.getLivros().addAll(Arrays.asList(l2));
		cat3.getLivros().addAll(Arrays.asList(l3,l5));
		cat4.getLivros().addAll(Arrays.asList(l4));
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4));
		this.livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5));
	}

}
