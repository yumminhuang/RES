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
 * Created by yummin on 13-11-19.
 */
public class ApartmentList extends ListActivity {

    private final static int countMax = 1000;
    private int count;
    private String[] tmp = new String[10];
    private String[] text = new String[countMax];
    private String[] address = new String[countMax];
    private String[] area = new String[countMax];
    private String[] number = new String[countMax];
    private String[] owner = new String[countMax];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        count = bundle.getInt("count");
        text = bundle.getStringArray("msg");

        for (int i = 0; i < count; i++) {
            tmp = text[i].split("#");
            address[i] = tmp[0];
            area[i] = tmp[1];
            number[i] = tmp[2];
            owner[i] = UserHandler.getNameFromID(Integer.parseInt(tmp[3]));
        }

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.resultlist, new String[]{"mainList", "subList"},
                new int[]{R.id.list_mainList, R.id.list_subList});
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        showDialog(R.string.address + address[pos] + "\n\n" +
                R.string.number + number[pos] + "\n\n" +
                R.string.area + area[pos] + "\n");
    }

    /**
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < count; i++) {
            map = new HashMap<String, Object>();
            map.put("mainList", R.string.apt + (i + 1) + "：" + address[i]);
            map.put("subList", R.string.number + number[i]);
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
