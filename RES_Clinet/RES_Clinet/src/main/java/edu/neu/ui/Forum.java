package edu.neu.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import org.odata4j.exceptions.NotFoundException;
import org.odata4j.exceptions.ServerErrorException;

import java.util.ArrayList;
import java.util.List;

import edu.neu.pattern.Topic;
import edu.neu.res_clinet.R;
import edu.neu.util.TopicHandler;
import edu.neu.util.UserHandler;

/**
 * Created by yummin on 13-11-22.
 */
public class Forum extends TabActivity {

    private EditText addTopic, addContent, searchName, searchKeyword;
    private Button addreset, addok, searchreset, searchok;
    private static final String PREFS_NAME = "Preference";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost myTabhost = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.topic, myTabhost.getTabContentView(), true);

		/*
         * Read topics
		 */
        myTabhost.addTab(myTabhost.newTabSpec("One")
                .setIndicator("Read", getResources().getDrawable(R.drawable.forum))
                .setContent(allTopics()));

		/*
         * Post a topic
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Add", getResources().getDrawable(R.drawable.add))
                .setContent(R.id.Topic_layout_add));

        /*
         * Search topics
         */
        myTabhost.addTab(myTabhost.newTabSpec("Three")
                .setIndicator("Search", getResources().getDrawable(R.drawable.search))
                .setContent(R.id.Topic_layout_search));

        // 实例化添加界面的控件。
        addTopic = (EditText) findViewById(R.id.ET_Topic_AdTopic);
        addContent = (EditText) findViewById(R.id.ET_Topic_AddContent);
        searchName = (EditText) findViewById(R.id.ET_Topic_ShName);
        searchKeyword = (EditText) findViewById(R.id.ET_Topic_ShKeyword);

        addreset = (Button) findViewById(R.id.BU_Topic_AdReset);
        addok = (Button) findViewById(R.id.BU_Topic_AdOk);
        searchreset = (Button) findViewById(R.id.BU_Topic_ShReset);
        searchok = (Button) findViewById(R.id.BU_Topic_ShOk);
        // 设置确定按钮。
        addreset.setOnClickListener(new addresetListener());
        addok.setOnClickListener(new addokListener());
        searchreset.setOnClickListener(new searchresetListener());
        searchok.setOnClickListener(new searchokListener());
    }

    private Intent allTopics() {
        List<Topic> topics = TopicHandler.findAllTopic();

        Intent intent = new Intent(Forum.this, TopicList.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("topics", (ArrayList<? extends Parcelable>) topics);
        intent.putExtras(bundle);
        return intent;
    }


    private String display(int id) {
        return (String) this.getResources().getString(id);
    }

    /**
     * @return
     */
    private int readID() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getInt("UserID", 0);
    }

    /*
     *  Reset button
     */
    class addresetListener implements View.OnClickListener {
        public void onClick(View v) {
            addTopic.setText("");
            addContent.setText("");
        }
    }

    /*
     *  响应添加记录按钮单击事件：
     */
    class addokListener implements View.OnClickListener {
        public void onClick(View v) {
            // Get input
            String topic = addTopic.getText().toString().trim();
            String content = addContent.getText().toString().trim();
            int id = readID();
            if (topic.equals("") || content.equals("")) {
                Toast.makeText(Forum.this, R.string.record_error, Toast.LENGTH_LONG).show();
                return;
            }
            try {
                TopicHandler.addTopic(topic, content, id);
            } catch (ServerErrorException e) {
                showDialog(R.string.error);
            }
        }
    }

    /*
     *  Reset button
     */
    class searchresetListener implements View.OnClickListener {
        public void onClick(View v) {
            searchName.setText("");
            searchKeyword.setText("");
        }
    }

    /*
     *  Search button
     */
    class searchokListener implements View.OnClickListener {
        public void onClick(View v) {
            // 获取用户输入的关键字。
            String name = searchName.getText().toString().trim();
            String keyword = searchKeyword.getText().toString().trim();
            List<Topic> topics = null;
            // 判断输入的关键字是否为空。
            if (name.equals("") && keyword.equals("")) {
                Toast.makeText(Forum.this, R.string.search_error, Toast.LENGTH_LONG).show();
                return;
            } else if (name.equals("") && !keyword.equals("")) {
                topics = TopicHandler.findTopic(keyword);
            } else if (!name.equals("") && keyword.equals("")) {
                try {
                    int id = UserHandler.getIDFromName(name);
                    topics = TopicHandler.findTopicByUser(id);
                } catch (NotFoundException e) {
                    showDialog(R.string.not_find_error);
                }
            }
            Intent intent = new Intent(Forum.this, TopicList.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("topics", (ArrayList<? extends Parcelable>) topics);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
