package ch.ethz.inf.vs.a3.ankoller.chat.clock;

/**
 * Created by anja on 25.10.2017.
 */

class VectorClock  implements Clock{
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
    public void setClockFromString(String clock) {

    }
}
