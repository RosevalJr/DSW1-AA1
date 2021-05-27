package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Importa classes que armazenam dados a serem usados pelos controladores. */
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp", "/listarVagasAbertas" })
public class IndexController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
    private VagaDAO dao;

    @Override
    public void init() {
        dao = new VagaDAO();
    }
	
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
							response.sendRedirect("admins/empresas/");
						} else {
							if(usuario.getPapel().equals("userEmpresa"))
								response.sendRedirect("users/empresas");
							else
								response.sendRedirect("users/profissionais");
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
		
        String action = request.getRequestURI();
        if (action == null) {
            action = "";
        }
        if(action.equals("/AA1/listarVagasAbertas")) {
        	lista(request, response);
        }
		
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
	
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vaga> listaVagasAbertas = dao.getAllAberta();
        request.setAttribute("listaVagasAbertas", listaVagasAbertas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listarVagasAbertas.jsp");
        dispatcher.forward(request, response);
    }

}
