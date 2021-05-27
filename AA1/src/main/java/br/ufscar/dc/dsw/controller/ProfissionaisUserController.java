package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/users/profissionais/*")
public class ProfissionaisUserController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

    private ProfissionalDAO dao;
    private CandidaturaDAO candidaturaDao;
    
    @Override
    public void init() {
    	dao = new ProfissionalDAO();
    	candidaturaDao = new CandidaturaDAO();
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
		} else if (!usuario.getPapel().equals("userProfissional")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [userProfissional] tem acesso a essa página");
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
    			case "/insercao":
    				insere(request, response);
    				break;
    			case "/remocao":
    				remove(request, response);
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
		
		Long cpf;
		
		try {
			cpf = Long.parseLong(request.getParameter("cpf"));
		} catch(NumberFormatException nfe) {
			Erro erros = new Erro();
			erros.add("O campo cpf deve ser apenas comoposto por números");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
			rd.forward(request, response);
			return;
		}
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		
		SimpleDateFormat formato;
		java.util.Date nascimento;
		try {
			formato = new SimpleDateFormat("yyyy-MM-dd");
			nascimento = formato.parse(request.getParameter("nascimento"));
		} catch(ParseException pe) {
			try {
				formato = new SimpleDateFormat("yyyy/MM/dd");
				nascimento = formato.parse(request.getParameter("nascimento"));
			} catch(ParseException pe2) {
				Erro erros = new Erro();
				erros.add("Data deve ser inserida como ano/mês/dia ou ano-mês-dia");
				request.setAttribute("mensagens", erros);
				RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
				rd.forward(request, response);
				return;
			}
		}
		Date nasc = new Date(nascimento.getTime());
		Long id = (long) 0;
		Profissional profissional = new Profissional(id, cpf, nome, senha, email, telefone, sexo, nasc);
		
		dao.insert(profissional);
		response.sendRedirect("lista");
    }
    
    
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	
    	Long idUsuario = usuario.getId();
    	
    	List<Candidatura> listaCandidaturas = candidaturaDao.getCandidaturasByPessoa(idUsuario);

    	List<Vaga> listaVagas = getVagaById(listaCandidaturas);
        request.setAttribute("listaVagas", listaVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));

		Profissional profissional = new Profissional(id);
		System.out.println(id);
		dao.delete(profissional);
		response.sendRedirect("lista");
	}

 
    
    protected List<Vaga> getVagaById(List<Candidatura> candidaturas){
    	List<Vaga> vagas = new ArrayList<Vaga>();
    	VagaDAO vagasDao = new VagaDAO();
    	
    	for(Candidatura c : candidaturas) {
    		vagas.add(vagasDao.getVaga(c.getIdVaga()));
    	}
    	
    	return vagas;
    }
	
}
