package com.livro.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.livro.model.Livro;
import com.livro.service.LivroService;

@RestController
@RequestMapping("/livro")
@CrossOrigin
public class LivroResource {

	@Autowired
	private LivroService livroService;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Livro> listar(){
		return livroService.listarTodos();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Livro> buscarPorId(@Validated @PathVariable Long id){
		return ResponseEntity.ok(livroService.listarPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Livro adicionar(@Validated @RequestBody Livro livro){
		return livroService.adicionar(livro);
	}
		
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> atualizar(@PathVariable Long id, @Validated @RequestBody Livro livro){
		livro.setId(id);
		livroService.atualizar(livro);
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> remover(@PathVariable Long id){
		Livro livroExistente = livroService.listarPorId(id);
		livroService.remover(livroExistente.getId());
		return ResponseEntity.status(HttpStatus.OK).body("Livro removido com sucesso!");
	}
	
}
