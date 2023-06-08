package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entity.dao.LivroDAO;
import modelo.entity.Livro;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int opcao = 0;		
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		while (true) {
			opcao = menu();
			
			switch(opcao) {
			case 1:
				inserir();
				break;
			case 2:
				remover();
				break;
			case 3:
				listarTodos();
				break;
			case 4:
				atualizar();
				break;
				}
			
		}
	}
			
	public static int menu() {
		System.out.println("===================================");
		System.out.println("============BEM-VINDO==============");
		System.out.println("1. Inserir");
		System.out.println("2. Remover");
		System.out.println("3. Listar todos os livros");
		System.out.println("4. Atualizar");
		return opcao = sc.nextInt();
	}
	
	private static void inserir() throws ClassNotFoundException, SQLException {
		LivroDAO livroDAO = new LivroDAO();
		System.out.println("Digite o ISBN do livro: ");
		long isbn = sc.nextLong();
		
		System.out.println("Digite o título do livro: ");
		sc.nextLine();
		String titulo = sc.nextLine();
		System.out.println("Digite a data de lancamento no formato (yyyy-mm-dd): ");
		String dataString = sc.next();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = formato.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Digite o genero do livro: ");
		String genero = sc.next().toUpperCase();
		
		
		Livro livro = new Livro(isbn,titulo,date,genero);
		livroDAO.inserir(livro);
		System.out.println("Livro cadastrado!");
	}

	private static void remover() throws ClassNotFoundException, SQLException {
		LivroDAO livroDAO = new LivroDAO();
		System.out.println("Digite o isbn que deseja remover: ");
		long isbn = sc.nextLong();
		
		livroDAO.remover(isbn);
		System.out.println("Livro removido!");
	}
	
	private static void listarTodos() throws ClassNotFoundException, SQLException {
		LivroDAO livroDAO = new LivroDAO();
		List<Livro> livros = livroDAO.listarLivros();
		for (Livro livro: livros) {
			System.out.println("ISBN: " + livro.getIsbn() + "| Titulo: " + livro.getTitulo());
		}
	}
	
	private static void atualizar() throws ClassNotFoundException, SQLException {
		LivroDAO livroDAO = new LivroDAO();
		System.out.println("Digite o isbn que deseja mudar: ");
		long isbn = sc.nextLong();
		
		System.out.println("Digite o título do livro: ");
		sc.nextLine();
		String titulo = sc.nextLine();
		System.out.println("Digite a data de lancamento no formato (yyyy-mm-dd): ");
		String dataString = sc.next();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = formato.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Digite o genero do livro: ");
		String genero = sc.next().toUpperCase();
		
		Livro livro = new Livro(isbn,titulo,date,genero);
		livroDAO.atualizar(livro);
		System.out.println("Livro atualizado!");
	}

}
