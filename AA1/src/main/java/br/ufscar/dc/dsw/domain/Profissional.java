package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Profissional {
	
	private Long id;
	private Long cpf;
	private String nome;
	private String senha;
	private String email;
	private String telefone;
	private String sexo;
	private Date nascimento;
	
	public Profissional(Long id, Long cpf, String nome, String senha, String email, String telefone, String sexo, Date nascimento) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nascimento = nascimento;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}	
	
	
	public Long getCpf() {
		return cpf;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSext(String sexo) {
		this.sexo = sexo;
	}
	
	public Date getNascimento() {
		return nascimento;
	}
	
	public void setNacimento(Date nascimento) {
		this.nascimento = nascimento;
	}
}

