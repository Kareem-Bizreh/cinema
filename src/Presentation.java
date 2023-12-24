public class Presentation {

    String hall_name;
    int hull_number;
    String movie_name;
    int movie_ID;
    Date time;
    Ticket p_tickets [][];
    int numberSoldTicke;
    int duration;

    public Presentation(String movie_name, int movie_ID, Date time, int duratoin, int hall_number) {
        this.hull_number = hall_number;
        this.hall_name = "Hull" + String.valueOf(hall_number);
        this.movie_name = movie_name;
        this.movie_ID = movie_ID;
        this.time = time;
        this.duration = duratoin;
        this.numberSoldTicke = 0;
        this.p_tickets = new Ticket[20][10];

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) {
                Pair<Integer, Integer> temp = new Pair<Integer, Integer>(i + 1, j + 1);
                p_tickets[i][j] = new Ticket(temp, time, movie_name, hall_name);
            }    
        }    
    }
    
    public boolean equals(Presentation p) {
        if(this.hall_name == p.hall_name && this.movie_ID == p.movie_ID && this.time.equals(p.time))
            return true;
        return false;    
    }
    
    public boolean is_full() {
        if(numberSoldTicke == 200) return true;
        return false;
    }

}