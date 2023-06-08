package entity.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conecta;
import conexao.IGenericDAO;
import modelo.entity.Livro;

public class LivroDAO implements IGenericDAO{
	private Connection connection;
	
	public LivroDAO() throws ClassNotFoundException, SQLException {
		// Conectando com o banco de dados, a partir da chamada do método da classe Conecta
		connection = Conecta.conexao();
	}
	
	@Override
	public void inserir(Livro livro) {
		PreparedStatement statement = null;
		// Escrevendo o comando SQL para adicionar tuplas
		String sql = "INSERT INTO livro (isbn,titulo,data_lancamento,genero) values (?,?,?,?)";
		
		try {
			// Chamando a API SQL "PreparedStatement" para adicionar as tuplas, seguindo o modelo abaixo
			statement = connection.prepareStatement(sql);
			
			// O metodo 'set' dessa classe recebe dois parametros: o numero referente a coluna, ou ordem do atributo, e o valor que deseja adicionar
			statement.setLong(1, livro.getIsbn());
			statement.setString(2, livro.getTitulo());
			statement.setDate(3, new java.sql.Date(livro.getData_lancamento().getTime()));
			statement.setString(4, livro.getGenero());
			
			// O metodo 'executeUpdate' executa o comando preescrito anteriormente, adicionando, assim, a tupla.
			statement.executeUpdate();
			System.out.println("Livro adicionado!");
		} catch (SQLException e) {
			e.printStackTrace();		
		} finally {
			// Fechando as conexões que foram abertas
			if (connection != null) {
				Conecta.desconexao(connection);
				Conecta.desconexao(statement);
			}
		}
	}
	
	@Override
	public void remover(long isbn) {
		PreparedStatement statement = null;		
		// Escrevendo o comando SQL para remover tuplas
		String sql = "DELETE FROM livro WHERE isbn = ?";		
		try {
			statement = connection.prepareStatement(sql);
			// Adicionando o parametro
			statement.setLong(1, isbn);
			
			// Numeros de tuplas que foram apagadas
			int tuplasDeletadas = statement.executeUpdate();
			// OBS: O metodo 'executeUpdate' retorna um numero inteiro	
			// Conferindo se alguma tupla foi apagada
			if (tuplasDeletadas > 0) {
				System.out.println("Tupla removida");
			} else {
				System.out.println("Tupla nao encontrada!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Fechando as conexoes que foram abertas
	    	  if (connection != null) {
	    		  Conecta.desconexao(connection);
	    		  Conecta.desconexao(statement);
	    	  }
		}
	}
	
	@Override
	 public List<Livro> listarLivros() {
		// Chamando a API SQL 'PreparedStatement' para adicionar as tuplas, seguindo o modelo abaixo
		 PreparedStatement statement = null; 
		 //Criando uma List, no qual ficará armazenado todo livro que foi lido do Banco de Dados
		 List<Livro> livros = new ArrayList<>();
	     // Escrevendo o comando SQL para mostrar todas as tuplas
	     String sql = "SELECT * FROM livro";
	        
	     try {
	    	 // Chamando a API SQL 'ResultSet' para fornecer metodos capazes de percorrer as tuplas
	    	 statement = connection.prepareStatement(sql);
	    	 ResultSet resultSet = statement.executeQuery();
	    	 	while (resultSet.next()) {
	    	 		// Criando variáveis correspondentes ao Banco de Dados
	    	 		long isbn = resultSet.getLong("isbn");
	    	 		String titulo = resultSet.getString("titulo");
	    	 		Date dataLancamento = resultSet.getDate("data_lancamento");
	    	 		String genero = resultSet.getString("genero");
	    	 		// A cada tupla percorrida, ele instancia essa classe e adiciona na lista, afim de ser mostrada no final
	    	 		Livro livro = new Livro(isbn, titulo, dataLancamento, genero);
	    	 		livros.add(livro);
	            }
	      } catch (SQLException e) {
	            e.printStackTrace();
	      } finally {
	    	// Fechando as conexoes que foram abertas
	    	  if (connection != null) {
	    		  Conecta.desconexao(connection);
	    		  Conecta.desconexao(statement);
	    	  }
	      }
	       return livros;
	    }
	 
	@Override
	 public void atualizar(Livro livro) {
		// Chamando a API SQL "PreparedStatement" para modificar as tuplas, seguindo o modelo abaixo
		 PreparedStatement statement = null;
		// Escrevendo o comando SQL para atualizar tupla 
		 String sql = "UPDATE livro SET titulo = ?, data_lancamento = ?, genero = ? WHERE isbn = ?";
		 
		 try {
			 // Semelhante ao método 'inserir', é necessário colocar os parametros referentes aos métodos 'set' da classe PreparedStatement
			 statement = connection.prepareStatement(sql);
			 statement.setString(1, livro.getTitulo());
			 statement.setDate(2, new java.sql.Date(livro.getData_lancamento().getTime()));
			 statement.setString(3,livro.getGenero());
			 statement.setLong(4, livro.getIsbn());
			 statement.executeUpdate();			 
			 // Conferindo para saber se alguma tupla foi atualizada
			 int tuplasAtualizadas = statement.executeUpdate();
			 if (tuplasAtualizadas > 0) {
				 System.out.println("Tupla atualizada!");
			 } else {
				 System.out.println("Tupla não encontrada!");
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 // Fechando as conexoes que foram abertas
	    	  if (connection != null) {
	    		  Conecta.desconexao(connection);
	    		  Conecta.desconexao(statement);
	    	  }
	      }
	 }

}
