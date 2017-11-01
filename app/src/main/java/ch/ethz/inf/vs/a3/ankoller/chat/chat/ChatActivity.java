package ch.ethz.inf.vs.a3.ankoller.chat.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ch.ethz.inf.vs.a3.ankoller.chat.R;

public class ChatActivity extends AppCompatActivity {
    Button Retrievechatbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Retrievechatbutton=(Button)findViewById(R.id.RetChatButton);

    }

    public void onRetChatButtonClick(View view) {

    }
}
