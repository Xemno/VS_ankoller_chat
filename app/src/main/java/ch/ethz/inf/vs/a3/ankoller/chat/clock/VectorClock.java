package ch.ethz.inf.vs.a3.ankoller.chat.clock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Added and implemented all methods and the Map which we have to use according to the exercise sheet and according to Clock.java.
//To understand HashMap: https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html

 public class VectorClock  implements Clock{

    private Map<Integer, Integer> vector = new HashMap<>();
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

    @Override
    public void setClock(Clock other) {
        vector = ((VectorClock)other).vector;
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

    @Override
    public String toString(){
        String result = "{";
        Set<Integer> keys = vector.keySet();
        int processnr = 0;
        for (Integer k : keys){
            result = result + "\"" + processnr + "\"" + ":" + vector.get(k) + ",";
            processnr = processnr + 1;
        }
        //To get rid of the last comma, but only if there is one. (Not in the case of an empty clock.
        if (result.length() > 1){
            result = result.substring(0, result.length() - 1);
        }

        result = result + "}";

        return result;
    }

    @Override
    public void setClockFromString(String clock) {
        Map<Integer, Integer> vectortest = new HashMap<>();
        vectortest = new HashMap<>();
        int key;
        int value;
        int help;

            //As long as there are key value pairs in clock.
            while (clock.length() > 6){
                //Get rid of the first { or the comma and of the ".
                clock = clock.substring(2, clock.length());
                try{
                    key = Integer.parseInt(clock.substring(0, 1));
                } catch (NumberFormatException nfe){
                    System.out.println("Could not parse " + nfe + ".");
                    break;
                }
                //Do this while-loop as long as the next element of the string is part of the key.
                while(true){
                    //Get rid of the one number of the key.
                    clock = clock.substring(1, clock.length());
                    try{
                        help = Integer.parseInt(clock.substring(0, 1));
                    } catch (NumberFormatException nfe){
                        System.out.println("Could not parse " + nfe + ".");
                        break;
                    }
                    key = key * 10 + help;
                    clock = clock.substring(1, clock.length());
                }

                //Get rid of the " and the :.
                clock = clock.substring(2, clock.length());
                try{
                    value = Integer.parseInt(clock.substring(0, 1));
                } catch (NumberFormatException nfe){
                    System.out.println("Could not parse " + nfe + ".");
                    break;
                }
                //Do this while-loop as long as the next element of the string is part of the value.
                while(true){
                    //Get rid of the one number of the value.
                    clock = clock.substring(1, clock.length());
                    try{
                        help = Integer.parseInt(clock.substring(0, 1));
                    } catch (NumberFormatException nfe){
                        System.out.println("Could not parse " + nfe + ".");
                        break;
                    }
                    value = value * 10 + help;
                    clock = clock.substring(1, clock.length());
                }
                vectortest.put(key, value);
            }
            //clock.length() equals 2 in case of an empty string and 1 if the string wasn't empty but a correct one.
            if (clock.length() <= 2){
                vector = vectortest;
            }

        }

    public int getTime(Integer pid){
        return vector.get(pid);
    }

    public void addProcess(Integer pid, int time){
        //Put adds a new key, value pair to the vector: vector.put(Integer key, Integer value).
        vector.put(pid, time);
    }
}
