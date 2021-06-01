package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO {
	public void insert(Empresa empresa) {
		Long id = null;
		// Cria primeiro o usuario.
		String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA, PAPEL) VALUES(?, ?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement = conn.prepareStatement(sql);
			statement.setString(1, empresa.getNome());
			statement.setString(2, empresa.getEmail());
			statement.setString(3, empresa.getSenha());
			statement.setString(4, "userEmpresa");
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// Consulta o numero de ID do usuario criado.
		String sql1 = "SELECT MAX(ID) FROM USUARIO";

		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();

			ResultSet resultSet = statement.executeQuery(sql1);
			while (resultSet.next()) {
				id = resultSet.getLong("MAX(ID)");
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// Insere a empresa referenciando a instancia de usuario criada.
		String sql2 = "INSERT INTO EMPRESA (IDUSUARIO, CNPJ, DESCRICAO, CIDADE) VALUES(?, ?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql2);

			statement = conn.prepareStatement(sql2);
			statement.setLong(1, id);
			statement.setLong(2, empresa.getCnpj());
			statement.setString(3, empresa.getDescricao());
			statement.setString(4, empresa.getCidade());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Empresa> getAll() {

		List<Empresa> listaEmpresas = new ArrayList<>();

		String sql = "SELECT ID, NOME, LOGIN, SENHA, CNPJ, DESCRICAO, CIDADE FROM EMPRESA, USUARIO WHERE ID = IDUSUARIO";

		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				long cnpj = resultSet.getLong("cnpj");
				String nome = resultSet.getString("nome");
				String senha = resultSet.getString("senha");
				String email = resultSet.getString("login");
				String descricao = resultSet.getString("descricao");
				String cidade = resultSet.getString("cidade");
				Empresa empresa = new Empresa(id, cnpj, nome, senha, email, descricao, cidade);
				listaEmpresas.add(empresa);
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaEmpresas;
	}

	public void delete(Empresa empresa) {
		// Delete on cascade pega na EMPRESA.
		String sql = "DELETE FROM USUARIO WHERE ID = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, empresa.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
		}

	}

	public void update(Empresa empresa) {
		// Update primerio usuario.
		String sql = "UPDATE USUARIO SET NOME = ? ,LOGIN = ?, SENHA = ? WHERE ID = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, empresa.getNome());
			statement.setString(2, empresa.getEmail());
			statement.setString(3, empresa.getSenha());
			statement.setLong(4, empresa.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		// Update segundamente a empresa.
		String sql1 = "UPDATE EMPRESA SET CNPJ = ?, DESCRICAO = ?, CIDADE = ? WHERE IDUSUARIO = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql1);

			statement.setLong(1, empresa.getCnpj());
			statement.setString(2, empresa.getDescricao());
			statement.setString(3, empresa.getCidade());
			statement.setLong(4, empresa.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Empresa get(Long id) {
		Empresa empresa = null;

		String sql = "SELECT * FROM USUARIO WHERE ID = ?";
		String nome = null, senha = null, email = null, descricao = null, cidade = null;
		Long cnpj = null;

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				nome = resultSet.getString("nome");
				senha = resultSet.getString("senha");
				email = resultSet.getString("login");
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String sql1 = "SELECT * FROM EMPRESA WHERE IDUSUARIO = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql1);

			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				cnpj = resultSet.getLong("cnpj");
				descricao = resultSet.getString("descricao");
				cidade = resultSet.getString("cidade");
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		empresa = new Empresa(id, cnpj, nome, senha, email, descricao, cidade);
		return empresa;
	}

	public Empresa getbyCnpj(Long cnpj) {
		Empresa empresa = null;

		String sql = "SELECT * FROM EMPRESA WHERE CNPJ = ?";
		String nome = null, senha = null, email = null, descricao = null, cidade = null;
		Long id = null;

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, cnpj);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getLong("idusuario");
				descricao = resultSet.getString("descricao");
				cidade = resultSet.getString("cidade");
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String sql1 = "SELECT * FROM USUARIO WHERE ID = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql1);

			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				nome = resultSet.getString("nome");
				senha = resultSet.getString("senha");
				email = resultSet.getString("login");
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		empresa = new Empresa(id, cnpj, nome, senha, email, descricao, cidade);
		return empresa;
	}
}
