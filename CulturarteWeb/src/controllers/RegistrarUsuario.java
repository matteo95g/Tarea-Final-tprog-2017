package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import publicador.Publicador;
import publicador.PublicadorService;
import publicador.UsuarioRepetidoException_Exception;

/**
 * Servlet implementation class RegistrarUsuario
 */
@WebServlet("/registrarUsuario")
@MultipartConfig
public class RegistrarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarUsuario() {
        super();
    }
    
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
        
    	
    	String nickname = request.getParameter("NickName");
    	String email = request.getParameter("E-mail");
    	String nombre = request.getParameter("Nombre");
    	String apellido = request.getParameter("Apellido");
    	String contrasenia = request.getParameter("Contrasenia");
    	String fechaNac = request.getParameter("Fecha_de_Nacimiento");
    	
    	String tipoUsr = request.getParameter("optradio");
    	
    	String direccion = request.getParameter("Direccion");
    	String biografia = request.getParameter("Biografia");
    	String web = request.getParameter("Web");
    	
    	if (tipoUsr.equals("Colaborador")) {
    		direccion = "";
    		biografia = "";
    		web = "";
    	}
    	
    	
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("es","ES"));
    	try {
			cal.setTime(sdf.parse(fechaNac));
		} catch (ParseException e) {
			e.printStackTrace();
		} // fecha prevista
    	
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		int anio = cal.get(Calendar.YEAR);
    	
    	
    	InputStream inputStream = null;
    	
    	Part filePart = request.getPart("uploaded");
    	if (filePart != null) {
    		inputStream = filePart.getInputStream();
    	}
    	
    	byte[] fotoBytes = new byte[inputStream.available()];
    	inputStream.read(fotoBytes);
    	
    	if (port.existeUsuarioPorNick(nickname)) {
    		request.setAttribute("EstadoRegistro", "errorRegistroNick");
    	} else if (port.existeUsuarioPorCorreo(email)){
    		request.setAttribute("EstadoRegistro", "errorRegistroCorreo");
    	} else {
    		try {
        		if (filePart != null) {
        			port.agregarUsuario(nickname, nombre, apellido, email, dia, mes, anio, fotoBytes, ".jpg", tipoUsr, direccion, biografia, web, contrasenia);
        		} else {
        			byte[] arrayVacio = new byte[0];        			
        			port.agregarUsuario(nickname, nombre, apellido, email, dia, mes, anio, arrayVacio, "", tipoUsr, direccion, biografia, web, contrasenia);
        		}
        		request.setAttribute("EstadoRegistro", "okRegistro");
    		} catch (UsuarioRepetidoException_Exception e) {
    		}
    	}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/altaUsuario");
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
