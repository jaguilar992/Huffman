/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.HashMap;

/**
 *
 * @author jaguilar992
 */
public class Codex {
    
    
    public static HashMap<Character,Integer> count_chars(String Text){
        HashMap<Character,Integer> dict = new HashMap<>();
        for (int i = 0; i < Text.length(); i++) {
            char c = Text.charAt(i);
            try {
                dict.put(c,dict.get(c)+1);
            } catch (Exception e) {
                dict.put(c, 1);
            }
        }
        return dict;
    }
}
