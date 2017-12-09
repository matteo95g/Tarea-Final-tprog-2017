package manejadores;


import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import controladores.Configuracion;
import logica.Registros;

public class ManejadorRegistros {
	
    private static ManejadorRegistros  instancia = null;	
    
    private ManejadorRegistros() {
    	
    }
    
    
	public static ManejadorRegistros getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorRegistros();
		}
		return instancia;
	}
	
	public void addRegistro(Registros reg) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaRegistros");
		EntityManager em = emf.createEntityManager();
		verificarRegistros();
		try {

			em.getTransaction().begin();
			em.persist(reg);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}

	}
	
	
	public List<Registros> getRegistros(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaRegistros");
		EntityManager em = emf.createEntityManager();
		List<Registros> listaRegistros = null;

		try {

			em.getTransaction().begin();

			TypedQuery<Registros> select = em.createQuery("SELECT r FROM Registros r ORDER BY r.fecha DESC",
					Registros.class);

			listaRegistros = select.getResultList();
			


		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}
		return  listaRegistros;
	}
	
	private void verificarRegistros(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenciaRegistros");
		EntityManager em = emf.createEntityManager();
		List<Registros> listaRegistros = null;

		try {

			em.getTransaction().begin();

			TypedQuery<Registros> select = em.createQuery("SELECT r FROM Registros r ORDER BY r.fecha DESC",
					Registros.class);

			listaRegistros = select.getResultList();
			
			if (listaRegistros.size() >= 10000) {

				Registros regEliminar = listaRegistros.get(9999);
				em.remove(regEliminar);
				em.getTransaction().commit();

			}
			
			for(Object obj : listaRegistros){
				Registros regAux = (Registros) obj;
				if(Configuracion.daysBetween(regAux.getFecha(), Calendar.getInstance()) > 30){
					em.remove(regAux);
					em.getTransaction().commit();
				}
			}
			


		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
			emf.close();
		}
		
	}

}
