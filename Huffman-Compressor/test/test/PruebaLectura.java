/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import tdas.ARBOL;
/**
 *
 * @author jaguilar992
 */
public class PruebaLectura {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {	
        
        /// ######################### LECTURA
        FileInputStream arh= new FileInputStream("lorem.huffman");
        ObjectInputStream reader = new ObjectInputStream(arh);
        ARBOL arbol = (ARBOL) reader.readObject();
        String cadena = (String) reader.readObject();
        System.out.println(arbol);
        System.out.println(cadena);
        
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
        
       // System.out.println(bits);
        
        /// ################ ESCRITURA
//        ARBOL arbolg = CREA("1",CREA("2"),CREA("3"));
//        String cadenag = "äÔ·æ£\u0000";
//        FileOutputStream h = new FileOutputStream("h.huffman");
//        ObjectOutputStream is1 = new ObjectOutputStream(h);
//        is1.writeObject(arbolg);
//        is1.writeObject(cadenag);
        
    }
}
