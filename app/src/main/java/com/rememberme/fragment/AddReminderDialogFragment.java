package com.rememberme.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.rememberme.R;

import java.util.Calendar;
import java.util.Date;

public class AddReminderDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

        private TextInputEditText mTextInputLayoutEditReminder;
        private TextView mTextViewDate;
        private View mViewDate;
        private Spinner mImportanceSpinner;
        private Button mButtonSave, mButtonCancel;
        private Date mDate;


      public AddReminderDialogFragment() {}

      public static AddReminderDialogFragment newInstance() {
        return new AddReminderDialogFragment(); }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.dialog_botton_sheet_add_reminder, container, false);
                mTextInputLayoutEditReminder = view.findViewById(R.id.edit_reminder);
                mTextViewDate = view.findViewById(R.id.edit_date);
                mViewDate = view.findViewById(R.id.view_rectangle_date);
                mImportanceSpinner = view.findViewById(R.id.spinner_importance);

                   String title = this.getArguments().getString("reminder_title").toString();
                   mTextInputLayoutEditReminder.setText(title);
                   int priority = this.getArguments().getInt("reminder_priority");
                   mImportanceSpinner.setSelection(priority);

                mButtonSave = view.findViewById(R.id.save_button);
                mButtonCancel = view.findViewById(R.id.cancel_button);
                mButtonSave.setOnClickListener(this);
                mButtonCancel.setOnClickListener(this);
                mViewDate.setOnClickListener(this);
                setupSpinner();
                return view;
                }


    @Override
        public void onClick(View v) {
                switch (v.getId()) {
                case R.id.save_button:
                    AddReminderListener addReminderListener = (AddReminderListener) getActivity();
                    addReminderListener.onAddReminderClick(mTextInputLayoutEditReminder.getText().toString(),
                            mImportanceSpinner.getSelectedItem().toString(), mTextViewDate.getText().toString());
                    dismiss();
                break;
                case R.id.cancel_button:
                dismiss();
                break;
                case R.id.view_rectangle_date:
                datePicker();
                break;
                }
      }

        private void setupSpinner() {
        ArrayAdapter importanceSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
        R.array.array_importance_options, android.R.layout.simple_spinner_item);
        importanceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mImportanceSpinner.setAdapter(importanceSpinnerAdapter);
      }

      private void datePicker(){
          final Calendar calendar = Calendar.getInstance();
          int day = calendar.get(Calendar.DAY_OF_MONTH);
          int month = calendar.get(Calendar.MONTH);
          int years = calendar.get(Calendar.YEAR);
          DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                  mTextViewDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                  Calendar instance = Calendar.getInstance();
                  instance.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                  instance.set(Calendar.MONTH, month);
                  instance.set(Calendar.YEAR, year);
                  mDate = instance.getTime();
              }
          }, years, month, day);
          datePickerDialog.show();
      }


    public interface AddReminderListener {
            void onAddReminderClick(String reminderText, String noteText, String dateText);
    }
}
