package com.example.myapp.entity;

public class FeedBackDetailEntity {
    private String content;
    private String time;
    private String objectId;
    private String id;

    public FeedBackDetailEntity(String content, String time, String objectId,String id) {
        this.content = content;
        this.time = time;
        this.objectId = objectId;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
