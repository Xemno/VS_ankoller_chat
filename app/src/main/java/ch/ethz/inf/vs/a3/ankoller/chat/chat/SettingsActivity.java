package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ch.ethz.inf.vs.a3.ankoller.chat.R;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

public class SettingsActivity extends AppCompatActivity {

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

        // retrieve settings if available
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        editIP.setText(sharedPreferences.getString(KEY_IP, NetworkConsts.SERVER_ADDRESS));
//        editPort.setText(sharedPreferences.getInt(KEY_PORT, NetworkConsts.UDP_PORT));
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(KEY_IP, editIP.getText().toString());
//                editor.putInt(KEY_PORT, Integer.parseInt(editPort.getText().toString()));
//                editor.apply();
//
//                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
//            }
//        });

    }
}
