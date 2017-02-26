package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Socio;

/**
 * Session Bean implementation class SocioBean
 */
@Stateless
@LocalBean
public class SocioBean {

	@PersistenceContext
	private EntityManager em;
	
    public SocioBean() {
       
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
    
	
	public List<Socio> getSocio(String iniciales){
		System.out.println("Entrando en el metodo getSocio()...");
		Query consulta=em.createQuery("SELECT s FROM Socio s WHERE UPPER(s.nombre) LIKE :iniciales");
		consulta.setParameter("iniciales","%"+iniciales.toUpperCase()+"%");
		List<Socio> listadoSocios=consulta.getResultList();
		for(Socio s: listadoSocios){
			em.refresh(s);
		}
		return listadoSocios;
	}

	public void modificarSocio(Socio s){
		em.merge(s);
	}
	
	public long getTotalSocios(){
		Query consulta=em.createQuery("Select count(s) from Socio s");
		return (long) consulta.getSingleResult();
	}
	
	public List<Socio> listarSocios(int comienzo, int numReg){
		Query consulta=em.createQuery("Select s from Socio s order by s.idsocio");
		consulta.setFirstResult(comienzo);
		consulta.setMaxResults(numReg);
		List<Socio>socios=consulta.getResultList();
		for(Socio s:socios){
			em.refresh(s);
		}
		return socios;
	}
	
	public List<Socio>sociosEnRango(int primerResultado,int maxResultados){
		Query consulta=em.createQuery("Select s from Socio s");
		consulta.setFirstResult(primerResultado);
		consulta.setMaxResults(maxResultados);
		List<Socio>listaSocios= consulta.getResultList();
		for(Socio s:listaSocios){
			em.refresh(s);
		}
		return listaSocios;
	}
	
	
    
}
