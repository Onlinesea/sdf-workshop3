package main.java.ShoppingCart2;

import java.io.Console;
import java.io.IOException;
import java.util.List;

public class Session {
    //List the functions:
    private final String LOGIN = "login";
    private final String ADD = "add";
    private final String DELETE = "delete";
    private final String LOAD = "load";
    private final String SAVE = "save";
    private final String LIST = "list";
    private final String USERS = "users";
    private final String END = "end";

    public Cart currCart;
    public Repository repo;
    
    public Session(Repository repo){
        this.repo = repo;
    }

    public void start() throws IOException{
        boolean stop = false;
        while(!stop){
            Console cons = System.console();
            String input = cons.readLine("> ").trim();
            String [] inputs = input.split(" ", 2);
            String cmd = inputs[0];
            //System.out.println(inputs.length);
            switch(cmd){

                case LOGIN:
                    String name = inputs[1].trim().toLowerCase();
                    currCart = new Cart(name);
                    System.out.printf("%s login OK\n", currCart.getName());
                    break;

                case ADD:
                    inputs[1]= inputs[1].replaceAll(",", "");
                    String [] addFruits = inputs[1].split(" ");
                    for(String fruit : addFruits){
                        boolean found = false;
                        for( String fruitInCart : currCart.getFruitList()){
                            if(fruit.equals(fruitInCart)){
                                found = true ;
                                System.out.printf("%s is already in the cart\n", fruit);
                                break;
                            }
                        }
                        if(!found){
                            currCart.add(fruit);
                            System.out.printf("%s added into the cart\n", fruit);
                        }
                    }
                    break;

                case DELETE:
                    int delIndex = Integer.parseInt( inputs[1] );
                    System.out.printf("%s is removed from your cart\n", currCart.delete(delIndex));
                
                    break;

                case LOAD:
                    if(inputs.length<2){
                        currCart = repo.load(currCart.getName());
                    }else{
                        currCart = repo.load(inputs[1].trim().toLowerCase());
                    }
                    System.out.printf("%s's cart is loaded, there are %d items.\n", currCart.getName(), currCart.getFruitList().size());
                    break;

                case SAVE:
                    repo.save(currCart);
                    System.out.println("Save okay");
                    break;

                case LIST:
                    printList(currCart.getFruitList());
                
                    break;

                case USERS:
                    List<String> Users = repo.getShoppingCarts();
                    printList(Users);
                    break;

                case END:
                    stop =true;
                    break;

                default: 
                    System.out.printf("Invalid input");

            }
        }
    }

    public void printList(List list){
        for( int i = 1; i<(list.size()+1); i++){
            System.out.printf("%d. %s\n", i,list.get(i-1));
        }
    }
}
