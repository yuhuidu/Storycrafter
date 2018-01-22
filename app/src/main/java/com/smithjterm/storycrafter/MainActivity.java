package com.smithjterm.storycrafter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah
    static final int EDIT_REQUEST = 1;
    static final int RESTART_REQUEST = 2;
    static final int BACK_REQUEST = 3;
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
    public static final String NODE_4_INFO_KEY = "node 4 title";
    public static final String NODE_5_INFO_KEY = "node 5 title";
    public static final String NODE_6_INFO_KEY = "node 6 title";
    public static final String NODE_7_INFO_KEY = "node 7 title";

    public static final String NODE_4_TITLE_KEY = "node 4 title";
    public static final String NODE_4_BODY_KEY = "node 4 body";

    public static final String NODE_5_TITLE_KEY = "node 5 title";
    public static final String NODE_5_BODY_KEY = "node 5 body";

    public static final String NODE_6_TITLE_KEY = "node 6 title";
    public static final String NODE_6_BODY_KEY = "node 6 body";

    public static final String NODE_7_TITLE_KEY = "node 7 title";
    public static final String NODE_7_BODY_KEY = "node 7 body";

    public static final String NODE_8_TITLE_KEY = "node 8 title";
    public static final String NODE_8_BODY_KEY = "node 8 body";

    public static final String NODE_9_TITLE_KEY = "node 9 title";
    public static final String NODE_9_BODY_KEY = "node 9 body";

    public static final String NODE_10_TITLE_KEY = "node 10 title";
    public static final String NODE_10_BODY_KEY = "node 10 body";

    public static final String NODE_11_TITLE_KEY = "node 11 title";
    public static final String NODE_11_BODY_KEY = "node 11 body";

    public static final String NODE_12_TITLE_KEY = "node 12 title";
    public static final String NODE_12_BODY_KEY = "node 12 body";

    public static final String NODE_13_TITLE_KEY = "node 13 title";
    public static final String NODE_13_BODY_KEY = "node 13 body";

    public static final String NODE_14_TITLE_KEY = "node 14 title";
    public static final String NODE_14_BODY_KEY = "node 14 body";

    public static final String NODE_15_TITLE_KEY = "node 15 title";
    public static final String NODE_15_BODY_KEY = "node 15 body";

    StoryTree mainTree = new StoryTree(0);
    private ViewGroup rootView;
    public static boolean addButtonClicked = false;

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

        Log.i("MainActivity",test);

        /*
            Intent i = new Intent(this, SecondActivity.class);

            i.putExtra(POST_KEY,postView.getText().toString());

            startActivity(i);

        } */
        rootView = (ViewGroup) findViewById(R.id.add_layout);
    }

    public void startNewActivity (View view){
        int id = (int) view.getId()-R.id.imageButton1;
        Log.i("MainActivity","searching for "+id);
        StoryTree tweakNode = mainTree.treeSearch(id);

        Intent intent = new Intent(this, NodeClicked.class);

        intent.putExtra(EDIT_ID_KEY,id);
        intent.putExtra(EDIT_TITLE_KEY, tweakNode.getTitle());
        intent.putExtra(EDIT_BODY_KEY, tweakNode.getBody());
        intent.putExtra(EDIT_CHOICE_1_KEY, tweakNode.getChoice1Txt());
        intent.putExtra(EDIT_CHOICE_2_KEY, tweakNode.getChoice2Txt());

        Log.i("tweaknode body", tweakNode.getBody());
        Log.i("main tree body", mainTree.getBody());

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

                int id = extras.getInt(EDIT_ID_KEY);
                StoryTree tweakNode = mainTree.treeSearch(id);

                tweakNode.setTitle(extras.getString(EDIT_TITLE_KEY));
                tweakNode.setBody(extras.getString(EDIT_BODY_KEY));
                tweakNode.setChoice1txt(extras.getString(EDIT_CHOICE_1_KEY));
                tweakNode.setChoice2txt(extras.getString(EDIT_CHOICE_2_KEY));
            }
        }
        else if (requestCode == RESTART_REQUEST){
            if (resultCode == RESULT_OK){
                mainTree = new StoryTree(0);
                mainTree.setLeft(new StoryTree(2));
                mainTree.setRight(new StoryTree(1));
                mainTree.getLeft().setLeft(new StoryTree( 4));
                mainTree.getLeft().setRight(new StoryTree( 6));
                mainTree.getRight().setLeft(new StoryTree(5));
                mainTree.getRight().setRight(new StoryTree(3));
            }
            if (addButtonClicked){
                mainTree.getLeft().getLeft().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 8));
                mainTree.getLeft().getLeft().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 9));
                mainTree.getLeft().getRight().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 10));
                mainTree.getLeft().getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 11));

                mainTree.getRight().getLeft().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 12));
                mainTree.getRight().getLeft().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 13));
                mainTree.getRight().getRight().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 14));
                mainTree.getRight().getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 15));
            }
        }
    }

    public void playActivity(View view){

        //Log.i("MainActivity",mainTree.toString(""));

        if (mainTree.isFull()) {
            if (!addButtonClicked) {
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

                startActivityForResult(intent, RESTART_REQUEST);
            }
            else{
                Intent intent = new Intent(this, PlayActivity.class);

                intent.putExtra(NODE_1_INFO_KEY, mainTree.packageAssets());
                intent.putExtra(NODE_2_INFO_KEY, mainTree.getLeft().packageAssets());
                intent.putExtra(NODE_3_INFO_KEY, mainTree.getRight().packageAssets());
                intent.putExtra(NODE_4_INFO_KEY, mainTree.getLeft().getLeft().packageAssets());
                intent.putExtra(NODE_5_INFO_KEY, mainTree.getLeft().getRight().packageAssets());
                intent.putExtra(NODE_6_INFO_KEY, mainTree.getRight().getLeft().packageAssets());
                intent.putExtra(NODE_7_INFO_KEY, mainTree.getRight().getRight().packageAssets());

                intent.putExtra(NODE_8_TITLE_KEY, mainTree.getLeft().getLeft().getLeft().getTitle());
                intent.putExtra(NODE_8_BODY_KEY, mainTree.getLeft().getLeft().getLeft().getBody());

                intent.putExtra(NODE_9_TITLE_KEY, mainTree.getLeft().getLeft().getRight().getTitle());
                intent.putExtra(NODE_9_BODY_KEY, mainTree.getLeft().getLeft().getRight().getBody());

                intent.putExtra(NODE_10_TITLE_KEY, mainTree.getLeft().getRight().getLeft().getTitle());
                intent.putExtra(NODE_10_BODY_KEY, mainTree.getLeft().getRight().getLeft().getBody());

                intent.putExtra(NODE_11_TITLE_KEY, mainTree.getLeft().getRight().getRight().getTitle());
                intent.putExtra(NODE_11_BODY_KEY, mainTree.getLeft().getRight().getRight().getBody());

                intent.putExtra(NODE_12_TITLE_KEY, mainTree.getRight().getLeft().getLeft().getTitle());
                intent.putExtra(NODE_12_BODY_KEY, mainTree.getRight().getLeft().getLeft().getBody());

                intent.putExtra(NODE_13_TITLE_KEY, mainTree.getRight().getLeft().getRight().getTitle());
                intent.putExtra(NODE_13_BODY_KEY, mainTree.getRight().getLeft().getRight().getBody());

                intent.putExtra(NODE_14_TITLE_KEY, mainTree.getRight().getRight().getLeft().getTitle());
                intent.putExtra(NODE_14_BODY_KEY, mainTree.getRight().getRight().getLeft().getBody());

                intent.putExtra(NODE_15_TITLE_KEY, mainTree.getRight().getRight().getRight().getTitle());
                intent.putExtra(NODE_15_BODY_KEY, mainTree.getRight().getRight().getRight().getBody());

                startActivityForResult(intent, RESTART_REQUEST);
            }
        }
        else{
            Toast.makeText(MainActivity.this,
                    "All buttons need to be filled!", Toast.LENGTH_SHORT).show();
        }
    }

    public void helpActivity(View view){
        Intent intent = new Intent(this,help.class);
        startActivity(intent);
    }

    public void addActivity(View view){
        if (!addButtonClicked) {
            for (int i = 1; i < 9; i++) {
                ImageButton button = new ImageButton(MainActivity.this);
                //button.setImageResource(R.drawable.button1);
                button.setId(2131165261 + i);

                setTreeInAddActivity(i);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startNewActivity(v);
                    }
                });

                Log.i("new node id", "" + button.getId());

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                button.setBackgroundResource(R.drawable.button2);
                button.setPadding(55, 55, 55, 55);
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                rootView.addView(button, params);
            }
            addButtonClicked = true;
        }
    }

    public void setTreeInAddActivity(int i){
        if (i == 1){
            mainTree.getLeft().getLeft().setLeft(new StoryTree(6+i));
        }
        else if (i == 2){
            mainTree.getLeft().getLeft().setRight(new StoryTree(6+i));
        }
        else if (i == 3){
            mainTree.getLeft().getRight().setLeft(new StoryTree(6+i));
        }
        else if (i == 4){
            mainTree.getLeft().getRight().setRight(new StoryTree(6+i));
        }
        else if (i == 5){
            mainTree.getRight().getLeft().setLeft(new StoryTree(6+i));
        }
        else if (i == 6){
            mainTree.getRight().getLeft().setRight(new StoryTree(6+i));
        }
        else if (i == 7){
            mainTree.getRight().getRight().setLeft(new StoryTree(6+i));
        }
        else if (i == 8){
            mainTree.getRight().getRight().setRight(new StoryTree(6+i));
        }
    }

}
