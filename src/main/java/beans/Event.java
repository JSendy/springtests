package beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("prototype")
public class Event {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private int id;
    private String msg;
    @Autowired
    @Qualifier("newDate")
    private Date date;
    @Autowired
    private DateFormat df;


    public Event(){
        this.id = AUTO_ID.getAndIncrement();
    }

    public Event(Date date, DateFormat df){
        this();
        this.date = date;
        this.df = df;
    }
    public Event(Date date) {
        this.date = date;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "Event id: " + id + " message: " + msg + " date: " + df.format(date);
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
