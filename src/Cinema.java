import java.util.ArrayList;
import java.util.HashMap;


public class Cinema {
private ArrayList<Hall>Halls;
 private HashMap<String,String>usersName;
 private String pwManager;
 ArrayList<Customer>customers;


 // unparameterized constructor 
public Cinema(){}


// parameterized constructor 
public Cinema( ArrayList<Hall>Halls,String pwManager,ArrayList<Customer>customers,HashMap<String,String>usersName)
 {
    this.Halls=Halls;
    this.pwManager=pwManager;
    this.customers=customers;
    this.usersName=usersName;
}


// set manager password
 public void setPwManager(String pwManager)
  {
    this.pwManager = pwManager;
  }



// set halls method

public void SetHalls(ArrayList<Hall> Halls)
 {
    this.Halls=Halls;
 }


// add users in hashmap if user doesn't exists already

public boolean addUser(String name,String password)
 {
  if(usersName.containsKey(name) && usersName.containsValue(password))
    return false;
  else
    return true;
 }
 
 
 // delete users from hashmap
 
 public void deleteUser(String name , String password)
 {
   usersName.remove(name, password);
 }
}
