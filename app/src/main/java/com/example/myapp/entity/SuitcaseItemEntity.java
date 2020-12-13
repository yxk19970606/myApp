package com.example.myapp.entity;

import java.io.Serializable;

public class SuitcaseItemEntity implements Serializable {
    private String currentNumber;
    private String reminderNumber;
    private String reminderTime;
    private int isNeedReminder;
    private String drugName;

    public SuitcaseItemEntity(String currentNumber, String reminderNumber, String reminderTime, int isNeedReminder, String drugName) {
        this.currentNumber = currentNumber;
        this.reminderNumber = reminderNumber;
        this.reminderTime = reminderTime;
        this.isNeedReminder = isNeedReminder;
        this.drugName = drugName;
    }
    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getReminderNumber() {
        return reminderNumber;
    }

    public void setReminderNumber(String reminderNumber) {
        this.reminderNumber = reminderNumber;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public int getIsNeedReminder() {
        return isNeedReminder;
    }

    public void setIsNeedReminder(int isNeedReminder) {
        this.isNeedReminder = isNeedReminder;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}
