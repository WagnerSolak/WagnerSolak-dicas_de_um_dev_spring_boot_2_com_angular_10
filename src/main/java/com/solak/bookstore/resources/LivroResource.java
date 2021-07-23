package com.solak.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.solak.bookstore.domain.Livro;
import com.solak.bookstore.dtos.LivroDTO;
import com.solak.bookstore.service.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id){
		Livro obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>>findAll(@RequestParam(value = "categoria", defaultValue = "0")Integer id_cat){
		// localhost:8080/livros?categoria=1
		// caso não seja informado id para pesquisa o defaultValue = "0"lança a excecao personalizada
		List<Livro> list = service.findAll(id_cat);
		List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj){
		Livro newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj){
		Livro newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
	
	// livro deve ter ao menos 1 categoria, por isso usa-se o defaultValue = "0
	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0")Integer id_cat,
			@RequestBody Livro obj){
		Livro newObj = service.create(id_cat,obj);
		// retornando a URI
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
