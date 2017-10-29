package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import ch.ethz.inf.vs.a3.ankoller.chat.R;
import ch.ethz.inf.vs.a3.ankoller.chat.udpclient.NetworkConsts;

/**
 * Created by anja on 25.10.2017.
 */

public class SettingsActivity extends FragmentActivity {
    //user should be able to set server adress and port --> modify xml file (activity_settingsttings.xml)
    //declare here
    EditText editadress;
    EditText editport;
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editadress = (EditText)findViewById(R.id.edit_adress);
        editport = (EditText)findViewById(R.id.edit_port);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editadress.setText(sharedPreferences.getString("key_stored_string_textview_adress", NetworkConsts.SERVER_ADDRESS));
        editport.setText(String.valueOf(sharedPreferences.getInt("key_stored_int_textview_port", NetworkConsts.UDP_PORT)));
    }




}
