package vtp2022.dayworkshop;


import java.io.Console;
import java.util.List;
import javax.imageio.stream.IIOByteBuffer;


public class Session {

    public static final String LIST = "list";
    public static final String CARTS = "carts";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String LOAD = "load";
    public static final String USERS = "users";
    public static final String SAVE = "save";
    public static final String END = "end";
    public static final String LOGIN = "login";

    private Respository respository;
    private Cart currCart;

    public Session(Repository repo){
        this.respository = repo;
    }

    public void start(){
        Console cons = System.console();
        boolean stop = false; 
        currCart = new Cart("anon");

        while(!stop){
            String input = cons.readLine(">");
            String[] terms = input.split(" ");
            switch(terms[0]){
                case CARTS:
                    System.out.println("List of shopping carts");
                    //list contents from LinkedList collection 
                    //TO DO
                    break;
                case LIST: 
                    System.out.printf("Items in %s shopping\n", currCart.getUsername());
                    printList(currCart.getContents());
                    //TO DO
                    break;
                case ADD:
                    int before = currCart.getContents().size();
                    for(int i=1; i<terms.length;i++){
                        currCart.add(terms[i]);
                        int addedCount = currCart.getContents().size() - before;
                        System.out.printf("Added %d item(s) to %s cart\n",
                            addedCount, currCart.getUsername());
                    }
                    break;
                case DELETE:
                    int idx = Integer.parseInt((terms[1]));
                    String item = currCart.delete(idx);
                    System.out.printf("Removed %s from %s's cart", item,currCart.getUsername());
                    break;
                case LOAD: 
                    //currCart = reps
                    //TO DO
                    break;

                case SAVE:
                    //TO DO
                    break;

                case LOGIN:
                    currCart = new Cart(terms[1]);
                    break;

                case USERS:

                            
                case END:
                    stop = true; 
                    break;
        
                default: 
                System.err.printf("Unknown input : %s\n", terms[0]);

            }
            System.out.println("");
        }
        System.out.println("thank you for shopping with us");
    }
    public void printList(List<String> list){
        if(list.size() <= 0 ){
            System.out.println("No record found");
            return;
        }
        for(int i=0; i<list.size(); i++){
            System.out.printf("%d. %s\n",(i+1),list.get(i)); 
        }
    }
}
