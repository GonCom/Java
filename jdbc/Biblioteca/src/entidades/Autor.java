package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Autor {
	private int idautor;
	private String nombre;
	private Date fecha;

	public Autor() {

	}

	public int getIdautor() {
		return idautor;
	}

	public void setIdautor(int idautor) {
		this.idautor = idautor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFechaFormateada() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		String fechaFormateada = null;
		if (fecha != null)
			fechaFormateada = formatoFecha.format(fecha);
		return fechaFormateada;
	}

}
