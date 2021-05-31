package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.CandidaturaDAO;
import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.util.Erro;

import static br.ufscar.dc.dsw.Constants.MAX_FILE_SIZE;
import static br.ufscar.dc.dsw.Constants.MAX_REQUEST_SIZE;
import static br.ufscar.dc.dsw.Constants.MEMORY_THRESHOLD;
import static br.ufscar.dc.dsw.Constants.UPLOAD_DIRECTORY;

import java.io.File;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
    			case "/aplicarVaga":
    				listaVagas(request, response);
    				break;
    			case "/candidatar":
    				apresentaFormFile(request, response);
    				break;
    			case "/insereCandidatura":
    				insere(request, response);
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
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Long idUsuario = usuario.getId();
    	Vaga vaga = (Vaga) request.getSession().getAttribute("ultimaVagaCandidatada");
		String fileName = null;
		
		if (ServletFileUpload.isMultipartContent(request)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				List<FileItem> formItems = upload.parseRequest(request);

				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							fileName = new File(item.getName()).getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							item.write(storeFile);
							request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
						}
					}
				}
			} catch (Exception ex) {
				Erro erros = new Erro();
				erros.add("Ocorreu um erro ao enviar o email");
				request.setAttribute("mensagens", erros);
				RequestDispatcher rd = request.getRequestDispatcher("/erros.jsp");
				rd.forward(request, response);
				return;
			}
			CandidaturaDAO candidaturaDao = new CandidaturaDAO();
			Candidatura candidatura = new Candidatura(vaga.getIdvaga(), idUsuario, fileName);
			candidaturaDao.insert(candidatura);
			response.sendRedirect("lista");
		}
    }
    
    
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Long idUsuario = usuario.getId();
    	
    	List<Candidatura> listaCandidaturas = candidaturaDao.getCandidaturasByPessoa(idUsuario);
    	
    	request.setAttribute("listaCandidaturas", listaCandidaturas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));

		Profissional profissional = new Profissional(id);

		dao.delete(profissional);
		response.sendRedirect("lista");
	}

    private void listaVagas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	Long idUsuario = usuario.getId();
    	
    	VagaDAO vagasDao = new VagaDAO();
    	
    	List<Vaga> vagasAbertas = vagasDao.getAllAberta();
    	List<Candidatura> candidaturas = candidaturaDao.getCandidaturasByPessoa(idUsuario);
    	
    	System.out.println(vagasAbertas.size());
    	
    	for(Candidatura c : candidaturas) {
    		vagasAbertas.removeIf(n -> (n.getIdvaga() == c.getIdvaga()));
    	}
    	
    	
    	request.setAttribute("listaVagas", vagasAbertas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/profissional/aplicarVaga.jsp");
        dispatcher.forward(request, response);
    	
    }
    
	private void apresentaFormFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idVaga = Long.parseLong(request.getParameter("id"));
		VagaDAO vagasDao = new VagaDAO();
		Vaga vaga = vagasDao.getVaga(idVaga);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/user/profissional/formularioFile.jsp");
		request.getSession().setAttribute("ultimaVagaCandidatada", vaga);
		request.setAttribute("vaga", vaga);
		dispatcher.forward(request, response);
	}
    
}
