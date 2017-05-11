package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ACTOSCONSULTA database table.
 * 
 */
@Embeddable
public class ActosconsultaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long consulta;

	@Column(insertable=false, updatable=false)
	private long actomedico;

	public ActosconsultaPK() {
	}
	public long getConsulta() {
		return this.consulta;
	}
	public void setConsulta(long consulta) {
		this.consulta = consulta;
	}
	public long getActomedico() {
		return this.actomedico;
	}
	public void setActomedico(long actomedico) {
		this.actomedico = actomedico;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActosconsultaPK)) {
			return false;
		}
		ActosconsultaPK castOther = (ActosconsultaPK)other;
		return 
			(this.consulta == castOther.consulta)
			&& (this.actomedico == castOther.actomedico);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.consulta ^ (this.consulta >>> 32)));
		hash = hash * prime + ((int) (this.actomedico ^ (this.actomedico >>> 32)));
		
		return hash;
	}
}