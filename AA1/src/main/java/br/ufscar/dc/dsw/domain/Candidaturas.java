package br.ufscar.dc.dsw.domain;

public class Candidaturas {
	
	private Long idVaga;
	private Long cnpjEmpresa;
	private Long cpfPessoa;
	
	public Candidaturas(Long idVaga, Long cnpjEmpresa, Long cpfPessoa) {
		this.idVaga = idVaga;
		this.cnpjEmpresa = cnpjEmpresa;
		this.cpfPessoa = cpfPessoa;
	}
	
	public Long getIdVaga() {
		return idVaga;
	}
	
	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}
	
	public Long getCnpjEmpresa() {
		return cnpjEmpresa;
	}
	
	public void setCnpjEmpresa(Long cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
	
	public Long getCpfPessoa() {
		return cpfPessoa;
	}
	
	public void setCpfPessoa(Long cpfPessoa) {
		this.cpfPessoa = cpfPessoa;
	}
}