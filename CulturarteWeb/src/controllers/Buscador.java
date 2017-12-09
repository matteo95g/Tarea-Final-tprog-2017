package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.DtColecciones;
import publicador.DtPropuesta;
import publicador.Publicador;
import publicador.PublicadorService;


/**
 * Servlet implementation class Buscador
 */
@WebServlet("/Buscador")
public class Buscador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscador() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
        
    	
    	String info = req.getParameter("info");
    	String propuesta = req.getParameter("propuesta");
    	String nick = req.getParameter("nick");
    	String correo = req.getParameter("correo");
    	
    	//PARA LA SUGERENCIA DE LOS NICKNAME    	
    	if (nick != null) {					
			resp.setContentType("text/plain");
			if(!port.nickLibre(nick)) {
				final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.-";
			    final int N = alphabet.length();
			    Random r = new Random();
			    Random r1 = new Random();
			    Random r2 = new Random();
			    
			    char s1 = alphabet.charAt(r.nextInt(N));
			    char s2 = alphabet.charAt(r1.nextInt(N));
			    char s3 = alphabet.charAt(r2.nextInt(N));
			    
			    String sugerencia = String.valueOf(s1) + String.valueOf(s2) + String.valueOf(s3);
			    
				resp.getWriter().write("<div class=\"alert alert-danger\" role=\"alert\">\n" + 
						"  <span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n" + 
						"  <span class=\"sr-only\">Error:</span>\n" + 
						"  Ese nickname no se encuentra disponible. Que tal " + nick + sugerencia +"?\n" + 
						"</div>");
			}else
				resp.getWriter().write("<div class=\"alert alert-success\" role=\"alert\">\n" + 
						"  <span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"></span>\n" + 
						"  <span class=\"sr-only\"></span>\n" + 
						"  Ese nickname se encuentra disponible!\n" + 
						"</div>");

        }else if (correo != null) {
        	try {
				port.getUsuarioPorCorreo(correo);
				resp.getWriter().write("<div class=\"alert alert-danger\" role=\"alert\">\n" + 
						"  <span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>\n" + 
						"  <span class=\"sr-only\">Error:</span>\n" + 
						"  Ese email ya esta registrado. Olvidaste tu password?\n" + 
						"</div>");
			} catch (Exception e) {
				System.out.println();
			}
        	
        }else if ((info!=null)&&(info!="")){
    		req.setAttribute("info", info);
    		req.setAttribute("propuesta", null);
    		req.getRequestDispatcher("/WEB-INF/Buscador/listarProp.jsp").forward(req, resp);
    	} else if (propuesta != null) {
    		req.setAttribute("propuesta", propuesta);
    		req.getRequestDispatcher("/propuestas").forward(req, resp);
    	} else {
    		req.setAttribute("propuesta", null);
    		DtColecciones dtProps = port.obtenerPropuestas();
    		List<DtPropuesta> props = dtProps.getPropuestas();
    		req.setAttribute("propuestas", props);
    		req.getRequestDispatcher("/WEB-INF/Propuestas/consultaPropuesta.jsp").
			forward(req, resp);
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
