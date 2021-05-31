package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.EmailService;
import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import javax.mail.internet.InternetAddress;


@WebServlet(urlPatterns = "/users/empresas/*")
public class EmpresasUserController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    private EmpresaDAO empresaDao;
    private VagaDAO vagaDao;
    private CandidaturaDAO candidaturaDao;
    private UsuarioDAO usuarioDao;
    private ProfissionalDAO profissionalDao;
    
    @Override
    public void init() {
    	usuarioDao = new UsuarioDAO();
        empresaDao = new EmpresaDAO();
        vagaDao = new VagaDAO();
        candidaturaDao = new CandidaturaDAO();
        profissionalDao = new ProfissionalDAO();
        
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
		} else if (!usuario.getPapel().equals("userEmpresa")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [userEmpresa] tem acesso a essa página");
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
					insere(request,response);
					break;
    			case "/cadastroVaga":
    				apresentaFormCadastro(request,response);
    				break;
    			case "/listarCandidaturas":
    				listaCandidaturas(request, response);
    				break;
    			case "/avaliando":
    				apresentaFormAvaliacao(request, response);
    				break;
    			case "/avaliaCandidatura":
    				avaliaCandidatura(request, response);
    				break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }
    
    private void avaliaCandidatura(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
       	Long idVaga = Long.parseLong(request.getParameter("idVaga"));
    	Long idProfissional = Long.parseLong(request.getParameter("idProfissional"));
    	Usuario usuario = usuarioDao.get(idProfissional);
    	
    	EmailService service = new EmailService();
    	
    	InternetAddress from = new InternetAddress("aa1seedsw1@gmail.com", "Fulano");
    	InternetAddress to = new InternetAddress(usuario.getLogin(), "Beltrano");
    	
    	if(request.getParameter("entrevista") != null) {
    		String link = request.getParameter("link");
    		candidaturaDao.update(idVaga, idProfissional, "ENTREVISTA");
    		
        	String subject = "ENTREVISTA - A11";
        	String body = "Aqui esta o link para a entrevista: " + link;
        	
    		service.send(from, to, subject, body);
    	}
    	else {
    		candidaturaDao.update(idVaga, idProfissional, "NÃO SELECIONADO");
        	String subject = "NÃO SELECIONADO - A11";
        	String body = "Infelizmente você não foi selecionado!";
        	
    		service.send(from, to, subject, body);
    	}
    	response.sendRedirect("lista");
    }
    
    private void listaCandidaturas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Long idvaga = Long.parseLong(request.getParameter("idvaga"));
    	
    	List<Candidatura> listaCandidaturas = candidaturaDao.getCandidaturasByVaga(idvaga);
    	
    	request.setAttribute("listaCandidaturas", listaCandidaturas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/empresa/listaCandidaturas.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormAvaliacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Long idvaga = Long.parseLong(request.getParameter("idvaga"));
    	Long idpessoa = Long.parseLong(request.getParameter("idpessoa"));
    	
    	Candidatura candidatura = candidaturaDao.getCandidatura(idvaga, idpessoa);
    	Profissional profissional = profissionalDao.get(idpessoa);
    	
    	// Informações da candidatura e do profissional.
    	request.setAttribute("candidatura", candidatura);
    	request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/empresa/listaAvaliacaoCandidatura.jsp");
        dispatcher.forward(request, response);
    }
    
	private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		
		SimpleDateFormat formato;
		java.util.Date dateLimite;
		try {
			formato = new SimpleDateFormat("yyyy-MM-dd");
			dateLimite = formato.parse(request.getParameter("datalimite"));
		} catch(ParseException pe) {
			try {
				formato = new SimpleDateFormat("yyyy/MM/dd");
				dateLimite = formato.parse(request.getParameter("datalimite"));
			} catch(ParseException pe2) {
				Erro erros = new Erro();
				erros.add("Data deve ser inserida como ano/mês/dia ou ano-mês-dia");
				request.setAttribute("mensagens", erros);
				RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
				rd.forward(request, response);
				return;
			}
		}
		Date dataLimite = new Date(dateLimite.getTime());
		

		
		float remuneracao = 0;
		try {
			remuneracao = Float.parseFloat(request.getParameter("remuneracao"));
		}catch(NumberFormatException e) {
			Erro erros = new Erro();
			erros.add("O campo de remuneração precisa ser numérico.");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
			rd.forward(request, response);
			return;
		}
		
		String descricao = request.getParameter("descricao");
		Empresa empresa = empresaDao.get(usuario.getId());
		
		Vaga vaga = new Vaga(empresa.getCnpj(), descricao, remuneracao, dataLimite);
		vagaDao.insert(vaga);
		response.sendRedirect("lista");
	}
    
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	
    	List<Vaga> listaVagas = vagaDao.getVagasEmpresa(usuario.getId());
        
        request.setAttribute("listaVagas", listaVagas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/empresa/lista.jsp");
        dispatcher.forward(request, response);
    }
    
	private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/empresa/formulario.jsp");
		dispatcher.forward(request, response);
	}
	
	/*private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		}
		catch (NumberFormatException nfe) {
			Erro erros = new Erro();
			erros.add("O campo cnpj deve ser apenas comoposto por números");
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
		
		Empresa empresa= new Empresa(id, cnpj, nome, senha, email, descricao, cidade);

		dao.update(empresa);
		response.sendRedirect("lista");
	}*/

	
}
