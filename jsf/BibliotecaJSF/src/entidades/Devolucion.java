package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the DEVOLUCION database table.
 * 
 */
@Entity
@NamedQuery(name="Devolucion.findAll", query="SELECT d FROM Devolucion d")
public class Devolucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DevolucionPK id;

	@Temporal(TemporalType.DATE)
	private Date fechadevolucion;

	public Devolucion() {
	}

	public DevolucionPK getId() {
		return this.id;
	}

	public void setId(DevolucionPK id) {
		this.id = id;
	}

	public Date getFechadevolucion() {
		return this.fechadevolucion;
	}

	public void setFechadevolucion(Date fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}

}