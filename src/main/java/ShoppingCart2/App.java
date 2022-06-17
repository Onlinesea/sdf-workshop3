package ShoppingCart2;

import java.io.IOException;

import main.java.ShoppingCart2.Repository;
import main.java.ShoppingCart2.Session;

/**
 * Hello world!
 *
 */
public class App 
{   private static String defaultdb = "db";
        public static void main( String[] args ) throws IOException
    {
        if(args.length>0){
            if(args[0] != null){
                defaultdb =args[0];
                System.out.println(args[0]);
            }
        }
        System.out.println( defaultdb );
        //Setting up the database 
        Repository repo = new Repository(defaultdb);
        //Setting up the session and pushing in the repo( database for the session to save the data)
        Session session = new Session(repo);
        //Start the session
        session.start();
    }
}
