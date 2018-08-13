package com.livro.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import com.livro.model.Livro;
import com.livro.repository.Livros;

@RestController
@RequestMapping("/livro")
@CrossOrigin("${origem-permitida}")
public class LivroResource {

	@Autowired
	private Livros livros;
	
	@GetMapping
	public List<Livro> listar(){
		return livros.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Long id){
		Livro livroId = livros.findOne(id);
		Optional.ofNullable(livroId).orElseThrow(() -> new RestClientException("Erro: O Livro não existe"));		
		return ResponseEntity.ok(livroId);
	}
	
	@PostMapping
	public Livro adicionar(@Valid @RequestBody Livro livro){
		return livros.save(livro);
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro){
		Livro livroExistente = livros.findOne(id);
		Optional.ofNullable(livroExistente).orElseThrow(() -> new RestClientException("Erro: O Livro não existe"));		
		BeanUtils.copyProperties(livro, livroExistente, "id");
		livros.save(livroExistente);
		return ResponseEntity.status(HttpStatus.OK).body(livroExistente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Livro livroExistente = livros.findOne(id);
		Optional.ofNullable(livroExistente).orElseThrow(() -> new RestClientException("Erro: O Livro não existe"));
		livros.delete(livroExistente);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(livroExistente);
	}
	
}
