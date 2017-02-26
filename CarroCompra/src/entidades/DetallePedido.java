package entidades;


public class DetallePedido {
	private int idpedido;
	private int lineadetalle;
	private int idproducto;
	private int cantidad;
	private int preciounidad;
	private int total;
	
	public DetallePedido() {
		
	}

	
	public DetallePedido(int idpedido, int lineadetalle, int idproducto,
			int cantidad, int preciounidad, int total) {
		super();
		this.idpedido = idpedido;
		this.lineadetalle = lineadetalle;
		this.idproducto = idproducto;
		this.cantidad = cantidad;
		this.preciounidad = preciounidad;
		this.total = total;
	}


	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public int getLineadetalle() {
		return lineadetalle;
	}

	public void setLineadetalle(int lineadetalle) {
		this.lineadetalle = lineadetalle;
	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPreciounidad() {
		return preciounidad;
	}

	public void setPreciounidad(int preciounidad) {
		this.preciounidad = preciounidad;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
