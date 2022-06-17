package ShoppingCart;

/**
 * Description:
 * Set a default database string 
 * The main app will read a name for the database if pass in 
 * If a string is read with the main class -> print the name and set the database to the input 
 * This string name will be passed on to repository to set up database 
 * The repository will be pass to session for the actions and functions
 * Session start function to initiate the session
 *
 */
public class App 
{
    private static String defaultdb = "db"; 
    public static void main( String[] args )
    { 
        if(args.length>0){
            if(args[0]!= null){
                System.out.println(args[0]);
                App.defaultdb = args[0];
            }
        }
        System.out.println(defaultdb);
        Repository repo = new Repository(defaultdb);
        System.out.println(repo);
        Session session = new Session(repo);
        session.start();
    }
}
