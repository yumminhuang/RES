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

import edu.neu.pattern.Apartment;
import edu.neu.res_clinet.R;
import edu.neu.util.UserHandler;

/**
 * Created by yummin on 13-11-19.
 */
public class ApartmentList extends ListActivity {

    private List<Apartment> apartments;

    private String display(int id) {
        return (String) this.getResources().getString(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        apartments = bundle.getParcelableArrayList("apts");

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.resultlist, new String[]{"mainList", "subList"},
                new int[]{R.id.list_mainList, R.id.list_subList});
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        showDialog(display(R.string.address) + apartments.get(pos).getAddress() + "\n\n" +
                display(R.string.number) + apartments.get(pos).getNumber() + "\n\n" +
                display(R.string.area) + apartments.get(pos).getArea() + "\n\n" +
                display(R.string.owner) + UserHandler.getNameFromID(apartments.get(pos).getOwner()));
    }

    /**
     * @return
     */
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (Apartment a : apartments) {
            map = new HashMap<String, Object>();
            map.put("mainList", display(R.string.apt) + a.getAddress() + " " + a.getNumber());
            map.put("subList", display(R.string.area) + a.getArea());
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
