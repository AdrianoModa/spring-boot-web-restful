package com.livro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "livro")
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id") @ToString
public class Livro implements Serializable {
	
	private static final long serialVersionUID = 6865151781535323830L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Long id;	
	@Getter @Setter private String nome;	
	@Getter @Setter private String serie;	
	@Getter @Setter private boolean seEmprestado;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy' 'HH:mm")
	@Getter @Setter private Date registroEmprestimo;	
	
}