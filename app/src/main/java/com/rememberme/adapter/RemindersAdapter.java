package com.rememberme.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rememberme.R;
import com.rememberme.model.Reminder;

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder> implements View.OnClickListener {

    private List<Reminder> mReminderList;
    private final ReminderAdapterInteraction mListener;

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
    public void onBindViewHolder(@NonNull RemindersAdapter.ViewHolder viewHolder, int i) {
    viewHolder.tvTitle.setText(mReminderList.get(i).getTitleReminder());
    viewHolder.tvNote.setText(mReminderList.get(i).getNoteReminder());

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
    Reminder reminder = mReminderList.get(position);
    mListener.onDeleteReminder(reminder);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, mReminderList.size());
    }



    public void updateList(List<Reminder> updateList){
        mReminderList = updateList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position){

     //   int position = (int) v.getTag();
        Reminder reminder = mReminderList.get(position);
        mListener.onDeleteReminder(reminder);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mReminderList.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        public final View mView;
        public final TextView tvTitle;
        public final TextView tvNote;
        public final CardView mCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tvTitle = itemView.findViewById(R.id.titleReminder);
            tvNote = itemView.findViewById(R.id.noteReminder);
            mCardView = itemView.findViewById(R.id.card_view_reminder);
            mCardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 1, 0, "Delete");

        }
    }
    public interface ReminderAdapterInteraction{
        void onDeleteReminder(Reminder reminder);
    }


}
