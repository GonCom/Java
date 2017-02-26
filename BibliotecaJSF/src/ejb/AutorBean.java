package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Autor;


@Stateless
public class AutorBean {
	@PersistenceContext
	private EntityManager em;
	
    public AutorBean() {
       
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
    
	public Autor crearAutor(Autor a){
		em.persist(a);  
		return a;
	}

	public List<Autor> buscarAutor(String iniciales){
		Query consulta=em.createQuery("Select a from Autor a where UPPER(a.nombre) like :iniciales");
		consulta.setParameter("iniciales", "%"+iniciales.toUpperCase()+"%");
		List<Autor> listadoAutores=consulta.getResultList();
		for(Autor a: listadoAutores){
			em.refresh(a);
		}
		return listadoAutores;
	}
	
	public void eliminarAutor(long idautor){
		em.remove(em.find(Autor.class, idautor));
	}
}
