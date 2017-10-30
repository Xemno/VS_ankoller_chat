package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes;

/**
 * Created by Qais on 29-Oct-17.
 */

public class ClientThread implements Runnable {

    public static final String TAG = "##Client Thread## -> ";
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
//            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("CT_Broadcast").putExtra("ERROR", e.toString()));
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

        return false;
    }
}
