package br.ufscar.dc.dsw.domain;

public class Candidatura {
	
	private Long idVaga;
	private Long idPessoa;
	
	public Candidatura(Long idVaga, Long idPessoa) {
		this.idVaga = idVaga;
		this.idPessoa = idPessoa;
	}
	
	public Long getIdVaga() {
		return idVaga;
	}
	
	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}
	
	public Long getIdPessoa() {
		return idPessoa;
	}
	
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
}