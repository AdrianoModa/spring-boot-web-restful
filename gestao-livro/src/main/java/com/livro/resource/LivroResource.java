package com.livro.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
		Livro livro = livros.findOne(id);
		if(livro == null){
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(livro);
	}
	
	@PostMapping("/adicionar/{nome}/{serie}/{emprestimo}/{registroEmprestimo}")
	public Livro adicionar(
			@PathVariable String nome,
			@PathVariable String serie,
			@PathVariable boolean emprestimo,
			@PathVariable String registroEmprestimo) throws ParseException {
		Livro livro = new Livro();
		livro.setNome(nome);
		livro.setSerie(serie);
		livro.setEmprestimo(emprestimo);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM-yyyy", new Locale("pt", "BR"));
		livro.setRegistroEmprestimo(sdf.parse(registroEmprestimo));
		return livros.save(livro);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id,
			@Valid @RequestBody Livro livro){
		Livro livroExistente = livros.findOne(id);
		if (livroExistente == null) {
			ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(livro, livroExistente, "id");
		
		livros.save(livroExistente);
		return ResponseEntity.ok(livroExistente);
	}
	
	@PutMapping("/{id}/{nome}/{serie}/{emprestimo}/{registroEmprestimo}")
	public ResponseEntity<Livro> atualizarTodos(
			@RequestBody @Valid
			@PathVariable Long id,
			@PathVariable String nome,
			@PathVariable String serie,
			@PathVariable boolean emprestimo,
			@PathVariable String registroEmprestimo) throws ParseException {
		Livro livro = livros.findOne(id);
		livro.setNome(nome);
		livro.setSerie(serie);
		livro.setEmprestimo(emprestimo);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM-yyyy", new Locale("pt", "BR"));
		livro.setRegistroEmprestimo(sdf.parse(registroEmprestimo));
		livros.save(livro);
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		Livro livro = livros.findOne(id);
		if(livro == null){
			ResponseEntity.notFound().build();
		}
		
		livros.delete(livro);
		return ResponseEntity.noContent().build();
	}
	
}
