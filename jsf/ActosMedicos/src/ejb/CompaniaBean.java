package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Actomedico;
import entidades.Compania;

/**
 * Session Bean implementation class CompaniaBean
 */
@Stateless
public class CompaniaBean {

	@PersistenceContext
    private EntityManager em;
	
    public CompaniaBean() {
        // TODO Auto-generated constructor stub
    }

    
    public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}



	public List<Actomedico> listarActosMedicos(){
    	Query consulta=em.createQuery("Select am From Actomedico am");
    	List<Actomedico>listadoActos=consulta.getResultList();
    	for(Actomedico a:listadoActos){
    		em.refresh(a);
    	}
    	return listadoActos;
    }
    
    public void altaNuevaCompania(Compania compania){
    	em.persist(compania);
    }
    
    public void altaActosMedicos(long[]actos,String compania){
    	for(int i=0;i<actos.length;i++){
    		Query consulta=em.createNativeQuery("Insert into Tarifa(COMPANIA,ACTOMEDICO) Values(?,?)");
    		consulta.setParameter(1, compania);
    		consulta.setParameter(2, actos[i]);
    		consulta.executeUpdate();
    	}
    }
    
    public Compania buscarCompania(String cif){
    	Query consultaQuery=em.createQuery("Select c From Compania c where c.cif=:cif");
    	consultaQuery.setParameter("cif", cif);
    	Compania com=(Compania) consultaQuery.getSingleResult();
    	em.refresh(com);
    	return com;
    }
}
