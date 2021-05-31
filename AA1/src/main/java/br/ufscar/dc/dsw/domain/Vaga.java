package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Vaga {
	
	private Long idVaga;
	private Long cnpjEmpresa;
	private String descricao;
	private Float remuneracao;
	private Date dataLimite;
	private boolean aberta;
	private String cidade;
	
	public Vaga(Long idVaga, Long cnpjEmpresa, String descricao, Float remuneracao, Date dataLimite) {
		this.cnpjEmpresa = cnpjEmpresa;
		this.idVaga = idVaga;
		this.descricao = descricao;
		this.remuneracao = remuneracao;
		this.dataLimite = dataLimite;
		
		// Checa se dado a dataLimite a vaga deve ser fechada ou nao.
		LocalDate dataAtual = java.time.LocalDate.now();
		Date dataAtualSql = Date.valueOf(dataAtual);
		System.out.println(dataLimite);
		System.out.println(dataAtualSql);
		if(dataLimite.equals(dataAtualSql)) {
			this.aberta = true;
		}
		else {
			if(dataLimite.before(dataAtualSql)) {
				this.aberta = false;
			}
			else {
				this.aberta = true;
			}
			
		}
		
	}
	
	public Vaga(Long cnpjEmpresa, String descricao, Float remuneracao, Date dataLimite) {
		this.cnpjEmpresa = cnpjEmpresa;
		this.descricao = descricao;
		this.remuneracao = remuneracao;
		this.dataLimite = dataLimite;
		
		// Checa se dado a dataLimite a vaga deve ser fechada ou nao.
		LocalDate dataAtual = java.time.LocalDate.now();
		Date dataAtualSql = Date.valueOf(dataAtual);
		System.out.println(dataLimite);
		System.out.println(dataAtualSql);
		if(dataLimite.equals(dataAtualSql)) {
			this.aberta = true;
		}
		else {
			if(dataLimite.before(dataAtualSql)) {
				this.aberta = false;
			}
			else {
				this.aberta = true;
			}	
		}	
	}
	
	public Long getCnpjempresa() {
		return cnpjEmpresa;
	}
	
	public void setCnpjempresa(Long cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
	
	public Long getIdvaga() {
		return idVaga;
	}
	
	public void setIdvaga(Long idVaga) {
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
	
	public Date getDatalimite() {
		return dataLimite;
	}
	
	public void setDatalimite(Date dataLimite) {
		this.dataLimite = dataLimite;
		
		LocalDate dataAtual = java.time.LocalDate.now();
		Date dataAtualSql = Date.valueOf(dataAtual);
		if(dataLimite.equals(dataAtualSql)) {
			this.aberta = true;
		}
		else {
			if(dataLimite.before(dataAtualSql)) {
				this.aberta = false;
			}
			else {
				this.aberta = true;
			}
			
		}
	}
	
	public boolean getAberta() {
		LocalDate dataAtual = java.time.LocalDate.now();
		Date dataAtualSql = Date.valueOf(dataAtual);
		if(dataLimite.equals(dataAtualSql)) {
			this.aberta = true;
		}
		else {
			if(dataLimite.before(dataAtualSql)) {
				this.aberta = false;
			}
			else {
				this.aberta = true;
			}
			
		}
		return this.aberta;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
