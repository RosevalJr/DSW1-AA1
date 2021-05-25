package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;

import java.util.List;

public class AcessaBD {
	public static void main(String[] args) {
		EmpresaDAO dao = new EmpresaDAO();
        List<Empresa> listaEmpresas = dao.getAll();
        for(int i = 0 ; i < listaEmpresas.size(); i++) {
        	System.out.println(listaEmpresas.get(i).getCnpj());
        }
    }
}