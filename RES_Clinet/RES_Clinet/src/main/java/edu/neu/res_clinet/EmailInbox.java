package edu.neu.res_clinet;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailInbox extends ListActivity {
    private final static int countMax = 1000;
    private int count;
    private String[] tmp = new String[10];
    private String[] sender = new String[countMax];
    private String[] key = new String[countMax];
    private String[] topic = new String[countMax];
    private String[] text = new String[countMax];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        count = bundle.getInt("count");
        text = bundle.getStringArray("msg");

        for (int i = 0; i < count; i++) {
            tmp = text[i].split("#");
            sender[i] = tmp[0];
            key[i] = tmp[1];
            topic[i] = tmp[2];
            text[i] = tmp[3];
        }

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.resultlist, new String[]{"mainList", "subList"},
                new int[]{R.id.meeting_list_mainList, R.id.meeting_list_subList});
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);

        showDialog("邮件主题：" + topic[pos] + "\n\n" +
                "发信人：" + sender[pos] + "\n\n" +
                "关键字：" + key[pos] + "\n\n" +
                "正文：\n" + text[pos] + "\n");
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < count; i++) {
            map = new HashMap<String, Object>();
            map.put("mainList", "邮件" + (i + 1) + "：" + topic[i]);
            map.put("subList", "发信人：" + sender[i]);
            list.add(map);
        }
        return list;
    }

    // 提示信息msg。
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
}

