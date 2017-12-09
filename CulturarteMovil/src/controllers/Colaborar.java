package controllers;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import publicador.EstadoPropuesta;
import publicador.Retorno;
import publicador.UsuarioYaColaboraException_Exception;
import publicador.DtColecciones;
import publicador.DtFechaCambio;
import publicador.DtPropuesta;
import publicador.Publicador;
import publicador.PublicadorService;


/**
 * Servlet implementation class Colaborar
 */
@WebServlet("/Colaborar")
public class Colaborar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Colaborar() {
        super();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, DatatypeConfigurationException, UsuarioYaColaboraException_Exception{
  		
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
        DtColecciones dtProps = port.obtenerPropuestas();
		List<DtPropuesta> props = dtProps.getPropuestas();
		
        
    	String titulo =req.getParameter("titulo");
    	String monto= req.getParameter("Monto");
    	String ret =req.getParameter("retorno");
    	System.out.println(monto);
    	System.out.println(ret);
    	System.out.println(titulo);
    	DtPropuesta propu=null;
    	
    	if( titulo!= null){
    		for (int i = 0; i < props.size(); i++) {
    			DtPropuesta prop = props.get(i);
    			if (prop.getTitulo().equals(titulo)){
    				propu = prop;
    			}
    		}
    		if ((monto!=null)&&(monto!="")){
        		
        		Retorno retor;
        		if (ret=="Entradas")
        			retor= Retorno.ENTRADAS;
        		else
        			retor= Retorno.PORCENTAJE;
    			
        			Integer monto2 = (!monto.isEmpty() ? Integer.parseInt(monto) : 0);
    				GregorianCalendar ahora = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    				XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora);
    				port.agregarColaboracion(Home.getUsuarioSesion(req).getNickname(), propu.getTitulo(), monto2.intValue(), fecha,retor);
    				if(propu.getEstado().getEstado()== EstadoPropuesta.PUBLICADA) {
        				//propu.agregarEstadoAnterior(propu.getEstado());
        				DtFechaCambio nuevo = new DtFechaCambio();
        				GregorianCalendar ahora2 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        				XMLGregorianCalendar fecha2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora2);
        				nuevo.setFecha(fecha2);
        				nuevo.setEstado(EstadoPropuesta.EN_FINANCIACION);
        				port.setEstado(propu,nuevo);
        			}        		
    				req.setAttribute("propuesta", null);    				
    				req.getRequestDispatcher("home").forward(req, resp); 						
        	}
    		else{
        			req.setAttribute("propuesta", propu);
        			req.getRequestDispatcher("/WEB-INF/Propuestas/colaboracion.jsp").forward(req, resp);

    		}
    	}
    	
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			processRequest(request,response);
		} catch (UsuarioYaColaboraException_Exception e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (UsuarioYaColaboraException_Exception e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

}