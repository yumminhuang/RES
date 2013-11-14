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
    private String[] itemName = new String[]{
            "House", "Meeting", "Message",
            "Calendar", "Account", "Search",
            "Map", "Setting", "Favorites"};
    private Integer[] mIcons = {
            R.drawable.house, R.drawable.meeting, R.drawable.message,
            R.drawable.calendar, R.drawable.user, R.drawable.search,
            R.drawable.map, R.drawable.setting, R.drawable.favorites};

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        GridView marketMainView = (GridView) findViewById(R.id.gridview);

        SimpleAdapter IconAdapter = new SimpleAdapter(this, fillMap(),
                R.layout.griditem, new String[]{"imageView", "imageTitle"},
                new int[]{R.id.imageView, R.id.imageTitle});
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
                    Toast.makeText(HomePage.this, "Message", Toast.LENGTH_SHORT).show();
                } else if (arg2 == 3) {
                    Toast.makeText(HomePage.this, "Calendar", Toast.LENGTH_SHORT).show();
                } else if (arg2 == 4) {
                    Toast.makeText(HomePage.this, "Account", Toast.LENGTH_SHORT).show();
                } else if (arg2 == 5) {
                    Toast.makeText(HomePage.this, "Search", Toast.LENGTH_SHORT).show();
                } else if (arg2 == 6) {
                    Toast.makeText(HomePage.this, "Map", Toast.LENGTH_SHORT).show();
                } else if (arg2 == 7) {
                    Toast.makeText(HomePage.this, "Settings", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomePage.this, "Favorites", Toast.LENGTH_SHORT).show();
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