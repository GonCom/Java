package ejb;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.IES;

/**
 * Session Bean implementation class IESBean
 */
@Stateless
public class IESBean {

	@PersistenceContext
	private EntityManager em;
	
    public IESBean() {
        // TODO Auto-generated constructor stub
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
    
    public List<IES> listarInstitutos(){
    	Query consulta=em.createQuery("Select i From IES i");
    	List<IES> institutos=consulta.getResultList();
    	for(IES i:institutos){
    		em.refresh(i);
    	}
    	return institutos;
    }

}
