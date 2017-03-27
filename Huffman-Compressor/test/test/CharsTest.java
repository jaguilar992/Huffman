/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import huffman.Codex;
import huffman.Huffman;
import java.util.ArrayList;
import java.util.HashMap;
import tdas.ARBOL;

/**
 *
 * @author jaguilar992
 */
public class CharsTest {
    public static void main(String args[]){
        String text= "Me gusta estar en mi clase de algoritmos y estructura de datos.";
       text="Hola Soy Goku";
       
        System.out.println("\n\nTRABAJAR");
        System.out.println(text);
        //System.out.println(text.length()*16+" bits");
        HashMap<Character,Integer> dict =  Codex.count_chars(text);
        //System.out.println(dict);
        
        ArrayList<ARBOL> bosque = new ArrayList<>();
        for (Character key : dict.keySet()){
            ARBOL n = ARBOL.CREA(key);
            n.setPeso(dict.get(key));
            bosque.add(n);
        }
        
        Huffman h = new Huffman(bosque);
        ARBOL r = h.arbolHuffman();
       //System.out.println(r);
        
        
        HashMap<Character,String> codigo = new HashMap<>();
        
        for (Character key : dict.keySet()){
            String path = h.caminoHuffman(key);
            codigo.put(key, path);
        }
        
        //System.out.println(codigo);
        String comp ="";
        for (Character c : codigo.keySet()) {
            text=text.replace(c.toString(), codigo.get(c));
        }
        
        //System.out.println("10111001 : \u00b9");
        //System.out.println(Character.toChars(Integer.parseInt("10111001", 2)));
        
        int restante = (8 - text.length()%8);
        //System.out.println(restante);
        for (int i = 0; i < restante; i++) {
            text+="0";
        }
        
        System.out.println(text);
//        System.out.println(text.length()+" bits");
        
        //System.out.println(text);
        //System.out.println(text.length()+" bits");
        
       // for (int i = 0; i < text.length(); i++) {
         //   int bite = Integer.parseInt(text.substring(i, i+8), 2);
            //System.out.print(bite+": ");
            //System.out.print(Character.toChars(bite));
            //i+=7;
        //}
       // System.out.println("111001001101010010110111111001101010001100000000");
        //System.out.println(text.length()/8.0+" bytes");
    }
}
