package backing;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import util.PaginacionAyuda;
import entidades.Socio;

public class BackingSocio {

	private String iniciales = null;
	private List<Socio> socios;
	private Socio socioseleccionado;
	private PaginacionAyuda paginacion;
	private int regPag;

	@EJB
	private ejb.SocioBean ejbSocio;

	public BackingSocio() {
		// TODO Auto-generated constructor stub
	}

	public String getIniciales() {
		return iniciales;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	public List<Socio> getSocios() {
		return socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	public Socio getSocioseleccionado() {
		return socioseleccionado;
	}

	public void setSocioseleccionado(Socio socioseleccionado) {
		this.socioseleccionado = socioseleccionado;
	}

	public ejb.SocioBean getEjbSocio() {
		return ejbSocio;
	}

	public void setEjbSocio(ejb.SocioBean ejbSocio) {
		this.ejbSocio = ejbSocio;
	}
	

	public PaginacionAyuda getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(PaginacionAyuda paginacion) {
		this.paginacion = paginacion;
	}

	public int getRegPag() {
		return regPag;
	}

	public void setRegPag(int regPag) {
		this.regPag = regPag;
	}

	public String prepararSocio() {
		if (socios != null)
			socios = null;
		return "/admin/buscarSocio.xhtml";
	}
	
	public String prepararListadoSocios(){
		if(paginacion==null){
			paginacion=new PaginacionAyuda(5,0){

				@Override
				public long getItemsCount() {
					return getEjbSocio().getTotalSocios();
				}
				
			};
		}
		if(socios!=null){
			paginacion.setPagina(0);
			paginacion.setNumRegPag(5);
			regPag=5;
		}
		socios=getEjbSocio().listarSocios(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		return "/admin/listadosocios.xhtml";
	}

	public int gettotalSociosModificar(){
		if(socios!=null){
			return socios.size();
		}else{
			return 0;
		}
	}
	
	public String mostrarSocio() {
		System.out.println("Entrando en el metodo mostrarSocio()...");
		socios = getEjbSocio().getSocio(iniciales);
		System.out.println("Listado socios: " + socios.size());
		return "/admin/buscarSocio.xhtml";
	}

	public String editarSocio() {
		System.out.println("Entrando en el metodo editarSocio()...");
		FacesContext fc=FacesContext.getCurrentInstance();
		Map<String,String> ruta=fc.getExternalContext().getRequestParameterMap();
		String url=ruta.get("pagina");
		fc.getCurrentInstance().getExternalContext().getSessionMap().put("pagina", url);
		return "/admin/editarSocio.xhtml";
	}

	public String actualizarSocio() {
		System.out.println("Entrando en el metodo actualizarSocio()...");
		getEjbSocio().modificarSocio(socioseleccionado);
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle archivomensajes = ResourceBundle.getBundle(
				"resources.application", context.getViewRoot().getLocale());
		context.addMessage(null,new FacesMessage(archivomensajes.getString("generico.registroActualizado")));
		return null;
	}
	
	public String volverAtras(){
		String url=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pagina");
		return url;
	}
	
	public void resetPaginacion(){
		/* Procedimiento que recalcula el número de página en función de como se sube y baja el
		* numero de registros por página. A este procedimiento se le llama mediante peticion ajax asociada
		* al cuadro combinado que permite seleccionar los registros por pagina. EL valor seleccionado
		* esta asociado a la propiedad slctnrpag de nuestro backing bean.
		*/
		int nuevapagina=(paginacion.getPrimerElemento()/regPag);
		/*System.out.println("Nueva pagina: "+nuevapagina);
		System.out.println("Nuevos registros por pagina: "+slctnrpag);*/
		paginacion.setNumRegPag(regPag);
		paginacion.setPagina(nuevapagina);
		socios=getEjbSocio().sociosEnRango(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		}

	public String PaginaSiguiente(){
		getPaginacion().getPaginaSig();
		
		socios=getEjbSocio().sociosEnRango(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		return "/admin/listadosocios.xhtml";
	}
	
	public String PaginaAnterior(){
		getPaginacion().getPaginaAnt();
		
		socios=getEjbSocio().sociosEnRango(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		return "/admin/listadosocios.xhtml";
	}
}
