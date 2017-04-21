package com.looking.classicalparty.moudles.mine.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import com.looking.classicalparty.R;

import java.util.Calendar;

/**
 * Created by xin on 2016/10/28.
 */

public class ChooiseDateDialog extends Dialog {
    
    private DatePicker datePicker;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private  onDateSetListener listener;
    
    public ChooiseDateDialog(Context context) {
        this(context, R.style.base_dialog_theme);
    }
    
    public ChooiseDateDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooise_date_layout);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    public void setDateListener(onDateSetListener listener){
        this.listener=listener;
    }
    
    public interface onDateSetListener{
        void setDate(int year,int month,int day);
    }
}
