package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admins/empresas/*")
public class EmpresasAdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private EmpresaDAO dao;

	@Override
	public void init() {
		dao = new EmpresaDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("admin")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [admin] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/naoAutori.jsp");
			rd.forward(request, response);
			return;
		}

		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
			case "/cadastro":
				apresentaFormCadastro(request, response);
				break;
			case "/insercao":
				insere(request, response);
				break;
			case "/remocao":
				remove(request, response);
				break;
			case "/edicao":
				apresentaFormEdicao(request, response);
				break;
			case "/atualizacao":
				atualize(request, response);
				break;
			default:
				lista(request, response);
				break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Long cnpj;

		try {
			cnpj = Long.parseLong(request.getParameter("cnpj"));
		} catch (NumberFormatException nfe) {
			Erro erros = new Erro();
			erros.add("O campo CNPJ deve ser apenas composto por números");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
			rd.forward(request, response);
			return;
		}
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String descricao = request.getParameter("descricao");
		String cidade = request.getParameter("cidade");
		Long id = (long) 0;
		Empresa empresa = new Empresa(id, cnpj, nome, senha, email, descricao, cidade);

		try {
			dao.insert(empresa);
		} catch (RuntimeException e) {
			Erro erros = new Erro();
			erros.add("Esse CPNJ já esta cadastrado no sistema.");
			erros.add("Por favor insira outro CNPJ ou procure pela empresa ja cadastrada.");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
			rd.forward(request, response);
			return;
		}
		response.sendRedirect("lista");
	}

	private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Empresa> listaEmpresas = dao.getAll();
		request.setAttribute("listaEmpresas", listaEmpresas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/empresa/lista.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/empresa/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));

		Empresa empresa = new Empresa(id);
		dao.delete(empresa);
		response.sendRedirect("lista");
	}

	private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Empresa empresa = dao.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/empresa/formulario.jsp");
		request.setAttribute("empresa", empresa);
		dispatcher.forward(request, response);
	}

	private void atualize(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Long id = Long.parseLong(request.getParameter("id"));
		Long cnpj;

		try {
			cnpj = Long.parseLong(request.getParameter("cnpj"));
		} catch (NumberFormatException nfe) {
			Erro erros = new Erro();
			erros.add("O campo CNPJ deve ser apenas comoposto por números.");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
			rd.forward(request, response);
			return;
		}
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String descricao = request.getParameter("descricao");
		String cidade = request.getParameter("cidade");

		Empresa empresa = new Empresa(id, cnpj, nome, senha, email, descricao, cidade);
		try {
			dao.update(empresa);
		} catch (RuntimeException e) {
			Erro erros = new Erro();
			erros.add("Esse CPNJ já esta cadastrado no sistema.");
			erros.add("Por favor insira outro CNPJ ou procure pela empresa ja cadastrada.");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
			rd.forward(request, response);
			return;
		}
		response.sendRedirect("lista");
	}

}
