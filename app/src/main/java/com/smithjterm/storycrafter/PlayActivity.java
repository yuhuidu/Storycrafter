package com.smithjterm.storycrafter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class PlayActivity extends AppCompatActivity {

    StoryTree currentTree, homeTree;
    TextView title, body, c1, c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        title = (TextView) findViewById(R.id.playTitle);
        body = (TextView) findViewById(R.id.playBody);
        c1 = (TextView) findViewById(R.id.playChoice1Text);
        c2 = (TextView) findViewById(R.id.playChoice2Text);

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

        storyTrees[0].setChildren(storyTrees[1],storyTrees[2]);
        storyTrees[1].setChildren(storyTrees[3],storyTrees[4]);
        storyTrees[2].setChildren(storyTrees[5],storyTrees[6]);

        // Log.i("PlayActivity",storyTrees[0].toString(""));

        /* for (StoryTree s : storyTrees){
            Log.i("PlayActivity",s.toString(""));
        }*/

        currentTree = storyTrees[0];
        homeTree = storyTrees[0];

        String choiceMsg = currentTree.getBody()+"\n>"+currentTree.getChoice1Txt()+"\n>"+currentTree.getChoice2Txt();
        Log.i("PlayActivity",choiceMsg);
        title.setText(currentTree.getTitle());
        body.setText(currentTree.getBody());
        c1.setText(currentTree.getChoice1Txt());
        c2.setText(currentTree.getChoice2Txt());
    }

    public void makeChoice(View view){

        if (!currentTree.isEnding()) {

            if (view.getId() == R.id.playChoice1) {
                currentTree = currentTree.getLeft();
            } else {
                currentTree = currentTree.getRight();
            }

            // update title and body
            title.setText(currentTree.getTitle());
            body.setText(currentTree.getBody());
        } else {
            if (view.getId() == R.id.playChoice1){
               currentTree = homeTree;
                title.setText(currentTree.getTitle());
                body.setText(currentTree.getBody());
            } else {
               Intent i = getIntent();
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        }



      if (currentTree.isEnding()){
          Log.i("PlayActivity", currentTree.getBody());
          c1.setText("Play Again");
          c2.setText("New Story");
      } else {
          String choiceMsg = currentTree.getBody()+"\n>"+currentTree.getChoice1Txt()+"\n>"+currentTree.getChoice2Txt();
          Log.i("PlayActivity",choiceMsg);
          c1.setText(currentTree.getChoice1Txt());
          c2.setText(currentTree.getChoice2Txt());
      }
    }

    public StoryTree makeTree(int id, String[] info){
        StoryTree tree = new StoryTree(info[0],info[1],info[2],info[3], id);
        return tree;
    }

    public StoryTree makeEnd(int id, String title, String body){
        StoryTree tree = new StoryTree(title, body, "","",id);
        tree.setEndStatus(true);
        return tree;
    }
}
