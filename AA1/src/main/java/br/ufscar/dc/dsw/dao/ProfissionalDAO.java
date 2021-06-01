package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public class ProfissionalDAO extends GenericDAO {
	public void insert(Profissional profissional) {
		Long id = null;
		// Cria primeiro o usuario.
		String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA, PAPEL) VALUES(?, ?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			statement = conn.prepareStatement(sql);
			statement.setString(1, profissional.getNome());
			statement.setString(2, profissional.getEmail());
			statement.setString(3, profissional.getSenha());
			statement.setString(4, "userProfissional");
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

		String sql2 = "INSERT INTO PROFISSIONAL VALUES(?, ?, ?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql2);

			statement = conn.prepareStatement(sql2);
			statement.setLong(1, id);
			statement.setLong(2, profissional.getCpf());
			statement.setString(3, profissional.getTelefone());
			statement.setString(4, profissional.getSexo());
			statement.setDate(5, profissional.getNascimento());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Profissional> getAll() {

		List<Profissional> listaProfissionais = new ArrayList<>();

		String sql = "SELECT ID, NOME, LOGIN, SENHA, CPF, TELEFONE, SEXO, NASCIMENTO FROM PROFISSIONAL, USUARIO WHERE ID = IDUSUARIO";

		try {
			Connection conn = this.getConnection();
			Statement statement = conn.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				Long cpf = resultSet.getLong("cpf");
				String nome = resultSet.getString("nome");
				String senha = resultSet.getString("senha");
				String email = resultSet.getString("login");
				String telefone = resultSet.getString("telefone");
				String sexo = resultSet.getString("sexo");
				Date nascimento = resultSet.getDate("nascimento");
				Profissional profissional = new Profissional(id, cpf, nome, senha, email, telefone, sexo, nascimento);
				listaProfissionais.add(profissional);
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaProfissionais;
	}

	public void delete(Profissional profissional) {
		String sql = "DELETE FROM USUARIO WHERE ID = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, profissional.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
		}
	}

	public void update(Profissional profissional) {
		// Update primerio usuario.
		String sql = "UPDATE USUARIO SET NOME = ? ,LOGIN = ?, SENHA = ? WHERE ID = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, profissional.getNome());
			statement.setString(2, profissional.getEmail());
			statement.setString(3, profissional.getSenha());
			statement.setLong(4, profissional.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		String sql1 = "UPDATE PROFISSIONAL SET CPF = ?, TELEFONE = ?, SEXO = ?, NASCIMENTO = ? WHERE IDUSUARIO = ?;";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql1);

			System.out.println(profissional.getId());

			statement.setLong(1, profissional.getCpf());
			statement.setString(2, profissional.getTelefone());
			statement.setString(3, profissional.getSexo());
			statement.setDate(4, profissional.getNascimento());
			statement.setLong(5, profissional.getId());
			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Profissional get(Long id) {
		Profissional profissional = null;

		String sql = "SELECT * FROM USUARIO WHERE ID = ?";
		String nome = null, senha = null, email = null, sexo = null, telefone = null;
		Long cpf = null;
		Date nascimento = null;

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

		String sql1 = "SELECT * FROM PROFISSIONAL WHERE IDUSUARIO = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql1);

			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				cpf = resultSet.getLong("cpf");
				telefone = resultSet.getString("telefone");
				sexo = resultSet.getString("sexo");
				nascimento = resultSet.getDate("nascimento");

				profissional = new Profissional(id, cpf, nome, senha, email, telefone, sexo, nascimento);
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return profissional;
	}

	public Profissional getbyCpf(Long cpf) {
		Profissional profissional = null;

		String sql = "SELECT ID, NOME, LOGIN, SENHA, TELEFONE, SEXO, NASCIMENTO FROM PROFISSIONAL, USUARIO WHERE CPF= ? AND ID = IDUSUARIO";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, cpf);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nome = resultSet.getString("nome");
				String senha = resultSet.getString("senha");
				String email = resultSet.getString("login");
				String telefone = resultSet.getString("telefone");
				String sexo = resultSet.getString("sexo");
				Date nascimento = resultSet.getDate("nascimento");

				profissional = new Profissional(id, cpf, nome, senha, email, telefone, sexo, nascimento);
			}

			resultSet.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return profissional;
	}
}
