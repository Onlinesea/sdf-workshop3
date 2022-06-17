package main.java.ShoppingCart2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    public String name;
    public List<String> fruitList = new LinkedList<String>();

    public Cart(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public List<String> getFruitList() {
        return fruitList;
    }

    public void load (InputStream is)throws IOException{
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String item;
        while((item = br.readLine())!=null){
            fruitList.add(item);
        }
        br.close();
        isr.close();
    }

    public void save(OutputStream os)throws IOException {
        OutputStreamWriter ows = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(ows);
        for(String fruit : fruitList){
            bw.write(fruit + "\n" );
        }
        bw.flush();
        ows.flush();
        bw.close();
        ows.close();
    }

    public void add(String fruit){
        fruitList.add(fruit);
    }

    public String delete (int delIndex){
        if(delIndex<fruitList.size()){
            return fruitList.remove(delIndex -1);
        }else{
            return "nothing";
        }
    }
}
