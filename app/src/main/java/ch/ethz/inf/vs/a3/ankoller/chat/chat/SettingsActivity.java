package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;

import ch.ethz.inf.vs.a3.ankoller.chat.R;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

/**
 * Created by anja on 25.10.2017.
 */

public class SettingsActivity extends FragmentActivity {

    public static String KEY_IP = "ch.ethz.inf.vs.a3.ankoller.chat.chat.KEY_IP";
    public static String KEY_PORT = "ch.ethz.inf.vs.a3.ankoller.chat.chat.KEY_PORT";

    EditText editadress;
    EditText editport;
    Button btnSave;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editadress = (EditText)findViewById(R.id.editText_port);
        editport = (EditText)findViewById(R.id.editText_port);




    }





}
