package ch.ethz.inf.vs.a3.ankoller.chat.clock;

import java.util.Map;

/**
 * Created by anja on 25.10.2017.
 */

//Added all methods and the Map which we have to use according to the exercise sheet and according to Clock.java.

class VectorClock  implements Clock{

    private Map<Integer, Integer> vector;
    //For each process id wou associate a logical time.

    @Override
    public void update(Clock other) {

    }

    @Override
    public void setClock(Clock other) {

    }

    @Override
    public void tick(Integer pid) {

    }

    @Override
    public boolean happenedBefore(Clock other) {
        return false;
    }

    @Override
    public String toString(){
        return null;
    }

    @Override
    public void setClockFromString(String clock) {

    }

    public int getTime(Integer pid){
        return 0;
    }

    public void addProcess(Integer pid, int time){

    }
}
