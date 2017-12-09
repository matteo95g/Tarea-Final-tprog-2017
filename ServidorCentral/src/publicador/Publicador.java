package publicador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import controladores.Configuracion;
import datatypes.DtCategoria;
import datatypes.DtColaboracion;
import datatypes.DtColaborador;
import datatypes.DtColecciones;
import datatypes.DtComentario;
import datatypes.DtFechaCambio;
import datatypes.DtPago;
import datatypes.DtPaypal;
import datatypes.DtProponente;
import datatypes.DtPropuesta;
import datatypes.DtTarjeta;
import datatypes.DtTransferencia;
import datatypes.DtUsuario;
import datatypes.Retorno;
import excepciones.CategoriaRepetidaException;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.UsuarioYaColaboraException;
import excepciones.UsuarioYaEsSeguidorException;
import interfaces.Fabrica;
import interfaces.IControladorCategoria;
import interfaces.IControladorColaboracion;
import interfaces.IControladorPropuesta;
import interfaces.IControladorUsuario;
import logica.Categoria;
import logica.Colaboracion;
import logica.Colaborador;
import logica.Comentario;
import logica.FechaCambio;
import logica.Pago;
import logica.Paypal;
import logica.Proponente;
import logica.Propuesta;
import logica.Tarjeta;
import logica.Transferencia;
import logica.Usuario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
	
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorCategoria iContCat = fabrica.getIControladorCategoria();
	private IControladorUsuario iContUsr = fabrica.getIControladorUsuario();
	private IControladorPropuesta iContPro = fabrica.getIControladorPropuesta();
	private IControladorColaboracion iContCol = fabrica.getIControladorColaboracion();

    private Endpoint endpoint = null;
    
    //Constructor
    public Publicador(){}
    
    @WebMethod(exclude = true)
    public void publicar(){
    	Properties defaultProps = new Properties();
    	try (FileInputStream in = new FileInputStream("culturarte.properties")) {
            defaultProps.load(in);
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //endpoint = Endpoint.publish("http://localhost:3050/culturarte", this);
        endpoint = Endpoint.publish("http://" + defaultProps.getProperty("maquina") + ":" + defaultProps.getProperty("puerto") +"/culturarte", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    
    // ================= CATEGORIA TO DTCATEGORIA ================== //

    private DtCategoria getDtCategoria(Categoria cat) {
		DtCategoria dtCat= new DtCategoria(cat.getNombre());
		if (cat.getHijas() != null) {
			ArrayList<DtCategoria> listHijas= new ArrayList<DtCategoria>();
			Iterator<Categoria> iter = cat.getHijas().iterator();
			while(iter.hasNext()){
				Categoria categHija = iter.next();
				DtCategoria dtCategHija = getDtCategoria(categHija);
				listHijas.add(dtCategHija);
			}
			dtCat.setHijas(listHijas);
		}
		return dtCat;
	}


    
    private ArrayList<DtCategoria> getListDtCategoria(List<Categoria> lst) {
    	ArrayList<DtCategoria> resul = new ArrayList<DtCategoria>();
    	for (int i = 0; i < lst.size(); i++) {
    		DtCategoria dtCateg = getDtCategoria(lst.get(i));
    		resul.add(dtCateg);
    	}
    	return resul;    	
    }
    
    // ================================================================ //	

    // ================= FECHACAMB TO DTFECHACAMB ================== //
    
    private DtFechaCambio getDtFechaCambio(FechaCambio fech){
    	DtFechaCambio dtFech = new DtFechaCambio(fech.getFecha(),fech.getEstado());
    	return dtFech;    	
    }
    
    private ArrayList<DtFechaCambio> getListDtFechaCambio(List<FechaCambio> lst){
    	ArrayList<DtFechaCambio> resul = new ArrayList<DtFechaCambio>();
    	for (int i = 0; i < lst.size(); i++) {
    		DtFechaCambio dtFechaCamb = getDtFechaCambio(lst.get(i));
    		resul.add(dtFechaCamb);
    	}
    	return resul;    	
    }
    
    // ================================================================ //	


    // ================= COLABORACION TO DTCOLABORACION ================== //

    private DtColaboracion getDtColaboracion(Colaboracion col){		
    	DtColaboracion dtCol= new DtColaboracion(col.getId(),col.getMonto(),col.getFechaColaboracion(),
				col.getRetornoElegido(),col.getPropuesta().getTitulo(),col.getUsuario().getNickname());
    	dtCol.setPaga(col.isPaga());
    	if (col.isPaga()){
			Pago pago = col.getPago();
			try {
				Transferencia trans = (Transferencia) pago;
				DtTransferencia dtTrans = new DtTransferencia(trans.getMonto(), trans.getBanco(), trans.getTitular(), trans.getCuenta());
				dtCol.setPago(dtTrans);
			} catch (Exception e) {
				try {
					Paypal ppal = (Paypal) pago;
					DtPaypal dtPpal = new DtPaypal(ppal.getMonto(), ppal.getTitular(), ppal.getCuenta());
					dtCol.setPago(dtPpal);
				}catch (Exception j) {
					Tarjeta tga = (Tarjeta) pago;
					DtTarjeta dtTga = new DtTarjeta(tga.getMonto(), tga.getTipo(), tga.getTitular(), tga.getNumero(), tga.getFechaVencimiento(), tga.getCvc());
					dtCol.setPago(dtTga);
				}
			}
			
		}   	
		
		return dtCol;
	}

    /*private ArrayList<DtColaboracion> mapToListColaboracion(Map<Integer, Colaboracion> colaboraciones){
		ArrayList<DtColaboracion> dtColabs= new ArrayList<DtColaboracion>();
		ArrayList<Colaboracion> colabs= new ArrayList<Colaboracion>(colaboraciones.values());
		Iterator<Colaboracion> it = colabs.iterator();
		while(it.hasNext()){
			dtColabs.add(getDtColaboracion(it.next()));
		}
		return dtColabs;
	}*/
    
    // ================================================================ //
    
    // ================== COMENTARIO TO DTCOMENTARIO ================== //
    
    
    private DtComentario getDtComentario(Comentario com){
    	DtComentario dtComen= new DtComentario(com.getIdComentario(),com.getComentario(),com.getFecha(),com.getColaborador().getNickname());
    	return dtComen;
    }

    /*private ArrayList<DtComentario> mapToListComentario(Map<String, Comentario> comentarios){
		ArrayList<DtComentario> dtComents= new ArrayList<DtComentario>();
		ArrayList<Comentario> coments= new ArrayList<Comentario>(comentarios.values());
		Iterator<Comentario> it = coments.iterator();
		while(it.hasNext()){
			dtComents.add(getDtComentario(it.next()));
		}
		return dtComents;
	}*/
    
    // ================================================================ //
    
    // ================== COLABORADOR TO DTCOLABORADOR ================== //

    private ArrayList<DtColaborador> mapToListColaborador(Map<String, Colaborador> colaboradores){
		ArrayList<DtColaborador> dtColabs= new ArrayList<DtColaborador>();
		ArrayList<Colaborador> colabs= new ArrayList<Colaborador>(colaboradores.values());
		Iterator<Colaborador> it = colabs.iterator();
		while(it.hasNext()){
			dtColabs.add(getDtColaborador(it.next()));
		}
		return dtColabs;
	}
    
    private DtColaborador getDtColaborador(Colaborador col){
		DtColaborador dtCol= new DtColaborador();
		dtCol.setNickname(col.getNickname());
		dtCol.setNombre(col.getNombre());
		dtCol.setApellido(col.getApellido());
		dtCol.setContra(col.getContra());
		dtCol.setCorreoElectronico(col.getCorreoElectronico());
		dtCol.setFechaNacimiento(col.getFechaNacimiento());
		dtCol.setSeguidores(new ArrayList<String>(col.getSeguidores().keySet()));
		dtCol.setSeguidos(new ArrayList<String>(col.getSeguidos().keySet()));
		if (col.getImagen() == null) {
			dtCol.setImagen("");
		} else {
			dtCol.setImagen(col.getImagen());
		}
		dtCol.setFavoritas(new ArrayList<String>(col.getFavoritas().keySet()));	
		dtCol.setColaboraciones(new ArrayList<Integer>(col.getColaboraciones().keySet()));
		return dtCol;
	}
	
	// ================================================================ //
   
	// ===================== PROPONENTE TO DTPROPONENTE ===================== //

    private DtProponente getDtProponente(Proponente pro){
		DtProponente dtPro= new DtProponente();
		dtPro.setNickname(pro.getNickname());
		dtPro.setNombre(pro.getNombre());
		dtPro.setApellido(pro.getApellido());
		dtPro.setContra(pro.getContra());
		dtPro.setCorreoElectronico(pro.getCorreoElectronico());
		dtPro.setFechaNacimiento(pro.getFechaNacimiento());
		dtPro.setSeguidores(new ArrayList<String>(pro.getSeguidores().keySet()));
		dtPro.setSeguidos(new ArrayList<String>(pro.getSeguidos().keySet()));
		if (pro.getImagen() == null) {
			dtPro.setImagen("");
		} else {
			dtPro.setImagen(pro.getImagen());
		}
		if (pro.getBiografia() == null) {
			dtPro.setBiografia("");
		} else {
			dtPro.setBiografia(pro.getBiografia());
		}
		if (pro.getSitioWeb() == null) {
			dtPro.setSitioWeb("");
		} else {
			dtPro.setSitioWeb(pro.getSitioWeb());
		}
		dtPro.setFavoritas(new ArrayList<String>(pro.getFavoritas().keySet()));
		dtPro.setDireccion(pro.getDireccion());
		dtPro.setPropuestas(new ArrayList<String>(pro.getPropuestas().keySet()));
		return dtPro;
	}
	
    private ArrayList<DtProponente> mapToListProponente(Map<String, Proponente> proponentes){
		ArrayList<DtProponente> dtProps = new ArrayList<DtProponente>();
		ArrayList<Proponente> props = new ArrayList<Proponente>(proponentes.values());
		Iterator<Proponente> it = props.iterator();
		while(it.hasNext()){
			dtProps.add(getDtProponente(it.next()));
		}
		return dtProps;
	}
	
    // ===================== USUARIO TO DTUSUARIO ===================== //

    /*private DtUsuario getDtUsuario(Usuario usr) {
		DtUsuario dtUsr = new DtUsuario();
		dtUsr.setNickname(usr.getNickname());
		dtUsr.setNombre(usr.getNombre());
		dtUsr.setApellido(usr.getApellido());
		dtUsr.setContra(usr.getContra());
		dtUsr.setCorreoElectronico(usr.getCorreoElectronico());
		dtUsr.setFechaNacimiento(usr.getFechaNacimiento());
		dtUsr.setSeguidores(new ArrayList<String>(usr.getSeguidores().keySet()));
		dtUsr.setSeguidos(new ArrayList<String>(usr.getSeguidos().keySet()));
		if (usr.getImagen() == null) {
			dtUsr.setImagen("");
		} else {
			dtUsr.setImagen(usr.getImagen());
		}
		dtUsr.setFavoritas(new ArrayList<String>(usr.getFavoritas().keySet()));		
		return dtUsr;
	}*/

    /*private ArrayList<DtUsuario> mapToListUsuario(Map<String,Usuario> mapa) {
		ArrayList<DtUsuario> listDtUsr = new ArrayList<DtUsuario>();
		List<Usuario> lstUsr = new ArrayList<Usuario>(mapa.values());
		for (int i = 0; i < lstUsr.size(); i++) {
			Usuario usrs = lstUsr.get(i);
			DtUsuario dtUsr = getDtUsuario(usrs);
			listDtUsr.add(dtUsr);
		}		
		return listDtUsr;
	}*/
    
    // ================================================================ //
    

    
    // =================== PROPUESTA TO DTPROPUESTA =================== // 
    
    /*private ArrayList<DtUsuario> toDtUsuario(Map<String,Usuario> mapa){
    	ArrayList<DtUsuario> resul = new ArrayList<DtUsuario>();
    	ArrayList<Usuario> lstUsr = new ArrayList<Usuario>(mapa.values());
    	for (int i = 0; i < lstUsr.size(); i++) {
    		try {
    			Proponente prop = (Proponente) lstUsr.get(i);
    			DtProponente dtProp = getDtProponente(prop);
    			resul.add(dtProp);    			
    		}catch (Exception a) {
    			Colaborador colab = (Colaborador) lstUsr.get(i);
    			DtColaborador dtColab = getDtColaborador(colab);
    			resul.add(dtColab);
    		}
    	}
    	return resul;    	
    }*/

    
    /*private ArrayList<DtColaborador> toDtColaborador(Map<String,Colaborador> mapa){
    	ArrayList<DtColaborador> resul = new ArrayList<DtColaborador>();
    	ArrayList<Colaborador> lstColab = new ArrayList<Colaborador>(mapa.values());
    	for (int i = 0; i < lstColab.size(); i++) {
			Colaborador colab = lstColab.get(i);
			DtColaborador dtColab = getDtColaborador(colab);
			resul.add(dtColab);
    	}
    	return resul;    	
    }*/
    
    /*private ArrayList<DtProponente> toDtProponente(Map<String,Proponente> mapa){
    	ArrayList<DtProponente> resul = new ArrayList<DtProponente>();
    	ArrayList<Proponente> lstProp = new ArrayList<Proponente>(mapa.values());
    	for (int i = 0; i < lstProp.size(); i++) {
			Proponente prop = lstProp.get(i);
			DtProponente dtProp = getDtProponente(prop);
			resul.add(dtProp);
    	}
    	return resul;    	
    }*/

    
    private DtPropuesta getDtPropuesta(Propuesta propu) {
		DtPropuesta dtProp = new DtPropuesta();
		dtProp.setTitulo(propu.getTitulo());
		dtProp.setDescripcion(propu.getDescripcion());
		if (propu.getImagen() == null) {
			dtProp.setImagen("");
		} else {
			dtProp.setImagen(propu.getImagen());
		}
		dtProp.setLugar(propu.getLugar());
		dtProp.setFechaRealizacion(propu.getFechaRealizacion());
		dtProp.setPrecioEntrada(propu.getPrecioEntrada());
		dtProp.setMontoNecesario(propu.getMontoNecesario());
		dtProp.setRetornos(propu.getRetornos());
		dtProp.setCategoria(propu.getCategoria().getNombre());
		dtProp.setProponente(propu.getProponente().getNickname());
		dtProp.setFechaIngreso(propu.getFechaIngreso());
		dtProp.setMontoActual(propu.getMontoActual());
		if (propu.getSeguidores() != null)
			dtProp.setSeguidores(new ArrayList<String>(propu.getSeguidores().keySet()));
		dtProp.setEstadosAnteriores(getListDtFechaCambio(propu.getEstadosAnteriores()));
		dtProp.setEstado(getDtFechaCambio(propu.getEstado()));
		dtProp.setColaboradores(new ArrayList<String>(propu.getColaboradores().keySet()));
		dtProp.setColaboraciones(new ArrayList<Integer>(propu.getColaboraciones().keySet()));
		dtProp.setComentarios(new ArrayList<String>(propu.getComentarios().keySet()));
		return dtProp;
	}

    private ArrayList<DtPropuesta> mapToListPropuesta(Map<String, Propuesta> propus) {
    	ArrayList<DtPropuesta> listDtProp = new ArrayList<DtPropuesta>();
		List<Propuesta> lstProp = new ArrayList<Propuesta>(propus.values());
		for (int i = 0; i < lstProp.size(); i++) {
			Propuesta prop = lstProp.get(i);
			DtPropuesta dtProp = getDtPropuesta(prop);
			listDtProp.add(dtProp);
		}		
		return listDtProp;    	
    }    
    
    // ================================================================ //

    
    // OPERACIONES QUE QUIERO PUBLICAR
 // ===================== PAGOS===================== //
    @WebMethod
    public void agregarTransferencia(Integer idColab,int monto, String banco, String titular, String cuenta ){
    	Transferencia trans = new Transferencia(monto,banco,titular,cuenta);
    	Colaboracion colab = iContCol.getColaboracion(idColab);
    	colab.setPago(trans);
    	colab.setPaga(true);
    }
    @WebMethod
    public void agregarPaypal(Integer idColab,int monto, String titular, String cuenta ){
    	Paypal pay = new Paypal(monto,titular,cuenta);
    	Colaboracion colab = iContCol.getColaboracion(idColab);
    	colab.setPago(pay);
    	colab.setPaga(true);
    }
    @WebMethod
    public void agregarTarjeta(Integer idColab,int monto, String tipo, String titular, int numero, String fechaVen, int cvc){
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("es", "ES"));
    	try {
    		cal.setTime(sdf.parse(fechaVen));
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	Tarjeta tar= new Tarjeta(monto,tipo,titular,numero,cal,cvc);
    	Colaboracion colab = iContCol.getColaboracion(idColab);
    	colab.setPago(tar);
    	colab.setPaga(true);
    }
    
    @WebMethod
    public DtPaypal DtPaypal() {
		return new DtPaypal(0, "", "");

	}
    @WebMethod
    public DtTransferencia DtTransferencia(){
    	return new DtTransferencia(0, "", "", "");
    }
    
    
    @WebMethod
    public DtTarjeta DtTarjeta(){
    	return new DtTarjeta(0, "", "", 0, Calendar.getInstance(), 0);
    }
    
    
    // ===================== CATEGORIAS ===================== //
    
    
    
    @WebMethod
    public void agregarCategoria(String nombre, String padre) throws CategoriaRepetidaException{
    	iContCat.agregarCategoria(nombre, padre);
    }
    
    @WebMethod
    public DtColecciones getCategorias(){
    	ArrayList<DtCategoria> lstDtCat = getListDtCategoria(iContCat.getCategorias());
    	//ArrayList<DtCategoria> lstDtCat = new ArrayList<DtCategoria>();
    	DtColecciones cols = new DtColecciones();
    	cols.setCategorias(lstDtCat);
    	return cols;
    }
    
    @WebMethod
    public Categoria getCategoria(String nombre) {
    	return iContCat.getCategoria(nombre);
    }
    
    // ===================== USUARIOS ===================== //
    
    @WebMethod
    public void agregarUsuario(String nick, String nombre, String apellido, String correo, int dia, int mes, int anio,
			byte[] imagen, String extImg, String perfil, String direccion, String biografia, String web, String contra) throws UsuarioRepetidoException {
    	if (extImg.equals("")) {
    		extImg = null;
    	}
    	iContUsr.agregarUsuario(nick, nombre, apellido, correo, dia, mes, anio, imagen, extImg, perfil, direccion, biografia, web, contra);
    }
    
    @WebMethod
    public boolean nickLibre(String nick) {
    	return iContUsr.nickLibre(nick);
    }
    
    
    @WebMethod
    public DtColecciones getProponentes() {
    	ArrayList<DtProponente> lstDtProp = mapToListProponente(iContUsr.getProponentes());
    	DtColecciones cols = new DtColecciones();
    	cols.setProponentes(lstDtProp);
    	return cols;
    }
    
    @WebMethod
    public DtColecciones getColaboradores() {
    	ArrayList<DtColaborador> lstDtCol = mapToListColaborador(iContUsr.getColaboradores());
    	DtColecciones cols = new DtColecciones();
    	cols.setColaboradores(lstDtCol);
    	return cols;
    }
    
    @WebMethod
    public DtUsuario getUsuarioPorNick(String nick) {
    	Usuario user = iContUsr.getUsuarioPorNick(nick);
    	try {
			Proponente userProp = (Proponente) user;
			DtProponente dtProp = getDtProponente(userProp);
			return dtProp;
		} catch (Exception e) {
			Colaborador userCol = (Colaborador) user;
			DtColaborador dtCol = getDtColaborador(userCol);
			return dtCol;
		}
    }
	
    @WebMethod
	public DtUsuario getUsuarioPorCorreo(String correo) {
    	Usuario user = iContUsr.getUsuarioPorCorreo(correo);
    	try {
			Proponente userProp = (Proponente) user;
			DtProponente dtProp = getDtProponente(userProp);
			return dtProp;
		} catch (Exception e) {
			Colaborador userCol = (Colaborador) user;
			DtColaborador dtCol = getDtColaborador(userCol);
			return dtCol;
		}
	}
    
    @WebMethod
    public boolean existeUsuarioPorNick(String nick) {
    	return iContUsr.existeUsuarioPorNick(nick);
    }
	
    @WebMethod
	public boolean existeUsuarioPorCorreo(String correo) {
		return iContUsr.existeUsuarioPorCorreo(correo);
	}
    
    @WebMethod
    public void agregarRegistro(String dirIP, String dirURL, String navegador, String sistOP) {
    	iContUsr.agregarRegistro(dirIP, dirURL, navegador, sistOP);
    }
    
    @WebMethod
    public void seguirUsuario(String seguidor, String seguido) throws UsuarioYaEsSeguidorException, UsuarioNoExisteException {
    	iContUsr.seguirUsuario(seguidor, seguido);
    }
    
    @WebMethod
    public void dejarSeguirUsuario(String seguidor, String seguido) throws UsuarioNoExisteException {
    	iContUsr.dejarSeguirUsuario(seguidor, seguido);
    }
    
    @WebMethod
    public void borrarProponenteSistema(String nickname) {
    	iContUsr.borrarProponente(nickname);
    }
  
    
    // ===================== PROPUESTAS ===================== //
    
    @WebMethod
    public void addPropuesta(String titulo, String descripcion, byte[] imagen, String extImg, String lugar, String fechaText, Integer precioEntrada,
    		Integer montoNecesario, Integer claveRet, String categoria, String nickProponente) throws PropuestaRepetidaException {
    	if (extImg.equals("")) {
    		extImg = null;
    	}
    	List<Retorno> retornos = new ArrayList<Retorno>();
    	if (claveRet == 0) {
    		retornos.add(Retorno.Entradas);
    	} else if (claveRet == 1) {
    		retornos.add(Retorno.Porcentaje);
    	} else if (claveRet==2) {
    		retornos.add(Retorno.Entradas);
    		retornos.add(Retorno.Porcentaje);
    	}
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("es", "ES"));
    	try {
    		cal.setTime(sdf.parse(fechaText));
    	} catch (ParseException e) {
    		e.printStackTrace();
    	} // fecha prevista
    	Categoria categoriaFinal = iContCat.getCategoria(categoria);
    	Proponente proponente = (Proponente) iContUsr.getUsuarioPorNick(nickProponente);
    	iContPro.addPropuesta(titulo, descripcion, imagen, extImg, lugar, cal, precioEntrada, montoNecesario, retornos,categoriaFinal, proponente);
    }
    
    @WebMethod
    public DtColecciones obtenerPropuestas(){
    	DtColecciones col = new DtColecciones();
    	col.setPropuestas(mapToListPropuesta(iContPro.getPropuestasTitulo()));
    	return col;
    }
    
    @WebMethod
    public DtPropuesta obtenerPropuestaPorTitulo(String titulo) {
    	Propuesta prop = iContPro.getPropuestasTitulo().get(titulo);
    	DtPropuesta ret = new DtPropuesta();
    	ret.setTitulo(null);
    	if (prop != null) {
    		return getDtPropuesta(prop);
    	} else {
    		return ret;
    	}
    	
    }
    
    @WebMethod
    public void addComentario(String propuesta, String coment, String nick) {
    	Propuesta prop = iContPro.getPropuestasTitulo().get(propuesta);
    	Colaborador colab = (Colaborador) iContUsr.getUsuarioPorNick(nick);
    	prop.addComentario(coment, colab);
    }
    
    @WebMethod
    public void agregarFavorita(String usuario, String propuesta) {
    	Usuario user = iContUsr.getUsuarioPorNick(usuario);
    	Propuesta prop = iContPro.getPropuestasTitulo().get(propuesta);
    	user.agregarFavorita(prop);
    }
    
    @WebMethod
    public void eliminarFavorita(String usuario, String propuesta) {
    	Usuario user = iContUsr.getUsuarioPorNick(usuario);
    	user.eliminarFavorita(propuesta);
    }
    
    public boolean existePropuestaPorTitulo(String titulo) {
    	return iContPro.getPropuestasTitulo().containsKey(titulo);
    }
    
    @WebMethod
    public DtColecciones obtenerFavoritas(String nick) {
    	DtColecciones dtCol = new DtColecciones();
    	Usuario usr = iContUsr.getUsuarioPorNick(nick);    	
    	dtCol.setPropuestas(mapToListPropuesta(usr.getFavoritas()));
    	return dtCol;
    }
    
    @WebMethod
    public boolean existeEnLista(DtColecciones dtCol, String titulo) {
    	List<DtPropuesta> lst = dtCol.getPropuestas();
    	Iterator<DtPropuesta> it = lst.iterator();
    	boolean encontre = false;
    	while (it.hasNext() && !encontre) {
    		DtPropuesta prop = it.next(); 
    		if (prop.getTitulo() == titulo)
    			encontre = true;
    	}
    	return encontre;
    }
    @WebMethod
    public void setEstado(DtPropuesta dtProp,DtFechaCambio dtFecha){
    	Map<String,Propuesta> props = iContPro.getPropuestasTitulo();
    	Propuesta pro =props.get(dtProp.getTitulo());
    	pro.agregarEstadoAnterior(pro.getEstado());
    	FechaCambio fecha= new FechaCambio(dtFecha.getFecha(),dtFecha.getEstado());
    	pro.setEstado(fecha);
    }
    
    // ===================== COLABORACION ===================== //
    
    @WebMethod
    public void agregarColaboracion(String usuario, String propuesta, Integer monto, Calendar fechaColaboracion,
			Retorno retornoElegido) throws UsuarioYaColaboraException {
    	iContCol.agregarColaboracion(usuario,propuesta,monto,fechaColaboracion,retornoElegido);
    }
    
    @WebMethod
    public DtColecciones obtenerColaboradores() {
    	DtColecciones dtCol = new DtColecciones();
    	Map<String,Colaborador> colabs = iContUsr.getColaboradores();    	
    	dtCol.setColaboradores(mapToListColaborador(colabs));
    	return dtCol;
    }
    @WebMethod
    public DtColecciones obtenerColaboraciones(String pro) {
    	DtColecciones res = new DtColecciones();
    	Propuesta prop = iContPro.seleccionarPropuesta(pro);
    	ArrayList<DtColaboracion> dtColabs = new ArrayList<DtColaboracion>();
    	ArrayList<Colaboracion> colabs = new ArrayList<Colaboracion>(prop.getColaboraciones().values());
    	for(int i = 0; i< colabs.size(); i++) {
    		DtColaboracion dtCol = getDtColaboracion(colabs.get(i));
    		dtColabs.add(dtCol);
    	}
    	res.setColaboraciones(dtColabs);
    	return res;
    }
    
    @WebMethod
    public DtColaboracion getColaboracion(Integer id) {
    	return getDtColaboracion(iContCol.getColaboracion(id));
    }
    
    @WebMethod
    public void realizarPago(DtPago pago, Integer idColab) {
    	Colaboracion colab = iContCol.getColaboracion(idColab);
    	try {
			DtTransferencia dtTrans = (DtTransferencia) pago;
			Transferencia trans = new Transferencia(dtTrans.getMonto(), dtTrans.getBanco(), dtTrans.getTitular(), dtTrans.getCuenta());
			colab.setPago(trans);
			colab.setPaga(true);
    	} catch (Exception e) {
			try {
				DtPaypal dtPay = (DtPaypal) pago;
				Paypal ppal = new Paypal(dtPay.getMonto(), dtPay.getTitular(), dtPay.getCuenta());
				colab.setPago(ppal);
				colab.setPaga(true);
			} catch (Exception e2) {
				DtTarjeta dtTarj = (DtTarjeta) pago;
				Tarjeta tar = new Tarjeta(dtTarj.getMonto(), dtTarj.getTipo(), dtTarj.getTitular(), dtTarj.getNumero(), dtTarj.getFechaVencimiento(), dtTarj.getCvc());
				colab.setPago(tar);
				colab.setPaga(true);
			}
		}
    }
    
 // ===================== COMENTARIO ===================== //
    @WebMethod
    public DtColecciones obtenerComentarios(String pro) {
    	DtColecciones res = new DtColecciones();
    	Propuesta prop= iContPro.seleccionarPropuesta(pro);
    	ArrayList<DtComentario> com = new ArrayList<DtComentario>();
    	ArrayList<Comentario> comen = new ArrayList<Comentario>(prop.getComentarios().values());
    	for(int i = 0; i<comen.size();i++) {
    		DtComentario dtCom = getDtComentario(comen.get(i));
    		com.add(dtCom);
    	}
    	res.setComentarios(com);
    	return res;
    }
    
    // ===================== IMAGEN ===================== //
    
    @WebMethod
    public byte[] getFile(@WebParam(name = "fileName") String imgName) throws  IOException {
    	Configuracion config = Configuracion.getInstancia();
    	String pathImg = config.getPathImagenes();
    	byte[] byteArray = null;
        try {
                File f = new File(pathImg + "/" + imgName);
                @SuppressWarnings("resource")
				FileInputStream streamer = new FileInputStream(f);
                byteArray = new byte[streamer.available()];
                streamer.read(byteArray);
        } catch (IOException e) {
                throw e;
        }
        return byteArray;
    }
    
}
