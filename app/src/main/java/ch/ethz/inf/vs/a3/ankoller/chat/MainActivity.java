package ch.ethz.inf.vs.a3.ankoller.chat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initial files
        //first change
        //change 2
    }
    public void OnClickJoin(View view){
        //method for what happens when joinbutton is clicked
        //also declared in the layout file android:OnClick in the join button
        //layout android_mainactivity
        //sends a message to the erceiver
        //on successful registration, application must transition
        //to a new activity (Chatactivity)
        //if registration not successful chat client tries automatically again
        //to register to the user 5 times, if still not successful,
        //application must display a notification like "registration not possible"
       //check if there is internet connection
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()){
            Toast.makeText(getApplication(), "no internetconnection!", Toast.LENGTH_LONG).show();
        }else{
            //TODO
            //declare, username, userid,... to at last call a thread wich tries again registration
        }

    }
    public void OnSettingsClick (View view){
        //method for what happens when settingbutton is clicked, also ins the layout file
        //int he settings button
        //transitions to another activity (Settingsactivity)
        //user should be able to set servers address and port --> see SettingsActivity class
        startActivity(new Intent(this,SettingsActivity.class));

    }
}
