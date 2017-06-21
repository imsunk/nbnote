package com.nbnote;

/**
 * Created by K on 2017. 6. 20..
 */
public class Test {
    String title;

    public Test(String title, String name){
        this.title = title;
        this.name = name;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

}
