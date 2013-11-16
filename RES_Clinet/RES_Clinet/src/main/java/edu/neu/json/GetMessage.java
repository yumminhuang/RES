package edu.neu.json;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Created by yummin on 13-11-15.
 */
public class GetMessage {

    public void getMessage(String url){
        String src = JSONReader.readJSON(url);
        try {
            JSONArray jsonArray = new JSONArray(src);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
