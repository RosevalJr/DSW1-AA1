package br.ufscar.dc.dsw.domain;

public class Empresa {

	private Long id;
	private Long cnpj;
	private String nome;
	private String senha;
	private String email;
	private String descricao;
	private String cidade;

	public Empresa(Long id) {
		this.id = id;
	}

	public Empresa(Long id, Long cnpj, String nome, String senha, String email, String descricao, String cidade) {
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.senha = senha;
		this.email = email;
		this.descricao = descricao;
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
