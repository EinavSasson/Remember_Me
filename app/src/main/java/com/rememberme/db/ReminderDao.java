package com.rememberme.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rememberme.model.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {

    @Query("SELECT * FROM Reminder")
    List<Reminder>getAll();

    @Query("SELECT * FROM Reminder WHERE id = :id")
    Reminder getReminderById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Reminder...reminders);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

    @Update
    void update(Reminder reminder);

}
