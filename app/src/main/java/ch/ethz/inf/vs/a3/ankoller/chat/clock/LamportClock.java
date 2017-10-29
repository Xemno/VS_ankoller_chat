package ch.ethz.inf.vs.a3.ankoller.chat.clock;


/**
 * Created by Lara Kohler on 26.10.2017.
 */

//Added and implemented all methods and the Map which we have to use according to the exercise sheet and according to Clock.java.

public class LamportClock implements Clock{

    private int time;

    @Override
    public void update(Clock other) {
        //This doesn't work: int othertime = other.getTime();
        //I need to use (LamportClock(other)) to show that other is of the class LamportClock and not only from the class Clock.
        int othertime = ((LamportClock)other).getTime();
        time = Math.max(time,othertime);
        //First max, then tick.
    }

    @Override
    public void setClock(Clock other) {
        time = ((LamportClock)other).getTime();
    }

    @Override
    public void tick(Integer pid) {
        //The method can be called with the "null" parameter: clock.tick(null)
        time = time + 1;
    }

    @Override
    public boolean happenedBefore(Clock other) {
        return (time < ((LamportClock)other).getTime());
    }

    @Override
    public String toString(){
        //int to string
        return String.valueOf(time);
    }

    @Override
    public void setClockFromString(String clock) {
        //string to int
        try {
            time = Integer.parseInt(clock.toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe + ".");
        }
    }

    public void setTime(int time){
        //Will return the current clock for the given process id.
        this.time = time;
    }

    public int getTime(){
        return time;
    }

}
