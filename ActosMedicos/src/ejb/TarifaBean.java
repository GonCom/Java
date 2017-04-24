package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Tarifa;


@Stateless
public class TarifaBean {

   @PersistenceContext
   private EntityManager em;
   
    public TarifaBean() {
       
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

    
	public void modificarTarifa(Tarifa t){
		em.merge(t);
	}
}
