package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SOCIO database table.
 * 
 */
@Entity
@NamedQuery(name="Socio.findAll", query="SELECT s FROM Socio s")
public class Socio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOCIO_IDSOCIO_GENERATOR", sequenceName="S_SOCIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOCIO_IDSOCIO_GENERATOR")
	private long idsocio;

	private String direccion;

	private String nombre;

	public Socio() {
	}

	public long getIdsocio() {
		return this.idsocio;
	}

	public void setIdsocio(long idsocio) {
		this.idsocio = idsocio;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}