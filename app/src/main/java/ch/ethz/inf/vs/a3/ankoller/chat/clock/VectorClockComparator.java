package ch.ethz.inf.vs.a3.ankoller.chat.clock;

import java.util.Comparator;

public class VectorClockComparator implements Comparator<VectorClock> {

    @Override
    public int compare(VectorClock lhs, VectorClock rhs) {
        // Write your code here
        //This class is used in exercise 3.
        //we already have message comparatore
        VectorClock sendclock = new VectorClock();
        VectorClock receiveclock = new VectorClock();
       // sendclock.setClockFromString(lhs, timestamp);
        //receiveclock.setClockFromString(rhs.timestamp);

        if (sendclock.happenedBefore(receiveclock)) {
            return -1;
        } else if (receiveclock.happenedBefore(sendclock)) {
            return 1;
        } else {
            return 0;
        }
    }
    //TODO: not finished, above might be not sufficient
}
