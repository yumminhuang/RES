package edu.neu.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.spec.InvalidKeySpecException;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import edu.neu.res_clinet.R;


public class Meeting extends TabActivity {
    private TabHost myTabhost;
    private EditText AddTopic, AddKey, AddStaff, AddText, SearchKey;
    private Button AddReset, AddOk, SearchReset, SearchOk;
    private String strAddTopic, strAddKey, strAddStaff, strAddText, strSearchKey;
    private final static String PrKPath = "/sdcard/darkblue/";
    private final static int countMax = 1000;

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
                .setIndicator("Add", getResources().getDrawable(R.drawable.meeting_add))
                .setContent(R.id.Meeting_layout_add));
        //widgets
        AddTopic = (EditText) findViewById(R.id.ET_Meeting_AdTopic);
        AddKey = (EditText) findViewById(R.id.ET_Meeting_AdKey);
        AddStaff = (EditText) findViewById(R.id.ET_Meeting_AdStaff);
        AddText = (EditText) findViewById(R.id.ET_Meeting_AdText);
        AddReset = (Button) findViewById(R.id.BU_Meeting_AdReset);
        AddOk = (Button) findViewById(R.id.BU_Meeting_AdOk);
        // 设置确定按钮。
        AddReset.setOnClickListener(new AddResetListener());
        AddOk.setOnClickListener(new AddOkListener());
		
		/*
		 *  Search meeting
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Search", getResources().getDrawable(R.drawable.search))
                .setContent(R.id.Meeting_layout_search));
        // 实例化搜索界面的控件。
        SearchKey = (EditText) findViewById(R.id.ET_Meeting_ShKey);
        SearchReset = (Button) findViewById(R.id.BU_Meeting_ShReset);
        SearchOk = (Button) findViewById(R.id.BU_Meeting_ShOk);
        // 设置确定按钮。
        SearchReset.setOnClickListener(new SearchResetListener());
        SearchOk.setOnClickListener(new SearchOkListener());
    }

    /*
     *  Reset button
     */
    class AddResetListener implements OnClickListener {
        public void onClick(View v) {
            AddTopic.setText("");
            AddKey.setText("");
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
            strAddKey = AddKey.getText().toString().trim();
            strAddStaff = AddStaff.getText().toString().trim();
            strAddText = AddText.getText().toString().trim();
            // 判断输入信息是否完整。
            if (strAddTopic.equals("") || strAddKey.equals("") || strAddText.equals("")) {
                Toast.makeText(Meeting.this, "请将会议记录填写完整", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /*
     *  响应重置信息按钮(search)单击事件：
     */
    class SearchResetListener implements OnClickListener {
        public void onClick(View v) {
            SearchKey.setText("");
        }
    }

    /*
     *  响应搜索按钮单击事件：
     */
    class SearchOkListener implements OnClickListener {
        public void onClick(View v) {
            // 获取用户输入的关键字。
            strSearchKey = SearchKey.getText().toString().trim();
            // 判断输入的关键字是否为空。
            if (strSearchKey.equals("")) {
                Toast.makeText(Meeting.this, "请填写关键字", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /*
     *  在本机读取用户userId的私钥。
     */
    private byte[] readPrK(String userId) {
        File file = new File(PrKPath + userId + "_PrK.key");
        try {
            InputStream in = new FileInputStream(file);
            byte[] Prk = new byte[(int) file.length()];
            in.read(Prk);
            return Prk;
        } catch (Exception e) {
            showDialog("密钥读取失败!");
        }
        return null;
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
}
