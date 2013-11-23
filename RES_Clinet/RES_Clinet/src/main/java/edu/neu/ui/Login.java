package edu.neu.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.odata4j.exceptions.NotFoundException;

import edu.neu.res_clinet.R;
import edu.neu.util.UserHandler;

/**
 * Created by yummin on 13-11-6.
 */
public class Login extends Activity {

    private EditText id_Edit, passw_Edit;
    private Button register, login;
    private String userName, password;

    public static final String PREFS_NAME = "Preference";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // 实例化各个控件。
        id_Edit = (EditText) findViewById(R.id.ET_Login_userId);
        passw_Edit = (EditText) findViewById(R.id.ET_Login_password);
        login = (Button) findViewById(R.id.BU_Login_login);
        register = (Button) findViewById(R.id.BU_Login_register);
        // Set sign-up and login button
        register.setOnClickListener(new RegisterListener());
        login.setOnClickListener(new LoginListener());
    }

    /*
     *  Show message in a dialog
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
     * Create help menu for login
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, R.string.about);
        menu.add(0, 1, 1, R.string.exit);
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
        new AlertDialog.Builder(Login.this).setTitle(R.string.about).setMessage(R.string.app_about)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                            }
                        }).show();
    }

    // 定义“退出”选项。
    private void exitOptionDialog() {
        // TODO Auto-generated method stub
        new AlertDialog.Builder(Login.this).setTitle(R.string.exit)
                .setMessage(R.string.app_menu_surequit).setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
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
            // Get user's id and password。
            userName = id_Edit.getText().toString().trim();
            password = passw_Edit.getText().toString().trim();
            // 判断账号密码是否完整。
            if (userName.equals("") || password.equals("")) {
                Toast.makeText(Login.this, R.string.record_error, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                int id = UserHandler.getIDFromName(userName);
                saveID(id);
                Intent intent = new Intent();
                intent.setClass(Login.this, HomePage.class);
                startActivity(intent);
            } catch (NotFoundException e) {
                showDialog("Please sign up first");
            }
            /*
            //连接服务器。
            try {
                Socket server = util.getCon();
                BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(server.getOutputStream())), true);
                //将登录信息发送到服务器。
                out.println("login");
                out.println(new String(Base64.encode(userId.getBytes())));
                out.println(new String(Base64.encode(password.getBytes())));
                //接收处理服务器返回的信息。
                String res = in.readLine();
                if (res.equals("success")) {
                    util.setUserId(userId);
                    */
            /*
                } else {
                    showDialog("用户名或者密码错误，请重新输入！");
                    id_Edit.setText("");
                    passw_Edit.setText("");
                }
                out.close();
                in.close();
                server.close();
            } catch (Exception e) {
                showDialog("连接不上服务器！" + e);
            }*/
        }
    }

    /**
     * @param id
     */
    private void saveID(int id) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("UserID", id);
        editor.commit();
    }
}
