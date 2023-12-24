import java.time.DayOfWeek;

public class Date {

    DayOfWeek day;
    int hour;
    
    public Date(DayOfWeek day, int houer) {
        this.day = day;
        this.hour = houer;
    }

    public boolean equals(Date d) {
        if(this.day == d.day && this.hour == d.hour) return true;
        return false;
    }
}