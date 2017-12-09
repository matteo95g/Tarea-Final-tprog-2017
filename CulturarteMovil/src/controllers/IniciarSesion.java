package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EstadoSesion;
import publicador.DtColaborador;
import publicador.DtProponente;
import publicador.DtUsuario;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/iniciarSesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesion() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
    	
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        boolean recordar = request.getParameter("checkRecordar") != null;
        
        if (port.existeUsuarioPorNick(login)) {
        	DtUsuario usr = port.getUsuarioPorNick(login);
        	try {
				@SuppressWarnings("unused")
				DtProponente prop = (DtProponente) usr;
				request.getRequestDispatcher("WEB-INF/home/errorCol.jsp").forward(request, response);
			} catch (Exception e) {
				DtColaborador colab = (DtColaborador) usr;
	        	if (!colab.getContra().equals(pass)) {
	        		request.getRequestDispatcher("WEB-INF/home/errorPass.jsp").forward(request, response);
	        	} else {
	        		request.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_CORRECTO);
	                request.getSession().setAttribute("usr_sesion", usr.getNickname());
	                if (recordar) {
	                    Cookie cookieSesion = new Cookie("cookieSesion", usr.getNickname());
	                    cookieSesion.setMaxAge(60*60*24);
	                    cookieSesion.setPath("/");
	                    response.addCookie(cookieSesion);
	                }
	        	}
			}
        } else if (port.existeUsuarioPorCorreo(login)) {
        	DtUsuario usr = port.getUsuarioPorCorreo(login);
        	try {
        		@SuppressWarnings("unused")
				DtProponente prop = (DtProponente) usr;
        		request.getRequestDispatcher("WEB-INF/home/errorCol.jsp").forward(request, response);
			} catch (Exception e) {
	        	if (!usr.getContra().equals(pass)) {
	        		request.getRequestDispatcher("WEB-INF/home/errorPass.jsp").forward(request, response);
	        	} else {
	        		request.getSession().setAttribute("estado_sesion", EstadoSesion.LOGIN_CORRECTO);
	                request.getSession().setAttribute("usr_sesion", usr.getNickname());
	                if (recordar) {
	                    Cookie cookieSesion = new Cookie("cookieSesion", usr.getNickname());
	                    cookieSesion.setMaxAge(60*60*24);
	                    cookieSesion.setPath("/");
	                    response.addCookie(cookieSesion);
	                }
	        	}
			}
        } else {
        	request.getRequestDispatcher("WEB-INF/home/errorLogin.jsp").forward(request, response);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home");
        dispatcher.forward(request, response);
    }
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
