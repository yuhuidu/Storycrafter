package com.smithjterm.storycrafter;

/**
 * Created by Sarah Abowitz on 1/16/18.
 */

public class StoryTree {
    private StoryTree left;
    private StoryTree right;

    private int id;
    private String title;
    private String body;
    private String choice1txt;
    private String choice2txt;

    public StoryTree(){
      title = "";
      body = "";
      choice1txt = "";
      choice2txt = "";
    }

    public StoryTree(int x){
        title = "";
        body = "";
        choice1txt = "";
        choice2txt = "";
        id = x;
    }

    public StoryTree(String t, String b, String c1, String c2, int x){
      title = t;
      body = b;
      choice1txt = c1;
      choice2txt = c2;
      id = x;
    }

    public StoryTree getLeft() {
        return left;
    }

    public StoryTree getRight() {
        return right;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getChoice1txt() {
        return choice1txt;
    }

    public String getChoice2txt() {
        return choice2txt;
    }

    public int getId() {
        return id;
    }

    public void setLeft(StoryTree left) {
        this.left = left;
    }

    public void setRight(StoryTree right) {
        this.right = right;
    }

    public void setData(String t, String b, String c1, String c2){
        title = t;
        body = b;
        choice1txt = c1;
        choice2txt = c2;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setChoice1txt(String choice1txt) {
        this.choice1txt = choice1txt;
    }

    public void setChoice2txt(String choice2txt) {
        this.choice2txt = choice2txt;
    }

    public boolean isFull(){
      if (title.length() < 1 || body.length() < 1 || choice1txt.length() < 1 || choice2txt.length() < 1){
          return false;
      }

      if (left != null && right != null){
          return (left.isFull() && right.isFull());
      }

      return true;
    }
}
