package com.smithjterm.storycrafter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah

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
}
