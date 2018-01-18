package com.smithjterm.storycrafter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah
    static final int EDIT_REQUEST = 1;
    public static final String EDIT_ID_KEY = "edit id";
    public static final String EDIT_TITLE_KEY = "edit title";
    public static final String EDIT_BODY_KEY = "edit body";
    public static final String EDIT_CHOICE_1_KEY = "edit choice 1";
    public static final String EDIT_CHOICE_2_KEY = "edit choice 2";


    // key for nodes below, this has nothing to do with the id field
    //     1
    //   2   3
    //  4 5 6 7

    public static final String NODE_1_INFO_KEY = "node 1 title";

    public static final String NODE_2_INFO_KEY = "node 2 title";

    public static final String NODE_3_INFO_KEY = "node 3 title";

    public static final String NODE_4_TITLE_KEY = "node 4 title";
    public static final String NODE_4_BODY_KEY = "node 4 body";

    public static final String NODE_5_TITLE_KEY = "node 5 title";
    public static final String NODE_5_BODY_KEY = "node 5 body";

    public static final String NODE_6_TITLE_KEY = "node 6 title";
    public static final String NODE_6_BODY_KEY = "node 6 body";

    public static final String NODE_7_TITLE_KEY = "node 7 title";
    public static final String NODE_7_BODY_KEY = "node 7 body";

    StoryTree mainTree = new StoryTree(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTree.setLeft(new StoryTree(2));
        mainTree.setRight(new StoryTree(1));

        mainTree.getLeft().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show",4));
        mainTree.getLeft().setRight(new StoryTree("", "", "This is an ending", "Choices will not show",6));

        mainTree.getRight().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show",5));
        mainTree.getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show",3));

        // The test below makes sure isFull works.

        //  String result = "test result: "+mainTree.isFull();
        //  Log.i("MainActivity",result);

        // 2131165250 1 2 3 4 5 6

        String test = " "+R.id.imageButton1+" "+R.id.imageButton2+" "+R.id.imageButton3+" "+R.id.imageButton4
                +" "+R.id.imageButton5+" "+R.id.imageButton6+" "+R.id.imageButton7;

        // Log.i("MainActivity",test);

        /*
            Intent i = new Intent(this, SecondActivity.class);

            i.putExtra(POST_KEY,postView.getText().toString());

            startActivity(i);

        } */
    }

    public void startNewActivity (View view){
        int id = (int) view.getId()-2131165254;
        Log.i("MainActivity","searching for "+id);
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

                // Log.i("MainActivity", ""+data.getExtras().size());

                Bundle extras = data.getExtras(); // why would this be null ?

                int id = extras.getInt(EDIT_ID_KEY);
                StoryTree tweakNode = mainTree.treeSearch(id);

                tweakNode.setTitle(extras.getString(EDIT_TITLE_KEY));
                tweakNode.setBody(extras.getString(EDIT_BODY_KEY));
                tweakNode.setChoice1txt(extras.getString(EDIT_CHOICE_1_KEY));
                tweakNode.setChoice2txt(extras.getString(EDIT_CHOICE_2_KEY));
                // Log.i("MainActivity", mainTree.getBody());
                // The Intent's data Uri identifies which contact was selected.
                // Do something with the contact here (bigger example below)
            }
        }
    }

    public void playActivity(View view){

        //Log.i("MainActivity",mainTree.toString(""));

        if (mainTree.isFull()) {

            Intent intent = new Intent(this, PlayActivity.class);

            intent.putExtra(NODE_1_INFO_KEY, mainTree.packageAssets());
            intent.putExtra(NODE_2_INFO_KEY, mainTree.getLeft().packageAssets());
            intent.putExtra(NODE_3_INFO_KEY, mainTree.getRight().packageAssets());

            intent.putExtra(NODE_4_TITLE_KEY, mainTree.getLeft().getLeft().getTitle());
            intent.putExtra(NODE_4_BODY_KEY, mainTree.getLeft().getLeft().getBody());

            intent.putExtra(NODE_5_TITLE_KEY, mainTree.getLeft().getRight().getTitle());
            intent.putExtra(NODE_5_BODY_KEY, mainTree.getLeft().getRight().getBody());

            intent.putExtra(NODE_6_TITLE_KEY, mainTree.getRight().getLeft().getTitle());
            intent.putExtra(NODE_6_BODY_KEY, mainTree.getRight().getLeft().getBody());

            intent.putExtra(NODE_7_TITLE_KEY, mainTree.getRight().getRight().getTitle());
            intent.putExtra(NODE_7_BODY_KEY, mainTree.getRight().getRight().getBody());

            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, popupWindow.class);
            startActivity(intent);
        }
    }

}
