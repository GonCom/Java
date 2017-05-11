package entidades;

public class Socio {
	private long idsocio;
	private String nombre;
	private String direccion;

	public Socio() {
	}

	public void setIdsocio(long idsocio) {
		this.idsocio = idsocio;
	}

	public long getIdsocio() {
		return idsocio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}
}
