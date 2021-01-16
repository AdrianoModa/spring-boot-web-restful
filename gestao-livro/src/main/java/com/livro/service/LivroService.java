package com.livro.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.livro.model.Livro;
import com.livro.repository.Livros;

@Service
public class LivroService {

	@Autowired
	private Livros repositoriolivros;

	public List<Livro> listarTodos(){
		return repositoriolivros.findAll();
	}

	public Livro listarPorId(Long id){
		Optional<Livro> livro = repositoriolivros.findById(id);
		return livro.orElse(null);
	}

	public Livro adicionar(Livro livro){
		return repositoriolivros.save(livro);
	}

	public Livro atualizar(Livro livro){
		return repositoriolivros.save(livro);
	}

	public void remover(Long id){
		Optional<Livro> livroExistente = repositoriolivros.findById(id);
		livroExistente.orElse(null);
		repositoriolivros.delete(livroExistente.get());
	}

}
