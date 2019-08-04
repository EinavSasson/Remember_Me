package com.rememberme.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.rememberme.R;
import com.rememberme.adapter.RemindersAdapter;
import com.rememberme.db.AppDataBase;
import com.rememberme.fragment.AddReminderDialogFragment;
import com.rememberme.model.Reminder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener, RemindersAdapter.ReminderAdapterInteraction, AddReminderDialogFragment.AddReminderListener{

    private RemindersAdapter mRemindersAdapter;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private List<Reminder> mReminder = new ArrayList<>();
    private AddReminderDialogFragment mAddReminderDialogFragment;
    private Context mContext;
    private AppDataBase mAppDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mContext != null){
            EventBus.getDefault().register(this);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setViews();
        onClickListener();
        setRecyclerView();

    }
    private void setViews() {
        mFloatingActionButton = findViewById(R.id.fab);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String data) {
        if(data.equals("refresh_data")) {
            mRemindersAdapter.notifyDataSetChanged();
        }
    }
    private void onClickListener(){
        mFloatingActionButton.setOnClickListener(this);
    }

    private void setRecyclerView() {
        mRecyclerView  = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRemindersAdapter = new RemindersAdapter(getReminderList(), this, mContext);
        mRecyclerView.setAdapter(mRemindersAdapter);
    }
    private List<Reminder> getReminderList(){
        return AppDataBase.getInstance(this).mReminderDao().getAll();
    }
    private Reminder createReminder(String reminderText, String noteText, String dateText){
        Reminder reminder = new Reminder();
        reminder.setTitleReminder(reminderText);
        reminder.setImportanceReminder(noteText);
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
    public void onAddReminderClick(String reminderText, String noteText, String dateText) {
        Reminder reminder = createReminder(reminderText, noteText, dateText);
        AppDataBase.getInstance(this).mReminderDao().insert(reminder);
        List<Reminder>updateReminderList = getReminderList();
        mRemindersAdapter.updateList(updateReminderList);
    }

    @Override
    public void onUpdateReminder(Reminder reminder) {
        //Toast.makeText(this, reminder.getTitleReminder(), Toast.LENGTH_SHORT).show();
        AddReminderDialogFragment addReminderDialogFragment = new AddReminderDialogFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(AddReminderDialogFragment.newInstance(), null);
        Bundle bundle = new Bundle();
        bundle.putString("reminder_data", "data");
        addReminderDialogFragment.setArguments(bundle);
        fragmentTransaction.commit();




    }
}
