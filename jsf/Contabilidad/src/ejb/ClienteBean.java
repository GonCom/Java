package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cliente;

/**
 * Session Bean implementation class ClienteBean
 */

@Stateless
public class ClienteBean {

	@PersistenceContext
	private EntityManager em;
	
    public ClienteBean() {
        // TODO Auto-generated constructor stub
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
    /**Metodo para cargar todos los clientes de la empresa*/
	 public List<Cliente> listarClientes(){
	    	Query consulta=em.createQuery("Select c From Cliente c");
	    	List<Cliente> clientes=consulta.getResultList();
	    	for(Cliente c:clientes){
	    		em.refresh(c);
	    	}
	    	return clientes;
	    }
}