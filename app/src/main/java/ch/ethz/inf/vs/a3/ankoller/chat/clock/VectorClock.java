package ch.ethz.inf.vs.a3.ankoller.chat.clock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Added all methods and the Map which we have to use according to the exercise sheet and according to Clock.java.
//To understand HashMap: https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html

class VectorClock implements Clock{

    private Map<Integer, Integer> vector = new HashMap<>();
    //For each process id a logical time is associated

    @Override
    public void update(Clock other) {

        Map<Integer, Integer> otherVector = ((VectorClock) other).vector;
        Set<Integer> keys = otherVector.keySet(); // the keys are the process ids (pid)

        for (Integer k : keys) {
            if (vector.containsKey(k)) {
                if (vector.get(k) < otherVector.get(k)) vector.put(k, otherVector.get(k));
            } else {
                vector.put(k, otherVector.get(k));
            }
        }
    }
    //TODO
    @Override
    public void setClock(Clock other) {

    }

    @Override
    public void tick(Integer pid) {
        if(vector.containsKey(pid)){
            vector.put(pid, vector.get(pid)+1);
        }
        else{
            //We use the else case if the pid isn't a key of the vector until now.
            //I assume th first tick i the one from 0 to 1.
            vector.put(pid, 1);
        }


    }

    @Override
    public boolean happenedBefore(Clock other) {
        Map<Integer, Integer> compare = ((VectorClock)other).vector;
        Set<Integer> keys = compare.keySet();
        for (Integer k : keys) {
            //Not true assumption: I assume that vector has to contain every key which compare contains to get a true.
            //I have to check that no value of vector is bigger than the value of compare.
            if(vector.containsKey(k)){
                if(vector.get(k) > compare.get(k)){
                    return false;
                }
            }
        }

        for (Integer k : keys) {
            //I have to check that at least one value from vector less than the value of compare.
            if(vector.containsKey(k)){
                if(vector.get(k) < compare.get(k)){
                    return true;
                }
            }

        }

        return false;
    }

    //TODO
    @Override
    public String toString(){
        return "";
    }

    @Override
    public void setClockFromString(String clock) {

    }

    public int getTime(Integer pid){
        return vector.get(pid);
    }

    public void addProcess(Integer pid, int time){
        // adds a process and its vector clock to the current clock
        //Put adds a new (key, value) pair to the vector: vector.put(Integer key, Integer value).
        vector.put(pid, time);
    }
}
