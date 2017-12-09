
package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import publicador.Retorno;
import publicador.DtProponente;
import publicador.PropuestaRepetidaException_Exception;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class PropuestaCorrecta
 */
@WebServlet("/propuestaCorrecta")
@MultipartConfig
public class PropuestaCorrecta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int claveRet;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropuestaCorrecta() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PropuestaRepetidaException_Exception {
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
    	
    	String titulo = request.getParameter("Titulo"); //titulo
    	String descripcion = request.getParameter("Descripcion"); //descripcion
    	String lugar = request.getParameter("Lugar"); //lugar
    	
    	String categoria = request.getParameter("categoria"); //categoria
    	
    	String precioText = request.getParameter("Precio_Entrada");
    	int precioEntrada = !precioText.isEmpty() ? Integer.parseInt(precioText) : 0; //precioEntradas
    	
    	String montoText = request.getParameter("Monto_Necesario");
    	int montoNecesario = !montoText.isEmpty() ? Integer.parseInt(montoText) : 0; //montoNecesario
    	
    	String[] retornosText = request.getParameterValues("retorno");
		
		ArrayList<Retorno> retornos = new ArrayList<Retorno>();
    	for (int iter = 0; iter < retornosText.length; iter++) {
    		String retorno = retornosText[iter];  		
    		if (retorno != null) {
    			if (retorno.equals("Entradas")) {
        			retornos.add(Retorno.ENTRADAS);    				
    			} else {
        			retornos.add(Retorno.PORCENTAJE);  
    			}	
    		}
    	}
    	

    	Iterator<Retorno> iterRet = retornos.iterator();
    	if (retornos.size()== 2) {
    		claveRet = 2;
    	} else if (retornos.size()== 1) {
    		if (iterRet.next().equals(Retorno.ENTRADAS)) {
    			claveRet = 0;
    		} else {
    			claveRet = 1;
    		}
    	}
    
    	DtProponente prop = (DtProponente) Home.getUsuarioSesion(request); // proponente   
    	String nick = prop.getNickname();
    	
    	String fechaText = request.getParameter("Fecha_Prevista");
    	
    	

    	
    	InputStream inputStream = null;
    	
    	Part filePart = request.getPart("uploaded");    	
    	if (filePart != null) {
    		inputStream = filePart.getInputStream();
    	}
    	
    	byte[] fotoBytes = new byte[inputStream.available()]; //imagen
    	inputStream.read(fotoBytes);
    	
    	if (filePart != null) {
    		port.addPropuesta(titulo, descripcion, fotoBytes, ".jpg", lugar, fechaText, precioEntrada, montoNecesario, claveRet, categoria, nick);;
    	} else {
    		byte[] arrayVacio = new byte[0];
    		port.addPropuesta(titulo, descripcion, arrayVacio, null, lugar, fechaText, precioEntrada, montoNecesario, claveRet, categoria, nick);;
    	}
   
    	request.getRequestDispatcher("WEB-INF/home/inicio.jsp").forward(request, response);  
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (PropuestaRepetidaException_Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (PropuestaRepetidaException_Exception e) {
			e.printStackTrace();
		}
	}

}
