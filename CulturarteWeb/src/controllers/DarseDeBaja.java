package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EstadoSesion;
import publicador.DtProponente;
import publicador.DtUsuario;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class DarseDeBaja
 */
@WebServlet("/DarseDeBaja")
public class DarseDeBaja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DarseDeBaja() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();

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
    	
    	
    	String nick = usr.getNickname();
    	

		req.getSession().setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
        req.getSession().setAttribute("usr_sesion", null);
        
    	port.borrarProponenteSistema(nick);
    	
    	req.getRequestDispatcher("/WEB-INF/home/inicio.jsp").forward(req, resp);
    	
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
