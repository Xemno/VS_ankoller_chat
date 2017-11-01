package ch.ethz.inf.vs.a3.ankoller.chat.message;

import java.util.Comparator;

import ch.ethz.inf.vs.a3.ankoller.chat.clock.VectorClock;

/**
 * Message comparator class. Use with PriorityQueue.
 */
public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message lhs, Message rhs) {
        // Write your code here
        //override compare method
        //use priority queue as a message buffer
        VectorClock sendclock= new VectorClock();
        VectorClock receiveclock= new VectorClock();

        if(sendclock.happenedBefore(receiveclock)){
            return -1;
        }else if(receiveclock.happenedBefore(sendclock)){
            //if receiveclock happpened before sendclock return 1
            return 1;
        }else {
            //happen at same time (send and receive)
            return 0;
        }

    }

}
