package ch.ethz.inf.vs.a3.ankoller.chat.message;

import ch.ethz.inf.vs.a3.ankoller.chat.clock.VectorClock;

public class Message {
    public String content;
    public String timestamp;

    private VectorClock time;

    public Message (String timestamp, String content){
        this.content = content;
        this.timestamp = timestamp;
        time = new VectorClock();
        time.setClockFromString(content);
    }

    public String toString(){
        return timestamp + ": " + content + "\n";
    }
}
