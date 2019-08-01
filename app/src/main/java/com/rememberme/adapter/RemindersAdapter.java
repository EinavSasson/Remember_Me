package com.rememberme.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rememberme.R;
import com.rememberme.db.AppDataBase;
import com.rememberme.model.Reminder;

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder> implements View.OnClickListener{

    private List<Reminder> mReminderList;
    private ReminderAdapterInteraction mListener;
    private AppDataBase db;


    public RemindersAdapter(ReminderAdapterInteraction reminderAdapterInteraction, List<Reminder> reminderList) {
        mListener = reminderAdapterInteraction;
        mReminderList = reminderList;

    }

    @NonNull
    @Override
    public RemindersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reminder_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RemindersAdapter.ViewHolder viewHolder, int i) {
    viewHolder.tvTitle.setText(mReminderList.get(i).getTitleReminder());
    viewHolder.tvImportance.setText(mReminderList.get(i).getImportanceReminder());
    viewHolder.mView.setOnClickListener(this);
    viewHolder.mView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return mReminderList.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        final Reminder reminder = mReminderList.get(position);
        mListener.onUpdateReminder(reminder);
    }

    public void updateList(List<Reminder> updateList){
        mReminderList = updateList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvImportance;
        public final CardView mCardView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mView = itemView;
            tvTitle = itemView.findViewById(R.id.titleReminder);
            tvImportance = itemView.findViewById(R.id.importanceReminder);
            mCardView = itemView.findViewById(R.id.card_view_reminder);

        }
    }
    public interface ReminderAdapterInteraction{
       void onUpdateReminder(Reminder reminder);

    }
}
