package vtp2022.dayworkshop;

import java.util.List;
import java.util.LinkedList;
import java.io.*;

public class Cart {
    private List<String> contents = new LinkedList<>();
    private String username;

    public Cart(String name){
        this.username = name;
        
    }
    public String getUsername() {
        return username;
    }
    
    public void add(String item){
        for(String inCart: contents){
            if(inCart.equals(item)){
                return;
            }
        }
        contents.add(item);
        }
        
        public String delete(int index){
            if(index<contents.size()){
                return contents.remove(index);
            }
            return "nothing";
        }

        public void load(InputStream is) throws IOException{
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String item;
            while((item = br.readLine()) != null){
                contents.add(item);
            }
            br.close();
            isr.close();
        }

        public void save(OutputStream os) throws IOException{
            OutputStreamWriter ows = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(ows);
            for(String item: contents){
                bw.write(item + "\n");
            } 
            ows.flush();
            bw.flush();
            ows.close();
            bw.close();
            // no need to have a sequence to close 
        }

        public List<String> getContents() {
            return contents;
        }

    }

