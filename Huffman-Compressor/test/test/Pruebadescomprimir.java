/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import huffman.Huffman;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import tdas.ARBOL;
import static tdas.ARBOL.CREA;
/**
 *
 * @author jaguilar992
 */
public class Pruebadescomprimir {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {	
       ArrayList<ARBOL> bosque= new ArrayList<>();
        ARBOL a=CREA("a");
        ARBOL b=CREA("b");
        ARBOL c=CREA("c");
        ARBOL d=CREA("d");
       
        
        a.setPeso(1);
        b.setPeso(2);
        c.setPeso(3);
        d.setPeso(4);
        
        
        bosque.add(a);
        bosque.add(b);
        bosque.add(c);
        bosque.add(d);
        
        
        Huffman k = new Huffman(bosque);
       
        System.err.println(Huffman.descomprimir(k.arbolHuffman(), "110111010"));
    }
}
