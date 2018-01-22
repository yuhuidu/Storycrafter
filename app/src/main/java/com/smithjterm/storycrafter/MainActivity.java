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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // let's tell some sci-fi stories! :D - sarah

    // TODO: @Helen, I made help do the loading thing. we just need a load button that when clicked
    // does the startLoadActivity.

    static final int EDIT_REQUEST = 1;
    static final int RESTART_REQUEST = 2;
    static final int BACK_REQUEST = 3; // if this isn't used pls delete
    static final int LOAD_REQUEST = 4;

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

    public static final String TREE_LOAD_KEY="tree load";
    public static final String LOAD_CODE_KEY="loaded code";

    // firebase instance variable
    private DatabaseReference firebaseDatabaseRef;

    StoryTree mainTree = new StoryTree(0);
    private String savedCode;
    private String alphabetPlus = "abcdefghijklmnopqrstuvwxyz01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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

    private ViewGroup rootView;
    public static boolean addButtonClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedCode = "";
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

        firebaseDatabaseRef = FirebaseDatabase.getInstance().getReference().child("stories");
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

    public void startLoadActivity(View view){
        Intent intent = new Intent(this,LoadScreen.class);
        startActivityForResult(intent, LOAD_REQUEST);
    }

    public void saveStory(View view){
            for (int i = 0; i < 6; i++){
              int x = (int) (Math.random() * alphabetPlus.length());
              if (x == alphabetPlus.length()-1){
                  savedCode += "Z";
              } else {
                  savedCode += alphabetPlus.substring(x,x+1);
              }
            }

            String file = thisToFile();// make the file

            firebaseDatabaseRef.push().setValue(new StoredTree(savedCode,file));

            Log.i("MainActivity",savedCode);
            // later: implement
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

                mainTree.getLeft().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show",4));
                mainTree.getLeft().setRight(new StoryTree("", "", "This is an ending", "Choices will not show",6));

                mainTree.getRight().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show",5));
                mainTree.getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show",3));
                savedCode = "";

            }
             else if (addButtonClicked){
                mainTree.getLeft().getLeft().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 8));
                mainTree.getLeft().getLeft().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 9));
                mainTree.getLeft().getRight().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 10));
                mainTree.getLeft().getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 11));

                mainTree.getRight().getLeft().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 12));
                mainTree.getRight().getLeft().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 13));
                mainTree.getRight().getRight().setLeft(new StoryTree("", "", "This is an ending", "Choices will not show", 14));
                mainTree.getRight().getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show", 15));

            } else if(requestCode == LOAD_REQUEST){
                if(resultCode == RESULT_OK){
                    Bundle extras = data.getExtras();
                    String fullTree = extras.getString(TREE_LOAD_KEY);
                    savedCode = extras.getString(LOAD_CODE_KEY);

                    TreeTokenizer treeto = new TreeTokenizer();
                    ArrayList<String> trees = treeto.separate(fullTree,"{[","]}");

                    treeto.assignNode(trees.get(0),mainTree);
                    treeto.assignNode(trees.get(1),mainTree.getLeft());
                    treeto.assignNode(trees.get(2),mainTree.getRight());
                    treeto.assignNode(trees.get(3),mainTree.getLeft().getLeft());
                    treeto.assignNode(trees.get(4),mainTree.getLeft().getRight());
                    treeto.assignNode(trees.get(5),mainTree.getRight().getLeft());
                    treeto.assignNode(trees.get(6),mainTree.getRight().getRight());

                    // treeto.separate(trees.get(0),"[","]");

            }
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

    public String thisToFile() {
        String result = "";

        // mainTree
        result += "{[" + mainTree.getTitle() + "][" + mainTree.getBody() + "][" + mainTree.getChoice1Txt() + "][" + mainTree.getChoice2Txt() + "]}";

        //mainTree.getLeft()
        result += "{[" + mainTree.getLeft().getTitle() + "][" + mainTree.getLeft().getBody() + "][" + mainTree.getLeft().getChoice1Txt() + "][" + mainTree.getLeft().getChoice2Txt() + "]}";

        //mainTree.getRight()
        result += "{[" + mainTree.getRight().getTitle() + "][" + mainTree.getRight().getBody() + "][" + mainTree.getRight().getChoice1Txt() + "][" + mainTree.getRight().getChoice2Txt() + "]}";

        //mainTree.getLeft().getLeft();
        result += "{[" + mainTree.getLeft().getLeft().getTitle() + "][" + mainTree.getLeft().getLeft().getBody() + "][" + mainTree.getLeft().getLeft().getChoice1Txt() + "][" + mainTree.getLeft().getLeft().getChoice2Txt() + "]}";

        //mainTree.getLeft().getRight();
        result += "{[" + mainTree.getLeft().getRight().getTitle() + "][" + mainTree.getLeft().getRight().getBody() + "][" + mainTree.getLeft().getRight().getChoice1Txt() + "][" + mainTree.getLeft().getRight().getChoice2Txt() + "]}";

        //mainTree.getRight().getLeft();
        result += "{[" + mainTree.getRight().getLeft().getTitle() + "][" + mainTree.getRight().getLeft().getBody() + "][" + mainTree.getRight().getLeft().getChoice1Txt() + "][" + mainTree.getRight().getLeft().getChoice2Txt() + "]}";

        //mainTree.getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show",3));
        result += "{[" + mainTree.getRight().getRight().getTitle() + "][" + mainTree.getRight().getRight().getBody() + "][" + mainTree.getRight().getRight().getChoice1Txt() + "][" + mainTree.getRight().getRight().getChoice2Txt() + "]}";

        return result;
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
