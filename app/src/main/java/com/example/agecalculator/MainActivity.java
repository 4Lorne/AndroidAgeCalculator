package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button selectDOB, selectDate, calcAge;
    TextView tvDOBResult;
    TextView tvDateSelection;
    TextView tvAgeResult;
    LocalDate dob, date;
    DatePicker dp;
    boolean choice = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectDOB = findViewById(R.id.btnSelectDOB);
        selectDate = findViewById(R.id.btnDate);
        calcAge = findViewById(R.id.btnCalcAge);
        tvDOBResult = findViewById(R.id.tvDOBResult);
        tvDateSelection = findViewById(R.id.tvDateSelection);
        tvAgeResult = findViewById(R.id.tvAgeResult);
        selectDOB.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
            choice = true;
        });
        selectDate.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date picker");
            choice = false;
        });
        calcAge.setOnClickListener(v -> setCalcAge());
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        month = month + 1;
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        if (choice){
            tvDOBResult.setText(currentDateString);
            dob = LocalDate.of(year,month,dayOfMonth);
        } else {
            tvDateSelection.setText(currentDateString);
            date = LocalDate.of(year,month,dayOfMonth);
        }
    }

    public void setCalcAge(){
        try {
            Period p = Period.between(dob,date);
            tvAgeResult.setText(String.valueOf(p.getYears()));
        } catch (Exception e) {
            Toast.makeText(this,"Both dates must be selected",Toast.LENGTH_SHORT).show();
        }

    }
}