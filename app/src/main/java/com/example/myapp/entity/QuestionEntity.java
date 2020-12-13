package com.example.myapp.entity;
import java.io.Serializable;
public class QuestionEntity implements Serializable{
    private String title;
    private String objectId;

    public QuestionEntity(String title, String objectId) {
        this.title = title;
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
