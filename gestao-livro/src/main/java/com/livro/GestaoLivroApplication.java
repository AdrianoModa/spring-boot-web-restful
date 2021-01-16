package com.livro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaoLivroApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoLivroApplication.class, args);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", new Locale("pt", "BR"));
		Calendar calendar = Calendar.getInstance();
		System.out.println("Projeto gestao-livro Iniciado... às: " + sdf.format(calendar.getTime()));
	}
}
