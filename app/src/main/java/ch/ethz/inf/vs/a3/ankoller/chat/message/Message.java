package ch.ethz.inf.vs.a3.ankoller.chat.message;

import ch.ethz.inf.vs.a3.ankoller.chat.clock.VectorClock;

public class Message {
    public String content;
    public String timestamp;

    private VectorClock time;

    public Message (String timestamp, String content){
        this.content = content;
        this.timestamp = timestamp;

        // time = new TreeMap<Integer, Integer>();
        time = new VectorClock();
        time.setClockFromString(content);


        /*// or just call Lamport
        // eini vo de beide lines sett funktioniere, has aber ned testet =D
        String[] sarray = timestamp.substring(1, timestamp.length() - 1).split(",");
        //String[] sarray = timestamp.replaceAll("\\{", "").replaceAll("\\}", "").split(",");

        int i = 0;
        for (String s : sarray){
            String[] sa = s.split(":");
            time.put(Integer.parseInt(sa[0].replaceAll("\"", "")), Integer.parseInt(sa[1]));
        }*/
    }

    public String toString(){
        return timestamp + ": " + content + "\n";
    }
}
