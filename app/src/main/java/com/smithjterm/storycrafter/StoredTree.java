package com.smithjterm.storycrafter;

/**
 * Created by Sarah Abowitz on 1/21/18.
 */

public class StoredTree {
    private String code;
    private String file;

    private StoredTree() {}

    public StoredTree(String c, String f) {
        this.code = c;
        this.file = f;
    }

    public String getCode() {
        return code;
    }

    public String getFile() {
        return file;
    }
}
