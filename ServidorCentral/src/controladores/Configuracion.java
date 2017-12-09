package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import datatypes.EstadoPropuesta;
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
import logica.Colaborador;
import logica.FechaCambio;
import logica.Proponente;
import logica.Propuesta;
import manejadores.ManejadorCategoria;
import manejadores.ManejadorProponente;


public class Configuracion {
	
	private String pathImagenes;
	private static Configuracion config = null;
	private IControladorUsuario interfaceCU; 
	private IControladorCategoria interfaceCC;
	private IControladorPropuesta interfaceCP;
	private IControladorColaboracion interfaceCCol;
	
	private Configuracion() {
		pathImagenes = null;
	}
	
	public static long daysBetween(Calendar startDate, Calendar endDate) {
	    long end = endDate.getTimeInMillis();
	    long start = startDate.getTimeInMillis();
	    return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	}
	
	public static Configuracion getInstancia() {
		if (config == null) {
			config = new Configuracion();
		}
		return config;
	}
	
	public void createPathImagenes() {
		new File(System.getProperty("user.home"), "Culturarte").mkdirs();
		//File pathImg = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"Culturarte");
		//pathImagenes = pathImg.getAbsolutePath();
		pathImagenes = System.getProperty("user.home")+System.getProperty("file.separator")+"Culturarte"+System.getProperty("file.separator");
	}
	
	public String getPathImagenes() {
		return pathImagenes;
	}
	
