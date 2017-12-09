package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.DtUsuario;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class Favoritas
 */
@WebServlet("/favoritas")
		public class Favoritas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Favoritas() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {   
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
    	
		DtUsuario usr = Home.getUsuarioSesion(req);
		if (usr != null) {
			String usuario = usr.getNickname();
			String accion = req.getParameter("accion");
			String propuesta = req.getParameter("prop");
			if (accion.equals("fav")) {
				//agrego la prop a fav
				port.agregarFavorita(usuario, propuesta);			
			} else {
				//saco la prop de fav
				port.eliminarFavorita(usuario, propuesta);
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

}
