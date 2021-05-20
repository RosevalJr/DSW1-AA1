package br.ufscar.dc.dsw.domain;

public class Vagas {
	
	private Long cnpjEmpresa;
	private Long idVaga;
	private String descricao;
	private Float remuneracao;
	private String dataLimite;
	
	public Vagas(Long cnpjEmpresa, Long idVaga, String descricao, Float remuneracao, String dataLimite) {
		this.cnpjEmpresa = cnpjEmpresa;
		this.idVaga = idVaga;
		this.descricao = descricao;
		this.remuneracao = remuneracao;
		this.dataLimite = dataLimite;
	}
	
	public Long getCnpjEmpresa() {
		return cnpjEmpresa;
	}
	
	public void setCnpjEmpresa(Long cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
	
	public Long getIdVaga() {
		return idVaga;
	}
	
	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Float getRemuneracao() {
		return remuneracao;
	}
	
	public void setRemuneracao(Float remuneracao) {
		this.remuneracao = remuneracao;
	}
	
	public String getDataLimite() {
		return dataLimite;
	}
	
	public void setDataLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}
}
