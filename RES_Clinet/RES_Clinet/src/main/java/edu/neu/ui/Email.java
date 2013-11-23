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
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.neu.pattern.Message;
import edu.neu.res_clinet.R;
import edu.neu.util.MessageHandler;

public class Email extends TabActivity {
    private EditText SendReceiver, SendTopic, SendText;
    private Button SendReset, SendSend;
    private String strSendReceiver, strSendTopic, strSendText;

    private static final String PREFS_NAME = "Preference";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost myTabhost = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.message, myTabhost.getTabContentView(), true);

		/*
         * Inbox
		 */
        myTabhost.addTab(myTabhost.newTabSpec("One")
                .setIndicator("Inbox", getResources().getDrawable(R.drawable.inbox))
                .setContent(initInbox()));

		/*
         * Outbox
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Outbox", getResources().getDrawable(R.drawable.outbox))
                .setContent(initOutbox())); 

        /*
         * Edit a message
         */
        myTabhost.addTab(myTabhost.newTabSpec("Three")
                .setIndicator("Send", getResources().getDrawable(R.drawable.message))
                .setContent(R.id.Email_layout_send));
        // 实例化添加界面的控件。
        SendReceiver = (EditText) findViewById(R.id.ET_Email_SdReceiver);
        SendTopic = (EditText) findViewById(R.id.ET_Email_SdTopic);
        SendText = (EditText) findViewById(R.id.ET_Email_SdText);
        SendReset = (Button) findViewById(R.id.BU_Email_SdReset);
        SendSend = (Button) findViewById(R.id.BU_Email_SdSend);
        // 设置确定按钮。
        SendReset.setOnClickListener(new SendResetListener());
        SendSend.setOnClickListener(new SendSendListener());
    }

    /*
     *  TODO : implement this
     *  初始化收信箱。
     */
    public Intent initInbox() {
        int id = readID();
        List<Message> messages = MessageHandler.getInbox(id);

        Intent intent = new Intent(Email.this, EmailInbox.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("inbox", (ArrayList<? extends Parcelable>) messages);
        intent.putExtras(bundle);
        return intent;
    }

    public Intent initOutbox() {
        int id = readID();
        List<Message> messages = MessageHandler.getOutbox(id);

        Intent intent = new Intent(Email.this, EmailOutbox.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("outbox", (ArrayList<? extends Parcelable>) messages);
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
     *  响应重置信息按钮(outbox)单击事件：
     */
    class SendResetListener implements OnClickListener {
        public void onClick(View v) {
            SendReceiver.setText("");
            SendTopic.setText("");
            SendText.setText("");
        }
    }

    /*
     *  TODO : implement this
     *  响应发送按钮单击事件：
     */
    class SendSendListener implements OnClickListener {
        public void onClick(View v) {
            // 获取用户输入信息。
            strSendReceiver = SendReceiver.getText().toString().trim();
            strSendTopic = SendTopic.getText().toString().trim();
            strSendText = SendText.getText().toString().trim();

            if (strSendReceiver.equals("") || strSendTopic.equals("")
                    || strSendText.equals("")) {
                Toast.makeText(Email.this, R.string.message_error, Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /**
     * @return
     */
    private int readID() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt("UserID", 0);
    }

}
