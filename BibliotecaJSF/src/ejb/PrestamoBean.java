package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;

/**
 * Session Bean implementation class PrestamoBean
 */
@Stateless
@LocalBean
public class PrestamoBean {

	@PersistenceContext
    private EntityManager em;
	
    public PrestamoBean() {
        
    }

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

    
	public List<Socio> listadoSociosMorosos(int comienzo,int numReg){
		System.out.println("Entrando a listadoSociosMorosos...");
		Query consulta=em.createQuery("select distinct(p.socio) from Prestamo p where FUNC('TRUNC',p.fechalimitedevolucion)<FUNC('TRUNC',current_date)");
		consulta.setFirstResult(comienzo);
		consulta.setMaxResults(numReg);
		List<Socio>listadoMorosos=consulta.getResultList();
		for(Socio s: listadoMorosos){
			em.refresh(s);
		}
		
		return listadoMorosos;
	}
	
	public long getTotalSocios(){
		Query consulta=em.createQuery("select count(distinct(p.socio)) from Prestamo p where FUNC('TRUNC',p.fechalimitedevolucion)<FUNC('TRUNC',current_date)");
		return (long) consulta.getSingleResult();
	}

	public List<Socio>sociosEnRango(int primerResultado,int maxResultados){
		Query consulta=em.createQuery("select distinct(p.socio) from Prestamo p where FUNC('TRUNC',p.fechalimitedevolucion)<FUNC('TRUNC',current_date)");
		consulta.setFirstResult(primerResultado);
		consulta.setMaxResults(maxResultados);
		List<Socio>listaMorosos= consulta.getResultList();
		for(Socio s:listaMorosos){
			em.refresh(s);
		}
		return listaMorosos;
	}
	
	public List<Object> verLibrosDemora(long idsocio){
		Query consulta=em.createNativeQuery("SELECT L.TITULO,TRUNC(SYSDATE-FECHALIMITEDEVOLUCION)DIAS FROM LIBRO L,EJEMPLAR E,PRESTAMO P WHERE P.IDEJEMPLAR=E.IDEJEMPLAR  AND E.ISBN=L.ISBN AND P.IDSOCIO=?");
		consulta.setParameter(1, idsocio);
		List<Object> librosDemora=consulta.getResultList();
		return librosDemora;
	}
}
