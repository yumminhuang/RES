package edu.neu.res_clinet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by yummin on 13-11-6.
 */
public class Login extends Activity {

    private EditText id_Edit, passw_Edit;
    private Button register, login, setIp;
    private String userId, password;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // 实例化各个控件。
        setIp = (Button) findViewById(R.id.BU_Login_setIp);
        id_Edit = (EditText) findViewById(R.id.ET_Login_userId);
        passw_Edit = (EditText) findViewById(R.id.ET_Login_password);
        login = (Button) findViewById(R.id.BU_Login_login);
        register = (Button) findViewById(R.id.BU_Login_register);
        // 设置注册和登录按钮。
        setIp.setOnClickListener(new SetIpListener());
        register.setOnClickListener(new RegisterListener());
        login.setOnClickListener(new LoginListener());
    }

    /*
     *  响应设置IP按钮单击事件。
     */
    class SetIpListener implements OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Login.this, SetServerIp.class);
            startActivity(intent);
        }
    }

    /*
     *  响应注册按钮单击事件：跳转到注册页面。
     */
    class RegisterListener implements OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(Login.this, Register.class);
            startActivity(intent);
        }
    }

    /*
     *  响应登录按钮单击事件：跳转到主页面。
     */
    class LoginListener implements OnClickListener {
        public void onClick(View v) {
            // 获取用户的账号和密码。
            userId = id_Edit.getText().toString().trim();
            password = passw_Edit.getText().toString().trim();
            // 判断账号密码是否完整。


            //	if(userId.equals("") || password.equals("")){
            //		Toast.makeText(Login.this, "请将您的账号密码填写完整", Toast.LENGTH_LONG).show();
            //		return;
            //	}
            // 连接服务器。
            //try{
            //		Socket server = util.getCon();
            //		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            //		PrintWriter out = new PrintWriter(new BufferedWriter(
            //				new OutputStreamWriter(server.getOutputStream())), true);
            // 将登录信息发送到服务器。
            //		out.println("login");
            //		out.println(new String(Base64.encode(userId.getBytes())));
            //		out.println(new String(Base64.encode(password.getBytes())));
            // 接收处理服务器返回的信息。
            //		String res = in.readLine();
            //		if(res.equals("success")){
            //			util.setUserId(userId);
            Intent intent = new Intent();
            intent.setClass(Login.this, HomePage.class);
            startActivity(intent);
            //} else {
            //	showDialog("用户名或者密码错误，请重新输入！");
            //	id_Edit.setText("");
            //	passw_Edit.setText("");
            //}
            //out.close();
            //in.close();
            //server.close();
            //} catch (Exception e){
            //	showDialog("连接不上服务器！"+e);
            //}
        }
    }

    /*
     *  提示信息msg。
     */
    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    /*
     * 创建登录界面的帮助菜单。
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "关于");
        menu.add(0, 1, 1, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    // 初始化帮助菜单选项。
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 0:
                aboutOptionDialog();
                break;
            case 1:
                exitOptionDialog();
                break;
        }
        return true;
    }

    // 定义“关于”选项。
    private void aboutOptionDialog() {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(Login.this)
                .setTitle("关于")
                .setMessage(R.string.About_Msg)
                        // 定义“确定：按钮。
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                            }
                        }).show();
    }

    // 定义“退出”选项。
    private void exitOptionDialog() {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(Login.this).setTitle("退出").setMessage("你确定退出吗？")
                        // 定义“确定”按钮。
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        }).setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                            }
                        }).show();
    }
}
