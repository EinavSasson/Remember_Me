package com.rememberme.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rememberme.R;
import com.rememberme.db.AppDataBase;
import com.rememberme.model.Reminder;

import java.text.SimpleDateFormat;
import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder>{

    private List<Reminder> mReminderList;
    private ReminderAdapterInteraction mListener;
    private AppDataBase db;
    private Context mContext;
    private Reminder mReminder;


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
        SimpleDateFormat dateFormat = new SimpleDateFormat("m");
        viewHolder.tvTitle.setText(mReminderList.get(i).getTitleReminder());
        viewHolder.tvImportance.setText(mReminderList.get(i).getImportanceReminder());
      //  viewHolder.tvDateMonth.setText(Utils.getMonthDate(mReminder.getDate()));
       // viewHolder.tvDayReminder.setText(Utils.getDayDate(mReminder.getDate()));
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView tvTitle, tvImportance, tvDateMonth, tvDayReminder;
        public final CardView mCardView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            mView = itemView;
            tvTitle = itemView.findViewById(R.id.titleReminder);
            tvImportance = itemView.findViewById(R.id.importanceReminder);
            tvDateMonth = itemView.findViewById(R.id.textMonthReminder);
            tvDayReminder = itemView.findViewById(R.id.textDayReminder);
            mCardView = itemView.findViewById(R.id.card_view_reminder);

            mView.setOnClickListener(this);

        }
            @Override
            public void onClick (View v){
                EditText titleValue, bodyValue;
                Button save, discard;

                AppDataBase noteDatabase;
                RemindersAdapter notesAdapter;
/*
                Toast.makeText(v.getContext(), "Einav", Toast.LENGTH_SHORT).show();
                View newNoteDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.new_note_dialog, null);

                AlertDialog.Builder newNoteDialogBuilder = new AlertDialog.Builder(v.getContext());
                newNoteDialogBuilder.setView(newNoteDialogView);
                final AlertDialog NewNoteDialog = newNoteDialogBuilder.create();
                NewNoteDialog.show();

                titleValue = newNoteDialogView.findViewById(R.id.title_editText_newNote_dialog);
                bodyValue = newNoteDialogView.findViewById(R.id.body_editText_newNote_dialog);
                save = newNoteDialogView.findViewById(R.id.save_new_note_dialog_btn);
                discard = newNoteDialogView.findViewById(R.id.discard_new_note_dialog_btn);

                note = mReminderList.get(getAdapterPosition());
                titleValue.setText(note.getTitleReminder());
              //  bodyValue.setText(note.getContent());
                //  bodyValue.setText(note.getContent());

                int position = (int) v.getTag();
                */


                int position = getAdapterPosition();
                Reminder reminder = mReminderList.get(position);
                if (mListener != null){
                    mListener.onUpdateReminder(reminder);
                }


                //mListener.onUpdateReminderClick();
              //  mListener.onUpdateReminderClick(mReminderList.get(position).getTitleReminder(), mReminderList.get(position).getImportanceReminder());


                //reminder = mReminderList.get(getAdapterPosition());




            }

    }

    public interface ReminderAdapterInteraction{
       void onUpdateReminder(Reminder reminder);
    }
}
