package com.smithjterm.storycrafter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StoryTree mainTree = new StoryTree();

        mainTree.setLeft(new StoryTree());
        mainTree.setRight(new StoryTree());

        mainTree.getLeft().setLeft(new StoryTree());
        mainTree.getLeft().setRight(new StoryTree());

        mainTree.getRight().setLeft(new StoryTree());
        mainTree.getRight().setRight(new StoryTree());

        // The test below makes sure isFull works.

        //  String result = "test result: "+mainTree.isFull();
        //  Log.i("MainActivity",result);
    }

    public void startNewActivity (View view){
        Intent intent = new Intent(this, NodeClicked.class);

        //TextView postView = (TextView) findViewById(R.id.postEntry);
        //intent.putExtra(POST_KEY,postView.getText().toString());

        startActivity(intent);
    }

    public void playActivity(View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}
