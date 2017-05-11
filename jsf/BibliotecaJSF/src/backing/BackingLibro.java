package backing;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import entidades.Ejemplar;
import entidades.Libro;

public class BackingLibro {

	@EJB
	private ejb.LibroBean ejbLibro;
	
	private List<Object> listadoLibros;
	private String textoBusqueda;
	private String opcionBusqueda;
	private String isbn;
	private Libro libroSeleccionado;
	private List<Ejemplar> listadoejemplares;
	private long[] ejemplares;
	
	public BackingLibro() {
		
	}

	public ejb.LibroBean getEjbLibro() {
		return ejbLibro;
	}

	public void setEjbLibro(ejb.LibroBean ejbLibro) {
		this.ejbLibro = ejbLibro;
	}

	public List<Object> getListadoLibros() {
		return listadoLibros;
	}

	public void setListadoLibros(List<Object> listadoLibros) {
		this.listadoLibros = listadoLibros;
	}
	
	public String getTextoBusqueda() {
		return textoBusqueda;
	}

	public void setTextoBusqueda(String textoBusqueda) {
		this.textoBusqueda = textoBusqueda;
	}

	public String getOpcionBusqueda() {
		return opcionBusqueda;
	}

	public void setOpcionBusqueda(String opcionBusqueda) {
		this.opcionBusqueda = opcionBusqueda;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public Libro getLibroSeleccionado() {
		return libroSeleccionado;
	}

	public void setLibroSeleccionado(Libro libroSeleccionado) {
		this.libroSeleccionado = libroSeleccionado;
	}

	public long[] getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(long[] ejemplares) {
		this.ejemplares = ejemplares;
	}

	public List<Ejemplar> getListadoejemplares() {
		return listadoejemplares;
	}

	public void setListadoejemplares(List<Ejemplar> listadoejemplares) {
		this.listadoejemplares = listadoejemplares;
	}

/*********************************************************************************************************************/
/*********************************************************************************************************************/

	public String prepararConsultarLibro(){
		System.out.println("Entrando en prepararConsultarLibro...");
		return "/admin/listadolibros.xhtml";
	}
	
	public String listarLibrosBusqueda(){
		if(opcionBusqueda.equals("autor")){
			listadoLibros=getEjbLibro().listarLibros(textoBusqueda, "%", "%");
		}
		if(opcionBusqueda.equals("titulo")){
			listadoLibros=getEjbLibro().listarLibros("%",textoBusqueda,"%");
		}
		if(opcionBusqueda.equals("isbn")){
			listadoLibros=getEjbLibro().listarLibros("%", "%",textoBusqueda);
		}
		
		return "/admin/listadolibros.xhtml";
	}

	public int getTotalLibrosBusqueda(){
		if(listadoLibros!=null){
			return listadoLibros.size();
		}else{
			return 0;
		}
	}
	
	public String listarEjemplaresLibroSeleccionado(){
		libroSeleccionado=getEjbLibro().buscarLibro(isbn);
		listadoejemplares=getEjbLibro().listarEjemplares(isbn);
		return "/admin/listadolibros.xhtml";
	}
	
	public String eliminarEjemplares(){
		System.out.println("Entrando en eliminarEjemplares()...");
		getEjbLibro().eliminarEjemplar(ejemplares);
		FacesContext context=FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes=ResourceBundle.getBundle("resources.application",context.getViewRoot().getLocale());
		context.addMessage(null, new FacesMessage(archivomensajes.getString("generico.ejemplaresEliminados")));
		libroSeleccionado=getEjbLibro().buscarLibro(isbn);
		return "/admin/listadolibros.xhtml";
	}
	
	public int getTotalEjemplares(){
		if(ejemplares!=null){
			return ejemplares.length;
		}else{
			return 0;
		}
	}
}
