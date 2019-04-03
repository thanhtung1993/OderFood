package com.example.thanhtung_pc.oderfood.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.thanhtung_pc.oderfood.R;

import java.util.Calendar;

public class  DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar=Calendar.getInstance();
        int iNam=calendar.get(Calendar.YEAR);
        int iThang=calendar.get(Calendar.MONTH);
        int iNgay=calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,iNgay,iThang,iNam);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText edtNgaySinh =  getActivity().findViewById(R.id.edtNgaySinhDK);
        String sNgaySinh = dayOfMonth + "/" + (dayOfMonth+1) + "/" + year;
        edtNgaySinh.setText(sNgaySinh);
    }
}
