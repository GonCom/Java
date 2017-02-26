package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidades.Autor;

public class DaoAutor extends BaseJPADao{
	EntityManager em;
	
	public DaoAutor() {
		
	}
	
	public void crearAutor(Autor a) {
		EntityManager em = getEntityManager();
		EntityTransaction utx = em.getTransaction();
		utx.begin();
		em.persist(a);
		utx.commit();
	}

	public List<Autor> getAllAutores() {
		EntityManager em = getEntityManager();
		Query allAutoresQuery = em.createQuery("select a from Autor a");
		List<Autor> allAutores = (List<Autor>) allAutoresQuery.getResultList();
		for(Autor a:allAutores)
			em.refresh(a);
		return allAutores;
	}
}
