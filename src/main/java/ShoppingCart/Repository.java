package ShoppingCart;

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

/*
 * contain File class variable -> repository
 * Methods required
 * 1. to read in the repository from the app, to initialise a location
 * 2. getShoppingCarts -> to list out all the carts save in the list 
 * 3. Save(someone'cart) -> 1. variable for cartName 2. saveLocation 3. File class that new File(saveLocation)
 * 4. Intialise a OutputStream to null 5. if the there is no such file exist, try create a new file -> by setting 
 * new path by path.get to getting path from repository. Based on the path, create a directories, then use the saveFiles
 * variables to create a new file. 6. os will take the data from the new file outputstream from the savelocation.
 * 4. Load(username) -> use the username to find the cart name in the directory. repository is a file class variable,
 * look through all the files, if the file matches the user's cart, load the cartFile'data into FileInputStream ->bytes
 * cart.load(bytes), then close the inputstream
 * 
 */
public class Repository {
    //Initial the class properties 
    public File repository;

    public Repository(String db){
        this.repository=new File(db);
    }

    public List<String>getShoppingCarts(){                     //get Shoppingcart users 
        List<String> carts = new LinkedList<>();
        for(String n:repository.list()){
            carts.add(n.replace(".cart",""));
        }
        return carts; 
    }
    
    public Cart load(String name){
        String cartName = name + ".cart";
        Cart cart = new Cart(name);
        for(File cartFile: repository.listFiles()){
            if(cartFile.getName().equals(cartName)){
                try{
                    InputStream is = new FileInputStream(cartFile);     //Changing the file into bytes 
                    cart.load(is);                                      //Giving the bytes information to the cart to read
                    is.close(); 
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return cart;
    }

    public void save(Cart cart){
        String cartName = cart.getUsername() + ".cart";                          // The filename that it will be saved 
        String saveLocation = repository.getPath() + File.separator + cartName ;  // To set where to save the files
        File saveFile = new File(saveLocation);                                  // What is the difference between this and repository?
        OutputStream os = null;                                                  // Why I initialise this first and not wait till line 74?
        try{
            if(!saveFile.exists()){
                Path path = Paths.get(repository.getPath());  
                // repository path = project path + File.separator + db, why need Paths.get()?
                Files.createDirectories(path);                
                // Create the directory called cartdb -> why need to create here ? Cannot create elsewhere which is earlier?
                saveFile.createNewFile();                     
                // Create the txt file of the cart 
            }
            
            os = new FileOutputStream(saveLocation);          // bytes format, OutputStream os = new FileOutputStream(saveLocation); ?
            cart.save(os);                                    // The outputstream give data on the saveLocation which is a string?
            os.flush();
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
