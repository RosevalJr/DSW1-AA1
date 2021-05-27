package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;

public class VagaDAO extends GenericDAO{
	
	public void insert(Vaga vaga) {
		
		// Recupera empresa dado o cnpj passado.
		EmpresaDAO empresaDao = new EmpresaDAO();
		Empresa empresa = empresaDao.getbyCnpj(vaga.getCnpjempresa());
		
		String sql = "INSERT INTO VAGA (IDEMPRESA, DESCRICAO, REMUNERACAO, DATALIMITE) VALUES(?, ?, ?, ?)";
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement = conn.prepareStatement(sql);
			statement.setLong(1, empresa.getId());
			statement.setString(2, vaga.getDescricao());
			statement.setFloat(3, vaga.getRemuneracao());
			statement.setDate(4, vaga.getDatalimite());
			statement.executeUpdate();
			
			statement.close();
			conn.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Vaga> getAll() {

        List<Vaga> listaVagas = new ArrayList<>();

        String sql = "SELECT ID, CNPJ, VAGA.DESCRICAO, REMUNERACAO, DATALIMITE FROM EMPRESA, VAGA WHERE IDUSUARIO = IDEMPRESA";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	long id = resultSet.getLong("id");
                long cnpj = resultSet.getLong("cnpj");
                String descricao = resultSet.getString("descricao");
                float remuneracao = resultSet.getFloat("remuneracao");
                Date dataLimite = resultSet.getDate("datalimite");
                Vaga vaga = new Vaga(id, cnpj, descricao, remuneracao, dataLimite);
                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }
	
	public List<Vaga> getAllAberta() {

        List<Vaga> listaVagasAbertas = new ArrayList<>();

        String sql = "SELECT ID, CNPJ, VAGA.DESCRICAO, REMUNERACAO, DATALIMITE FROM EMPRESA, VAGA WHERE IDUSUARIO = IDEMPRESA";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	long id = resultSet.getLong("id");
                long cnpj = resultSet.getLong("cnpj");
                String descricao = resultSet.getString("descricao");
                float remuneracao = resultSet.getFloat("remuneracao");
                Date dataLimite = resultSet.getDate("datalimite");
                Vaga vaga = new Vaga(cnpj,id , descricao, remuneracao, dataLimite);
                if(vaga.getAberta()) // Caso for aberta adiciona a vaga na lista.
                	listaVagasAbertas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagasAbertas;
    }
	
	public Vaga getVaga(Long id) {

		Vaga vaga = null;
		String sql = "SELECT ID, IDEMPRESA, CNPJ,  VAGA.DESCRICAO, REMUNERACAO, DATALIMITE FROM VAGA, EMPRESA WHERE IDEMPRESA = EMPRESA.IDUSUARIO AND ID = ?";
	
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
            	Long idLocal = resultSet.getLong("id");
            	Long cnpjEmpresa = resultSet.getLong("cnpj");
                String descricao = resultSet.getString("descricao");
                float remuneracao = resultSet.getFloat("remuneracao");
                Date dataLimite = resultSet.getDate("datalimite");
                vaga = new Vaga(idLocal, cnpjEmpresa, descricao, remuneracao, dataLimite);
            }

            resultSet.close();
            statement.close();
            conn.close();	
		} catch (SQLException e) {
		}
		
		return vaga;
	}
	
}
