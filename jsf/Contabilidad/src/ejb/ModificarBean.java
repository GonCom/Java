package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cliente;
import entidades.Factura;

/**
 * Session Bean implementation class ModificarBean
 */
@Stateless
public class ModificarBean {
	
	@PersistenceContext
	private EntityManager em;
    
    public ModificarBean() {
        // TODO Auto-generated constructor stub
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
    
	/****************************************************************/
	
	public void modificarCliente(Cliente c){
		em.merge(c);
	}
	/*******************Listado de facturas por cliente*************************/
	public List<Factura> listarFacturaCliente(long idcliente){
		Query consulta = em.createQuery("select c from Cliente c where c.codcliente =:codcliente");
		consulta.setParameter("idies",idcliente );
		List<Factura> facturas = consulta.getResultList();
		for(Factura f : facturas){
			em.refresh(f);
		}
		return facturas;	
	}
}