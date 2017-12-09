package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.DtColecciones;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class RankingUsuarios
 */
@WebServlet("/RankingUsuarios")
public class RankingUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
        DtColecciones dtColab = port.getColaboradores();
        DtColecciones dtProp = port.getProponentes();     
        request.setAttribute("colaboradores", dtColab.getColaboradores());
    	request.setAttribute("proponentes", dtProp.getProponentes());
    	request.getRequestDispatcher("/WEB-INF/usuarios/ranking.jsp").
		forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
