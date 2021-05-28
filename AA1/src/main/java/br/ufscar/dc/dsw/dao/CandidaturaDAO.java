package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

public class CandidaturaDAO extends GenericDAO{

	public void insert(Candidatura candidatura) {
		VagaDAO vagaDao = new VagaDAO();
		Vaga vaga = vagaDao.getVaga(candidatura.getIdVaga());
		
		ProfissionalDAO profissionalDao = new ProfissionalDAO();
		Profissional profissional = profissionalDao.get(candidatura.getIdPessoa());
		
		String sql = "INSERT INTO CANDIDATURA (IDVAGA, IDPROFISSIONAL) VALUES(?, ?)";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getIdvaga());
            statement.setLong(2, profissional.getId());
            statement.executeUpdate();
			
			statement.close();
			conn.close();
            
		} catch(SQLException e) {
			
		}
	}
	
	public void delete(Candidatura candidatura) {
		String sql = "DELETE FROM CANDIDATURA WHERE IDVAGA = ?, IDPROFISSIONAL = ?";
		
		try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, candidatura.getIdVaga());
            statement.setLong(2, candidatura.getIdPessoa());
            statement.executeUpdate();

            statement.close();
            conn.close();
	
		} catch(SQLException e) {
		}
	}
	
	public List<Candidatura> getCandidaturasByPessoa(Long idPessoa) {
		List<Candidatura> candidaturas = new ArrayList<>(); 
		
		String sql = "SELECT IDVAGA, IDPROFISSIONAL FROM CANDIDATURA WHERE IDPROFISSIONAL = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setLong(1, idPessoa);
			ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Long idVaga = resultSet.getLong("idvaga");
            	Long idPes = resultSet.getLong("idprofissional");
            	Candidatura candidatura = new Candidatura(idVaga,idPes);
            	candidaturas.add(candidatura);
            }
            
            statement.close();
            conn.close();
		} catch(SQLException e) {
		}

		return candidaturas;
	}
	
	public List<Candidatura> getCandidaturasByVaga(Long idVaga){
		List<Candidatura> candidaturas = new ArrayList<>();
		
		String sql = "SELECT IDVAGA, IDPESSOA FROM CANDIDATURA WHERE IDVAGA = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setLong(1, idVaga);
			
			ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Long idVag = resultSet.getLong("idvaga");
            	Long idPes = resultSet.getLong("idpessoa");
            	Candidatura candidatura = new Candidatura(idVag,idPes);
            	candidaturas.add(candidatura);
            }
		} catch(SQLException e) {
		}

		return candidaturas;
	}
}
