package com.example.wiseman.saps;

/**
 * Created by EDU01 on 5/3/2017.
 */

public class noticeItems {

    private String message;

    public noticeItems(String message, String time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;


}
