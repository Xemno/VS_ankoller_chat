package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ch.ethz.inf.vs.a3.ankoller.chat.R;
import ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

public class ChatActivity extends AppCompatActivity {


    public static final String TVMESSAGE = "ch.ethz.inf.vs.a3.ankoller.chat.chat.TV_MESSAGE";

    private static String user;
    private static String uuid;
    private static String serverAddress = NetworkConsts.SERVER_ADDRESS;
    private static int udpPort = NetworkConsts.UDP_PORT;

    private Button retrievechatbutton;
    private TextView chattextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        retrievechatbutton = (Button)findViewById(R.id.RetChatButton);
        chattextView = (TextView)findViewById(R.id.tv_chatactivity);

        Bundle bundle = getIntent().getExtras();

        user = bundle.getString(ClientThread.USER_KEY);
        uuid = bundle.getString(ClientThread.UUID_KEY);

        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.PREFERENCES, Context.MODE_PRIVATE);
        serverAddress = sharedPreferences.getString(SettingsActivity.KEY_IP, NetworkConsts.SERVER_ADDRESS);
        udpPort = sharedPreferences.getInt(SettingsActivity.KEY_PORT, NetworkConsts.UDP_PORT);

        LocalBroadcastManager.getInstance(this).registerReceiver(new LBReceiver(), new IntentFilter("Chat_Broadcast"));

    }

    public class LBReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            chattextView.setText(intent.getStringExtra("MESSAGES"));
            retrievechatbutton.setEnabled(true);
        }
    }

    public void onRetChatButtonClick(View view) {
        retrievechatbutton.setEnabled(false);
        new Thread(new ChatThread(this, user, uuid, MessageTypes.RETRIEVE_CHAT_LOG, serverAddress, udpPort)).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.PREFERENCES, Context.MODE_PRIVATE);
        chattextView.setText(sharedPreferences.getString(TVMESSAGE, ""));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TVMESSAGE, chattextView.getText().toString());
        editor.apply();
    }
}
