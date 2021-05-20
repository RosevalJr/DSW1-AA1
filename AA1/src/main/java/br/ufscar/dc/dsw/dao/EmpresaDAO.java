package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO{
	public void insert(Empresa empresa) {
		
		String sql = "INSERT INTO EMPRESA VALUES(?, ?, ?, ?, ?, ?)";
		
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
	
	public void delete(Empresa empresa) {
		
		String sql = "DELETE FROM EMPRESA WHERE CNPJ = ?";
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement = conn.prepareStatement(sql);
			statement.setLong(1, empresa.getCnpj());	
			statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Empresa empresa) {
		
		String sql = "UPDATE EMPRESA SET CNPJ = ?, NOME = ?, SENHA = ?, EMAIL = ?, DESCRICAO = ?, CIDADE = ?";
		
		 try {
	            Connection conn = this.getConnection();
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setLong(1, empresa.getCnpj());
	            statement.setString(2, empresa.getNome());
	            statement.setString(3, empresa.getSenha());
	            statement.setString(4, empresa.getEmail());
	            statement.setString(5, empresa.getDescricao());
	            statement.setString(6, empresa.getCidade());
	            statement.executeUpdate();

	            statement.close();
	            conn.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	            
	}
	
	
}
