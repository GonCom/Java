package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long codcliente;

	private String apellido;

	private String dni;

	@Temporal(TemporalType.DATE)
	private Date fechanacimiento;

	private String nombre;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="cliente")
	private List<Empresa> empresas;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="cliente")
	private List<Factura> facturas;

	public Cliente() {
	}

	public long getCodcliente() {
		return this.codcliente;
	}

	public void setCodcliente(long codcliente) {
		this.codcliente = codcliente;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa addEmpresa(Empresa empresa) {
		getEmpresas().add(empresa);
		empresa.setCliente(this);

		return empresa;
	}

	public Empresa removeEmpresa(Empresa empresa) {
		getEmpresas().remove(empresa);
		empresa.setCliente(null);

		return empresa;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setCliente(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setCliente(null);

		return factura;
	}

}