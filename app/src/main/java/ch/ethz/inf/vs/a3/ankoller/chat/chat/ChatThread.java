package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Vector;

import ch.ethz.inf.vs.a3.ankoller.chat.message.Message;
import ch.ethz.inf.vs.a3.ankoller.chat.message.MessageComparator;
import ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

public class ChatThread implements Runnable {

    public static final String TAG = "##Chat## -> ";

    private static String USER;
    private static String UUID;
    private static String TYPE;
    private static String IP;
    private static int PORT = 0;
    private static Context context;

    public ChatThread(Context context, String user, String uuid, String type, String serverAddress, int udpPort){
        this.context = context;
        this.USER = user;
        this.UUID = uuid;
        this.TYPE = type;
        this.IP = serverAddress;
        this.PORT = udpPort;
    }
    @Override
    public void run() {
        Vector<Message> messages = getMessages();

        Collections.sort(messages, new MessageComparator());

        String result = "";
        for (Message m : messages) result += m.toString();
        if (result.equals("")) result = "No messages received!";
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("Chat_Broadcast").putExtra("MESSAGES", result));

    }

    private Vector<Message> getMessages() {
        DatagramSocket socket = null;
        Vector<Message> vectorMessages = new Vector<>();

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(NetworkConsts.SOCKET_TIMEOUT);
            InetAddress ip = InetAddress.getByName(IP);

            //##// SEND REQUEST //##//
            byte[] packetData = ClientThread.createPacketData(USER, UUID, "", TYPE, "").getBytes();
            DatagramPacket packet = new DatagramPacket(packetData, packetData.length, ip, PORT);
            socket.send(packet);

            //##// RECEIVE REQUEST //##//
            byte[] buffer = new byte[2048];
            packet = new DatagramPacket(buffer, buffer.length);
            while(true){
                socket.receive(packet); // blocks until a datagram is received
                String received = new String(packet.getData(), 0, packet.getLength());
                JSONObject jsonObject = new JSONObject(received);
                if (jsonObject.getJSONObject("header").optString("type").equals(MessageTypes.CHAT_MESSAGE)){
                    vectorMessages.add(new Message(jsonObject.getJSONObject("header").optString("timestamp"), jsonObject.getJSONObject("body").optString("content")));
                }
            }

        } catch (SocketException se) { Log.i(TAG, se.toString());}
          //catch (UnknownHostException uhe) {Log.i(TAG, uhe.toString());}
          catch (SocketTimeoutException ste) {Log.i(TAG, ste.toString()); return vectorMessages;}
          catch (IOException ioe) {Log.i(TAG, ioe.toString());}
          catch (JSONException je) {Log.i(TAG, je.toString());}
          finally {
            if (socket != null) socket.close();
        }
        return vectorMessages;
    }
}
