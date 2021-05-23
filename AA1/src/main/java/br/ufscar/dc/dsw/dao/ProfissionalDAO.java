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

public class ProfissionalDAO extends GenericDAO{
	public void insert(Profissional profissional) {
		
		String sql = "INSERT INTO PROFISSIONAL (CPF, NOME, SENHA, EMAIL, TELEFONE, SEXO, NASCIMENTO) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement = conn.prepareStatement(sql);
			statement.setLong(1, profissional.getCpf());
			statement.setString(2, profissional.getNome());
			statement.setString(3, profissional.getSenha());
			statement.setString(4, profissional.getEmail());
			statement.setString(5, profissional.getTelefone());
			statement.setString(6, profissional.getSexo());
			statement.setDate(6, profissional.getNascimento());
			statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * FROM PROFISSIONAL";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	Long id = resultSet.getLong("id");
                Long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
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
        String sql = "DELETE FROM PROFISSIONAL WHERE ID = ?";

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
        String sql = "UPDATE PROFISSIONAL SET NOME = ?, senha = ?, email = ?, telefone = ?, sexo = ?, nascimento = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getSenha());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getTelefone());
            statement.setString(5, profissional.getSexo());
            statement.setDate(6, profissional.getNascimento());
            statement.setLong(7, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(Long id) {
        Profissional profissional= null;

        String sql = "SELECT * FROM PROFISSIONAL WHERE ID = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long cpf = resultSet.getLong("cpf");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
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
    
    public Profissional getbyCpf(Long cpf) {
        Profissional profissional = null;

        String sql = "SELECT * FROM PROFISSIONAL WHERE CPF= ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("id");
            	String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
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
