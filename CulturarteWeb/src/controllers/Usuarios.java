package controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicador.DtColaborador;
import publicador.DtColecciones;
import publicador.DtProponente;
import publicador.DtUsuario;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class Usuarios
 */
@WebServlet("/usuarios")
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean existeEnListaColab(List<DtColaborador> lst, String nick) {
    	Iterator<DtColaborador> it = lst.iterator();
    	boolean encontre = false;
    	while (it.hasNext() && !encontre) {
    		DtUsuario dtUsr = it.next();
    		if (dtUsr.getNickname().equals(nick)) {
    			encontre = true;
    		}    		
    	}
    	return encontre;    	
    }
    
    private boolean existeEnListaProp(List<DtProponente> lst, String nick) {
    	Iterator<DtProponente> it = lst.iterator();
    	boolean encontre = false;
    	while (it.hasNext() && !encontre) {
    		DtUsuario dtUsr = it.next();
    		if (dtUsr.getNickname().equals(nick)) {
    			encontre = true;
    		}    		
    	}
    	return encontre;    	
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();
        
        DtColecciones dtColab = port.getColaboradores();
        DtColecciones dtProp = port.getProponentes();
        
		String usuario = request.getParameter("usuario");
		DtUsuario usrlog = Home.getUsuarioSesion(request);
		if (usrlog != null) {
	    	request.setAttribute("seguidos", usrlog.getSeguidos());  		    	
		}
		if(usuario == null) {
			
			// no se seteó el usuario (lista todos los usuarios)
    	
	    	request.setAttribute("colaboradores", dtColab.getColaboradores());
	    	request.setAttribute("proponentes", dtProp.getProponentes());						
			request.getRequestDispatcher("/WEB-INF/usuarios/listarUsuarios.jsp").forward(request, response);
				
		} else {
			// ve el perfil de un solo usuario
			if (existeEnListaColab(dtColab.getColaboradores(), usuario)) {
				DtColaborador colab = (DtColaborador) port.getUsuarioPorNick(usuario);
				request.setAttribute("usuario", colab);
				request.getRequestDispatcher("/WEB-INF/usuarios/verPerfilColaborador.jsp").
						forward(request, response);				
			}else if (existeEnListaProp(dtProp.getProponentes(), usuario)) {
				DtProponente propo = (DtProponente) port.getUsuarioPorNick(usuario);
				request.setAttribute("usuario", propo);				
				request.getRequestDispatcher("/WEB-INF/usuarios/verPerfilProponente.jsp").
						forward(request, response);				
			}else {
				//response.sendError(404); // SI PONGO ESO NO ANDA
				request.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
						include(request, response);
				return;				
			}			
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
