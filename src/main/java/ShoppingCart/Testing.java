package ShoppingCart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Testing {
public static void main(String[] args) throws IOException {
    File repo = new File("pokemon");
    System.out.println(repo.getAbsolutePath());
    Path path = Paths.get(repo.getPath());
    Files.createDirectories(path);

    String savelocation = repo.getPath() + File.separator + "kevin" ;
    File saveFile = new File(savelocation);
    saveFile.createNewFile();
    System.out.println(path);
    //System.out.println("Hello World");
}   
}