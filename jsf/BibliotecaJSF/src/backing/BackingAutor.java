package backing;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entidades.Autor;


public class BackingAutor {
	@EJB
	private ejb.AutorBean ejbAutor;
	private Autor autor;
	private String iniciales;
	private List<Autor> listadoAutores;
	private long idautorBorrar;
	
	public BackingAutor() {
		
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	
	public ejb.AutorBean getEjbAutor() {
		return ejbAutor;
	}

	

	public String getIniciales() {
		return iniciales;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	public void setEjbAutor(ejb.AutorBean ejbAutor) {
		this.ejbAutor = ejbAutor;
	}

	
	public List<Autor> getListadoAutores() {
		return listadoAutores;
	}

	public void setListadoAutores(List<Autor> listadoAutores) {
		this.listadoAutores = listadoAutores;
	}

	public long getIdautorBorrar() {
		return idautorBorrar;
	}

	public void setIdautorBorrar(long idautorBorrar) {
		this.idautorBorrar = idautorBorrar;
	}

	public String prepararCrearAutor(){
		System.out.println("Instanciando el autor");
		this.autor=new Autor();
		return "/admin/altaautor.xhtml";
	}
	
	public String prepararEliminarAutor(){
		System.out.println("Entrando en prepararEliminarAutor...");
		return "/admin/eliminarAutor.xhtml";
	}
	
	public String nuevoAutor() {
		System.out.println("En nuevo autor: "+autor.getIdautor()+" "+autor.getNombre());
		getEjbAutor().crearAutor(autor);
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(archivomensajes.getString("generico.registroCreadoConExito")));
		System.out.println("Antes de llamar a prepararCrearAutor por tanto debe haber nueva instancia");
		return prepararCrearAutor();
		/* Esta segunda opción que se nos podría ocurrir no
		* resuelve que el control del control de transacciones
		* se encargue el contenedor.
		* Para ello hay que hacer el persist en un objeto
		* gestionado por el contenedor como un enterprise java bean.
		*
		getAutorFacade().getEntityManager().persist(autor);
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(archivomensajes.getString("registroCreadoConExito")));
		return prepararCrearAutor();*/
		}

	public String busquedaAutor(){
		listadoAutores=getEjbAutor().buscarAutor(iniciales);
		return "/admin/eliminarAutor.xhtml";
	}
	
	public String eliminarAutor(){
		getEjbAutor().eliminarAutor(idautorBorrar);
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(archivomensajes.getString("generico.autorEliminado")));
		listadoAutores=getEjbAutor().buscarAutor(iniciales);
		return "/admin/eliminarAutor.xhtml";
	}
	
	public int getTotalAutoresEliminar(){
		if(listadoAutores!=null){
			return listadoAutores.size();
		}else{
			return 0;
		}
	}
}
