package com.example.wiseman.saps;

/**
 * Created by Wiseman on 2017-05-04.
 */

public class homeItems {

    private int image;
    private String name;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public homeItems(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public homeItems(String time, String message) {
        this.time = time;
        this.message = message;
    }
}
