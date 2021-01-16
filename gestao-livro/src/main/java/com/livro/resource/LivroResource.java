package com.livro.resource;

import java.util.List;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import com.livro.model.Livro;
import com.livro.service.LivroService;

@RestController
@RequestMapping("/livro")
@CrossOrigin
public class LivroResource {

	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public List<Livro> listar(){
		return livroService.listarTodosLivros();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@Valid @PathVariable Long id){
		Livro livroId = livroService.listarLivroPorId(id);	
		return ResponseEntity.ok(livroId);
	}
	
	@PostMapping
	public Livro adicionar(@Valid @RequestBody Livro livro){
		return livroService.adicionarLivro(livro);
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro){
		Livro livroExistente = livroService.listarLivroPorId(id);		
		livroService.atualizarLivro(livroExistente);
		return ResponseEntity.status(HttpStatus.OK).body("Livro atualizado com sucesso! " + livroExistente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Livro livroExistente = livroService.listarLivroPorId(id);
		livroService.removerLivro(livroExistente.getId());
		return ResponseEntity.status(HttpStatus.OK).body("Livro removido com sucesso!");
	}
	
}
