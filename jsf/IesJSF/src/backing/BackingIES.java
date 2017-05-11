package backing;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import entidades.IES;

public class BackingIES {

	@EJB
	private ejb.IESBean ejbIES;
	private List<IES> listadoIes;
	
	public BackingIES() {
		// TODO Auto-generated constructor stub
	}

	public ejb.IESBean getEjbIES() {
		return ejbIES;
	}

	public void setEjbIES(ejb.IESBean ejbIES) {
		this.ejbIES = ejbIES;
	}

	public List<IES> getListadoIes() {
		return listadoIes;
	}

	public void setListadoIes(List<IES> listadoIes) {
		this.listadoIes = listadoIes;
	}

	/**********************************************************************************************************************/
	public String prepararInstitutos(){
		
		listadoIes=getEjbIES().listarInstitutos();
		
		return "listarIES.xhtml";
	}
	
}
