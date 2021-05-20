package br.ufscar.dc.dsw.domain;

public class Profissional {
	
	private Long cpf;
	private String nome;
	private String senha;
	private String email;
	private String telefone;
	private Boolean sexo;
	private String nascimento;
	
	public Profissional(Long cpf, String nome, String senha, String email, String telefone, Boolean sexo, String nascimento) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nascimento = nascimento;
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
	
	public Boolean getSexo() {
		return sexo;
	}
	
	public void setSext(Boolean sexo) {
		this.sexo = sexo;
	}
	
	public String getNascimento() {
		return nascimento;
	}
	
	public void setNacimento(String nascimento) {
		this.nascimento = nascimento;
	}
}

