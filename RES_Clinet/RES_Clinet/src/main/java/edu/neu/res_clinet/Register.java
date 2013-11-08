package edu.neu.res_clinet;


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

public class Register extends Activity {
    private EditText name_Edit, id_Edit, pw_Edit;
    private Button reset_Button, register_Button;
    private Spinner job_spinner, department_spinner;
    private ArrayAdapter<String> adapter1, adapter2;
    private static final String[] job_Str = {"经理", "员工"};
    private static final String[] department_Str = {"财务部", "生产部", "市场部", "人事部"};
    private String userId, password, name, job, department;
    private final static String PrKPath = "/sdcard/darkblue/";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        // instantialize the widget
        id_Edit = (EditText) findViewById(R.id.ET_Register_userId);
        pw_Edit = (EditText) findViewById(R.id.ET_Register_password);
        name_Edit = (EditText) findViewById(R.id.ET_Register_name);
        department_spinner = (Spinner) findViewById(R.id.SP_Register_department);
        job_spinner = (Spinner) findViewById(R.id.SP_Register_job);
        reset_Button = (Button) findViewById(R.id.BU_Register_reset);
        register_Button = (Button) findViewById(R.id.BU_Register_register);
        // 职务的适配器。
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, job_Str);
        job = "经理";
        job_spinner.setAdapter(adapter1);
        job_spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                job = job_Str[arg2];
                arg0.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        // 部门的适配器。
        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, department_Str);
        department = "财务部";
        department_spinner.setAdapter(adapter2);
        department_spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                department = department_Str[arg2];
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
            id_Edit.setText("");
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
            userId = id_Edit.getText().toString().trim();
            password = pw_Edit.getText().toString().trim();
            // 判断输入的注册信息是否完整。
            if (userId.equals("") || password.equals("") || name.equals("")) {
                Toast.makeText(Register.this, "请将您的 信息填写完整", Toast.LENGTH_LONG).show();
                return;
            }
            // 连接服务器。
            try {
                Socket server = util.getCon();
                BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(server.getOutputStream())), true);
                // 生成公钥和私钥。
                KeyPair keyPair = RSACoder.initKey();
                byte[] publicKey = RSACoder.getPublicKey(keyPair);
                byte[] privateKey = RSACoder.getPrivateKey(keyPair);
                // 将注册信息发送到服务器。
                out.println("register");
                out.println(new String(Base64.encode(userId.getBytes())));
                out.println(new String(Base64.encode(password.getBytes())));
                out.println(new String(Base64.encode(name.getBytes())));
                out.println(new String(Base64.encode(job.getBytes())));
                out.println(new String(Base64.encode(department.getBytes())));
                out.println(new String(Base64.encode(publicKey)));
                // 接收处理服务器返回的信息。
                String res = in.readLine();
                if (res.equals("success")) {
                    util.setUserId(userId);
                    writePrK(userId, Base64.encode(privateKey));
                    RegisterDialog("注册成功，您的私钥已经保存在：" + PrKPath + userId +
                            "_PrK.key文件中\n\n" + "点击确定按钮自动跳转到您主页面");
                } else {
                    Toast.makeText(Register.this, "这个账号已经存在，请重新输入！", Toast.LENGTH_LONG).show();
                    id_Edit.setText("");
                    pw_Edit.setText("");
                }
                out.close();
                in.close();
                server.close();
            } catch (UnknownHostException e) {
                Toast.makeText(Register.this, "连接不上服务器！", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(Register.this, "网络异常！", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                Toast.makeText(Register.this, "InvalidKey!" + e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(Register.this, "解密失败！" + e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
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
