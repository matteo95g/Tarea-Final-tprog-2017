package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import publicador.DtColaboracion;
import publicador.DtColecciones;
import publicador.DtPropuesta;
import publicador.Publicador;
import publicador.PublicadorService;


/**
 * Servlet implementation class Pagar
 */
@WebServlet("/pagar")
public class Pagar extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pagar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DatatypeConfigurationException {
                
        String colab = req.getParameter("id");
    	PublicadorService service = new PublicadorService();
		Publicador port = service.getPublicadorPort();	 	
		if (colab != null){
			DtColaboracion dtCol = port.getColaboracion(Integer.parseInt(colab));
			//tit = dtCol.getPropuesta();
			//monto = dtCol.getMonto();
		}
        String banco = req.getParameter("banco");
        String numero = req.getParameter("numero");
        String titular = req.getParameter("titular");
        String cuenta = req.getParameter("cuenta");
        String cvs = req.getParameter("cvs");
        String fecha = req.getParameter("Fecha_de_Vencimiento");
        String tipo = req.getParameter("tipo");
        
        
        if (colab != null) {
        	DtColaboracion dtCol = port.getColaboracion(Integer.parseInt(colab));
        	Integer monto = dtCol.getMonto();
        	System.out.println(monto);
        	DtColecciones dtColec = port.obtenerPropuestas();
        	List<DtPropuesta> props = dtColec.getPropuestas();
        	
        	if(banco != null){

        		System.out.println(banco);
        		port.agregarTransferencia(Integer.parseInt(colab),monto,banco,titular,cuenta);
        		req.getRequestDispatcher("/home").forward(req,resp);
        	}
        	else if(numero != null){

        		System.out.println(numero);
        		port.agregarTarjeta(Integer.parseInt(colab), monto, tipo, titular, Integer.parseInt(numero), fecha, Integer.parseInt(cvs));
        		req.getRequestDispatcher("/home").forward(req,resp);
        	}
        	else if(titular != null){
        		System.out.println(titular);
        		port.agregarPaypal(Integer.parseInt(colab), monto, titular, cuenta);
    	    	req.getRequestDispatcher("/home").forward(req,resp);
  
        	}else{
        		req.setAttribute("idCol", colab);
        		req.getRequestDispatcher("/WEB-INF/Propuestas/Pagar.jsp").forward(req, resp);;
        	}
        }
        
        
                
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
