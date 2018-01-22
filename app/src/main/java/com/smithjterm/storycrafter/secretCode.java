package com.smithjterm.storycrafter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class secretCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_code);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String postText = extras.getString(MainActivity.CODE_KEY);

        TextView postView = (TextView) findViewById(R.id.codeText);
        postView.setText(postText);
    }

    public void startNewActivity (View view){
        finish();
    }

    public void copyActivity(View view){
        TextView postView = (TextView) findViewById(R.id.codeText);
        String text = postView.getText().toString();
        String label = "secret code";

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(secretCode.this,
                "Secret code has been copied!", Toast.LENGTH_SHORT).show();
    }
}
