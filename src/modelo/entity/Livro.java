package modelo.entity;

import java.sql.Date;

public class Livro {
	private long isbn;
	private String titulo;
	private java.util.Date data_lancamento;
	private String genero;
	
	public Livro() {
		
	}
	
	public Livro(String titulo, java.util.Date data_lancamento, String genero) {
		this.titulo = titulo;
		this.data_lancamento = data_lancamento;
		this.genero = genero;
	}

	public Livro(long isbn, String titulo, java.util.Date Date, String genero) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.data_lancamento = Date;
		this.genero = genero;
	}

	public long getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public java.util.Date getData_lancamento() {
		return data_lancamento;
	}

	public void setData_lancamento(Date data_lancamento) {
		this.data_lancamento = data_lancamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
