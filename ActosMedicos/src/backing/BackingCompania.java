package backing;

import java.util.List;

import javax.ejb.EJB;

import entidades.Actomedico;
import entidades.Compania;


public class BackingCompania {
	
	@EJB
	private ejb.CompaniaBean ejbCompania;
	private Compania companiaNueva;
	private long[] idActosMed;
	private List<Actomedico> listadoActos;
	private List<Compania> listadocompania;
	private Compania compania;

	public BackingCompania() {
		
	}

	public ejb.CompaniaBean getEjbCompania() {
		return ejbCompania;
	}

	public void setEjbCompania(ejb.CompaniaBean ejbCompania) {
		this.ejbCompania = ejbCompania;
	}

	public Compania getCompaniaNueva() {
		return companiaNueva;
	}

	public void setCompaniaNueva(Compania companiaNueva) {
		this.companiaNueva = companiaNueva;
	}

	public long[] getIdActosMed() {
		return idActosMed;
	}

	public void setIdActosMed(long[] idActosMed) {
		this.idActosMed = idActosMed;
	}

	public List<Actomedico> getListadoActos() {
		return listadoActos;
	}

	public void setListadoActos(List<Actomedico> listadoActos) {
		this.listadoActos = listadoActos;
	}

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	/******************************************************************************************************************/
	
	public String prepararCompania(){
		compania=getEjbCompania().buscarCompania(companiaNueva.getCif());
		return "listadoActosMedicos.xhtml";
	}
	public String prepararNuevaCompania(){
		System.out.println("Entrando en preperarNuevaCompania...");
		listadoActos=getEjbCompania().listarActosMedicos();
		this.companiaNueva=new Compania();
		return "altaCompania.xhtml";
	}
	
	public String darAltaCompania(){
		System.out.println("Entrando en darAltaCompania...");
		getEjbCompania().altaNuevaCompania(companiaNueva);
		getEjbCompania().altaActosMedicos(idActosMed, companiaNueva.getCif());
		compania=getEjbCompania().buscarCompania(companiaNueva.getCif());
		this.companiaNueva=new Compania();
		return "listadoActosMedicos.xhtml";
	}
}
