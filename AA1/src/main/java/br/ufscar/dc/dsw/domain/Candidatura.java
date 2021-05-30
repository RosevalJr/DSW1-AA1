package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Candidatura {
	
	private Long idVaga;
	private Long idPessoa;
	private Long cnpjEmpresa;
	private String descricao;
	private Float remuneracao;
	private Date dataLimite;
	private String status;
	private String curriculo;
	
	public Candidatura(Long idVaga, Long idPessoa, String status, String curriculo) {
		this.idVaga = idVaga;
		this.idPessoa = idPessoa;
		this.status = status;
		this.curriculo = curriculo;
	}
	
	public Candidatura(Long idVaga, Long idPessoa, String curriculo) {
		this.idVaga = idVaga;
		this.idPessoa = idPessoa;
		this.curriculo = curriculo;
	}
	
	public Candidatura(Long idVaga, Long idPessoa) {
		this.idVaga = idVaga;
		this.idPessoa = idPessoa;
	}
	
	public Candidatura(Long idVaga, Long idPessoa, String status, String curriculo, Long cnpjEmpresa, String descricao, Float remuneracao, Date dataLimite) {
		this.idVaga = idVaga;
		this.idPessoa = idPessoa;
		this.status = status;
		this.curriculo = curriculo;
		this.cnpjEmpresa = cnpjEmpresa;
		this.descricao = descricao;
		this.remuneracao = remuneracao;
		this.dataLimite = dataLimite;
	}
	
	public Long getIdvaga() {
		return idVaga;
	}
	
	public void setIdvaga(Long idVaga) {
		this.idVaga = idVaga;
	}
	
	public Long getIdpessoa() {
		return idPessoa;
	}
	
	public void setIdpessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCurriculo() {
		return this.curriculo;
	}
	
	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
	}
	
	public Long getCnpjempresa() {
		return this.cnpjEmpresa;
	}
	
	public void setCnpjempresa(Long cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Float getRemuneracao() {
		return this.remuneracao;
	}
	
	public void setRemuneracao(Float remuneracao) {
		this.remuneracao = remuneracao;
	}
	
	public Date getDatalimite() {
		return this.dataLimite;
	}
	
	public void setDatalimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}
}