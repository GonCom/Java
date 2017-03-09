package ejb;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Ciclo;
import entidades.Ofertaeducativa;
import entidades.OfertaeducativaPK;
import entidades.Turno;

/**
 * Session Bean implementation class OfertaBean
 */
@Stateless
public class OfertaBean {

	@PersistenceContext
	private EntityManager em;
	
    public OfertaBean() {
        // TODO Auto-generated constructor stub
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
    
	/*************************************************************************************************************/
	
	public void modificarPlazasOferta(Ofertaeducativa o){
		em.merge(o);
	}
	
	public List<Ciclo> listarCiclos(long idies){
		Query consultaQuery=em.createQuery("select c from Ciclo c where c.idciclo not in "+
											"(select o.ciclo.idciclo from Ofertaeducativa o where o.IES.idies=:idies)");
		consultaQuery.setParameter("idies", idies);
		List<Ciclo> ciclos=consultaQuery.getResultList();
		for(Ciclo c : ciclos){
			em.refresh(c);
		}
		return ciclos;
	}
	
	public List<Turno> listarTurnos(){
		Query consulta=em.createQuery("Select t from Turno t");
		List<Turno> turnos=consulta.getResultList();
		for(Turno t : turnos){
			em.refresh(t);
		}
		return turnos;
	}
	
	public void eliminarOferta(Ofertaeducativa o){
		System.out.println("Entrando en eliminarOferta...");
		em.remove(em.find(Ofertaeducativa.class, o.getId()));
	}
	
	public List<Ofertaeducativa> mostrarOfertasEduc(long idies){
		Query consulta=em.createQuery("Select o From Ofertaeducativa o where o.IES.idies= :idies");
		consulta.setParameter("idies", idies);
		List<Ofertaeducativa> ofertas=consulta.getResultList();
		for(Ofertaeducativa o:ofertas){
			em.refresh(o);
		}
		return ofertas;
	}
	
	public void añadirNuevaOferta(OfertaeducativaPK nuevaOferta,BigDecimal plazas){
		System.out.println("Entrando en añadirNuevaOferta...");
		Ofertaeducativa oferta=new Ofertaeducativa();
		oferta.setPlazas(plazas);
		oferta.setId(nuevaOferta);
		em.persist(oferta);
	}
}