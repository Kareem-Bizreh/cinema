import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    String name;
    String password;
    int ID;
    static int userID;
    Ticketing booking = new Ticketing();
    ArrayList<Ticket> user_ticket = new ArrayList<>();

    static {
        try {
            File f = new File("userID.txt");
            if(f.createNewFile()) {
                PrintWriter pw = new PrintWriter("userID.txt");
                pw.print(0);
                pw.close();
            }
            Scanner sc = new Scanner(f);
            userID = sc.nextInt();
            sc.close();
        } catch(IOException ioe) { } catch(Exception e) { }
    }
    
}
