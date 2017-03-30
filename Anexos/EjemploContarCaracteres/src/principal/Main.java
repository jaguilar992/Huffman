package principal;

import java.util.ArrayList;


public class Main {
	
	public static ArrayList <CaracteresCadena> caracteres;
	public static String caracterTemporal;
	public static String tablaAscci[];
	public static String[] arrayString;


	public static void main(String[] args) {
		
		caracteres=new ArrayList<>();
		tablaAscci=new String[255];
	
		
		//Asignar a un arreglo de caracteres la tabla asccii que consta de 255 caracteres
		char letra=0;
		for (int i = 0; i < tablaAscci.length; i++) {
			tablaAscci[i]=String.valueOf(letra++);
		}
		
	
		
		String cadena="Me gusta estar en mi clase de algoritmos y estructura de datos.";
		
		//arrayChar = cadena.toCharArray();//separa los caracteres de la cadena que se le manda a la funcion
		
		
		arrayString=cadena.split("");
		
	

		/*para acceder a los caracteres se hace lo siguiente:
		 * imaginar el siguiente arrayList:
		 * caracter  peso
		 *   a        2
		 *   b        3
		 *   c		  4
		 *   g		  6
		 * donde la letra es el caracter y el numero es el peso 
		 * para acceder a la letra se usara---->> caracteres(i).getCaracter(); dode i se genera del ciclo
		 * para acceder al numero se usara---->>> caracteres(i).getPeso(); donde i se genera del ciclo
		 */
		
	

		for (int i = 0; i < arrayString.length; i++) {
			caracterTemporal=obtenerCaracterTablaAscci(arrayString[i]);
			//saber si el caracter actual ya fue contado
			if (!isContado(caracterTemporal)) {
				CaracteresCadena c=new CaracteresCadena();
				c.setCaracter(caracterTemporal);
				c.setPeso(contarCaracter(caracterTemporal));
				c.setCount(true);
				caracteres.add(c);
			}
		
				
		}
		
		for (int i = 0; i < caracteres.size(); i++) {
			System.out.println(caracteres.get(i));
		}
		
		
		System.out.println("-----------fin del programa-------------");
	
		
	}	
	
	
	public static int contarCaracter(String caracter){
		int peso=0;
		String arrayTemp[]=new String[arrayString.length];
		int j=0;
		for (int i = 0; i < arrayString.length; i++) {
			if (!isContado(arrayString[i])) {
				arrayTemp[j]=arrayString[i];
				j++;
			}
		}
		
		for (int i = 0; i < arrayTemp.length; i++) {
			if (caracter.equals(arrayTemp[i])) {
				peso++;
			}
		}
		
		
		
	return peso;
	}
	

	public static String obtenerCaracterTablaAscci(String caracter){
		String caracterTabla="";
		for (int i = 0; i < tablaAscci.length; i++) {
			if (tablaAscci[i].equals(caracter)) {
				caracterTabla=tablaAscci[i];
			}
		}
		return caracterTabla;
	}
	
	public static boolean isContado(String caracter){
		boolean isContado=false;
		for (int i = 0; i < caracteres.size(); i++) {
			if (caracteres.get(i).getCaracter().equals(caracter)) {
				if (caracteres.get(i).isCount()) {
					isContado=true;
				}
			}
		}
		return isContado;
	}

}
