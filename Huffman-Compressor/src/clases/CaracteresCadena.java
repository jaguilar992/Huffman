package clases;

public class CaracteresCadena {
	private String caracter;
	private int peso;
	private boolean isCount;
	
	
	public CaracteresCadena(){
		isCount=false;
	}
	
	public CaracteresCadena(String caracter, int pesoPorCaracter) {
		super();
		this.caracter = caracter;
		this.peso = pesoPorCaracter;
	}
	
	
	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	
	public boolean isCount() {
		return isCount;
	}

	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}

	@Override
	public String toString() {
		return "CaracteresCadena [caracter=" + caracter + ", pesoPorCaracter=" + peso + "]";
	}
	
	

}
