package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.DtUsuario;
import publicador.Publicador;
import publicador.PublicadorService;
import publicador.UsuarioNoExisteException_Exception;
import publicador.UsuarioYaEsSeguidorException_Exception;


/**
 * Servlet implementation class SeguirUsr
 */
@WebServlet("/SeguirUsr")
public class SeguirUsr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public SeguirUsr() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, UsuarioYaEsSeguidorException_Exception, UsuarioNoExisteException_Exception {
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
    	
		DtUsuario usr = Home.getUsuarioSesion(req);

		resp.setContentType("text/plain");
		
		if (usr != null) {
			String seguidor = usr.getNickname();
			String accion = req.getParameter("accion");
			String seguido = req.getParameter("seguido");
			if (accion.equals("seguir")) {
				port.seguirUsuario(seguidor, seguido);
			} else {
				port.dejarSeguirUsuario(seguidor, seguido);
			}
		}
	}
    	


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (UsuarioYaEsSeguidorException_Exception | UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (UsuarioYaEsSeguidorException_Exception | UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
