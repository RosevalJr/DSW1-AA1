package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Importa classes que armazenam dados a serem usados pelos controladores. */
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro(); // Variavel para guardar os erros.
		if(request.getParameter("loginOk") != null) { // Caso efetuado login.
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			if(login.isEmpty()) {
				erros.add("Nome de usuário não informado!");
			}
			if(senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if(!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = dao.getbyLogin(login);
				if(usuario != null) {
					if(usuario.getSenha().equals(senha)) {
						request.getSession().setAttribute("usuarioLogado", usuario);
						if(usuario.getPapel().equals("admin")) {
							response.sendRedirect("empresas/");
						} else {
							response.sendRedirect("user/");
						}
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Nome de usuário não encontrado!");
				}
			}
			
		}
		request.getSession().invalidate(); // Caso nao logado invalida a sessao.
		request.setAttribute("mensagensErros", erros);
		
		String URL = "/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
