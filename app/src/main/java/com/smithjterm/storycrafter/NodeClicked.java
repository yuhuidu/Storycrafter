package com.smithjterm.storycrafter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NodeClicked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_clicked);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        int id = extras.getInt(MainActivity.EDIT_ID_KEY);
        String title = extras.getString(MainActivity.EDIT_TITLE_KEY);
        String body = extras.getString(MainActivity.EDIT_BODY_KEY);
        String choice1 = extras.getString(MainActivity.EDIT_CHOICE_1_KEY);
        String choice2 = extras.getString(MainActivity.EDIT_CHOICE_2_KEY);

    }

    public void saveEntryActivity(View view){
        Intent i = getIntent();

        TextView titleView = (TextView) findViewById(R.id.editTitle);
        i.putExtra(MainActivity.EDIT_TITLE_KEY,titleView.getText().toString());

        TextView bodyView = (TextView) findViewById(R.id.editBody);
        i.putExtra(MainActivity.EDIT_BODY_KEY,bodyView.getText().toString());

        TextView c1View = (TextView) findViewById(R.id.editChoice1);
        i.putExtra(MainActivity.EDIT_CHOICE_1_KEY,c1View.getText().toString());

        TextView c2View = (TextView) findViewById(R.id.editChoice2);
        i.putExtra(MainActivity.EDIT_CHOICE_2_KEY,c2View.getText().toString());

        finish();
    }
}
