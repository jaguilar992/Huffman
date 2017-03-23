package Compresor;




import TDA.ARBOL;
import java.util.ArrayList;

/**
 *
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
            System.out.println("ARBOL creado");
        }else
        {
            //busqueda de los dos arboles mas pequeños
            MenosPesado = ARBOLMenorPeso();
            MasPesado = ARBOLMenorPeso();
            //fin de la busqueda de los dos arboles mas pequeños
            
            //se añade un nuevo arbol a la lista
            arboles.add(new ARBOL().CREA((ARBOLMenorPeso().getPeso()+ARBOLMenorPeso().getPeso()),
                        ARBOLMenorPeso(),ARBOLMenorPeso()));
            
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
        
        //arbol de menor getPeso
        ARBOL menorARBOL = null;
        
        //Busqueda del arbol de menorPeso
        for (ARBOL arbol : arboles) {
            if (arbol.getPeso()< menorPeso){
                menorPeso = arbol.getPeso();
                menorARBOL = arbol;
            }
        }// fin de la busqueda
        
        //Finalmente se quita de la lista el arbol menor
        arboles.remove(menorARBOL);
        
        return menorARBOL;
    }
    
    private ARBOL arbolHuffman(){
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
}
