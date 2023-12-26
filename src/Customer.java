/*
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
*/
import java.util.ArrayList;

public class Customer {
    String name;
    String password;
    int ID;
    static int userID = 0;
    Ticketing booking;
    ArrayList<Ticket> user_tickets;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        user_tickets = new ArrayList<>();
        booking = new Ticketing();
        this.ID = userID;
        userID++; 
    }

/*
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
*/    




}
