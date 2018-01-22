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
    }

    public void startNewActivity (View view){
        int id = (int) view.getId()-R.id.imageButton1;
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

                // Log.i("MainActivity", ""+data.getExtras().size());

                Bundle extras = data.getExtras(); // why would this be null ?

                int id = extras.getInt(EDIT_ID_KEY);
                StoryTree tweakNode = mainTree.treeSearch(id);

                tweakNode.setTitle(extras.getString(EDIT_TITLE_KEY));
                tweakNode.setBody(extras.getString(EDIT_BODY_KEY));
                tweakNode.setChoice1txt(extras.getString(EDIT_CHOICE_1_KEY));
                tweakNode.setChoice2txt(extras.getString(EDIT_CHOICE_2_KEY));
                // Log.i("MainActivity", mainTree.getBody());

            }
        } else if (requestCode == RESTART_REQUEST){
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

            startActivityForResult(intent, RESTART_REQUEST);
        }
        else{
            Intent intent = new Intent(this, popupWindow.class);
            startActivity(intent);
        }
    }

    public void helpActivity(View view){
        Intent intent = new Intent(this,help.class);
        startActivity(intent);
    }

    public String thisToFile(){
        String result = "";

        // mainTree
        result += "{["+mainTree.getTitle()+"]["+mainTree.getBody()+"]["+mainTree.getChoice1Txt()+"]["+mainTree.getChoice2Txt()+"]}";

        //mainTree.getLeft()
        result += "{["+mainTree.getLeft().getTitle()+"]["+mainTree.getLeft().getBody()+"]["+mainTree.getLeft().getChoice1Txt()+"]["+mainTree.getLeft().getChoice2Txt()+"]}";

        //mainTree.getRight()
        result += "{["+mainTree.getRight().getTitle()+"]["+mainTree.getRight().getBody()+"]["+mainTree.getRight().getChoice1Txt()+"]["+mainTree.getRight().getChoice2Txt()+"]}";

        //mainTree.getLeft().getLeft();
        result += "{["+mainTree.getLeft().getLeft().getTitle()+"]["+mainTree.getLeft().getLeft().getBody()+"]["+mainTree.getLeft().getLeft().getChoice1Txt()+"]["+mainTree.getLeft().getLeft().getChoice2Txt()+"]}";

        //mainTree.getLeft().getRight();
        result += "{["+mainTree.getLeft().getRight().getTitle()+"]["+mainTree.getLeft().getRight().getBody()+"]["+mainTree.getLeft().getRight().getChoice1Txt()+"]["+mainTree.getLeft().getRight().getChoice2Txt()+"]}";

        //mainTree.getRight().getLeft();
        result += "{["+mainTree.getRight().getLeft().getTitle()+"]["+mainTree.getRight().getLeft().getBody()+"]["+mainTree.getRight().getLeft().getChoice1Txt()+"]["+mainTree.getRight().getLeft().getChoice2Txt()+"]}";

        //mainTree.getRight().setRight(new StoryTree("", "", "This is an ending", "Choices will not show",3));
        result += "{["+mainTree.getRight().getRight().getTitle()+"]["+mainTree.getRight().getRight().getBody()+"]["+mainTree.getRight().getRight().getChoice1Txt()+"]["+mainTree.getRight().getRight().getChoice2Txt()+"]}";

        return result;

    }

}
