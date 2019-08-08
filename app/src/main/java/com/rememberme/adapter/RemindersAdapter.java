package com.rememberme.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rememberme.R;
import com.rememberme.Utils;
import com.rememberme.model.Reminder;

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder>{

    private List<Reminder> mReminderList;
    private ReminderAdapterInteraction mListener;
    private Context mContext;

    public RemindersAdapter(List<Reminder> reminderList, ReminderAdapterInteraction listener, Context context) {
        mReminderList = reminderList;
        mListener = listener;
        mContext = context;
    }

    @NonNull
    @Override
    public RemindersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reminder_item, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RemindersAdapter.ViewHolder viewHolder, int i) {
        Reminder reminder = mReminderList.get(i);
        viewHolder.tvTitle.setText(mReminderList.get(i).getTitleReminder());
        viewHolder.tvImportance.setText(mReminderList.get(i).getImportanceReminder());
        viewHolder.tvDateReminder.setText(Utils.formatDateTime(reminder.getDate()));
        viewHolder.mView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mReminderList.size();
    }


    public void updateList(List<Reminder> updateList){
        mReminderList = updateList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final View mView;
        public final TextView tvTitle, tvImportance, tvDateReminder;
        public final CardView mCardView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            mView = itemView;
            tvTitle = itemView.findViewById(R.id.titleReminder);
            tvImportance = itemView.findViewById(R.id.importanceReminder);
            tvDateReminder = itemView.findViewById(R.id.textDateReminder);
            mCardView = itemView.findViewById(R.id.card_view_reminder);

            mView.setOnClickListener(this);
            mView.setOnLongClickListener(this);


        }
            @Override
            public void onClick (View v){
                int position = getAdapterPosition();
                Reminder reminder = mReminderList.get(position);
                if (mListener != null){
                    mListener.onUpdateReminder(reminder);
                }

            }

        @Override
        public boolean onLongClick(View v) {
            int position = (int) v.getTag();
            Reminder reminder = mReminderList.get(position);
            mListener.onDeleteReminder(reminder);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mReminderList.size());
            return true;
        }
    }

    public interface ReminderAdapterInteraction{
       void onUpdateReminder(Reminder reminder);
       void onDeleteReminder(Reminder reminder);
    }
}