	public void cargarDatos() throws UsuarioRepetidoException, IOException, UsuarioYaColaboraException {
		
		Fabrica fabrica = Fabrica.getInstance();
		config = Configuracion.getInstancia();
		interfaceCU = fabrica.getIControladorUsuario();
		interfaceCC = fabrica.getIControladorCategoria();
		interfaceCP = fabrica.getIControladorPropuesta();
		interfaceCCol = fabrica.getIControladorColaboracion();
		
		byte[] byteVacio = new byte[0];
		
		ManejadorCategoria mcat = ManejadorCategoria.getInstancia();
		ManejadorProponente mprop = ManejadorProponente.getInstancia();
		
		config.createPathImagenes();
		String pathImg = config.getPathImagenes();
		
		//==============CREO LOS USUARIOS================//
		
		File fotoFile;
		byte[] fotoBytes;
		FileInputStream fotoInputStream;
		
		fotoFile = new File(pathImg + "hrubino.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("hrubino", "Horacio", "Rubino", "horacio.rubino@guambia.com.uy", 
				25, 1, 1962, fotoBytes, ".jpg", "Proponente", "18 de Julio 1234", "Horacio Rubino Torres nace el 25 de febrero de 1962,"
						+ " es conductor, actor y libretista. Debuta en 1982 en carnaval en Los \"Klaper's\", donde estuvo"
						+ " cuatro años, actuando y libretando. Luego para 'Gaby's' (6 años), escribió en categoría revistas"
						+ " y humoristas y desde el comienzo y hasta el presente en su propio conjunto Momosapiens.",
						"https://twitter.com/horaciorubino", "K6keGZEx");

		fotoFile = new File(pathImg + "mbusca.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("mbusca", "Martín", "Buscaglia", "Martin.bus@agadu.org.uy", 14, 5, 1972, fotoBytes, ".jpg", "Proponente",
				"Colonia 4321", "Martín Buscaglia (Montevideo, 1972) es un artista, músico, compositor y productor uruguayo. Tanto"
						+ " con su banda (\"Los Bochamakers\") como en su formato \"Hombre orquesta\", o solo con su guitarra,"
						+ " ha recorrido el mundo tocando entre otros países en España, Estados Unidos, Inglaterra, Francia, Australia,"
						+ " Brasil, Colombia, Argentina, Chile, Paraguay, México y Uruguay. (Actualmente los Bochamakers son"
						+ " Matías Rada, Martín Ibarburu, Mateo Moreno, Herman Klang) Paralelamente, tiene proyectos a dúo con el"
						+ " español Kiko Veneno, la cubana Yusa, el argentino Lisandro Aristimuño, su compatriota Antolín, y a trío"
						+ " junto a los brasileros Os Mulheres Negras.", "http://www.martinbuscaglia.com/", "aL4aNcYF");

		fotoFile = new File(pathImg + "hectorg.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("hectorg", "Héctor", "Guido", "hector.gui@elgalpon.org.uy", 7, 0, 1954, fotoBytes, ".jpg", "Proponente",
				"Gral. Flores 5645", "En 1972 ingresó a la Escuela de Arte Dramático del teatro El Galpón. Participó en más de treinta obras teatrales y" + 
						"varios largometrajes. Integró el elenco estable de Radioteatro del Sodre, y en 2006 fue asesor de su Consejo" + 
						"Directivo. Como actor recibió múltiples reconocimientos: cuatro premios Florencio, premio al mejor actor" + 
						"extranjero del Festival de Miami y premio Mejor Actor de Cine 2008. Durante varios períodos fue directivo del" + 
						"teatro El Galpón y dirigente de la Sociedad Uruguaya de Actores (SUA); integró también la Federación Uruguaya" + 
						"de Teatros Independientes (FUTI). Formó parte del equipo de gestión de la refacción de los teatros La Máscara," + 
						"Astral y El Galpón, y del equipo de gestión en la construcción del teatro De la Candela y de la sala Atahualpa de El" + 
						"Galpón.", "", "dcUCulNa");

		fotoFile = new File(pathImg + "tabarec.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("tabarec", "Tabaré", "Cardozo", "tabare.car@agadu.org.uy", 24, 6, 1971, fotoBytes, ".jpg", "Proponente",
				"Santiago Rivas 1212 ", "Tabaré Cardozo (Montevideo, 24 de julio de 1971) es un cantante, compositor y murguista uruguayo; conocido por" + 
						"su participación en la murga Agarrate Catalina, conjunto que fundó junto a su hermano Yamandú y Carlos" + 
						"Tanco en el año 2001.", "https://www.facebook.com/Tabar%C3%A9-Cardozo-55179094281/?ref=br_rs", "od5CX5gS");
		
		fotoFile = new File(pathImg + "cachilas.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("cachilas", "Waldemar \"Cachila\"", "Silva", "Cachila.sil@c1080.org.uy", 1, 0, 1947, fotoBytes, ".jpg", "Proponente", "Br. Artigas 4567", "Nace en el año 1947 "
				+ "en el conventillo \"Medio Mundo\" ubicado en pleno Barrio Sur. Es heredero parcialmente -junto al resto de sus hermanos- de la Comparsa \"Morenada\" "
				+ "(iniciativa desde el fallecimiento de Juan Ángel Silva), en 1999 forma su propia Comparsa de negros y lobulos \"Cuareim 1080\". Director responsable,"
				+ "compositor y cantante de la misma.", "https://www.facebook.com/C1080?ref=br_rs", "JGgqYPTS");
		
		
		
		interfaceCU.agregarUsuario("juliob", "Julio", "Bocca", "juliobocca@sodre.com.uy", 16, 2, 1967, byteVacio, null, "Proponente", "Benito Blanco 4321", "", "", "9FpNxvih");
		
		interfaceCU.agregarUsuario("diegop", "Diego", "Parodi", "diego@efectocine.com", 1, 0, 1975, byteVacio, null, "Proponente", "Emilio Frugoni 1138 Ap. 02", "", "http://www.efectocine.com", "s7L8BpKG");

		fotoFile = new File(pathImg + "kairoh.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("kairoh", "Kairo", "Herrera", "kairoher@pilsenrock.com.uy", 25, 3, 1840, fotoBytes, ".jpg", "Proponente", "Paraguay 1423", "", "", "Xa0GPWZA");

		fotoFile = new File(pathImg + "losBardo.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("losBardo", "Los", "Bardo", "losbardo@bardocientifico.com", 31, 9, 1980, fotoBytes, ".jpg", "Proponente", "8 de Octubre 1429", "Queremos ser vistos y reconocidos"
				+ "como una organización: referente en divulgación científica con un fuerte espíritu didáctico y divertido, a través de acciones coordinadas con otros divulgadores"
				+ "científicos, que permitan establecer puentes de comunicación. Impulsora en la generación de espacios de democratización y apropación social del reconocimiento científico." , 
				"https://bardocientifico.com/", "r8b16VnQ");
		
		interfaceCU.agregarUsuario("robinh", "Robin", "Henderson", "Robin.h@tinglesa.com.uy", 3, 7, 1940, byteVacio, null, "Colaborador", "", "", "", "V1sPsIpl");

		fotoFile = new File(pathImg + "marcelot.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("marcelot", "Marcelo", "Tinelli", "marcelot@ideasdelsur.com.ar", 1, 3, 1960, fotoBytes, ".jpg", "Colaborador", "", "", "", "S0E8vCK2");

		fotoFile = new File(pathImg + "novick.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("novick", "Edgardo", "Novick", "edgardo@novick.com.uy", 17, 6, 1952, fotoBytes, ".jpg", "Colaborador", "", "", "", "kZIwfxnV");
		
		fotoFile = new File(pathImg + "sergiop.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("sergiop", "Sergio", "Puglia", "puglia@alpanpan.com.uy", 28, 0, 1950, fotoBytes, ".jpg", "Colaborador", "", "", "", "Fld4hEtI");
		
		interfaceCU.agregarUsuario("chino", "Alvaro", "Recoba", "chino@trico.org.uy", 17, 2, 1976, byteVacio, null, "Colaborador", "", "", "", "1BAoBp1r");
		
		fotoFile = new File(pathImg + "tonyp.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("tonyp", "Antonio", "Pacheco", "eltony@manya.org.uy", 14, 1, 1955, fotoBytes, ".jpg", "Colaborador", "", "", "", "9ovUu61d");

		fotoFile = new File(pathImg + "nicoJ.jpg");
		fotoBytes = new byte[(int) fotoFile.length()];
		fotoInputStream = new FileInputStream(fotoFile);
		fotoInputStream.read(fotoBytes);
		fotoInputStream.close();
		interfaceCU.agregarUsuario("nicoJ", "Nicolás", "Jodal", "jodal@artech.com.uy", 9, 7, 1960, fotoBytes, ".jpg", "Colaborador", "", "", "", "c9ogZ9r4");
		
		interfaceCU.agregarUsuario("juanP", "Juan", "Perez", "juanp@elpueblo.com", 1, 0, 1970, byteVacio, null, "Colaborador", "", "", "", "aDJvvrJm");
		
		interfaceCU.agregarUsuario("Mengano", "Mengano", "Gómez", "menganog@elpueblo.com", 2, 1, 1982, byteVacio, null, "Colaborador", "", "", "", "bfEWtrqQ");
		
		interfaceCU.agregarUsuario("Perengano", "Perengano", "López", "pere@elpueblo.com", 3, 2, 1985, byteVacio, null, "Colaborador", "", "", "", "m5nbaRBV");
		
		interfaceCU.agregarUsuario("Tiajaci", "Tía", "Jacinta", "jacinta@elpueblo.com", 4, 3, 1990, byteVacio, null, "Colaborador", "", "", "", "uIo1s1GR");
		
		//===============SEGUIDORES=============//	
		
		
		try {
			interfaceCU.seguirUsuario("hrubino", "hectorg");
			interfaceCU.seguirUsuario("hrubino", "diegop");
			interfaceCU.seguirUsuario("hrubino", "losBardo");
			interfaceCU.seguirUsuario("mbusca", "tabarec");
			interfaceCU.seguirUsuario("mbusca", "cachilas");
			interfaceCU.seguirUsuario("mbusca", "kairoh");
			interfaceCU.seguirUsuario("hectorg", "mbusca");
			interfaceCU.seguirUsuario("hectorg", "juliob");
			
			interfaceCU.seguirUsuario("tabarec", "hrubino");
			interfaceCU.seguirUsuario("tabarec", "cachilas");
			
			interfaceCU.seguirUsuario("cachilas", "hrubino");
			
			interfaceCU.seguirUsuario("juliob", "mbusca");
			interfaceCU.seguirUsuario("juliob", "diegop");
			
			interfaceCU.seguirUsuario("diegop", "hectorg");
			interfaceCU.seguirUsuario("diegop", "losBardo");
			
			interfaceCU.seguirUsuario("kairoh", "sergiop");
			
			interfaceCU.seguirUsuario("losBardo", "hrubino");
			interfaceCU.seguirUsuario("losBardo", "nicoJ");
			
			interfaceCU.seguirUsuario("robinh", "hectorg");
			interfaceCU.seguirUsuario("robinh", "juliob");
			interfaceCU.seguirUsuario("robinh", "diegop"); 
			
			interfaceCU.seguirUsuario("marcelot", "cachilas");
			interfaceCU.seguirUsuario("marcelot", "juliob");
			interfaceCU.seguirUsuario("marcelot", "kairoh");
			
			interfaceCU.seguirUsuario("novick", "hrubino");
			interfaceCU.seguirUsuario("novick", "tabarec");
			interfaceCU.seguirUsuario("novick", "cachilas");
			
			interfaceCU.seguirUsuario("sergiop", "mbusca");
			interfaceCU.seguirUsuario("sergiop", "juliob");
			interfaceCU.seguirUsuario("sergiop", "diegop");
			
			interfaceCU.seguirUsuario("chino", "tonyp");
			
			interfaceCU.seguirUsuario("tonyp", "chino");
			
			interfaceCU.seguirUsuario("nicoJ", "diegop");
			interfaceCU.seguirUsuario("nicoJ", "losBardo");
			
			interfaceCU.seguirUsuario("juanP", "tabarec");
			interfaceCU.seguirUsuario("juanP", "cachilas");
			interfaceCU.seguirUsuario("juanP", "kairoh");
			
			interfaceCU.seguirUsuario("Mengano", "hectorg");
			interfaceCU.seguirUsuario("Mengano", "juliob");
			interfaceCU.seguirUsuario("Mengano", "chino");
			
			interfaceCU.seguirUsuario("Perengano", "diegop");
			interfaceCU.seguirUsuario("Perengano", "tonyp");
			
			interfaceCU.seguirUsuario("Tiajaci", "juliob");
			interfaceCU.seguirUsuario("Tiajaci", "kairoh");
			interfaceCU.seguirUsuario("Tiajaci", "sergiop");
		} catch (UsuarioYaEsSeguidorException e1) {
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		//=================CATEGORIAS======================//
		
		try {
			interfaceCC.agregarCategoria("Teatro", "");
			interfaceCC.agregarCategoria("Teatro Dramático", "Teatro");
			interfaceCC.agregarCategoria("Teatro Musical", "Teatro");
			interfaceCC.agregarCategoria("Comedia", "Teatro");
			interfaceCC.agregarCategoria("Stand-up", "Comedia");
			
			interfaceCC.agregarCategoria("Literatura", "");
			
			interfaceCC.agregarCategoria("Música", "");
			interfaceCC.agregarCategoria("Festival", "Música");
			interfaceCC.agregarCategoria("Concierto", "Música");
			
			interfaceCC.agregarCategoria("Cine", "");
			interfaceCC.agregarCategoria("Cine al Aire Libre", "Cine");
			interfaceCC.agregarCategoria("Cine a Pedal", "Cine");
			
			interfaceCC.agregarCategoria("Danza", "");
			interfaceCC.agregarCategoria("Ballet", "Danza");
			interfaceCC.agregarCategoria("Flamenco", "Danza");
			
			interfaceCC.agregarCategoria("Carnaval", "");
			interfaceCC.agregarCategoria("Murga", "Carnaval");
			interfaceCC.agregarCategoria("Humoristas", "Carnaval");
			interfaceCC.agregarCategoria("Parodistas", "Carnaval");
			interfaceCC.agregarCategoria("Lubolos", "Carnaval");
			interfaceCC.agregarCategoria("Revista", "Carnaval");
			
		} catch (CategoriaRepetidaException e) {
			return;
		}
		
		//==============PROPUESTAS================//		
		Calendar fechaRealizacion, fechaIngreso, fecha;
		List<Retorno> retornos = new LinkedList<Retorno>();
		Categoria categoria = null;
		Proponente proponente = null;
		Propuesta prop = null;
		FechaCambio estado = null;
		List<FechaCambio> estAnt = null;
		

		try {
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 5, 16);
			retornos.add(Retorno.Porcentaje);
			categoria = mcat.getCategoria("Cine al Aire Libre");
			proponente = mprop.getProponentePorNick("diegop");
			interfaceCP.addPropuesta("Cine en el Botánico", "El 16 de Diciembre a la hora 20 se proyectará la "
					+ "película \"Clever\", en el Jardín Botánico (Av. 19 de Abril 1181) en el marco de "
					+ "las actividades realizadas por el ciclo Cultura al Aire Libre. El largometraje "
					+ "uruguayo de ficción Clever es dirigido por Federico Borgia y Guillermo Madeiro. "
					+ "Es apto para mayores de 15 años.", byteVacio, null, "Járdin Botánico", fechaRealizacion, 200, 150000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("Cine en el Botánico");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 5, 16, 15, 30);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//PUBLICADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 5, 17, 8, 30);
				estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
				estAnt.add(estado);
				//EN FINANCIACION
				fecha = Calendar.getInstance();
				fecha.set(2017, 5, 20, 14, 30);
				estado = new FechaCambio(fecha, EstadoPropuesta.EnFinanciacion);
				estAnt.add(estado);
				//FINANCIADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 6, 16, 8, 30);
				estado = new FechaCambio(fecha, EstadoPropuesta.Financiada);
				estAnt.add(estado);
				//CANCELADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 6, 20, 14, 50);
				estado = new FechaCambio(fecha, EstadoPropuesta.Cancelada);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
				
			}
			
			
			
			
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 6, 18);
			retornos = null;
			retornos = new LinkedList<Retorno>();
			retornos.add(Retorno.Porcentaje);
			retornos.add(Retorno.Entradas);
			categoria = mcat.getCategoria("Parodistas");
			proponente = mprop.getProponentePorNick("hrubino");
			fotoFile = new File(pathImg + "Religiosamente.jpg");
			fotoBytes = new byte[(int) fotoFile.length()];
			fotoInputStream = new FileInputStream(fotoFile);
			fotoInputStream.read(fotoBytes);
			fotoInputStream.close();
			interfaceCP.addPropuesta("Religiosamente", "MOMOSAPIENS presenta \"Religiosamente\". Mediante dos parodias "
					+ "y un hilo conductor que aborda la temática de la religión Momosapiens, mediante el humor "
					+ "y la reflexión, hilvana una historia que muestra al hombre inmerso en el tema religioso. "
					+ "El libreto está escrito utilizando diferentes lenguajes de humor, dando una visión "
					+ "satírica y reflexiva desde distintos puntos de vista, logrando mediante situaciones "
					+ "paródicas armar una propuesta plena de arte carnavalero.", 
					fotoBytes, ".jpg", "Teatro de Verano", fechaRealizacion, 300, 300000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("Religiosamente");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 6, 18, 4, 28);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//PUBLICADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 6, 20, 4, 56);
				estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
				estAnt.add(estado);
				//EN FINANCIACION
				fecha = Calendar.getInstance();
				fecha.set(2017, 6, 30, 14, 25);
				estado = new FechaCambio(fecha, EstadoPropuesta.EnFinanciacion);
				estAnt.add(estado);
				//FINANCIADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 7, 20, 4, 56);
				estado = new FechaCambio(fecha, EstadoPropuesta.Financiada);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
				
			}
			
			
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 7, 26);
			retornos = null;
			retornos = new LinkedList<Retorno>();
			retornos.add(Retorno.Porcentaje);
			categoria = mcat.getCategoria("Concierto");
			proponente = mprop.getProponentePorNick("mbusca");
			fotoFile = new File(pathImg + "El Pimiento Indomable.jpg");
			fotoBytes = new byte[(int) fotoFile.length()];
			fotoInputStream = new FileInputStream(fotoFile);
			fotoInputStream.read(fotoBytes);
			fotoInputStream.close();
			interfaceCP.addPropuesta("El Pimiento Indomable", "El Pimiento Indomable, formación compuesta por Kiko Veneno "
					+ "y el uruguayo Martín Buscaglia, presentará este 19 de Octubre, su primer trabajo. Bajo un título "
					+ "homónimo al del grupo, es un disco que según los propios protagonistas “no se parece al de ninguno "
					+ "de los dos por separado. Entre los títulos que se podrán escuchar se encuentran “Nadador salvador', "
					+ "'América es más grande', 'Pescaito Enroscado' o 'La reina del placer'. ", 
					fotoBytes, ".jpg", "Teatro Solís", fechaRealizacion, 400, 400000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("El Pimiento Indomable");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 7, 26, 15, 30);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//PUBLICADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 7, 31, 8, 30);
				estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
				estAnt.add(estado);
				//EN FINANCIACION
				fecha = Calendar.getInstance();
				fecha.set(2017, 8, 1, 7, 40);
				estado = new FechaCambio(fecha, EstadoPropuesta.EnFinanciacion);
				estAnt.add(estado);
				//NO FINANCIADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 8, 30, 8, 30);
				estado = new FechaCambio(fecha, EstadoPropuesta.NoFinanciada);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
			}
			
			
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 11, 15);
			retornos = null;
			retornos = new LinkedList<Retorno>();
			retornos.add(Retorno.Porcentaje);
			retornos.add(Retorno.Entradas);
			categoria = mcat.getCategoria("Festival");
			proponente = mprop.getProponentePorNick("kairoh");
			fotoFile = new File(pathImg + "Pilsen Rock.jpg");
			fotoBytes = new byte[(int) fotoFile.length()];
			fotoInputStream = new FileInputStream(fotoFile);
			fotoInputStream.read(fotoBytes);
			fotoInputStream.close();
			interfaceCP.addPropuesta("Pilsen Rock", "La edición 2017 del Pilsen Rock se celebrará el 21 de Octubre en la Rural del "
					+ "Prado y contará con la participación de más de 15 bandas nacionales. Quienes no puedan trasladarse al "
					+ "lugar, tendrán la posibilidad de disfrutar los shows a través de Internet, así como entrevistas en vivo "
					+ "a los músicos una vez finalizados los conciertos.", 
					fotoBytes, ".jpg", "Rural del Prado", fechaRealizacion, 1000, 900000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("Pilsen Rock");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 11, 15, 15, 40);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//PUBLICADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 11, 20, 14, 30);
				estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
				estAnt.add(estado);
				//EN FINANCIACION
				fecha = Calendar.getInstance();
				fecha.set(2017, 11, 20, 16, 50);
				estado = new FechaCambio(fecha, EstadoPropuesta.EnFinanciacion);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
			}
			
			
			
			
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 11, 5);
			retornos = null;
			retornos = new LinkedList<Retorno>();
			retornos.add(Retorno.Porcentaje);
			categoria = mcat.getCategoria("Ballet");
			proponente = mprop.getProponentePorNick("juliob");
			fotoFile = new File(pathImg + "Romeo y Julieta.jpg");
			fotoBytes = new byte[(int) fotoFile.length()];
			fotoInputStream = new FileInputStream(fotoFile);
			fotoInputStream.read(fotoBytes);
			fotoInputStream.close();
			interfaceCP.addPropuesta("Romeo y Julieta", "Romeo y Julieta de Kenneth MacMillan, uno de los ballets favoritos del "
					+ "director artístico Julio Bocca, se presentará nuevamente el 5 de Noviembre en el Auditorio Nacional del "
					+ "Sodre. Basada en la obra homónima de William Shakespeare, Romeo y Julieta es considerada la coreografía maestra "
					+ "del MacMillan. La producción de vestuario y escenografía se realizó en los Talleres del Auditorio Adela Reta, "
					+ "sobre los diseños originales.", 
					fotoBytes, ".jpg", "Auditorio Nacional del Sodre", fechaRealizacion, 800, 750000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("Romeo y Julieta");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 11, 5, 12, 20);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//PUBLICADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 11, 6, 10, 25);
				estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
				estAnt.add(estado);
				//EN FINANCIACION
				fecha = Calendar.getInstance();
				fecha.set(2017, 11, 8, 4, 58);
				estado = new FechaCambio(fecha, EstadoPropuesta.EnFinanciacion);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
			}
			
			
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 11, 16);
			retornos = null;
			retornos = new LinkedList<Retorno>();
			retornos.add(Retorno.Porcentaje);
			retornos.add(Retorno.Entradas);
			categoria = mcat.getCategoria("Murga");
			proponente = mprop.getProponentePorNick("tabarec");
			fotoFile = new File(pathImg + "Un día de Julio.jpg");
			fotoBytes = new byte[(int) fotoFile.length()];
			fotoInputStream = new FileInputStream(fotoFile);
			fotoInputStream.read(fotoBytes);
			fotoInputStream.close();
			interfaceCP.addPropuesta("Un día de Julio", "La Catalina presenta el espectáculo \"Un Día de Julio\" en Landia. Un hombre misterioso y "
					+ "solitario vive encerrado entre las cuatro paredes de su casa. Intenta, con sus teorías extravagantes, cambiar el mundo "
					+ "exterior que le resulta inhabitable. Un día de Julio sucederá algo que cambiará su vida y la de su entorno para siempre.", 
					fotoBytes, ".jpg", "Landia", fechaRealizacion, 650, 300000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("Un día de Julio");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 11, 16, 2, 00);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//PUBLICADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 11, 17, 4, 50);
				estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
				estAnt.add(estado);
				//EN FINANCIACION
				fecha = Calendar.getInstance();
				fecha.set(2017, 11, 18, 4, 48);
				estado = new FechaCambio(fecha, EstadoPropuesta.EnFinanciacion);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
			}
			
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 12, 3);
			retornos = null;
			retornos = new LinkedList<Retorno>();
			retornos.add(Retorno.Entradas);
			categoria = mcat.getCategoria("Teatro Dramático");
			proponente = mprop.getProponentePorNick("hectorg");
			interfaceCP.addPropuesta("El Lazarillo de Tormes", "Vuelve unas de las producciones de El Galpón más aclamadas de los últimos tiempos. "
					+ "Esta obra se ha presentado en Miami, Nueva York, Washington, México, Guadalajara, Río de Janeiro y La Habana. En nuestro "
					+ "país, El Lazarillo de Tormes fue nominado en los rubros mejor espectáculo y mejor dirección a los Premios Florencio 1995, "
					+ "obteniendo su protagonista Héctor Guido el Florencio a Mejor actor de ese año.", 
					byteVacio, null, "Teatro el Galpón", fechaRealizacion, 350, 175000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("El Lazarillo de Tormes");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 12, 3, 2, 40);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//PUBLICADA
				fecha = Calendar.getInstance();
				fecha.set(2017, 12, 9, 21, 58);
				estado = new FechaCambio(fecha, EstadoPropuesta.Publicada);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
			}
			
			fechaRealizacion = Calendar.getInstance();
			fechaRealizacion.set(2018, 12, 10);
			retornos = null;
			retornos = new LinkedList<Retorno>();
			retornos.add(Retorno.Entradas);
			categoria = mcat.getCategoria("Stand-up");
			proponente = mprop.getProponentePorNick("losBardo");
			interfaceCP.addPropuesta("Bardo en la FING", "El 10 de Diciembre se presentará Bardo Científico en la FING. El humor puede ser usado como una"
					+ " herramienta importante para el aprendizaje y la democratización de la ciencia, los monólogos científicos son una forma didáctica "
					+ "de apropiación del conocimiento científico y contribuyen a que el público aprenda ciencia de forma amena. Los invitamos a pasar un rato "
					+ "divertido, en un espacio en el cual aprenderán cosas de la ciencia que los sorprenderán. ¡Los esperamos!", 
					byteVacio, null, "Anfiteatro Edificio \"José Luis Massera\"", fechaRealizacion, 200, 100000, retornos, categoria, proponente);
			prop = interfaceCP.seleccionarPropuesta("Bardo en la FING");
			if (prop != null) {
				estAnt = prop.getEstadosAnteriores();
				//INGRESADA
				fechaIngreso = Calendar.getInstance();
				fechaIngreso.set(2017, 12, 10, 2, 12);
				prop.setFechaIngreso(fechaIngreso);				
				estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
				estAnt.add(estado);
				//SETEO VALORES
				prop.setEstadosAnteriores(estAnt);
				prop.setEstado(estado);
			}
			
			//=============COLABORACIONES===============//			
			Retorno retornoPorcentaje = Retorno.Porcentaje;
			Retorno retornoEntrada = Retorno.Entradas;
			
			Calendar fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 4, 20, 14, 30);			
			interfaceCCol.agregarColaboracion("novick", "Cine en el Botánico", 50000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 4, 24, 17, 25);	
			interfaceCCol.agregarColaboracion("robinh", "Cine en el Botánico", 50000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 4, 30, 18, 30);	
			interfaceCCol.agregarColaboracion("nicoJ", "Cine en el Botánico", 50000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 5, 30, 14, 25);	
			interfaceCCol.agregarColaboracion("marcelot", "Religiosamente", 200000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 6, 1, 18, 05);	
			interfaceCCol.agregarColaboracion("Tiajaci", "Religiosamente", 500, fechaColaboracion, retornoEntrada);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 6, 7, 17, 45);	
			interfaceCCol.agregarColaboracion("Mengano", "Religiosamente", 600, fechaColaboracion, retornoEntrada);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 6, 10, 14, 35);	
			interfaceCCol.agregarColaboracion("novick", "Religiosamente", 50000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 6, 15, 9, 45);	
			interfaceCCol.agregarColaboracion("sergiop", "Religiosamente", 50000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 1, 7, 40);	
			interfaceCCol.agregarColaboracion("marcelot", "El Pimiento Indomable", 200000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 3, 9, 25);	
			interfaceCCol.agregarColaboracion("sergiop", "El Pimiento Indomable", 80000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 5, 16, 50);	
			interfaceCCol.agregarColaboracion("chino", "Pilsen Rock", 50000, fechaColaboracion, retornoEntrada);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 10, 15, 50);	
			interfaceCCol.agregarColaboracion("novick", "Pilsen Rock", 120000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 15, 19, 30);	
			interfaceCCol.agregarColaboracion("tonyp", "Pilsen Rock", 120000, fechaColaboracion, retornoEntrada);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 13, 4, 58);	
			interfaceCCol.agregarColaboracion("sergiop", "Romeo y Julieta", 100000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 14, 11, 25);	
			interfaceCCol.agregarColaboracion("marcelot", "Romeo y Julieta", 200000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 15, 4, 48);	
			interfaceCCol.agregarColaboracion("tonyp", "Un día de Julio", 30000, fechaColaboracion, retornoEntrada);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 17, 15, 30);	
			interfaceCCol.agregarColaboracion("marcelot", "Un día de Julio", 150000, fechaColaboracion, retornoPorcentaje);
			
			fechaColaboracion = Calendar.getInstance();
			fechaColaboracion.set(2017, 7, 17, 15, 30);	
			interfaceCCol.agregarColaboracion("chino", "Un día de Julio", 3000000, fechaColaboracion, retornoPorcentaje);
			
			
			//============COMENTARIOS============//
			
			Propuesta propu;
			Colaborador usuario;
			
			usuario = (Colaborador) interfaceCU.getUsuarioPorNick("novick");
			propu = interfaceCP.getPropuestasTitulo().get("Cine en el Botánico");
			propu.addComentario("Muy buena propuesta.", usuario);
			
			usuario = (Colaborador) interfaceCU.getUsuarioPorNick("robinh");
			propu = interfaceCP.getPropuestasTitulo().get("Cine en el Botánico");
			propu.addComentario("Realmente una pena que la propuesta haya sido cancelada.", usuario);
			
			usuario = (Colaborador) interfaceCU.getUsuarioPorNick("nicoJ");
			propu = interfaceCP.getPropuestasTitulo().get("Cine en el Botánico");
			propu.addComentario("No se lo pueden perder!", usuario);
			
			usuario = (Colaborador) interfaceCU.getUsuarioPorNick("marcelot");
			propu = interfaceCP.getPropuestasTitulo().get("Religiosamente");
			propu.addComentario("Todos al teatro de verano este 7 de Octubre!", usuario);
			
			usuario = (Colaborador) interfaceCU.getUsuarioPorNick("Mengano");
			propu = interfaceCP.getPropuestasTitulo().get("Religiosamente");
			propu.addComentario("Arriba Momosapiens!!!", usuario);
			
			usuario = (Colaborador) interfaceCU.getUsuarioPorNick("sergiop");
			propu = interfaceCP.getPropuestasTitulo().get("Religiosamente");
			propu.addComentario("Los conmino a todos a ir!", usuario);
			
			usuario = (Colaborador) interfaceCU.getUsuarioPorNick("novick");
			propu = interfaceCP.getPropuestasTitulo().get("Religiosamente");
			propu.addComentario("Excelente propuesta. Ahí estaremos.", usuario);
			
			//============FAVORITAS============//
			
			
			interfaceCU.getUsuarioPorNick("hrubino").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			interfaceCU.getUsuarioPorNick("hrubino").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Pimiento Indomable"));
			interfaceCU.getUsuarioPorNick("hrubino").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Un día de Julio"));
			
			interfaceCU.getUsuarioPorNick("mbusca").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Cine en el Botánico"));
			interfaceCU.getUsuarioPorNick("mbusca").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Pimiento Indomable"));
			interfaceCU.getUsuarioPorNick("mbusca").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Pilsen Rock"));
			
			interfaceCU.getUsuarioPorNick("hectorg").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Romeo y Julieta"));
			interfaceCU.getUsuarioPorNick("hectorg").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Lazarillo de Tormes"));
			
			interfaceCU.getUsuarioPorNick("tabarec").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			interfaceCU.getUsuarioPorNick("tabarec").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Un día de Julio"));
			
			interfaceCU.getUsuarioPorNick("cachilas").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			
			interfaceCU.getUsuarioPorNick("juliob").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Romeo y Julieta"));
			interfaceCU.getUsuarioPorNick("juliob").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Lazarillo de Tormes"));
			
			interfaceCU.getUsuarioPorNick("diegop").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Cine en el Botánico"));
			interfaceCU.getUsuarioPorNick("diegop").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Lazarillo de Tormes"));
			
			interfaceCU.getUsuarioPorNick("kairoh").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			interfaceCU.getUsuarioPorNick("kairoh").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Pilsen Rock"));
			
			interfaceCU.getUsuarioPorNick("losBardo").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Bardo en la FING"));
			
			interfaceCU.getUsuarioPorNick("robinh").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Cine en el Botánico"));
			
			interfaceCU.getUsuarioPorNick("marcelot").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			interfaceCU.getUsuarioPorNick("marcelot").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Pimiento Indomable"));
			
			interfaceCU.getUsuarioPorNick("novick").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			interfaceCU.getUsuarioPorNick("novick").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Pilsen Rock"));
			
			interfaceCU.getUsuarioPorNick("sergiop").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Pimiento Indomable"));
			interfaceCU.getUsuarioPorNick("sergiop").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Romeo y Julieta"));
			
			interfaceCU.getUsuarioPorNick("chino").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Pilsen Rock"));
			
			interfaceCU.getUsuarioPorNick("tonyp").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Pilsen Rock"));
			interfaceCU.getUsuarioPorNick("tonyp").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Un día de Julio"));
			
			interfaceCU.getUsuarioPorNick("nicoJ").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Cine en el Botánico"));
			
			interfaceCU.getUsuarioPorNick("juanP").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Pilsen Rock"));
			
			interfaceCU.getUsuarioPorNick("Mengano").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			interfaceCU.getUsuarioPorNick("Mengano").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Un día de Julio"));
			
			interfaceCU.getUsuarioPorNick("Perengano").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Pilsen Rock"));
			interfaceCU.getUsuarioPorNick("Perengano").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Un día de Julio"));
			
			interfaceCU.getUsuarioPorNick("Tiajaci").agregarFavorita(interfaceCP.getPropuestasTitulo().get("Religiosamente"));
			interfaceCU.getUsuarioPorNick("Tiajaci").agregarFavorita(interfaceCP.getPropuestasTitulo().get("El Lazarillo de Tormes"));	
			
			
		} catch (PropuestaRepetidaException e) {
			return;
		}
		
		
	}

}
