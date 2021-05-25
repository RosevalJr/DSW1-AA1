package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionaisController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    private ProfissionalDAO dao;
    
    @Override
    public void init() {
    	dao = new ProfissionalDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("admin")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMINISTRADOR] tem acesso a essa página");
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
        } catch (RuntimeException | IOException | ServletException | ParseException e) {
            throw new ServletException(e);
        }
    }
    
    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		request.setCharacterEncoding("UTF-8");
		
		Long cpf = Long.parseLong(request.getParameter("cpf"));
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date nascimento = formato.parse(request.getParameter("nascimento"));
		Date nasc = new Date(nascimento.getTime());
		
		Long id = (long) 0;
		
		Profissional profissional = new Profissional(id, cpf, nome, senha, email, telefone, sexo, nasc);
		
		dao.insert(profissional);
		response.sendRedirect("lista");
    }
    
    
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Profissional> listaProfissionais = dao.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
		dispatcher.forward(request, response);
	}
    
    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));

		Profissional profissional = new Profissional(id);
		System.out.println(id);
		dao.delete(profissional);
		response.sendRedirect("lista");
	}
    
    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Profissional profissional = dao.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/profissional/formulario.jsp");
		request.setAttribute("profissional", profissional);
		dispatcher.forward(request, response);
	}
    
    private void atualize(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {

    	Long cpf = Long.parseLong(request.getParameter("cpf"));
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date nascimento = formato.parse(request.getParameter("nascimento"));
		Date nasc = new Date(nascimento.getTime());
		
		Long id = (long) 0;
		
		Profissional profissional = new Profissional(id, cpf, nome, senha, email, telefone, sexo, nasc);
	
		dao.update(profissional);
		response.sendRedirect("lista");
	} 
}
