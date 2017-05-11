package entidades;

public class Ofertaeducativa {
	
	private long idies;
	private long idciclo;
	private long turno;
	private long plazas;
	private String descripcion;
	private String nombre;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getIdies() {
		return idies;
	}

	public void setIdies(long idies) {
		this.idies = idies;
	}

	public long getIdciclo() {
		return idciclo;
	}

	public void setIdciclo(long idciclo) {
		this.idciclo = idciclo;
	}

	public long getTurno() {
		return turno;
	}

	public void setTurno(long turno) {
		this.turno = turno;
	}

	public long getPlazas() {
		return plazas;
	}

	public void setPlazas(long plazas) {
		this.plazas = plazas;
	}

	

	public Ofertaeducativa() {
		// TODO Auto-generated constructor stub
	}

}
