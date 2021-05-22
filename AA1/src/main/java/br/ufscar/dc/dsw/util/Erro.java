/* Classe que armazena as mensagens de erro para serem apresentadas pela Visão. */
package br.ufscar.dc.dsw.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Erro implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final List<String> erros;
	
	public Erro() {
		erros = new ArrayList<>();
	}
	
	public void add(String mensagem) {
		erros.add(mensagem);
	}
	
	public boolean isExisteErros() {
		return !erros.isEmpty();
	}
	
	public List<String> getErros(){
		return erros;
	}
}
