package com.rememberme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rememberme.adapter.RemindersAdapter;
import com.rememberme.db.AppDataBase;
import com.rememberme.model.Reminder;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RemindersAdapter.ReminderAdapterInteraction {
    private RemindersAdapter mRemindersAdapter;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setViews();
        setRecyclerView();


        mFloatingActionButton.setOnClickListener(this);

    }
    private void setViews() {
        mFloatingActionButton = findViewById(R.id.fab);
    }

    private void setRecyclerView() {
        mRecyclerView  = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRemindersAdapter = new RemindersAdapter(this, getReminderList());
    }
    private List<Reminder> getReminderList(){
        return AppDataBase.getInstance(this).mReminderDao().getAll();
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
                break;
        }

    }

    @Override
    public void onDeleteReminder(Reminder reminder) {
        AppDataBase.getInstance(this).mReminderDao().delete(reminder);
    }
}
