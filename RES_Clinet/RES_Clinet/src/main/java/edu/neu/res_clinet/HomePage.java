package edu.neu.res_clinet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class HomePage extends Activity {
    private String[] itemName = new String[] {
            "会议记录", "邮件", "公告通知",
            "通讯录", "资源下载","薪资信息",
            "待办任务", "部门信息", "财务报表" };
    private Integer[] mIcons = {
            R.drawable.meeting_record, R.drawable.email,R.drawable.announcement,
            R.drawable.address_list, R.drawable.download,R.drawable.compensation,
            R.drawable.assignment, R.drawable.department_information,R.drawable.report_forms };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        GridView marketMainView = (GridView) findViewById(R.id.gridview);

        SimpleAdapter IconAdapter = new SimpleAdapter(this, fillMap(),
                R.layout.griditem, new String[] { "imageView", "imageTitle" },
                new int[] { R.id.imageView, R.id.imageTitle });
        marketMainView.setAdapter(IconAdapter);
        marketMainView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                if (arg2 == 0) {
                    Intent intent0 = new Intent(HomePage.this, Meeting.class);
                    startActivity(intent0);
                } else if (arg2 == 1) {
                    Intent intent1 = new Intent(HomePage.this, Email.class);
                    startActivity(intent1);
                } else if (arg2 == 2) {
                    Toast.makeText(HomePage.this, "公告通知",Toast.LENGTH_SHORT).show();
                } else if (arg2 == 3) {
                    Toast.makeText(HomePage.this, "通讯录",Toast.LENGTH_SHORT).show();
                } else if (arg2 == 4) {
                    Toast.makeText(HomePage.this, "资源下载",Toast.LENGTH_SHORT).show();
                } else if (arg2 == 5) {
                    Toast.makeText(HomePage.this, "薪资信息",Toast.LENGTH_SHORT).show();
                } else if (arg2 == 6) {
                    Toast.makeText(HomePage.this, "代办任务",Toast.LENGTH_SHORT).show();
                } else if (arg2 == 7) {
                    Toast.makeText(HomePage.this, "部门信息",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomePage.this, "财务报表",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // marketMainView.setAdapter(new IconAdapter(this));
    }

    private List<Map<String, Object>> fillMap() {
        // TODO Auto-generated method stub
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0, j = itemName.length; i < j; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("imageView", mIcons[i]);
            map.put("imageTitle", itemName[i]);
            list.add(map);
        }
        return list;
    }
}