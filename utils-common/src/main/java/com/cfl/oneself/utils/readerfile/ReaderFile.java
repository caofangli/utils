package com.cfl.oneself.utils.readerfile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName： ReaderFile
 * @Description： TODO:
 * @Author： cfl
 * @Date: Created in 8:28 2019/12/6
 * @Vesion 1.0
 */
public class ReaderFile {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/a.txt")));
        String line="";
        HashMap<String, Integer> map = new HashMap<>();
        while ((line = br.readLine())!=null){
            String[] words = line.split(" ");
            for(String word : words){
                if(map.containsKey(word)){
                    map.put(word, map.get(word)+1);
                }else {
                    map.put(word,1);
                }
            }
        }
        br.close();
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for(Map.Entry<String, Integer> entrie :map.entrySet()){
            System.out.println(entrie.getKey()+"-----"+entrie.getValue());
        }
    }
}
