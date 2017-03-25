package application;

import static tdas.ARBOL.CREA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.CaracteresCadena;
import huffman.Huffman;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tdas.ARBOL;

public class Controlador implements Initializable{
	
	@FXML private TextArea txtAPanel;
	@FXML private TextArea txtACodigoHuffman;
	@FXML private MenuButton menuPrincipal;
	@FXML private MenuItem menuImportarTexto;
	@FXML private MenuItem menuNuevaVentana;
	@FXML private MenuItem menuDescomprimir;
	@FXML private MenuItem menuSalir;
	@FXML private Button bttComprimir;
	@FXML private Button bttSalir;
	@FXML private ProgressBar progress;
	
	
	//variables de lectura de archivo
	private FileReader flujoLectura;
	private BufferedReader bRLectura;
	private String linea;
	
	//variables de escritura de archivo
	private FileWriter flujoEscritura;
	private BufferedWriter bREscritura;
	
	
	//variables para contar caracteres
	private  ArrayList <CaracteresCadena> caracteres;
	private  String caracterTemporal;
	private  String tablaAscci[];
	private  String[] arrayString;
	private  ArrayList<ARBOL>bosque;
	private  Huffman huffman; 
        private ARBOL codex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	@FXML
	public void comprimirArchivo(){
		
		 JFileChooser buscador= new JFileChooser(System.getProperty("C:/User/Desktop"));
		 //Establecer un filtor .Huffman
		 FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.Huffman","Huffman");
 		 buscador.setFileFilter(filtro);
		 buscador.setApproveButtonText("Seleccionar");
         buscador.showSaveDialog(null);

         //Llamar al metodo para contar el peso de cada caracter
         pesoDeCaracteres(txtAPanel.getText());
         
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
 		
         //inicializar el arrayList de arboles
         bosque=new ArrayList<>();
         for (int i = 0; i <caracteres.size(); i++) {
        	 String caracter=caracteres.get(i).getCaracter();
        	 ARBOL arbol = CREA(caracter);
        	 arbol.setPeso(caracteres.get(i).getPeso());
        	 bosque.add(arbol);
        	  
		}
         
        //imprimir los arboles del bosque huffman para verificar que funcione
        /*for (int i = 0; i < arboles.size(); i++) {
			System.out.println(arboles.get(i));
		}*/
         
      
         
         //MANDAR EL BOSQUE al objeto de la clase huffman
         
         /*Trabajo el reso en la clase huffman:
          * ordenar los arboles segun su peso EN EL METODO arbolHuffman()
          * colocarles 0 y 1 al arbol huffman
          * crear el codigo binario
          * y lo que haga falta*/
         
         
         huffman=new Huffman(bosque);
         codex=huffman.arbolHuffman();
         
       
        //OJO NO TOCAR TRY Y CATH 
		try {
			
		 	flujoEscritura = new FileWriter(buscador.getSelectedFile()+".Huffman", true);
			bREscritura = new BufferedWriter(flujoEscritura);
			bREscritura.write(txtAPanel.getText());//aqui en lugar de txtAPanel.getText() ira el archivo con la 
												  //nueva representacion de caracteres es decir lo que se desea guardar 
												  //el archivo.Huffman
		
			bREscritura.flush();
			flujoEscritura.close();//cerrar el flujo de escritura para eliminar basura de memoria

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*codigo para comprimir el archivo de texto a un archivo .Huffman
		 * 
		 * 
		 * */
		
		mostrarCodigoHuffman();
		
	}
	
	@FXML
	public void importarTexto(){
		
		
		JFileChooser buscador= new JFileChooser(System.getProperty("C:/Documentos"));
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
		buscador.setFileFilter(filtro);
		buscador.setApproveButtonText("Seleccionar");
        buscador.showOpenDialog(null);
        txtAPanel.setText(null);
        
 

        
        try{
           flujoLectura= new FileReader(buscador.getSelectedFile());
           bRLectura= new BufferedReader(flujoLectura);
           linea= bRLectura.readLine();
            
            while(linea!=null){
                txtAPanel.appendText(linea+"\n");
                linea= bRLectura.readLine();
            }
            flujoLectura.close();
        }catch(Exception ex){
            
        }
	}
	
	@FXML
	public void nuevaVemtama(){
		Stage nuevo=new Stage();
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Vista.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			nuevo.setScene(scene);
			nuevo.setTitle("NotePad");
			nuevo.setResizable(false);
			nuevo.getIcons().add(new Image("recursos/icono.png"));
			nuevo.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void salir(){
		System.exit(0);
		
	}
	
	@FXML
	public void DescomprimirArchivoHuffman(){
		JFileChooser buscador= new JFileChooser(System.getProperty("C:/Documentos"));
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.Huffman","Huffman");
		buscador.setFileFilter(filtro);
		buscador.setApproveButtonText("Seleccionar");
        buscador.showOpenDialog(null);
        txtAPanel.setText(null);
        
 

        
        try{
           flujoLectura= new FileReader(buscador.getSelectedFile());
           bRLectura= new BufferedReader(flujoLectura);
           linea= bRLectura.readLine();
            
            while(linea!=null){
                txtAPanel.appendText(linea+"\n");
                linea= bRLectura.readLine();
            }
            flujoLectura.close();
        } catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void mostrarCodigoHuffman(){
		/*codificacion necesaria  para mostrar el nuevo codigo huffman en:
		*txtACodigoHuffman.setText("Meter aqui el codigo huffman");*/
		txtACodigoHuffman.setText(codex.toString());
		
	}
	
	public void pesoDeCaracteres(String cadena){
		caracteres=new ArrayList<>();
		tablaAscci=new String[255];
	
		
		//Asignar a un arreglo de caracteres la tabla asccii que consta de 255 caracteres
		char letra=0;
		for (int i = 0; i < tablaAscci.length; i++) {
			tablaAscci[i]=String.valueOf(letra++);
		}
		
	
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
		
		//mostrar los caracteres y sus pesos 
		/*for (int i = 0; i < caracteres.size(); i++) {
			System.out.println(caracteres.get(i));
		}
		*/
		
	}
	
	public  int contarCaracter(String caracter){
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
	

	public  String obtenerCaracterTablaAscci(String caracter){
		String caracterTabla="";
		for (int i = 0; i < tablaAscci.length; i++) {
			if (tablaAscci[i].equals(caracter)) {
				caracterTabla=tablaAscci[i];
			}
		}
		return caracterTabla;
	}
	
	public  boolean isContado(String caracter){
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
