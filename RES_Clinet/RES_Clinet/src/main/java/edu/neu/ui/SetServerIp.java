package edu.neu.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.neu.res_clinet.R;

public class SetServerIp extends Activity {
    private String Ip;
    private EditText ip_Edit;
    private Button reset, ok;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setip);
        ip_Edit = (EditText) findViewById(R.id.ET_SetIp_Ip);
        reset = (Button) findViewById(R.id.BU_SetIp_Reset);
        ok = (Button) findViewById(R.id.BU_SetIp_Ok);
        reset.setOnClickListener(new ResetListener());
        ok.setOnClickListener(new OkListener());
    }

    /*
     *  提示信息msg。
     */
    private void SetIpDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(SetServerIp.this, Login.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     *
     */
    class ResetListener implements OnClickListener {
        public void onClick(View v) {
            ip_Edit.setText("");
        }
    }

    /**
     *
     */
    class OkListener implements OnClickListener {
        public void onClick(View v) {
            Ip = ip_Edit.getText().toString().trim();
            if (Ip.equals("")) {
                Toast.makeText(SetServerIp.this, R.string.ip_error, Toast.LENGTH_LONG).show();
                return;
            }
        }
    }
}
