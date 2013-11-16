package edu.neu.ui;


import java.io.*;
import java.net.*;
import java.security.KeyPair;
import java.security.spec.InvalidKeySpecException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.neu.res_clinet.R;

public class Register extends Activity {
    private EditText name_Edit, email_Edit, phone_Edit, pw_Edit;
    private Button reset_Button, register_Button;
    private Spinner id_spinner;
    private ArrayAdapter<String> adapter1;
    private static final String[] job_Str = {"Agent", "Landlord", "Tenant"};
    private String name, password, email, phone, identity;
    private final static String PrKPath = "/sdcard/darkblue/";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        // instantialize the widget
        email_Edit = (EditText) findViewById(R.id.ET_Register_email);
        phone_Edit = (EditText) findViewById(R.id.ET_Register_phone);
        pw_Edit = (EditText) findViewById(R.id.ET_Register_password);
        name_Edit = (EditText) findViewById(R.id.ET_Register_name);
        id_spinner = (Spinner) findViewById(R.id.SP_Register_identity);
        reset_Button = (Button) findViewById(R.id.BU_Register_reset);
        register_Button = (Button) findViewById(R.id.BU_Register_register);
        // 职务的适配器。
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, job_Str);
        identity = "经理";
        id_spinner.setAdapter(adapter1);
        id_spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                identity = job_Str[arg2];
                arg0.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        // 设置生成密钥和注册按钮。
        reset_Button.setOnClickListener(new ResetListener());
        register_Button.setOnClickListener(new RegisterListener());
    }

    /*
     *  响应重置信息按钮单击事件：
     */
    class ResetListener implements OnClickListener {
        public void onClick(View v) {
            name_Edit.setText("");
            email_Edit.setText("");
            phone_Edit.setText("");
            pw_Edit.setText("");
        }
    }

    /*
     *  响应注册按钮单击事件：
     */
    class RegisterListener implements OnClickListener {
        public void onClick(View v) {
            // 获得用户输入的注册信息。
            name = name_Edit.getText().toString().trim();
            email = email_Edit.getText().toString().trim();
            phone = phone_Edit.getText().toString().trim();
            password = pw_Edit.getText().toString().trim();
            // 判断输入的注册信息是否完整。
            if (name.equals("") || password.equals("") || email.equals("") || phone.equals("")) {
                Toast.makeText(Register.this, "请将您的 信息填写完整", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /*
     *  将私钥写入本机的文件中。
     */
    private void writePrK(String userId, byte[] pk) {
        try {
            File path = new File(PrKPath);
            File file = new File(PrKPath + userId + "_PrK.key");
            if (!path.exists()) {
                path.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter fos = new OutputStreamWriter(
                    new FileOutputStream(file));
            String str = new String(pk);
            // 将byte数组转换为String，写入文件/sdcard/darkblue/userId_PrK.txt
            fos.write(str);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *  提示信息msg。
     */
    private void RegisterDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    // 响应确定按钮单击事件：跳转到主页。
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(Register.this, HomePage.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
