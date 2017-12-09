package controllers;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class PropuestaPorCat
 */
@WebServlet("/PropuestaPorCat")
public class PropuestaPorCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropuestaPorCat() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
        
        DtColecciones colProps = port.obtenerPropuestas();
        List<DtPropuesta> props = colProps.getPropuestas();
        
    	req.setAttribute("propuestas", props);
    	req.getRequestDispatcher("/WEB-INF/Propuestas/propuestasPorCat.jsp").forward(req,resp);
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
