package com.smithjterm.storycrafter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Intent i = getIntent();

        Bundle extras = i.getExtras();

        StoryTree[] storyTrees = new StoryTree[7];

        storyTrees[0] = makeTree(1, extras.getStringArray(MainActivity.NODE_1_INFO_KEY));
        storyTrees[1] = makeTree(2, extras.getStringArray(MainActivity.NODE_2_INFO_KEY));
        storyTrees[2] = makeTree(3, extras.getStringArray(MainActivity.NODE_3_INFO_KEY));

        storyTrees[3] = makeEnd(4, extras.getString(MainActivity.NODE_4_TITLE_KEY),
        extras.getString(MainActivity.NODE_4_BODY_KEY));

        storyTrees[4] = makeEnd(5, extras.getString(MainActivity.NODE_5_TITLE_KEY),
                extras.getString(MainActivity.NODE_5_BODY_KEY));

        storyTrees[5] = makeEnd(6, extras.getString(MainActivity.NODE_6_TITLE_KEY),
                extras.getString(MainActivity.NODE_6_BODY_KEY));

        storyTrees[6] = makeEnd(7, extras.getString(MainActivity.NODE_7_TITLE_KEY),
                extras.getString(MainActivity.NODE_7_BODY_KEY));

        /* for (StoryTree s : storyTrees){
            Log.i("PlayActivity",s.toString(""));
        }*/
    }

    public StoryTree makeTree(int id, String[] info){
        StoryTree tree = new StoryTree(info[0],info[1],info[2],info[3], id);
        return tree;
    }

    public StoryTree makeEnd(int id, String title, String body){
        StoryTree tree = new StoryTree(title, body, "","",id);
        return tree;
    }
}
