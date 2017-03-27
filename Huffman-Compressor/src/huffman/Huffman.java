package huffman;




import tdas.ARBOL;
import java.util.ArrayList;
import static tdas.ARBOL.CREA;



/**
 *package arbolhuffman;

import java.util.ArrayList;
 * @author Carcamo
 */
public class Huffman {

    private final ArrayList<ARBOL> arboles;
    ARBOL MasPesado;
    ARBOL MenosPesado;

    /**
     *
     * @param arboles
     */
    public Huffman(ArrayList arboles) {
        this.arboles = arboles;
        crearARBOL ();
    }

    private void crearARBOL() {
        
        if (arboles.size()==1){
         //   System.out.println("ARBOL creado");
        }else
        {
            //busqueda de los dos arboles mas pequeños
            MenosPesado = ARBOLMenorPeso();
            MasPesado = ARBOLMenorPeso();
            //fin de la busqueda de los dos arboles mas pequeños
            
            //se añade un nuevo arbol a la lista
            arboles.add(CREA( (MenosPesado.getPeso()+ MasPesado.getPeso()+">>"+MenosPesado.RAIZ()+"|"+MasPesado.RAIZ()),
                        MenosPesado, MasPesado));
            //Nota de Antonio: Tuve que lo anterior porque algunos Raiz quedaban identicos al sumar los pesos
            // Esto perjudica a la operacion PADRE
            
            /**llamada recursiva del metodo crearARBOL
             * mientras exista mas de un elemento
             * en la lista
             */
            crearARBOL();
        }
        
    }

    private ARBOL ARBOLMenorPeso() {
        
        // inicializar la variable menorPeso con el primer arbol de la lista
        int menorPeso = arboles.get(0).getPeso();
        
        //arbol de menor peso
        ARBOL menorARBOL = arboles.get(0);
        
        //Busqueda del arbol de menorPeso
        for (ARBOL arbol : arboles) {
            if (arbol.getPeso()<menorPeso){
                menorPeso = arbol.getPeso();
                menorARBOL = arbol;
            }
        }// fin de la busqueda
        
        //Finalmente se quita de la lista el arbol menor
        arboles.remove(menorARBOL);
        
        return menorARBOL;
    }
    
    public ARBOL arbolHuffman(){
        /**
         * Aqui se retorna el arbol final
         * Siempre y cuando la lista 
         * contenga un solo arbol
         */
        if (arboles.size()==1){
            return arboles.get(0);
        }
            
        return null;
    }

    //Metodo caminoHuffman
    
    public String caminoHuffman(Object nodo){
        // Construccion recursiva del camino, empieza buscando el padre y se mueve hasta la raíz,
        // Se detiene cuando se alcanza el padre de la raíz = null;
    	ARBOL arbol=this.arbolHuffman();
    	Object P=arboles.get(0).PADRE(nodo);  //P es el padre del nodo en cuestion
    	//Compara el nodo en cuestion con el Hijo_izq de su Padre, si son iguales agrega cero al camino
        if (P!=null && nodo.equals(arbol.HIJO_MAS_IZQ(P))) {
                return caminoHuffman(P)+"0";
        } //Compara el nodo en cuestion con el hijo Derecho de su padre
        else if (P!=null && nodo.equals(arbol.HIJO_MAS_DER(P))) {
            return caminoHuffman(P)+"1";
        }else{
            return "";
        }
    }
    
    public static String descomprimir(ARBOL arbol, String textoComprimido){
            String textoOriginal = "";
            Object nodo = arbol.RAIZ();
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
