package ShoppingCart;

import java.io.Console;
import java.util.List;

/*
 *  Take in the repository in case there is a need to pass this to the save function
 *  Session.start() from App to start the session 
 * 
 */
public class Session {
    private final String LOGIN = "login" ;        // To list the functions of the session clearly 
    private final String ADD = "add" ;
    private final String DELETE = "delete" ;
    private final String LOAD = "load" ;
    private final String SAVE = "save" ;
    private final String LIST = "list" ;
    private final String END = "end" ;
    private final String CARTS = "carts" ;
    private final String USERS = "users" ;

    public Cart currCart;                                       // Initialise the object that will be used 
    public Repository repository;

    public Session(Repository repo){                            // The repository will be the place for the database to be stored 
        this.repository=repo;
    }

    public void start(){
        Console cons = System.console();
        boolean stop = false;
        currCart = new Cart ("anon");

        while(!stop){
            String input = cons.readLine("> ");
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];

            switch(command){
                case LOGIN:
                    String username = inputs[1].trim();
                    currCart = new Cart(username);
                    System.out.printf("%s login okay", username);
                    break;

                case ADD:
                    inputs[1]=inputs[1].replaceAll(",", "");
                    String[] fruits = inputs[1].split(" ");
                    for(String fruit:fruits){
                        boolean found = false;
                        for (String fruitInCart: currCart.getFruitList()){
                            if(fruit.equalsIgnoreCase(fruitInCart)){
                                found = true;
                                System.out.printf("%s is already inside the cart\n", fruit);
                            }
                        }
                        if(!found){
                            currCart.add(fruit);
                            System.out.printf("%s is added into the cart\n", fruit);
                        }
                    }
                    break;

                case DELETE:
                    int delIndex = Integer.parseInt(inputs[1]);
                    String item = currCart.delete(delIndex);
                    System.out.printf("%s deleted from %s's cart", item, currCart.getUsername());
                    break;

                case LOAD:
                    currCart = repository.load(currCart.getUsername());
                    System.out.printf("Hi %s, your cart have been loaded, there is %d items in your cart", 
                        currCart.getUsername(),currCart.getFruitList().size());
                    break;

                case SAVE:
                    repository.save(currCart);
                    System.out.println("Done!");
                    break;

                case LIST:
                    System.out.printf("Items in %s's shopping cart\n",
                        currCart.getUsername());
                        printList(currCart.getFruitList()); 
                    break;

                case CARTS:
                    //TO BE REVIEWED    
                    break;

                case USERS:
                    List<String>allCarts = repository.getShoppingCarts();          //Get the list of the shoppingCarts
                    this.printList(allCarts);
                    break;

                case END:
                    stop = true;
                    break;

                default:
                    System.out.printf("Unknown input: %s\n", inputs[0]);
                    break;
                    
            }//end of switch command 
            System.out.println(" ");
        }
        System.out.println("Thank you for shopping with us");
        
    }

    public void printList(List<String> list){                           // A method that is used to print any list object in a numbered order 
        if(list.size()<=0){
            System.out.println("No record found!");
            return;
        }else {
            for(int i=0; i<list.size();i++){
                System.out.printf("%d. %s\n",i+1, list.get(i)); // 1. item1 2. item2 3. ....
            }
        }
    }

}
