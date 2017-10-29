package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.ethz.inf.vs.a3.ankoller.chat.R;

public class MainActivity extends AppCompatActivity {

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

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void OnClickJoin(View view){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()){
            Toast.makeText(getApplication(), "No internet connection!", Toast.LENGTH_LONG).show();
        }else{  // connection to the internet is made
            //TODO
            //declare, username, userid,... to at last call a thread wich tries again registration
        }

    }
    public void OnClickSettings (View view){
        // start activity for settings result
        startActivity(new Intent(this,SettingsActivity.class));

    }
}
