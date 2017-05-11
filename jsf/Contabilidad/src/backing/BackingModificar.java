package backing;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import entidades.Cliente;
import entidades.Factura;

public class BackingModificar {
	
	@EJB
	private ejb.ModificarBean ejbModificar;
	private List<Factura> listadoFacturas;
	private Cliente cliente;
	private Long idcliente;
	public BackingModificar() {
		// TODO Auto-generated constructor stub
	}

	public ejb.ModificarBean getEjbModificar() {
		return ejbModificar;
	}

	public void setEjbModificar(ejb.ModificarBean ejbModificar) {
		this.ejbModificar = ejbModificar;
	}

	public List<Factura> getListadoFacturas() {
		return listadoFacturas;
	}

	public void setListadoFacturas(List<Factura> listadoFacturas) {
		this.listadoFacturas = listadoFacturas;
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	/*****************Mostrar facturas por idcliente****************/
	
	public String mostrarFacturasCliente(){
		listadoFacturas=getEjbModificar().listarFacturaCliente(idcliente);
		return "clientes.xhtml";
	}
	
	public String editarCliente(){
		FacesContext fc=FacesContext.getCurrentInstance();
		Map<String,String> ruta=fc.getExternalContext().getRequestParameterMap();
		String url=ruta.get("pagina");
		fc.getCurrentInstance().getExternalContext().getSessionMap().put("pagina", url);
		System.out.println("LLega a editar cliente " + cliente);
		return "editarClientes.xhtml";
	}
}
