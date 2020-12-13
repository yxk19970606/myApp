package com.example.myapp.entity;

public class Feedback {
    public Feedback(String content, String state, String time,String objectId) {
        this.content = content;
        this.state = state;
        this.time = time;
        this.objectId = objectId;
    }
    private String content;
    private String state;
    private String time;
    private String objectId;
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
