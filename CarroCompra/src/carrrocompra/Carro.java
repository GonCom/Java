package carrrocompra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import entidades.Producto;
import entidades.ProductoCarro;

public class Carro {
	private ArrayList<Producto> productos;
	
	public Carro() {
		productos = new ArrayList<Producto>();
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	/**************AÑADIR PRODUCTO AL CARRO**************/
	public void addProducto(Producto p){
		int posicion=productos.indexOf(p);
		if(posicion==-1){
			productos.add(p);
		}else{
			Producto actual=productos.get(posicion);
			actual.setCantidad(actual.getCantidad()+1);
		}
		
	}//addProducto
	
	/**************QUITAR PRODUCTO DEL CARRO*************/
	public void quitarProducto(Producto p){
		int posicion=productos.indexOf(p);
		if(posicion==-1){
			int cont=0;//no hacemos nada si el producto no está en el carro
		}else{
			Producto actual=productos.get(posicion);
			if(actual.getCantidad()==1){
				productos.remove(posicion);
			}else{
				actual.setCantidad(actual.getCantidad()-1);
			}
		}
		
	}//quitarProducto
	
}//Carro
