package ShoppingCart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

/*
 * Cart class that neeed to contain 1. cartItems 2. Name of the cart belongs to 
 * Method required 1. add items 2. delete items 3. load data from a given path 4. save data into a given path
 * 5. getusername 6. getcontent
 * Load method -> read inputstream (bytes) -> BufferedReader (characters)
 * Save method -> write outputstream(bytes) -> BufferedWriter (characters), flush and close 
 * 
 */
public class Cart {
    public String username;
    public List<String> fruitList = new LinkedList<String>(); 


    public List<String> getFruitList() {
        return fruitList;
    }
    public String getUsername() {
        return username;
    }

    public Cart(String name){
        this.username = name;
    }

    public void add(String fruit){
        fruitList.add(fruit);
    }
    public String delete(int delIndex) {
        if(delIndex<fruitList.size()){
            return fruitList.remove(delIndex-1);
        }
        return "nothing";
    }

    public void load(InputStream is)throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String item;
        while((item = br.readLine())!=null){
            fruitList.add(item);
        }
        br.close();
        isr.close();
    }
    
    public void save(OutputStream os)throws IOException {            // os tell you where to save the file ? 
        OutputStreamWriter ows = new OutputStreamWriter(os);         
        BufferedWriter bw = new BufferedWriter(ows);
        for(String item: fruitList){
            bw.write(item+ "\n");
        }
        ows.flush();
        bw.flush();
        bw.close();
        ows.close();
    } 





}
