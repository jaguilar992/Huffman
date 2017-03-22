/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import TDA.ARBOL;

/**
 *
 * @author jaguilar992
 */
public class Prueba {
    public static void main(String[] args) {
        ARBOL Huffman = new ARBOL();
        
        Huffman.raiz=0;
        int[] izq = {1,2,7,-1,1,6,-1,-1,-1};
        Object[] data ={1,2,5,3,6,4,7,8,9};
        int[] der = {-2,3,4,5,-1,-1,8,-1,-1};
        
        for (int i = 0; i < data.length; i++) {
            Huffman.hijo_mas_izq[i]=izq[i];
            Huffman.data[i]=data[i];
            Huffman.hermano_dere[i]=der[i];
        }
        
      //  System.out.println(Huffman.PADRE(3));
        //System.out.println(Huffman.ES_HERM_DER_DE(2));
        
        ARBOL manzana = new ARBOL();
        //int[] hijos = {manzana.CREA("b"),manzana.CREA("c"),manzana.CREA("d")};
        //manzana.CREA("a", manzana.CREA("b"),manzana.CREA("c"),manzana.CREA("d"));
        
//        for (int i = 0; i < 4; i++) {
//            System.out.print(i+". ");
//            System.out.print(manzana.hijo_mas_izq[i]+" ");
//            System.out.print(manzana.data[i]+" ");
//            System.out.println(manzana.hermano_dere[i]);
//        }
        System.out.println(manzana.RAIZ());
        System.out.println(manzana.HIJO_MAS_IZQ("a"));
        System.out.println(manzana.PADRE("b"));
        
    }
}