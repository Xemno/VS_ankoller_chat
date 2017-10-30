package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ch.ethz.inf.vs.a3.ankoller.chat.R;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = "##SettingsActivity## -> ";
    public static final String KEY_IP = "ch.ethz.inf.vs.a3.ankoller.chat.chat.IP_KEY";
    public static final String KEY_PORT = "ch.ethz.inf.vs.a3.ankoller.chat.chat.PORT_KEY";

    EditText editIP, editPort;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editIP = (EditText) findViewById(R.id.editText_ip);
        editPort = (EditText) findViewById(R.id.editText_port);
        saveButton = (Button) findViewById(R.id.save_button);

         //retrieve settings if available
        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.PREFERENCES, Context.MODE_PRIVATE);

        int port = sharedPreferences.getInt(KEY_PORT, NetworkConsts.UDP_PORT);
        editPort.setText(String.valueOf(port));
        final String ip = sharedPreferences.getString(KEY_IP, NetworkConsts.SERVER_ADDRESS);
        if (ip != null) {
            editIP.setText(ip);
        } else {
            Log.i(TAG, "Null reference to String");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = getApplicationContext();
                SharedPreferences sharedPreferences = context.getSharedPreferences(MainActivity.PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String ipStr = editIP.getText().toString();
                if (isIP(ipStr)) {
                    editor.putString(KEY_IP, ipStr);
                }

                String portStr = editPort.getText().toString();
                if (isPort(portStr)) {
                    editor.putInt(KEY_PORT, Integer.parseInt(portStr));
                }
                editor.apply();

                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });

    }

    public static boolean isPort(String str)
    {
        return str.matches("\\d+");
    }

    public static boolean isIP(String str)
    {
        return str.matches("\\d+(\\.\\d+)+");
    }
}
