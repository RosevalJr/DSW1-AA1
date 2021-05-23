package br.ufscar.dc.dsw;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcessaBD {
	public static void main(String[] args) {
		EmpresaDAO dao = new EmpresaDAO();
        List<Empresa> listaEmpresas = dao.getAll();
        for(int i = 0 ; i < listaEmpresas.size(); i++) {
        	System.out.println(listaEmpresas.get(i).getCnpj());
        }
    }
}