package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;

public class CandidaturaDAO extends GenericDAO {

	public void insert(Candidatura candidatura) {
		String sql = "INSERT INTO CANDIDATURA (IDVAGA, IDPROFISSIONAL, CURRICULO) VALUES(?, ?, ?)";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, candidatura.getIdvaga());
			statement.setLong(2, candidatura.getIdpessoa());
			statement.setString(3, candidatura.getCurriculo());
			statement.executeUpdate();

			statement.close();
			conn.close();

		} catch (SQLException e) {

		}
	}

	public void delete(Candidatura candidatura) {
		String sql = "DELETE FROM CANDIDATURA WHERE IDVAGA = ?, IDPROFISSIONAL = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, candidatura.getIdvaga());
			statement.setLong(2, candidatura.getIdpessoa());
			statement.executeUpdate();

			statement.close();
			conn.close();

		} catch (SQLException e) {
		}
	}

	public List<Candidatura> getCandidaturasByPessoa(Long idPessoa) {
		List<Candidatura> candidaturas = new ArrayList<>();

		String sql = "SELECT IDVAGA, IDPROFISSIONAL, STATUS, CURRICULO, CNPJ, VAGA.DESCRICAO, REMUNERACAO, DATALIMITE FROM CANDIDATURA, VAGA, EMPRESA WHERE IDPROFISSIONAL = ? AND IDVAGA = ID AND IDUSUARIO = IDEMPRESA";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, idPessoa);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Long idVaga = resultSet.getLong("idvaga");
				Long idPes = resultSet.getLong("idprofissional");
				String status = resultSet.getString("status");
				String curriculo = resultSet.getString("curriculo");
				Long cnpj = resultSet.getLong("cnpj");
				String descricao = resultSet.getString("vaga.descricao");
				Float remuneracao = resultSet.getFloat("remuneracao");
				Date dataLimite = resultSet.getDate("datalimite");
				Candidatura candidatura = new Candidatura(idVaga, idPes, status, curriculo, cnpj, descricao,
						remuneracao, dataLimite);
				candidaturas.add(candidatura);
			}

			statement.close();
			conn.close();
		} catch (SQLException e) {
		}

		return candidaturas;
	}

	public List<Candidatura> getCandidaturasByVaga(Long idVaga) {
		List<Candidatura> candidaturas = new ArrayList<>();

		String sql = "SELECT IDVAGA, IDPROFISSIONAL, STATUS, CURRICULO, CNPJ, VAGA.DESCRICAO, REMUNERACAO, DATALIMITE FROM CANDIDATURA, EMPRESA, VAGA WHERE IDVAGA = ? AND IDVAGA = ID AND IDEMPRESA = IDUSUARIO";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, idVaga);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Long idVag = resultSet.getLong("idvaga");
				Long idPes = resultSet.getLong("idprofissional");
				String status = resultSet.getString("status");
				String curriculo = resultSet.getString("curriculo");
				Long cnpj = resultSet.getLong("cnpj");
				String descricao = resultSet.getString("vaga.descricao");
				Float remuneracao = resultSet.getFloat("remuneracao");
				Date dataLimite = resultSet.getDate("datalimite");
				Candidatura candidatura = new Candidatura(idVag, idPes, status, curriculo, cnpj, descricao, remuneracao,
						dataLimite);
				candidaturas.add(candidatura);
			}
		} catch (SQLException e) {
		}

		return candidaturas;
	}

	public Candidatura getCandidatura(Long idVaga, Long idPessoa) {

		String sql = "SELECT IDVAGA, IDPROFISSIONAL, STATUS, CURRICULO, CNPJ, VAGA.DESCRICAO, REMUNERACAO, DATALIMITE FROM CANDIDATURA, EMPRESA, VAGA WHERE IDVAGA = ? AND IDPROFISSIONAL = ? AND IDVAGA = ID AND IDEMPRESA = IDUSUARIO";
		Candidatura candidatura = null;
		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, idVaga);
			statement.setLong(2, idPessoa);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Long idVag = resultSet.getLong("idvaga");
				Long idPes = resultSet.getLong("idprofissional");
				String status = resultSet.getString("status");
				String curriculo = resultSet.getString("curriculo");
				Long cnpj = resultSet.getLong("cnpj");
				String descricao = resultSet.getString("vaga.descricao");
				Float remuneracao = resultSet.getFloat("remuneracao");
				Date dataLimite = resultSet.getDate("datalimite");
				candidatura = new Candidatura(idVag, idPes, status, curriculo, cnpj, descricao, remuneracao,
						dataLimite);
			}
		} catch (SQLException e) {
		}

		return candidatura;
	}

	public void update(Long idVaga, Long idProfissional, String status) {
		// Update primerio usuario.
		String sql = "UPDATE CANDIDATURA SET STATUS = ?  WHERE IDVAGA = ? AND IDPROFISSIONAL = ?";

		try {
			Connection conn = this.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, status);
			statement.setLong(2, idVaga);
			statement.setLong(3, idProfissional);

			statement.executeUpdate();

			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
