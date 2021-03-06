package edu.neu.ui;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import org.odata4j.exceptions.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

import edu.neu.res_clinet.R;
import edu.neu.util.ApartmentHandler;


public class Apartment extends TabActivity {
    private TabHost myTabhost;
    private EditText AddAddress, AddNumber, AddArea, SearchAddress, SearchArea;
    private Button AddReset, AddOk, SearchReset, SearchOk;
    private String strAddAddress, strAddNumber, strAddArea, strSearchAddress, strSearchArea;

    private static final String PREFS_NAME = "Preference";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myTabhost = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.apartment, myTabhost.getTabContentView(), true);

		/*
         *  Widgets in UI for adding an apartment：
		 */
        myTabhost.addTab(myTabhost.newTabSpec("One")
                .setIndicator("Add", getResources().getDrawable(R.drawable.add))
                .setContent(R.id.Apartment_layout_add));
        //widgets
        AddAddress = (EditText) findViewById(R.id.ET_Apartment_AdAddress);
        AddNumber = (EditText) findViewById(R.id.ET_Apartment_AdNumber);
        AddArea = (EditText) findViewById(R.id.ET_Apartment_AdArea);
        AddReset = (Button) findViewById(R.id.BU_Apartment_AdReset);
        AddOk = (Button) findViewById(R.id.BU_Apartment_AdOk);

        // Set Buttons
        AddReset.setOnClickListener(new AddResetListener());
        AddOk.setOnClickListener(new AddOkListener());

		/*
         *  Widgets in UI for searching an apartment
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Search", getResources().getDrawable(R.drawable.search))
                .setContent(R.id.Apartment_layout_search));
        // 实例化搜索界面的控件。
        SearchAddress = (EditText) findViewById(R.id.ET_Apartment_ShAddress);
        SearchArea = (EditText) findViewById(R.id.ET_Apartment_ShArea);
        SearchReset = (Button) findViewById(R.id.BU_Apartment_ShReset);
        SearchOk = (Button) findViewById(R.id.BU_Apartment_ShOk);
        // 设置确定按钮。
        SearchReset.setOnClickListener(new SearchResetListener());
        SearchOk.setOnClickListener(new SearchOkListener());
    }

    /*
     *  Sow message
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
     *  Reset button
     */
    class AddResetListener implements OnClickListener {
        public void onClick(View v) {
            AddAddress.setText("");
            AddArea.setText("");
            AddNumber.setText("");
        }
    }

    /**
     * @return
     */
    private int readID() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt("UserID", 0);
    }

    /*
     *  响应添加记录按钮单击事件：
     */
    class AddOkListener implements OnClickListener {
        public void onClick(View v) {
            // Get input
            strAddAddress = AddAddress.getText().toString().trim();
            strAddArea = AddArea.getText().toString().trim();
            strAddNumber = AddNumber.getText().toString().trim();
            if (strAddAddress.equals("") || strAddArea.equals("") || strAddNumber.equals("")) {
                Toast.makeText(Apartment.this, R.string.record_error, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                ApartmentHandler.addApartment(strAddNumber, strAddAddress, Integer.parseInt(strAddArea), readID());
                Toast.makeText(Apartment.this, R.string.add_mesage, Toast.LENGTH_LONG).show();
                AddAddress.setText("");
                AddArea.setText("");
                AddNumber.setText("");
            } catch (ServerErrorException e) {
                showDialog(R.string.error);
            }
        }
    }

    /*
     *  Reset button
     */
    class SearchResetListener implements OnClickListener {
        public void onClick(View v) {
            SearchAddress.setText("");
            SearchArea.setText("");
        }
    }

    /*
     *  Search button
     */
    class SearchOkListener implements OnClickListener {
        public void onClick(View v) {
            // 获取用户输入的关键字。
            strSearchAddress = SearchAddress.getText().toString().trim();
            strSearchArea = SearchArea.getText().toString().trim();
            List<edu.neu.pattern.Apartment> apartments = null;
            // 判断输入的关键字是否为空。
            if (strSearchAddress.equals("") && strSearchArea.equals("")) {
                Toast.makeText(Apartment.this, R.string.search_error, Toast.LENGTH_LONG).show();
                return;
            } else if (strSearchAddress.equals("") && !strSearchArea.equals("")) {
                apartments = ApartmentHandler.findApartments(Integer.parseInt(strSearchArea));
            } else if (!strSearchAddress.equals("") && strSearchArea.equals("")) {
                apartments = ApartmentHandler.findApartments(strSearchAddress);
            } else {
                apartments = ApartmentHandler.findApartments(strSearchAddress, Integer.parseInt(strSearchArea));
            }
            Intent intent = new Intent(Apartment.this, ApartmentList.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("apts", (ArrayList<? extends Parcelable>) apartments);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
