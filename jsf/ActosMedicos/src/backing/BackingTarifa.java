package backing;

import javax.ejb.EJB;

import entidades.Tarifa;

public class BackingTarifa {

	@EJB
	private ejb.TarifaBean ejbTarifa;
	private Tarifa tarifa;
	
	public BackingTarifa() {
		// TODO Auto-generated constructor stub
	}

	public ejb.TarifaBean getEjbTarifa() {
		return ejbTarifa;
	}

	public void setEjbTarifa(ejb.TarifaBean ejbTarifa) {
		this.ejbTarifa = ejbTarifa;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	/****************************************************************************************************************/
	
	public String preparareditarPrecio(){
		return "editarActoMedico.xhtml";
	}
	
	public String modificarTarifa(){
		getEjbTarifa().modificarTarifa(tarifa);
		return "listadoActosMedicos.xhtml";
	}
}
