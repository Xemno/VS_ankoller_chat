package ch.ethz.inf.vs.a3.ankoller.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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


    }
    public void OnSettingsClick (View view){
        //method for what happens when settingbutton is clicked, also ins the layout file
        //int he settings button
        //transitions to another activity (Settingsactivity)
        //user should be able to set servers address and port --> see SettingsActivity class
        startActivity(new Intent(this,SettingsActivity.class));

    }
}
