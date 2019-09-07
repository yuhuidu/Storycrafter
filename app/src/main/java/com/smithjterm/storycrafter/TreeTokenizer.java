package com.smithjterm.storycrafter;

import android.util.Log;

import java.io.IOException;
import java.lang.StringBuilder;
import java.util.ArrayList;

/**
 * Created by Sarah Abowitz on 1/21/18. Note: This is adapted from tokenization code I used in
 * Google CodeU Summer 2017 to implement the Tokenizing Input feature.
 */

public class TreeTokenizer {
    private StringBuilder token; // builds current token

    private String file; // takes a tree

    private int at; // char pos in source

    public TreeTokenizer(){
        token = new StringBuilder();
        at = 0;
        file = "";
    }

    public TreeTokenizer( String s){
        file = s;
        token = new StringBuilder();
        at = 0;
    }

    public ArrayList<String> separate(String s, String opener, String closer){
        ArrayList<String> result = new ArrayList<>();
        String clone = new String(s);
        String bookend = closer+opener;

        while (clone.indexOf(bookend) > 1){
            int index = clone.indexOf(bookend)+bookend.length()-1;
            String addition = clone.substring(0, index);
            result.add(addition);
            Log.i("TreeTokenizer", "addition: "+addition);
            clone = clone.substring(index);
        }

        result.add(clone);
        Log.i("TreeTokenizer","clone: "+clone);

        return result;
    }

    public StoryTree assignNode(String treeInfo, StoryTree dest){
        ArrayList<String> infoDump = separate(treeInfo,"[","]");
        String[] results = new String[4];
        int i = 0;
        for (String s: infoDump){
            int start = s.indexOf("[")+1;
            int end = s.lastIndexOf("]");
            results[i] = s.substring(start,end);
            i++;
            Log.i("TreeTokenizer",s);
        }

        dest.setTitle(results[0]);
        dest.setBody(results[1]);

        if (infoDump.size() > 2) {
            dest.setChoice1txt(results[2]);
            dest.setChoice2txt(results[3]);
        }

        return dest;
    }

}
