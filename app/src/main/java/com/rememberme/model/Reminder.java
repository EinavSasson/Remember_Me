package com.rememberme.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titleReminder;
    private String importanceReminder;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImportanceReminder() {
        return importanceReminder;
    }

    public void setImportanceReminder(String importanceReminder) {
        this.importanceReminder = importanceReminder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleReminder() {
        return titleReminder;
    }

    public void setTitleReminder(String titleReminder) {
        this.titleReminder = titleReminder;
    }

}
