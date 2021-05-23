package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO{
	public void insert(Empresa empresa) {
		
		String sql = "INSERT INTO EMPRESA (CNPJ, NOME, SENHA, EMAIL, DESCRICAO, CIDADE) VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement = conn.prepareStatement(sql);
			statement.setLong(1, empresa.getCnpj());
			statement.setString(2, empresa.getNome());
			statement.setString(3, empresa.getSenha());
			statement.setString(4, empresa.getEmail());
			statement.setString(5, empresa.getDescricao());
			statement.setString(6, empresa.getCidade());
			statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Empresa> getAll() {

        List<Empresa> listaEmpresas = new ArrayList<>();

        String sql = "SELECT * FROM EMPRESA";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	long id = resultSet.getLong("id");
                long cnpj = resultSet.getLong("cnpj");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
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
        String sql = "DELETE FROM EMPRESA WHERE ID = ?";

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
        String sql = "UPDATE EMPRESA SET NOME = ?, senha = ?, email = ?, descricao = ?, cidade = ? WHERE cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getNome());
            statement.setString(2, empresa.getSenha());
            statement.setString(3, empresa.getEmail());
            statement.setString(4, empresa.getDescricao());
            statement.setString(5, empresa.getCidade());
            statement.setLong(6, empresa.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Empresa get(Long id) {
        Empresa empresa= null;

        String sql = "SELECT * FROM EMPRESA WHERE ID = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long cnpj = resultSet.getLong("cnpj");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");

                empresa = new Empresa(id, cnpj, nome, senha, email, descricao, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }
    
    public Empresa getbyCnpj(Long cnpj) {
        Empresa empresa = null;

        String sql = "SELECT * FROM EMPRESA WHERE CNPJ = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("id");
            	String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String email = resultSet.getString("email");
                String descricao = resultSet.getString("descricao");
                String cidade = resultSet.getString("cidade");

                empresa = new Empresa(id, cnpj, nome, senha, email, descricao, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }
}
