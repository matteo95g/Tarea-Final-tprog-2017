package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EstadoSesion;
import publicador.DtUsuario;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class Home
 */

@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public static void initSession(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("estado_sesion") == null) {
			boolean sesionIniciada = false;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("cookieSesion")) {
						if (!cookies[i].getValue().equals("")) {
							sesionIniciada = true;
							String nick = cookies[i].getValue();
							session.setAttribute("estado_sesion", EstadoSesion.LOGIN_CORRECTO);
							session.setAttribute("usr_sesion", nick);
						}
					}
				}
			}
			if (!sesionIniciada) {
				session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
				session.setAttribute("usr_sesion", null);
			}
		}
	}

	public static EstadoSesion getEstadoSesion(HttpServletRequest request) {
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}
	
	public static DtUsuario getUsuarioSesion(HttpServletRequest request) {
		
		PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("usr_sesion") == null) {
			return null;
		} else {
			return port.getUsuarioPorNick((String)session.getAttribute("usr_sesion"));
		}
		
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		initSession(req);
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
