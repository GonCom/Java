package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.sun.jmx.mbeanserver.Util;
import java.security.MessageDigest;

import entidades.Grupo;
import entidades.GrupoPK;
import entidades.Socio;
import entidades.Usuario;

public class DaoSocio extends BaseJPADao{
	
	EntityManager em;
	
	public DaoSocio(){
		
	}
	
	public List<Socio>sociosPorNombre(String socio){
		EntityManager em=getEntityManager();
		Query consulta=em.createQuery("select s from Socio s where UPPER(s.nombre) like :nombresocio");
		consulta.setParameter("nombresocio","%"+socio.toUpperCase()+"%");
		List<Socio>listaSocios=consulta.getResultList();
		return listaSocios;
	}
	
	public Socio buscarSocio(long idsocio) {
		EntityManager em = getEntityManager();
		Socio socio = em.find(Socio.class, idsocio);
		if (socio != null)
			em.refresh(socio);
		return socio;
	}
	
	public void actualizarSocio(Socio s){
		EntityManager em=getEntityManager();
		EntityTransaction tx;
		tx=em.getTransaction();
		tx.begin();
		em.merge(s);
		tx.commit();
	}
	
	public void altaSocio(Socio socio,String contraseña){
		EntityManager em=getEntityManager();
		EntityTransaction utx = em.getTransaction();
		String clave=Socio.encriptar(contraseña);
		Usuario user=new Usuario();
		user.setUsuario(socio.getNombre());
		user.setClave(clave);
		GrupoPK grupoPK=new GrupoPK();
		grupoPK.setIdgrupo("sociosbiblioteca");
		grupoPK.setIdusuario(socio.getNombre());
		Grupo grupo=new Grupo();
		grupo.setId(grupoPK);
		
		utx.begin();
		em.persist(socio);
		em.persist(user);
		utx.commit();
		
		utx.begin();
		em.persist(grupo);
		utx.commit();
		
	}
	
	public List<Socio> listarSocios(int comienzo, int numReg){
		EntityManager em=getEntityManager();
		Query consulta=em.createQuery("Select s from Socio s order by s.idsocio");
		consulta.setFirstResult(comienzo);
		consulta.setMaxResults(numReg);
		List<Socio>socios=consulta.getResultList();
		for(Socio s:socios){
			em.refresh(s);
		}
		return socios;
	}

	
	
	public long numRegistros(){
		long registros;
		EntityManager em=getEntityManager();
		Query consulta=em.createQuery("Select count(s) from Socio s");
		registros=(long) (consulta.getSingleResult());
		return registros;
	}
	
}