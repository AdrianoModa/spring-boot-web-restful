package com.livro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livro.model.Livro;

public interface Livros extends JpaRepository<Livro, Long> {

}
