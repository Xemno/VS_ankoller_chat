package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import ch.ethz.inf.vs.a3.ankoller.chat.R;
import ch.ethz.inf.vs.a3.ankoller.chat.message.MessageTypes;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "##MainActivity## -> ";
    public static final String PREFERENCES = "ch.ethz.inf.vs.a3.ankoller.chat.chat.PREFERENCE_FILE_KEY";
    public static final String BROADCAST = "ch.ethz.inf.vs.a3.ankoller.chat.chat.BROADCAST_KEY";

    public static String PACKET_DATA;
    private EditText et_name;
    private Button button_join, button_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PACKET_DATA = getResources().getString(R.string.packet_data);
        et_name = (EditText) findViewById(R.id.edit_text_name);

        button_join = (Button) findViewById(R.id.button_join);
        button_join.setEnabled(true);

        button_settings = (Button) findViewById(R.id.button_settings);
        button_settings.setEnabled(true);

        LocalBroadcastManager.getInstance(this).registerReceiver(new LBReceiver(), new IntentFilter(BROADCAST));


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void OnClickJoin(View view){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        Log.i(TAG, "Network Info: " + networkInfo.toString());

        if (networkInfo == null || !networkInfo.isConnected()){
            Toast.makeText(getApplication(), "No internet connection!", Toast.LENGTH_LONG).show();
        }else{  // connection to the internet is made
            button_join.setEnabled(false);
            button_settings.setEnabled(false);

            String userName = et_name.getText().toString();
            String uuid = UUID.randomUUID().toString();

            //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Context context = getApplicationContext();
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

            String serverAddress = sharedPreferences.getString(SettingsActivity.KEY_IP, NetworkConsts.SERVER_ADDRESS);
            int udpPort = sharedPreferences.getInt(SettingsActivity.KEY_PORT, NetworkConsts.UDP_PORT);

            new Thread(new ClientThread(this, userName, uuid, MessageTypes.REGISTER, serverAddress, udpPort)).start();

        }

    }
    public void OnClickSettings (View view){
        // start activity for settings result
        startActivity(new Intent(this,SettingsActivity.class));

    }

    private class LBReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(ClientThread.ERROR);
            Toast.makeText(getApplication(), stringExtra, Toast.LENGTH_LONG).show();
            if (stringExtra.equals(MessageTypes.REGISTER)) {
                button_join.setEnabled(true);
                button_settings.setEnabled(true);
            }
        }
    }
}
