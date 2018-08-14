package com.livro.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.livro.model.Livro;
import com.livro.repository.Livros;

@Service
public class LivroService {

	@Autowired
	private Livros repositoriolivros;

	public List<Livro> listarTodosLivros(){
		return repositoriolivros.findAll();
	}

	public Livro listarLivroPorId(Long id){
		Livro livro = repositoriolivros.findOne(id);
		Optional.ofNullable(livro).orElseThrow(() -> new EntityExistsException("O objeto não existe ou não foi identificado"));
		return livro;
	}

	public Livro adicionarLivro(Livro livro){
		return repositoriolivros.save(livro);
	}

	public Livro atualizarLivro(Livro livro){
		Livro livroExistente = repositoriolivros.findOne(livro.getId());
		Optional.ofNullable(livroExistente).orElseThrow(() -> new EntityExistsException("O objeto não existe ou não foi identificado"));		
		return repositoriolivros.saveAndFlush(livroExistente);
	}

	public void removerLivro(Long id){
		Livro livroExistente = repositoriolivros.findOne(id);
		Optional.ofNullable(livroExistente).orElseThrow(() -> new EntityExistsException("O objeto não existe ou não foi identificado"));
		repositoriolivros.delete(livroExistente);
	}

}
