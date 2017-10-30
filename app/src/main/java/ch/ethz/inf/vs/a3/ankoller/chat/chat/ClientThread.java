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

import ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

public class ClientThread implements Runnable {

    public static final String TAG = "##ClientThread## -> ";
    public static final String ERROR = "ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes.ERROR";
    public static final String UUID_KEY = "ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes.UUID";
    public static final String USER_KEY = "ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes.USER";

    private static String USER = "";
    private static String UUID = "";
    private static String TYPE = "";
    private static String IP = "";
    private static int PORT = 0;
    private static Context context;

    public ClientThread(Context context, String user, String uuid, String type, String serverAddress, int udpPort){
        this.context = context;
        this.USER = user;
        this.UUID = uuid;
        this.TYPE = type;
        this.IP = serverAddress;
        this.PORT = udpPort;
    }

    @Override
    public void run() {
        try {
            // register or unregister depending on TYPE
            register(TYPE);
            Log.i(TAG, TYPE + "ed successfully");

            // transition to new activity
            if (TYPE.equals(MessageTypes.REGISTER)) context.startActivity(new Intent(context, ChatActivity.class).putExtra(USER_KEY, USER).putExtra(UUID_KEY, UUID));
            else context.startActivity(new Intent(context, MainActivity.class));
        }
        catch (Exception e){
            Log.i(TAG, e.toString());
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(MainActivity.BROADCAST).putExtra(ERROR, e.toString()));
            return;
        }
    }

    private void register(String type) throws Exception {
        // try to register or deregister 5 times
        boolean registered = false;
        for (int i = 0; i < 5 && !registered; i++) {
            registered = doRegDereg(TYPE); // Do a registration or De-registration
        }
        if (!registered) throw new Exception(type + " FAILED");
    }

    private boolean doRegDereg(String type) {
    // Do a registration or De-registration
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(NetworkConsts.SOCKET_TIMEOUT);
            InetAddress ip = InetAddress.getByName(IP);
            Log.i(TAG, IP);
            Log.i(TAG, String.valueOf(PORT));

            //##// SEND REQUEST //##//
            byte[] packetData = createPacketData(USER, UUID, "", TYPE, "").getBytes();
            DatagramPacket packet = new DatagramPacket(packetData, packetData.length, ip, PORT);
            socket.send(packet);

            //##// RECEIVE REQUEST //##//
            byte[] buffer = new byte[2048];
            packet = new DatagramPacket(buffer, buffer.length);
            while (true){
                socket.receive(packet); // blocks until a datagram is received
                String received = new String(packet.getData(), 0, packet.getLength());
                if (new JSONObject(received).getJSONObject("header").optString("type").equals(MessageTypes.ACK_MESSAGE)) return true;
            }

        } catch (SocketException se) {Log.i(TAG, se.toString());}
          catch (UnknownHostException uhe) {Log.i(TAG, uhe.toString());}
          catch (SocketTimeoutException ste) {Log.i(TAG, ste.toString()); return false;}
          catch (IOException ioe) {Log.i(TAG, ioe.toString());}
          catch (JSONException je) {Log.i(TAG, je.toString());}
          finally {
            if (socket != null) socket.close();
        }
        return false;
    }

    private String createPacketData(String user, String uuid, String time, String type, String body){
        return MainActivity.PACKET_DATA.replace("|#|#|USER|#|#|", user).replace("|#|#|UUID|#|#|", uuid).replace("|#|#|TIME|#|#|", time).replace("|#|#|TYPE|#|#|", type).replace("|#|#|BODY|#|#|", body);
    }
}
