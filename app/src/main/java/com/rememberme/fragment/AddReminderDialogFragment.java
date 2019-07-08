package com.rememberme.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rememberme.R;
import com.rememberme.adapter.RemindersAdapter;
import com.rememberme.model.Reminder;

public class AddReminderDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener, RemindersAdapter.ReminderAdapterInteraction {

    private ImageView mImageViewCloseButton;
    private TextView mTextViewDateTitle;
    private TextInputEditText mTextInputEditTextReminder, mTextInputEditTextNote;
    private Button mButtonCreateReminder;

    public AddReminderDialogFragment() {
    }

    public static AddReminderDialogFragment newInstance(){
        return new AddReminderDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.dialog_botton_sheet_add_reminder,container, false);
       mImageViewCloseButton = view.findViewById(R.id.close_dialog_button);
       mTextViewDateTitle = view.findViewById(R.id.text_date);
       mTextInputEditTextReminder = view.findViewById(R.id.edit_reminder);
       mTextInputEditTextNote = view.findViewById(R.id.edit_note);
       mButtonCreateReminder = view.findViewById(R.id.button_new_reminder);
       mButtonCreateReminder.setOnClickListener(this);
       mImageViewCloseButton.setOnClickListener(this);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_new_reminder:
                AddReminderListener addReminderListener = (AddReminderListener) getActivity();
                addReminderListener.onAddReminderClick(mTextInputEditTextReminder.getText().toString(),
                        mTextInputEditTextNote.getText().toString());
                       dismiss();
                       break;
            case R.id.close_dialog_button:
                dismiss();
                break;
            case R.id.text_date:
                // TODO date picker - textView should open a date picker
                break;
        }
    }

    @Override
    public void onDeleteReminder(Reminder reminder) {

    }
/*
    @Override
    public void onUpdateReminder(Reminder reminder) {
      String titleReminder = mTextInputEditTextReminder.getText().toString();
      String noteReminder = mTextInputEditTextNote.getText().toString();
      reminder.setTitleReminder(titleReminder);
      reminder.setNoteReminder(noteReminder);
      AppDataBase.getInstance(getActivity()).mReminderDao().update(reminder);
    }
    */
    public interface AddReminderListener{
        void onAddReminderClick(String reminderText, String noteText);
    }
}
