package com.smithjterm.storycrafter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StoryTree mainTree = new StoryTree(1);

        mainTree.setLeft(new StoryTree(2));
        mainTree.setRight(new StoryTree(3));

        mainTree.getLeft().setLeft(new StoryTree(4));
        mainTree.getLeft().setRight(new StoryTree(5));

        mainTree.getRight().setLeft(new StoryTree(6));
        mainTree.getRight().setRight(new StoryTree(7));

        // The test below makes sure isFull works.

        //  String result = "test result: "+mainTree.isFull();
        //  Log.i("MainActivity",result);

        /* public void startNewActivity(View view){ // have View as a param
            Intent i = new Intent(this, SecondActivity.class);

            TextView postView = (TextView) findViewById(R.id.postEntry);

            i.putExtra(POST_KEY,postView.getText().toString());

            startActivity(i);

        } */
    }

    public void startNewActivity (View view){
        Intent intent = new Intent(this, NodeClicked.class);

        //TextView postView = (TextView) findViewById(R.id.postEntry);
        //intent.putExtra(POST_KEY,postView.getText().toString());

        startActivity(intent);
    }
}
