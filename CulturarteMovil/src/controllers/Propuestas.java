package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.DtColaborador;
import publicador.DtColecciones;
import publicador.DtPropuesta;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class Propuestas
 */
@WebServlet("/propuestas")
public class Propuestas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Propuestas() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
    	
    	String propuesta = req.getParameter("propuesta");
    	String comentario = req.getParameter("comentario");        
    	String colaborador = req.getParameter("colaborador");

    	DtColecciones dtCol = port.obtenerPropuestas();
    	List<DtPropuesta> props = dtCol.getPropuestas();
    	
    	if (propuesta == null) {	    	
	    	req.setAttribute("propuestas", props);
	    	req.getRequestDispatcher("/WEB-INF/Propuestas/consultaPropuesta.jsp").forward(req,resp);
    	}else if (propuesta != null) {
    		DtPropuesta prop = port.obtenerPropuestaPorTitulo(propuesta);
    		if (prop != null) {
    	    	if (comentario != null) {
    	    		DtPropuesta propu = port.obtenerPropuestaPorTitulo(propuesta);
    	    		DtColaborador colab = (DtColaborador) port.getUsuarioPorNick(colaborador);
    	    		port.addComentario(propu.getTitulo(), comentario, colab.getNickname());
    	    		req.setAttribute("propuesta", prop);
    	    		req.getRequestDispatcher("/WEB-INF/Propuestas/comentarios.jsp").
    				forward(req, resp);
    	    	}
    	    	else{
	    		req.setAttribute("propuesta", prop);
	    		req.getRequestDispatcher("/WEB-INF/Propuestas/comentarios.jsp").
				forward(req, resp);
    	    	}
    		}else {
    			req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
				include(req, resp);
    			return;	
    		}
    	}
    	
   
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
