package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidades.Libro;

public class DaoLibro extends BaseJPADao{

	EntityManager em;
	
	public DaoLibro() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Object> listadoLibros(String autor,String titulo,String isbn){
		em=getEntityManager();
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
	
	
	public boolean buscarLibro(String isbn){
		em=getEntityManager();
		Libro libro=em.find(Libro.class, isbn);
		if(libro==null){
			return false;
		}else{
			return true;
		}
	}
	
	public void eliminarLibros(String isbn){
		Libro l =new Libro();
		l.setIsbn(isbn);
		em=getEntityManager();
		EntityTransaction et= em.getTransaction();
		et.begin();
		l=em.find(Libro.class, l.getIsbn());
		em.remove(l);
		et.commit();
	}

}
