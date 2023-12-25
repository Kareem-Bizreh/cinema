import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cinema {
    private ArrayList<Customer>customers;
    private ArrayList<Movie>movies;
    Hall halls []= new Hall[20];  
    int personsPerHour []= new int [24];

// unparameterized constructor 

    public Cinema()
    {
        for(int i=0;i<24;i++)
         personsPerHour[i]=0;
    }
 // parameterized constructor 
    public Cinema(ArrayList<Customer> customers, ArrayList<Movie> movies) {
        for(int i=0;i<24;i++)
          personsPerHour[i]=0;
        this.customers = customers;
        this.movies = movies;
    }
      
    
// boolean fun to add movie with comments and categories 

   boolean addMovie(String name, int duration, TypeMovie type,  HashMap<Date, Integer> showtimes,ArrayList<String> comments, ArrayList<String> categories)
    {
     Movie movie;
     for(int i=0;i<movies.size();i++)
      if(movies.get(i).getName()==name)
        return false;
     movie=new Movie(name,duration,type);
     movie.showtimes=showtimes;
     movie.comments=comments;
     movie.categories=categories;
     return true;  
    }


    // overloaded boolean fun to add movie without comments and categories 



    boolean addMovie(String name, int duration, TypeMovie type,  HashMap<Date, Integer> showtimes, ArrayList<String> categories)
    {
     Movie movie;
     for(int i=0;i<movies.size();i++)
      if(movies.get(i).getName()==name)
        return false;
     movie=new Movie(name,duration,type);
     movie.showtimes=showtimes;
     movie.categories=categories;
     return true;  
    }


// fun to return the most popular movie in the cinema

    Movie mostPopular()
    {
        
        Movie movie = new Movie(null, 0, null);
        int ans=0;
        for(Movie m:movies)
        {
          int count=0;
        Set set = m.showtimes.entrySet()  ;
        Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            Map.Entry mEntry =(Map.Entry)iterator.next();
            count+
        }
        if((int)mEntry.getValue()>count)
            {
                count=(int)mEntry.getValue();
                movie=m;
            }
       }
       return movie;
    }


// fun returns the crowded hour between all the showtimes 


    int rushHour(Movie movie)
    {
        Date time = new Date(null, 0);
        int count=-1;
        Set set = movie.showtimes.entrySet()  ;
        Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            Map.Entry mEntry =(Map.Entry)iterator.next();
            if((int)mEntry.getValue()>count)
            {
                count=(int)mEntry.getValue();
                time=(Date)mEntry.getKey();
            }
        }
        return time.hour;
    }


//fun returns the croweded city in the cinema 

    
    int rushHour()
    {
         Date time;
        for(int i=0;i<movies.size();i++)
         for(Movie movie:movies)
         {
          Set set = movie.showtimes.entrySet()  ;
          Iterator iterator = set.iterator();
          while(iterator.hasNext())
           {
            Map.Entry mEntry =(Map.Entry)iterator.next();
            time=(Date)mEntry.getKey();
            personsPerHour[time.hour]+=(int)mEntry.getValue();
           }
        } 
        int result=0;
        for(int i=0;i<24;i++)
         {
           result=Math.max(result, personsPerHour[i]);
         }
         return result;
    }

 // add user in the application user==customer
    boolean addUser(String name,String password)
    {
        for(int i=0;i<customers.size();i++)
        if(customers.get(i).name==name && customers.get(i).password==password)
          return false;
        Customer customer = new Customer();
        customer.name=name;
        customer.password=password;
        customers.add(customer);
        return true;
    }

  // remove the customer or user from the application
    
   boolean removeUser(Customer customer)
   {
    return customers.remove(customer);
   }

}
