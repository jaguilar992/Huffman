package application;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//import huffman.NewArchivo;
import huffman.Codex;
import huffman.Huffman;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        @FXML private Button bttLimpiar;
	@FXML private ProgressBar progress;
	
	
	//variables de lectura de archivo
	private FileReader flujoLectura;
	private BufferedReader bRLectura;
	private String linea;
	
	//variables de escritura de archivo
	private FileWriter flujoEscritura;
	private BufferedWriter bREscritura;
	
	
	//variables para contar caracteres
	private ArrayList<ARBOL>bosque;
        private HashMap<Character,String> codigoHuffman;
        private HashMap<Character,Integer> dictionarioFrecuency;
        private String text;
        ARBOL arbol;
        String cadena;
       
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	@FXML
	public void comprimirArchivo(){
            
            //SETEAR EL PROGRESSBAR
             progress.setProgress(0);
		
            //LO COMENTE PARA QUE CUANDO SE REALIZEN LAS PRUEBAS NO ESTE PIDENDO DIRECCION PARA GUARDAR EL ARCHIVO.Huffman

             JFileChooser buscador= new JFileChooser(System.getProperty("C:/User/Desktop"));
             //Establecer un filtor .Huffman
             FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.Huffman","Huffman");
             buscador.setFileFilter(filtro);
             buscador.setApproveButtonText("Seleccionar");
             buscador.showSaveDialog(null);



             //Llamar al metodo para contar el peso de cada caracter
             text=txtAPanel.getText();


             //System.out.println(text.length()*16+" bits");

             //diccionario de frecuencia de apariciones de caracteres
             dictionarioFrecuency =  Codex.count_chars(text);
             
             //ASIGNAR 10% al progreso 
             progress.setProgress(10);

             //probar que el diccionario de apariciones de caracteres funcione

             //System.out.println(dictionarioFrecuency.toString());



             bosque = new ArrayList<>();
             for (Character key : dictionarioFrecuency.keySet()){
                ARBOL n = ARBOL.CREA(key);
                n.setPeso(dictionarioFrecuency.get(key));
                bosque.add(n);
             }
             
             //ASIGNAR 40% al progreso 
             progress.setProgress(40);

             //imprimir los arboles del bosque huffman para verificar que funcione
            /* for (int i = 0; i < bosque.size(); i++) {
                        System.out.println(bosque.get(i));
                     }*/

            //MANDAR EL BOSQUE al objeto de la clase huffman

             Huffman h = new Huffman(bosque);
             ARBOL r = h.arbolHuffman();
             
             //ASIGNAR 50% al progreso 
             progress.setProgress(50);

             //System.out.println(r);

             codigoHuffman = new HashMap<>();

             for (Character key : dictionarioFrecuency.keySet()){
                 String path = h.caminoHuffman(key);
                 codigoHuffman.put(key, path);
             }
              //ASIGNAR 65% al progreso 
             progress.setProgress(65);

             //probar que la nueva codificacion huffman funcione correctamente
             //System.out.println(codigo.toString());

            //mostrar frecuencia de apariciones de caracteres asi como su nueva representacion huffman
            System.out.println(text.length()*16+" bits");
            for (Character c : codigoHuffman.keySet()) {
                text=text.replace(c.toString(), codigoHuffman.get(c));
            }

            //System.out.println("10111001 : \u00b9");
            //System.out.println(Character.toChars(Integer.parseInt("10111001", 2)));

            int restante = (8 - text.length()%8);
            //System.out.println(restante);
            for (int i = 0; i < restante; i++) {
                text+="0";
            }
            
             //ASIGNAR 85% al progreso 
             progress.setProgress(65);
             
            //System.out.println(text.length()+" bits");
            
            String cod ="";
            for (int i = 0; i < text.length(); i++) {
                int bite = Integer.parseInt(text.substring(i, i + 8), 2);
                cod+=String.valueOf(Character.toChars(bite));
                //System.out.println(cod);
                i += 7;
            }
            
            
             //ASIGNAR 95% al progreso 
             progress.setProgress(95);
            
            String mensajeCodificado=cod;
            mostrarCodigoHuffman(text,cod);



            //LO COMENTADO LO DEJO PARA QUE SIGAN TRABAJANDO A QUIEN LE TOQUE LO SIGUIENTE
            /*//System.out.println(codigo);
            String comp ="";
            //for (Character c : codigo.keySet()) {
               // text=text.replace(c.toString(), codigo.get(c));
            //}
            //System.out.println(text);
            System.out.println(text.length()+" bits");
            //System.out.println("10111001 : \u00b9");
            System.out.println(Character.toChars(Integer.parseInt("10111001", 2)));
            for (int i = 0; i < text.length(); i++) {
                int bite = Integer.parseInt(text.substring(i, i+8), 2);
                System.out.print(Character.toChars(bite));
                i+=8;
            }

            //System.out.println(text.length()/8.0+" bytes");


           
         

            //OJO NO TOCAR TRY Y CATH 
                  /*  try {

                            //flujoEscritura = new FileWriter(buscador.getSelectedFile()+".Huffman", true);
                            //bREscritura = new BufferedWriter(flujoEscritura);
                            //bREscritura.write(txtAPanel.getText());//aqui en lugar de txtAPanel.getText() ira el archivo con la 
                                                                                                      //nueva representacion de caracteres es decir lo que se desea guardar 
                                                                                                     //el archivo.Huffman
                            //cambiale nombre a objectoutputstream flujoEscritura tenes el  mismo nombre que arrba 
                            
                            ObjectOutputStream flujoEscrituraObject=new ObjectOutputStream(new FileOutputStream(buscador.getSelectedFile()+".Huffman", true));                                                                                
                            flujoEscrituraObject.writeObject((new NewArchivo(h.arbolHuffman(), mensajeCodificado)).getArchivo());
                            //bREscritura.flush();
                            flujoEscrituraObject.close();//cerrar el flujo de escritura para eliminar basura de memoria
                            
                            
                    } catch (IOException e) {
                            e.printStackTrace();
                    }*/
             

             
             try {
                 try (ObjectOutputStream flujoEscrituraObject = new ObjectOutputStream(new FileOutputStream(buscador.getSelectedFile()+".Huffman", true))) {
                     flujoEscrituraObject.writeObject(r);
                     flujoEscrituraObject.writeObject(cod);
                     //bREscritura.flush();
                     flujoEscrituraObject.close();//cerrar el flujo de escritura para eliminar basura de memoria
                 }
                    
                  } catch (IOException e) {
                      
                   }
             //ASIGNAR 100% al progreso 
             progress.setProgress(100);
		
	}
	
	@FXML
	public void importarTexto(){
		
            progress.setProgress(0);
            JFileChooser buscador= new JFileChooser(System.getProperty("C:/Documentos"));
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
            buscador.setFileFilter(filtro);
            buscador.setApproveButtonText("Seleccionar");
            buscador.showOpenDialog(null);
            limpiar();




            try{
               flujoLectura= new FileReader(buscador.getSelectedFile());
               bRLectura= new BufferedReader(flujoLectura);
               linea= bRLectura.readLine();
               progress.setProgress(50);

                while(linea!=null){
                    txtAPanel.appendText(linea+"\n");
                    linea= bRLectura.readLine();
                }
                progress.setProgress(100);
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
                progress.setProgress(0);
		JFileChooser buscador= new JFileChooser(System.getProperty("C:/Documentos"));
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.Huffman","Huffman");
		buscador.setFileFilter(filtro);
		buscador.setApproveButtonText("Seleccionar");
                buscador.showOpenDialog(null);
                limpiar();
                
                /// LECTURA DEL ARCHIVO.HUFFMAN
               
              
            try {
               
                FileInputStream arh= new FileInputStream(buscador.getSelectedFile());
                ObjectInputStream reader = new ObjectInputStream(arh);
                progress.setProgress(20);
                arbol = (ARBOL) reader.readObject();
                cadena = (String) reader.readObject();
                progress.setProgress(40);
                //System.out.println(arbol);
               // System.out.println(cadena);
                
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
             
                progress.setProgress(50);


                // RECOSTRUCCION DE CADENA 100011000100 (LECTURA)
                String bits ="";
                for (int i = 0; i < cadena.length(); i++) {
                    char p =cadena.charAt(i);
                    String bite = Integer.toBinaryString(Integer.valueOf(p));
                    int n = bite.length();
                    for (int j = 0; j < 8-n; j++) {
                        bite= "0"+(bite);
                    }
                    bits+=bite;
                }
                 progress.setProgress(70);

                String texto = Huffman.descomprimir(arbol,bits);
                //System.out.println(texto);
                txtAPanel.setText("------Archivo descompreso exitosamente--------\n"
                                  +texto
                );
          
                  
                progress.setProgress(100);

        
 

        
               /* try{
                   /*flujoLectura= new FileReader(buscador.getSelectedFile());
                   bRLectura= new BufferedReader(flujoLectura);
                   linea= bRLectura.readLine();

                    while(linea!=null){
                        txtAPanel.appendText(linea+"\n");
                        linea= bRLectura.readLine();
                    }
                    flujoLectura.close();*/
                  /*  FileInputStream arh= new FileInputStream(buscador.getSelectedFile());
            ObjectInputStream reader = new ObjectInputStream(arh);
            
            //NewArchivo Archivo = (NewArchivo)reader.readObject();
            arh.close();
                } catch(Exception e) {
                                e.printStackTrace();
                        }*/


	}
	
	
	public void mostrarCodigoHuffman(String codigoHuffmanBinario,String mensajeCodificado){
	    /*codificacion necesaria  para mostrar el nuevo codigo huffman en:
            *txtACodigoHuffman.setText("Meter aqui el codigo huffman");*/
            txtACodigoHuffman.setText(null);
            String mostrarCodigo="-----------Apariciones de cada caracter-------"+"\n"
                                  +dictionarioFrecuency.toString()
                                  +"\n-----------------Nuevo Codigo Binario--------------\n"
                                  +codigoHuffman.toString()
                                  +"\n---------Codificacion Binaria Frase---------\n"
                                  + codigoHuffmanBinario
                                  +"\n---------Codificacion Frase---------\n"
                                  + mensajeCodificado
                 
                 ;

            txtACodigoHuffman.setText(mostrarCodigo);
			
	}
        
         @FXML
	 public void limpiar(){
                progress.setProgress(0);
	 	txtACodigoHuffman.setText(null);
	 	txtAPanel.setText(null);
	 }

	public String descomprimir(ARBOL arbol, String textoComprimido){
            String textoOriginal = "";
            Object nodo = new Object();
            nodo = arbol.RAIZ();
            char[] chars = textoComprimido.toCharArray();
            
           for (int i = 0; i < chars.length; i++) {
                if (chars[i]=='0') {
                    nodo = arbol.HIJO_MAS_IZQ(nodo);
                    if (arbol.HIJO_MAS_IZQ(nodo)==null && arbol.HIJO_MAS_DER(nodo)==null) {
                        textoOriginal+=arbol.ETIQUETA(nodo);
                        nodo = arbol.RAIZ();
                    }
                } else {
                    nodo = arbol.HIJO_MAS_DER(nodo);
                    if ( arbol.HIJO_MAS_IZQ(nodo)==null && arbol.HIJO_MAS_DER(nodo)==null) {
                        textoOriginal+=arbol.ETIQUETA(nodo);
                        nodo = arbol.RAIZ();
                    }
                }
                    
                }
                
            
           
            return textoOriginal;
        }
	
}
