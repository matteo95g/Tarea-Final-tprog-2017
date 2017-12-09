package controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.Publicador;
import publicador.PublicadorService;

@SuppressWarnings("serial")
@WebServlet("/imagenes")
public class FileServlet extends HttpServlet {
	
	public FileServlet() {
		super();
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
        
        String id = (String) request.getParameter("id");
        
        byte[] img = null;
        
        PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
        
        try {

            img = port.getFile(id);
            response.setContentType("image/jpg");
            response.setContentLength((int) img.length);
            OutputStream out = response.getOutputStream();
            out.write(img);
            out.close();
            
        } catch (Exception ex) {

        }
        
        
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}