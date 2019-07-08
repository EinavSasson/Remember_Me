package com.rememberme.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rememberme.R;
import com.rememberme.adapter.RemindersAdapter;
import com.rememberme.db.AppDataBase;
import com.rememberme.fragment.AddReminderDialogFragment;
import com.rememberme.model.Reminder;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RemindersAdapter.ReminderAdapterInteraction, AddReminderDialogFragment.AddReminderListener {
    private RemindersAdapter mRemindersAdapter;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private Reminder mReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setViews();
        onClickListener();
        setRecyclerView();


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                mRemindersAdapter.deleteItem(item.getGroupId());
                List<Reminder>updateReminderList = getReminderList();
                mRemindersAdapter.updateList(updateReminderList);
                return true;
            default:
        }
        return super.onContextItemSelected(item);
    }

    private void setViews() {
        mFloatingActionButton = findViewById(R.id.fab);

    }
    private void onClickListener(){
        mFloatingActionButton.setOnClickListener(this);
    }

    private void setRecyclerView() {
        mRecyclerView  = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRemindersAdapter = new RemindersAdapter(this, getReminderList());
        mRecyclerView.setAdapter(mRemindersAdapter);
    }
    private List<Reminder> getReminderList(){
        return AppDataBase.getInstance(this).mReminderDao().getAll();
    }
    private Reminder createReminder(String reminderText, String noteText){
        Reminder reminder = new Reminder();
        reminder.setTitleReminder(reminderText);
        reminder.setNoteReminder(noteText);
        return reminder;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                AddReminderDialogFragment.newInstance().show(getSupportFragmentManager(), "add_reminder");
                break;
        }
    }

    @Override
    public void onDeleteReminder(Reminder reminder) {
        List<Reminder>updateReminderList = getReminderList();
        mRemindersAdapter.updateList(updateReminderList);
        AppDataBase.getInstance(this).mReminderDao().delete(reminder);
    }
/*
    @Override
    public void onUpdateReminder(Reminder reminder) {
        Toast.makeText(this, reminder.getTitleReminder(), Toast.LENGTH_SHORT).show();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(AddReminderDialogFragment.newInstance(), null);
        AppDataBase.getInstance(this).mReminderDao().update(reminder);
        fragmentTransaction.commit();


    }
*/
    @Override
    public void onAddReminderClick(String reminderText, String noteText) {
        Reminder reminder =createReminder(reminderText, noteText);
        AppDataBase.getInstance(this).mReminderDao().insert(reminder);
        List<Reminder>updateReminderList = getReminderList();
        mRemindersAdapter.updateList(updateReminderList);
    }

}
