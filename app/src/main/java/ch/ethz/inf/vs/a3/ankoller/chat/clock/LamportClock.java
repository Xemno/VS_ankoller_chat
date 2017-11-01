package ch.ethz.inf.vs.a3.ankoller.chat.clock;

//Added and implemented all methods and the Map which we have to use according to the exercise sheet and according to Clock.java.

public class LamportClock implements Clock{

    private int time;

    @Override
    public void update(Clock other) {
        int othertime = ((LamportClock) other).getTime();
        time = Math.max(time,othertime);
    }

    @Override
    public void setClock(Clock other) {
        time = ((LamportClock) other).getTime();
    }

    @Override
    public void tick(Integer pid) {
        //The method can be called with the "null" parameter: clock.tick(null)
        time = time + 1;
    }

    @Override
    public boolean happenedBefore(Clock other) {
        return (time < ((LamportClock) other).getTime());
    }

    @Override
    public String toString(){
        //int to string
        return String.valueOf(time);
    }

    @Override
    public void setClockFromString(String clock) {
        // Must check if decimal values and string not empty
        // I think regular expression "\\d+" would be sufficient
        if (!clock.matches(".*\\d+.*") && !clock.isEmpty()) {
            time = Integer.parseInt(clock);
        }

    }

    public void setTime(int time){
        //Will return the current clock for the given process id.
        this.time = time;
    }

    public int getTime(){
        return this.time;
    }

}
