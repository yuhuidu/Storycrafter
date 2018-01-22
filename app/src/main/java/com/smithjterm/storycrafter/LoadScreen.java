package com.smithjterm.storycrafter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class LoadScreen extends AppCompatActivity {

    String result = "";

    // Firebase instance variables
    private DatabaseReference mFirebaseDatabaseReference;
    ArrayList<StoredTree> stories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference().child("stories");
        stories = new ArrayList<>();

        mFirebaseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                stories.clear();
                for (DataSnapshot d: dataSnapshot.getChildren()){
                    StoredTree t = d.getValue(StoredTree.class);
                    stories.add(t);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LoadScreen", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    public void loadEntryActivity(View view){
        TextView blank = (TextView) findViewById(R.id.loadLine);
        String index = blank.getText().toString();
        if (Objects.equals(index, "")) {
            Toast.makeText(LoadScreen.this,
                    "Please enter the secret code", Toast.LENGTH_SHORT).show();
        } else {
            for (StoredTree t : stories) {
                if (index.equals(t.getCode()))
                    result = t.getFile();
            }

            Intent intent = getIntent();

            intent.putExtra(MainActivity.TREE_LOAD_KEY, result);
            intent.putExtra(MainActivity.LOAD_CODE_KEY, index);

            setResult(Activity.RESULT_OK, intent);

            finish();
        }
    }
}
