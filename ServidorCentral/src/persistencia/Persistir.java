package persistencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import controladores.ControladorUsuario;
import datatypes.Retorno;
import interfaces.IPersistencia;
import logica.Colaboracion;
import logica.Proponente;
import logica.Propuesta;



public class Persistir implements IPersistencia {
	
	
	
	
	public void persistirDatos(String nick){
		
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaCulturarte");
		EntityManager em = emf.createEntityManager();

		try {
			
			em.getTransaction().begin();
			
			ControladorUsuario cusu = new ControladorUsuario();
			Proponente prop = (Proponente) cusu.getUsuarioPorNick(nick);
			ProponentePer propAPer = new ProponentePer();

			
			//Copio Datos Proponente
			propAPer.setNickname(prop.getNickname());
			propAPer.setNombre(prop.getNombre());
			propAPer.setApellido(prop.getApellido());
			propAPer.setCorreoElectronico(prop.getCorreoElectronico());
			propAPer.setFechaNacimiento(prop.getFechaNacimiento());
			propAPer.setContra(prop.getContra());
			propAPer.setFechaBaja(Calendar.getInstance());

			ArrayList<PropuestaPer> propuestas = new ArrayList<PropuestaPer>();

			List<Propuesta> listProp = new ArrayList<Propuesta>(prop.getPropuestas().values());

			PropuestaPer iter = new PropuestaPer();

			
			//Copio todas las Propuestas que posee
			for (Object obj : listProp) {
				Propuesta propSel = (Propuesta) obj;

				TypedQuery<CategoriaPer> query = em.createQuery("SELECT c FROM CategoriaPer c WHERE c.nombre = :nombre",
						CategoriaPer.class);
				query.setParameter("nombre", propSel.getCategoria().getNombre());
				
				CategoriaPer cateaux = em.find(CategoriaPer.class, propSel.getCategoria().getNombre());
				

				iter.setCategoria(cateaux);
				iter.setLugar(propSel.getLugar());
				iter.setFechaRealizacion(propSel.getFechaRealizacion());
				iter.setIdProponente(propSel.getProponente().getNickname());
				iter.setMontoNecesario(propSel.getMontoNecesario());
				iter.setPrecioEntrada(propSel.getPrecioEntrada());
				iter.setTitulo(propSel.getTitulo());

				List<Retorno> retornos = propSel.getRetornos();
				ListIterator<Retorno> it = (ListIterator<Retorno>) retornos.iterator();
				String retorno = "";
				while (it.hasNext()) {
					Retorno r = it.next();
					if (r == Retorno.Entradas) {
						retorno = retorno + "Entradas ";
					} else {
						retorno = retorno + "Porcentaje ";
					}
				}
				iter.setTiposRetorno(retorno);

				// A borar>>
				List<Colaboracion> listCol = new ArrayList<Colaboracion>(propSel.getColaboraciones().values());

				// A guardar>>
				List<ColaboracionPer> listaColaAPer = new ArrayList<ColaboracionPer>();

				
				//Copio las Colaboraciones
				for (Object obj3 : listCol) {
					Colaboracion c = (Colaboracion) obj3;
					ColaboracionPer colAux = new ColaboracionPer();
					colAux.setFechaColaboracion(c.getFechaColaboracion());
					colAux.setIdPropuesta(propSel.getTitulo());
					colAux.setMonto(c.getMonto());
					colAux.setNickColaborador(c.getUsuario().getNickname());
					colAux.setTipoRetorno(c.getRetornoElegido().toString());

					listaColaAPer.add(colAux);
				}

				iter.setColaboraciones(listaColaAPer);
				
				propuestas.add(iter);
				

			}
			
			propAPer.setPropuestas(propuestas);
	        
			
			//Persisto
			
			TypedQuery<ProponentePer> query2 = em.createQuery("SELECT p FROM ProponentePer p WHERE p.nickname = :nick", ProponentePer.class);
			query2.setParameter("nick", propAPer.getNickname());
			
			List<ProponentePer> lista = (List<ProponentePer>) query2.getResultList();
			
			if (lista.isEmpty()) {
				
				em.persist(propAPer);
				em.flush();
				em.getTransaction().commit();
				
			}
			
			

		
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}
	
	
	public List<ProponentePer> getProponentesPer() {
		
		// creo EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaCulturarte");
		EntityManager em = emf.createEntityManager();

		try {

			// Inicializo y creo la query
			em.getTransaction().begin();
			TypedQuery<ProponentePer> query = em.createQuery("SELECT p FROM ProponentePer p", ProponentePer.class);
			List<ProponentePer> lista = (List<ProponentePer>) query.getResultList();
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		} finally {
			em.close();
			emf.close();
		}
	}
	
	
	public List<PropuestaPer> getPropuestasPer(String idProp) {
		// creo EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaCulturarte");
		EntityManager em = emf.createEntityManager();

		try {

			// Inicializo y creo la query
			em.getTransaction().begin();
			TypedQuery<PropuestaPer> query = em
					.createQuery("SELECT p FROM PropuestaPer p WHERE p.idProponente = :nombre", PropuestaPer.class);
			query.setParameter("nombre", idProp);

			List<PropuestaPer> lista = (List<PropuestaPer>) query.getResultList();
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		} finally {
			em.close();
			emf.close();
		}

	}
	
	public List<ColaboracionPer> getColaboracionesPer(String idPropu) {

		// creo EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaCulturarte");
		EntityManager em = emf.createEntityManager();

		try {
            
			// Inicializo y creo la query
			em.getTransaction().begin();
			TypedQuery<ColaboracionPer> query = em.createQuery(
					"SELECT c FROM ColaboracionPer c WHERE c.idPropuesta = :propuesta", ColaboracionPer.class);
			query.setParameter("propuesta", idPropu);

			List<ColaboracionPer> lista = (List<ColaboracionPer>) query.getResultList();
			return lista;

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			return null;
		} finally {
			em.close();
			emf.close();
		}

	}
	
	public void cargarCategorias() {
        
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaCulturarte");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); 
		TypedQuery<CategoriaPer> select = em.createQuery("SELECT c FROM CategoriaPer c", CategoriaPer.class);
		List<CategoriaPer> cate = select.getResultList();
		if (cate.isEmpty()) {

			CategoriaPer Teatro = new CategoriaPer();
			Teatro.setNombre("Teatro");
			Teatro.setPadre("");
			CategoriaPer teaDra = new CategoriaPer();
			teaDra.setNombre("Teatro Dramático");
			teaDra.setPadre("Teatro");
			CategoriaPer teaMus = new CategoriaPer();
			teaMus.setNombre("Teatro Musica");
			teaMus.setPadre("Teatro");
			CategoriaPer Comedia = new CategoriaPer();
			Comedia.setNombre("Comedia");
			Comedia.setPadre("Teatro");
			CategoriaPer standup = new CategoriaPer();
			standup.setNombre("Stand Up");
			standup.setPadre("Comedia");
			CategoriaPer lite = new CategoriaPer();
			lite.setNombre("Literatura");
			lite.setPadre("");
			CategoriaPer mus = new CategoriaPer();
			mus.setNombre("Música");
			mus.setPadre("");
			CategoriaPer fest = new CategoriaPer();
			fest.setNombre("Festival");
			fest.setPadre("Música");
			CategoriaPer con = new CategoriaPer();
			con.setNombre("Concierto");
			con.setPadre("Múusica");
			CategoriaPer cine = new CategoriaPer();
			cine.setNombre("Cine");
			cine.setPadre("");
			CategoriaPer cineAl = new CategoriaPer();
			cineAl.setNombre("Cine al Aire Libre");
			cineAl.setPadre("Cine");
			CategoriaPer cinepd = new CategoriaPer();
			cinepd.setNombre("Cine a Pedal");
			cinepd.setPadre("Cine");
			CategoriaPer dan = new CategoriaPer();
			dan.setNombre("Danza");
			dan.setPadre("");
			CategoriaPer ball = new CategoriaPer();
			ball.setNombre("Ballet");
			ball.setPadre("Danza");
			CategoriaPer fla = new CategoriaPer();
			fla.setNombre("Flamenco");
			fla.setPadre("Danza");
			CategoriaPer car = new CategoriaPer();
			car.setNombre("Carnaval");
			car.setPadre("");
			CategoriaPer mur = new CategoriaPer();
			mur.setNombre("Murga");
			mur.setPadre("Carnaval");
			CategoriaPer hum = new CategoriaPer();
			hum.setNombre("Humoristas");
			hum.setPadre("Murga");
			CategoriaPer par = new CategoriaPer();
			par.setNombre("Parodistas");
			par.setPadre("Carnaval");
			CategoriaPer lbl = new CategoriaPer();
			lbl.setNombre("Lubolos");
			lbl.setPadre("Carnaval");
			CategoriaPer rev = new CategoriaPer();
			rev.setNombre("Revista");
			rev.setPadre("Carnaval");

			try {
				//em.getTransaction().begin();
				em.persist(Teatro);
				em.persist(teaDra);
				em.persist(teaMus);
				em.persist(Comedia);
				em.persist(standup);
				em.persist(lite);
				em.persist(mus);
				em.persist(fest);
				em.persist(con);
				em.persist(cine);
				em.persist(cineAl);
				em.persist(cinepd);
				em.persist(dan);
				em.persist(ball);
				em.persist(fla);
				em.persist(car);
				em.persist(mur);
				em.persist(hum);
				em.persist(par);
				em.persist(lbl);
				em.persist(rev);
				em.getTransaction().commit();

			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
				emf.close();
			}

		}
	}
	
	
	
}
