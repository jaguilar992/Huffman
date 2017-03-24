package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controlador implements Initializable{
	
	@FXML private TextArea txtAPanel;
	@FXML private TextArea txtACodigoHuffman;
	@FXML private MenuButton menuPrincipal;
	@FXML private MenuItem menuImportarTexto;
	@FXML private MenuItem menuNuevaVentana;
	@FXML private MenuItem menuDescomprimir;
	@FXML private MenuItem menuSalir;
	@FXML private Button bttComprimir;
	@FXML private Button bttMonstrarCodigoHuffman;
	@FXML private Button bttSalir;
	
	

	
	
	
	//variables de lectura de archivo
	private FileReader flujoLectura;
	private BufferedReader bRLectura;
	private String linea;
	
	//variables de escritura de archivo
	private FileWriter flujoEscritura;
	private BufferedWriter bREscritura;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	@FXML
	public void comprimirArchivo(){
		
		 JFileChooser buscador= new JFileChooser(System.getProperty("C:/Documentos"));
		 FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.Huffman","Huffman");
 		 buscador.setFileFilter(filtro);
		 buscador.setApproveButtonText("Seleccionar");
         buscador.showSaveDialog(null);
         
         //Establecer un filtor .Huffman
       
         
		try {
			
		 	flujoEscritura = new FileWriter(buscador.getSelectedFile()+".Huffman", true);
			bREscritura = new BufferedWriter(flujoEscritura);
			bREscritura.write(txtAPanel.getText());
		
			bREscritura.flush();
			flujoEscritura.close();

			
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
		txtACodigoHuffman.setText("Meter aqui el codigo huffman");
		
	}
	
}
