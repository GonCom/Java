package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Ejemplar;
import entidades.Libro;

/**
 * Session Bean implementation class LibroBean
 */
@Stateless
public class LibroBean {

	@PersistenceContext
	private EntityManager em;

	public LibroBean() {

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<Object> listarLibros(String autor,String titulo,String isbn){
		Query consulta=em.createNativeQuery("SELECT LI.ISBN,TRANSLATE(UPPER(NOMBRE),'Á,É,Í,Ó,Ú','A,E,I,O,U')NOMBRE,TITULO,EJEMPLARESTOTALES,NVL(B.EJEMPLARESPRESTADOS,0)EJEMPLARESPRESTADOS,(EJEMPLARESTOTALES-NVL(B.EJEMPLARESPRESTADOS,0))EJEMPLARESDISPONIBLES"
				+ " FROM LIBRO LI,AUTOR AU,(SELECT L.ISBN, COUNT(*)EJEMPLARESTOTALES"
				+ " FROM EJEMPLAR E,LIBRO L"
				+ " WHERE E.ISBN=L.ISBN"
				+ " GROUP BY L.ISBN)A,"
				+ " (SELECT L.ISBN, COUNT(*)EJEMPLARESPRESTADOS"
				+ " FROM EJEMPLAR E,LIBRO L,PRESTAMO P"
				+ " WHERE E.ISBN=L.ISBN"
				+ " AND P.IDEJEMPLAR=E.IDEJEMPLAR"
				+ " GROUP BY L.ISBN)B"
				+ " WHERE  A.ISBN=B.ISBN(+)"
				+ " AND LI.ISBN=A.ISBN"
				+ " AND AU.IDAUTOR=LI.IDAUTOR"
				+ " AND TRANSLATE(UPPER(NOMBRE),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
				+ " AND TRANSLATE(UPPER(TITULO),'Á,É,Í,Ó,Ú','A,E,I,O,U') LIKE TRANSLATE(UPPER(?),'Á,É,Í,Ó,Ú','A,E,I,O,U')"
				+ " AND LI.ISBN LIKE ? " + " ORDER BY LI.ISBN");
		consulta.setParameter(1, "%" + autor + "%");
		consulta.setParameter(2, "%" + titulo + "%");
		consulta.setParameter(3, isbn);
		List<Object> libros=consulta.getResultList();
		return libros;	
	}
	
	public Libro buscarLibro(String isbn) {
		Libro libro=em.find(Libro.class, isbn);
		em.refresh(libro);
		return libro;
	}
	
	public List<Ejemplar> listarEjemplares(String isbn){
		Query consulta=em.createQuery("Select e from Ejemplar e"+
                                      " where e.libro.isbn=:isbn"+
                                      " and e.idejemplar not in(Select p.idejemplar from Prestamo p" +
                                                               " where p.ejemplar.libro.isbn=:isbn)");
		consulta.setParameter("isbn", isbn);
		List<Ejemplar>ejemplares=consulta.getResultList();
		for(Ejemplar e : ejemplares){
			em.refresh(e);
		}
		return ejemplares;
	}
	
	public void eliminarEjemplar(long [] ejemplares){
		System.out.println("Entrando en eliminarEjemplar()...");
		for(long ejemplar : ejemplares){
			System.out.println("Ejemplares: " + ejemplar);
		}
		for(long ejemplar : ejemplares){
			em.remove(em.find(Ejemplar.class, ejemplar));
		}
	}

}
