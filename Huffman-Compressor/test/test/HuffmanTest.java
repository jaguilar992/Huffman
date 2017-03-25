/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import huffman.Huffman;
import tdas.ARBOL;
import static tdas.ARBOL.CREA;
import java.util.ArrayList;

/**
 *
 * @author jaguilar992
 */
public class HuffmanTest {
    public static void main(String[] args) {
        ArrayList<ARBOL> bosque= new ArrayList<>();
        ARBOL a=CREA("a");
        ARBOL b=CREA("b");
        ARBOL e=CREA("e");
        ARBOL g=CREA("g");
        ARBOL l=CREA("l");
        
        a.setPeso(2);
        b.setPeso(1);
        e.setPeso(5);
        g.setPeso(4);
        l.setPeso(1);
        
        bosque.add(a);
        bosque.add(b);
        bosque.add(e);
        bosque.add(g);
        bosque.add(l);
        
        Huffman k = new Huffman(bosque);
        System.out.println(k.arbolHuffman());
        //System.out.println(k.arbolHuffman().HIJO_MAS_DER(null));
        System.out.println(k.caminoHuffman("a"));
    }
   
}

