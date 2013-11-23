package edu.neu.ui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.neu.pattern.Schedule;
import edu.neu.res_clinet.R;
import edu.neu.util.UserHandler;

public class MeetingList extends ListActivity {

    private List<Schedule> meetings;

    private final static String PrKPath = "/sdcard/oh!data/id.dat";

    private String display(int id) {
        return (String) this.getResources().getString(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        meetings = bundle.getParcelableArrayList("mymeeting");

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.resultlist, new String[]{"mainList", "subList"},
                new int[]{R.id.list_mainList, R.id.list_subList});
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        showDialog(display(R.string.time) + meetings.get(pos).getScheduletime() + "\n\n" +
                display(R.string.with) + getStaffName(meetings.get(pos).getSchedulefrom(),
                meetings.get(pos).getScheduleto()) + "\n\n" +
                display(R.string.content) + meetings.get(pos).getContent() + "\n");
    }

    /*
     *
	 */
    private int readID() {
        File file = new File(PrKPath);
        try {
            InputStream in = new FileInputStream(file);
            int id = in.read();
            return id;
        } catch (Exception e) {
            showDialog(R.string.error);
        }
        return 0;
    }

    private String getStaffName(int from, int to) {
        int id = 6;
        readID();
        if (from != id)
            return UserHandler.getNameFromID(from);
        else if (to != id)
            return UserHandler.getNameFromID(to);
        return null;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (Schedule s : meetings) {
            map = new HashMap<String, Object>();
            map.put("mainList", display(R.string.meeting) + s.getScheduletime());
            map.put("subList", display(R.string.with) + getStaffName(s.getSchedulefrom(), s.getScheduleto()));
            list.add(map);
        }
        return list;
    }

    /**
     * Show meeting information message
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