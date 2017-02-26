package backing;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import util.PaginacionAyuda;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;

public class BackingMoroso {

	@EJB
	private ejb.PrestamoBean ejbPrestamo;
	private List<Socio> listadoMorosos;
	private PaginacionAyuda paginacion;
	private int regPag;
	private List<Object> listadolibros;
	private Socio socioMorosoSeleccionado;
	
	public BackingMoroso() {
		// TODO Auto-generated constructor stub
	}

	public ejb.PrestamoBean getEjbPrestamo() {
		return ejbPrestamo;
	}

	public void setEjbPrestamo(ejb.PrestamoBean ejbPrestamo) {
		this.ejbPrestamo = ejbPrestamo;
	}

	public List<Socio> getListadoMorosos() {
		return listadoMorosos;
	}

	public void setListadoMorosos(List<Socio> listadoMorosos) {
		this.listadoMorosos = listadoMorosos;
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
	
	
	public List<Object> getListadolibros() {
		return listadolibros;
	}

	public void setListadolibros(List<Object> listadolibros) {
		this.listadolibros = listadolibros;
	}

	public Socio getSocioMorosoSeleccionado() {
		return socioMorosoSeleccionado;
	}

	public void setSocioMorosoSeleccionado(Socio socioMorosoSeleccionado) {
		this.socioMorosoSeleccionado = socioMorosoSeleccionado;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Metodo para listar a los socios morosos cuando accedemos a través del enlace del menú
	public String prepararListadoMorosos(){
		if(paginacion==null){
			paginacion=new PaginacionAyuda(5,0){

				@Override
				public long getItemsCount() {
					return getEjbPrestamo().getTotalSocios();
				}
				
			};
		}
		if(listadoMorosos!=null){
			paginacion.setPagina(0);
			paginacion.setNumRegPag(5);
			regPag=5;
		}
		listadoMorosos=getEjbPrestamo().listadoSociosMorosos(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		return "/admin/listadomorosos.xhtml";
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Metodo que reseta la paginacion cuando cambiamos el nº e registros a mostrar en el menu select
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
		listadoMorosos=getEjbPrestamo().sociosEnRango(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String PaginaSiguiente(){
		paginacion.getPaginaSig();
		setListadolibros(null);
		listadoMorosos=getEjbPrestamo().sociosEnRango(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		return "/admin/listadosocios.xhtml";
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String PaginaAnterior(){
		getPaginacion().getPaginaAnt();
		setListadolibros(null);
		listadoMorosos=getEjbPrestamo().sociosEnRango(paginacion.getPagina()*paginacion.getNumRegPag(), paginacion.getNumRegPag());
		return "/admin/listadosocios.xhtml";
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String listarLibrosMorosos(){
		listadolibros=getEjbPrestamo().verLibrosDemora(socioMorosoSeleccionado.getIdsocio());
	
		return "/admin/listadomorosos.xhtml";
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getTotalLibrosDemora(){
		System.out.println("Entrando en el metodo getTotalLibrosDemora...");
		if(listadolibros!=null){
			return listadolibros.size();
		}else{
			return 0;
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
