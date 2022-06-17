package main.java.ShoppingCart2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Repository {
    public File repository;
    
    public Cart currCart;


    public Repository(String db){
        this.repository = new File (db);
    }

    public List<String> getShoppingCarts(){
        List<String> carts = new LinkedList<String>();
        for(String cartName : repository.list()){
            cartName = cartName.replace(".cart", "");
            carts.add(cartName);
        }
        return carts;
    }

    public Cart load(String name)throws IOException{
        //I will need to find the file that is with the name 
        currCart = new Cart(name);
        String cartName = currCart.getName() + ".cart";
        for(File cartFile : repository.listFiles()){
            if(cartFile.getName().equals(cartName)){
                InputStream is = new FileInputStream(cartFile);
                currCart.load(is);
                is.close();
            }
        }
        return currCart;
    }

    public void save(Cart cart)throws IOException{
        String cartName = cart.getName() + ".cart";
        String saveLocation = repository.getPath() + File.separator + cartName;
        File saveFile = new File(saveLocation);
        OutputStream os = null;
        if(!saveFile.exists()){
            Path path = Paths.get(repository.getPath());
            Files.createDirectories(path);
            saveFile.createNewFile();
        }
        os = new FileOutputStream(saveLocation); // why not put os = new FileOutputStream(saveFile)
        cart.save(os);
        os.flush();
        os.close();
    }
}
