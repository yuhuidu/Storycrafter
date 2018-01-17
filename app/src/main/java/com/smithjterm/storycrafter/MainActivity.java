package com.smithjterm.storycrafter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah
    static final int EDIT_REQUEST = 1;
    public static final String EDIT_ID_KEY = "edit id";
    public static final String EDIT_TITLE_KEY = "edit title";
    public static final String EDIT_BODY_KEY = "edit body";
    public static final String EDIT_CHOICE_1_KEY = "edit choice 1";
    public static final String EDIT_CHOICE_2_KEY = "edit choice 2";

    StoryTree mainTree = new StoryTree(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTree.setLeft(new StoryTree(2));
        mainTree.setRight(new StoryTree(1));

        mainTree.getLeft().setLeft(new StoryTree(4));
        mainTree.getLeft().setRight(new StoryTree(6));

        mainTree.getRight().setLeft(new StoryTree(5));
        mainTree.getRight().setRight(new StoryTree(3));

        // The test below makes sure isFull works.

        //  String result = "test result: "+mainTree.isFull();
        //  Log.i("MainActivity",result);

        // 2131165250 1 2 3 4 5 6

        String test = " "+R.id.imageButton1+" "+R.id.imageButton2+" "+R.id.imageButton3+" "+R.id.imageButton4
                +" "+R.id.imageButton5+" "+R.id.imageButton6+" "+R.id.imageButton7;

        Log.i("MainActivity",test);

        /*
            Intent i = new Intent(this, SecondActivity.class);

            i.putExtra(POST_KEY,postView.getText().toString());

            startActivity(i);

        } */
    }

    public void startNewActivity (View view){
        int id = (int) view.getId()-2131165250;
        // Log.i("MainActivity","searching for "+id);
        StoryTree tweakNode = mainTree.treeSearch(id);

        Intent intent = new Intent(this, NodeClicked.class);

        //TextView postView = (TextView) findViewById(R.id.postEntry);
        intent.putExtra(EDIT_ID_KEY,id);
        intent.putExtra(EDIT_TITLE_KEY, tweakNode.getTitle());
        intent.putExtra(EDIT_BODY_KEY, tweakNode.getBody());
        intent.putExtra(EDIT_CHOICE_1_KEY, tweakNode.getChoice1Txt());
        intent.putExtra(EDIT_CHOICE_2_KEY, tweakNode.getChoice2Txt());

        startActivityForResult(intent, EDIT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == EDIT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user EDITED THINGS.
                // get EDITED THINGS.
                Bundle extras = data.getExtras();
                int id = 2131165250 + extras.getInt(EDIT_ID_KEY);
                StoryTree tweakNode = mainTree.treeSearch(id);

                tweakNode.setTitle(extras.getString(EDIT_TITLE_KEY));
                tweakNode.setBody(extras.getString(EDIT_BODY_KEY));
                tweakNode.setChoice1txt(extras.getString(EDIT_CHOICE_1_KEY));
                tweakNode.setChoice1txt(extras.getString(EDIT_CHOICE_2_KEY));

                // The Intent's data Uri identifies which contact was selected.
                // Do something with the contact here (bigger example below)
            }
        }
    }

    public void playActivity(View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}
