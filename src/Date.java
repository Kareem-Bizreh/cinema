import java.time.DayOfWeek;

public class Date {

    DayOfWeek day;
    int hour;
    
    public Date(DayOfWeek day, int houer) {
        this.day = day;
        this.hour = houer;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if(this.day == date.day && this.hour == date.hour) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Date [day=" + day.toString() + ", hour=" + hour + "]";
    }

}