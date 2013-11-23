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

import edu.neu.pattern.Message;
import edu.neu.res_clinet.R;
import edu.neu.util.UserHandler;

public class EmailInbox extends ListActivity {

    private List<Message> messages;

    private String display(int id) {
        return (String) this.getResources().getString(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        messages = bundle.getParcelableArrayList("inbox");

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.resultlist, new String[]{"mainList", "subList"},
                new int[]{R.id.list_mainList, R.id.list_subList});
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);

        showDialog(display(R.string.from) + UserHandler.getNameFromID(messages.get(pos).getMessagefrom()) + "\n\n" +
                display(R.string.content) + messages.get(pos).getContent() + "\n");
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (Message m : messages) {
            map = new HashMap<String, Object>();
            map.put("mainList", display(R.string.from) + UserHandler.getNameFromID(m.getMessagefrom()));
            map.put("subList", display(R.string.time) + m.getMessagetime());
            list.add(map);
        }
        return list;
    }

    // 提示信息msg。
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

