package edu.neu.ui;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import org.joda.time.LocalDateTime;
import org.odata4j.exceptions.ServerErrorException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.neu.pattern.Schedule;
import edu.neu.res_clinet.R;
import edu.neu.util.ScheduleHandler;
import edu.neu.util.UserHandler;


public class Meeting extends TabActivity {
    private EditText AddTopic, AddStaff, AddText;
    private DatePicker AddDate;
    private Button AddReset, AddOk;
    private String strAddTopic, strAddStaff, strAddText;
    private Date date;

    private static final String PREFS_NAME = "Preference";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost myTabhost = this.getTabHost();
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

        // Set Buttons
        AddReset.setOnClickListener(new AddResetListener());
        AddOk.setOnClickListener(new AddOkListener());

		/*
         *  all my meeting
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Search", getResources().getDrawable(R.drawable.meeting))
                .setContent(myMeeting()));
    }

    /**
     * @return
     */
    private int readID() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt("UserID", 0);
    }

    private Intent myMeeting() {
        int id = readID();
        List<Schedule> meetings = ScheduleHandler.getMeetingById(id);

        Intent intent = new Intent(Meeting.this, MeetingList.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("mymeeting", (ArrayList<? extends Parcelable>) meetings);
        intent.putExtras(bundle);
        return intent;
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
                Toast.makeText(Meeting.this, R.string.record_error, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                int to = UserHandler.getIDFromName(strAddStaff);
                ScheduleHandler.addMeeting(readID(), to, strAddText, new LocalDateTime(date));
                Toast.makeText(Meeting.this, R.string.add_mesage, Toast.LENGTH_LONG).show();
            } catch (ServerErrorException e) {
                showDialog(R.string.error);
            }
        }
    }
}
