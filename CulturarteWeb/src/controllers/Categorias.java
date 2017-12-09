package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.DtCategoria;
import publicador.DtColecciones;
import publicador.DtProponente;
import publicador.DtUsuario;
import publicador.Publicador;
import publicador.PublicadorService;


/**
 * Servlet implementation class AltaPropuesta
 */
@WebServlet("/categorias")
public class Categorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categorias() {
        super();
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();

    	String categorias = req.getParameter("categorias");

    	DtColecciones colCats = port.getCategorias();
    	
    	List<DtCategoria> lstCats = colCats.getCategorias();
    	if (categorias == null) {
    		DtUsuario usr = Home.getUsuarioSesion(req);

        	if (usr == null) {
        		req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
    			include(req, resp);
    			return;	
        	} else {
        		try {
        			@SuppressWarnings("unused")
    				DtProponente prop = (DtProponente) usr;
        		} catch (Exception ex) {
        			req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
        			include(req, resp);
        			return;
        		}
        	}
    		req.setAttribute("categorias", lstCats);
    		req.getRequestDispatcher("/WEB-INF/Propuestas/AltaPropuesta.jsp").forward(req, resp);
    	}
    	else if (categorias != null) {
    		req.setAttribute("categorias", lstCats);
    		req.getRequestDispatcher("/WEB-INF/Propuestas/propuestasPorCat.jsp").forward(req, resp);
    	}
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
		processRequest(request, response);
	}

}
