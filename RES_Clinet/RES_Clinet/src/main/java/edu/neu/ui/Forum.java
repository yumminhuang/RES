package edu.neu.ui;

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

import edu.neu.res_clinet.R;
import edu.neu.util.UserHandler;

/**
 * Created by yummin on 13-11-22.
 */
public class Forum extends ListActivity {

    private final static int countMax = 1000;
    private int count;
    private String[] tmp = new String[10];
    private String[] text = new String[countMax];
    private String[] topic = new String[countMax];
    private String[] user = new String[countMax];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        count = bundle.getInt("count");
        text = bundle.getStringArray("msg");

        for (int i = 0; i < count; i++) {
            tmp = text[i].split("#");
            topic[i] = tmp[0];
            user[i] = UserHandler.getNameFromID(Integer.parseInt(tmp[1]));
        }

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.resultlist, new String[]{"mainList", "subList"},
                new int[]{R.id.list_mainList, R.id.list_subList});
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        showDialog(R.string.topic + topic[pos] + "\n\n" +
                   R.string.postby + user[pos] + "\n");
    }

    /**
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < count; i++) {
            map = new HashMap<String, Object>();
            map.put("mainList", R.string.topic + (i + 1) + "：" + topic[i]);
            map.put("subList", R.string.postby + user[i]);
            list.add(map);
        }
        return list;
    }

    /**
     * Show apartment information message
     *
     * @param msg
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
