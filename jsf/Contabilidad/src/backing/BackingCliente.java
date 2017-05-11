package backing;

import java.util.List;

import javax.ejb.EJB;

import entidades.Cliente;

public class BackingCliente {
	
	@EJB
	private ejb.ClienteBean ejbCliente;
	private List<Cliente> listadoclientes;

	public BackingCliente() {
		// TODO Auto-generated constructor stub
	}

	public ejb.ClienteBean getEjbCliente() {
		return ejbCliente;
	}

	public void setEjbCliente(ejb.ClienteBean ejbCliente) {
		this.ejbCliente = ejbCliente;
	}

	public List<Cliente> getListadoclientes() {
		return listadoclientes;
	}

	public void setListadoclientes(List<Cliente> listadoclientes) {
		this.listadoclientes = listadoclientes;
	}
	
	public String prepararClientes(){
			listadoclientes=getEjbCliente().listarClientes();
		return "clientes.xhtml";
	}	
}