package edu.neu.ui;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

import edu.neu.res_clinet.R;


public class Apartment extends TabActivity {
    private TabHost myTabhost;
    private EditText AddTopic, AddStaff, AddText, SearchStaff;
    private DatePicker AddDate;
    private TimePicker AddTime;
    private Button AddReset, AddOk, SearchReset, SearchOk;
    private String strAddTopic, strAddStaff, strAddText, strSearchKey;
    private Date date;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myTabhost = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.meeting, myTabhost.getTabContentView(), true);

		/*
		 *  Add Meeting：
		 */
        myTabhost.addTab(myTabhost.newTabSpec("One")
                .setIndicator("Add", getResources().getDrawable(R.drawable.add))
                .setContent(R.id.Meeting_layout_add));
        //widgets
        AddTopic = (EditText) findViewById(R.id.ET_Meeting_AdTopic);
        AddStaff = (EditText) findViewById(R.id.ET_Meeting_AdStaff);
        AddText = (EditText) findViewById(R.id.ET_Meeting_AdText);
        AddDate = (DatePicker) findViewById(R.id.meeting_datePicker);
        AddTime = (TimePicker) findViewById(R.id.meeting_timePicker);
        AddReset = (Button) findViewById(R.id.BU_Meeting_AdReset);
        AddOk = (Button) findViewById(R.id.BU_Meeting_AdOk);

        AddDate.init(2013, 8, 20, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
            }
        });

        AddTime.setIs24HourView(true);
        AddTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay,
                                      int minute) {
            }
        });

        // Set Buttons
        AddReset.setOnClickListener(new AddResetListener());
        AddOk.setOnClickListener(new AddOkListener());

		/*
		 *  Search meeting
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Search", getResources().getDrawable(R.drawable.search))
                .setContent(R.id.Meeting_layout_search));
        // 实例化搜索界面的控件。
        SearchStaff = (EditText) findViewById(R.id.ET_Meeting_ShStaff);
        SearchReset = (Button) findViewById(R.id.BU_Meeting_ShReset);
        SearchOk = (Button) findViewById(R.id.BU_Meeting_ShOk);
        // 设置确定按钮。
        SearchReset.setOnClickListener(new SearchResetListener());
        SearchOk.setOnClickListener(new SearchOkListener());
    }

    /*
     *  提示信息msg。
     */
    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*
     *  Reset button
     */
    class AddResetListener implements OnClickListener {
        public void onClick(View v) {
            AddTopic.setText("");
            AddStaff.setText("");
            AddText.setText("");
        }
    }

    /*
     *  响应添加记录按钮单击事件：
     */
    class AddOkListener implements OnClickListener {
        public void onClick(View v) {
            // 获取用户输入信息。
            strAddTopic = AddTopic.getText().toString().trim();
            strAddStaff = AddStaff.getText().toString().trim();
            strAddText = AddText.getText().toString().trim();
            int year = AddDate.getYear();
            int month = AddDate.getMonth();
            int day = AddDate.getDayOfMonth();
            date = new Date(year, month, day);
            // 判断输入信息是否完整。
            if (strAddTopic.equals("") || strAddStaff.equals("") || strAddText.equals("")) {
                Toast.makeText(Apartment.this, "请将会议记录填写完整", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /*
     *  响应重置信息按钮(search)单击事件：
     */
    class SearchResetListener implements OnClickListener {
        public void onClick(View v) {
            SearchStaff.setText("");
        }
    }

    /*
     *  响应搜索按钮单击事件：
     */
    class SearchOkListener implements OnClickListener {
        public void onClick(View v) {
            // 获取用户输入的关键字。
            strSearchKey = SearchStaff.getText().toString().trim();
            // 判断输入的关键字是否为空。
            if (strSearchKey.equals("")) {
                Toast.makeText(Apartment.this, "请填写关键字", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }
}
