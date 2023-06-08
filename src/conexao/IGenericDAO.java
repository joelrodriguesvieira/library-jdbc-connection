package conexao;

import java.sql.SQLException;
import java.util.List;

import modelo.entity.Livro;

public interface IGenericDAO {
	public void inserir(Livro livro) throws SQLException;
	public void remover(long isbn) throws SQLException;
	public List<Livro> listarLivros() throws SQLException;
	public void atualizar(Livro livro) throws SQLException;
}
