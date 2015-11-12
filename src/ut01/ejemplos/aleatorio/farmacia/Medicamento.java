package ut01.ejemplos.aleatorio.farmacia;


public class Medicamento {
	
	public final static int TAM_NOMBRE = 30;
	public final static int TAM_REGISTRO = 88;
	private String nombre; // 60 bytes
	private double precio; // 8 bytes
	private int cod; // 4 bytes
	private int stock; // 4 bytes
	private int stockMaximo; // 4 bytes
	private int stockMinimo; // 4 bytes
	private int codProveedor; // 4 bytes
	
	
	public Medicamento(int cod, String nombre, double precio,int codProveedor ,
			int stock ) {
		// rellenamos hasta ocupar 30
		StringBuffer buffer = new StringBuffer(nombre);
		buffer.setLength(TAM_NOMBRE);
		
		this.nombre = buffer.toString();
		
		this.precio = precio;
		this.cod = cod;
		this.stock = stock;
		this.stockMaximo = 0;
		this.stockMinimo = 0;
		this.codProveedor = codProveedor;
	}
		
	
	public Medicamento(int cod, String nombre, double precio,int codProveedor ,
			int stock, int stockMaximo, int stockMinimo ) {
		
		StringBuffer buffer = new StringBuffer(nombre);
		buffer.setLength(TAM_NOMBRE);
		
		this.nombre = buffer.toString();
		this.precio = precio;
		this.cod = cod;
		this.stock = stock;
		this.stockMaximo = stockMaximo;
		this.stockMinimo = stockMinimo;
		this.codProveedor = codProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	/**
	 * El nombre ocupara siempre 30
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		StringBuffer buffer = new StringBuffer(nombre);
		buffer.setLength(TAM_NOMBRE);
		
		this.nombre = buffer.toString();
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStockMaximo() {
		return stockMaximo;
	}
	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}
	public int getStockMinimo() {
		return stockMinimo;
	}
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	public int getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}
	
	public String toString(){
		return "cod: "+cod+
			   " " +
			   "nombre: "+nombre+" precio "+precio+
			   " stock "+stock+" proveedpor" +codProveedor;
	}

}

