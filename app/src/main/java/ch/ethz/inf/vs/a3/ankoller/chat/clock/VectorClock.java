package ch.ethz.inf.vs.a3.ankoller.chat.clock;

import java.util.Map;
import java.util.Set;

/**
 * Created by anja on 25.10.2017.
 */

//Added all methods and the Map which we have to use according to the exercise sheet and according to Clock.java.
//To understand HashMap: https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html

class VectorClock  implements Clock{

    private Map<Integer, Integer> vector;
    //For each process id you associate a logical time.

    @Override
    public void update(Clock other) {
        //I need to put .vector to get the Map<Integer, Integer>.
        Map<Integer, Integer> compare = ((VectorClock)other).vector;
        //How to iterate over a set: https://stackoverflow.com/questions/12455737/how-to-iterate-over-a-set-hashset-without-an-iterator
       Set<Integer> keys = compare.keySet();
        for (Integer k : keys) {
           if(vector.containsKey(k)){
                if(vector.get(k) < compare.get(k)){
                    vector.put(k, compare.get(k));
                }
           }
           else{
               vector.put(k, compare.get(k));
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
    //TODO
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
        return vector.get(pid);
    }

    public void addProcess(Integer pid, int time){
        //Put adds a new key, value pair to the vector: vector.put(Integer key, Integer value).
        vector.put(pid, time);
    }
}
