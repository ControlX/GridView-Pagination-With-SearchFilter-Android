package com.github.controlx.catalogue.helper;

import android.content.Context;

import com.github.controlx.catalogue.model.Content;
import com.github.controlx.catalogue.model.Page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Abhishek on 11/27/2016.
 */
public class JsonLoader {
    private Context context;
    private static JsonLoader instance = null;

    public enum Parse {
        FIRST_FILE(1),
        SECOND_FILE(2),
        THIRD_FILE(3);

        private int code;

        Parse(int code){
            this.code = code;
        }

        public int getCode(){
            return code;
        }
    }

    private JsonLoader() {
    }

    public static JsonLoader getInstance() {
        if (instance == null) {
            instance = new JsonLoader();
        }
        return instance;
    }

    public ArrayList<Content> parseAndLoadClass(Context context, Enum enumType) {

        this.context = context;

        switch ((Parse) enumType) {
            case FIRST_FILE:
            case SECOND_FILE:
            case THIRD_FILE:
                return loadLatestUpdates(((Parse) enumType).getCode());
        }
        return null;
    }

    private ArrayList<Content> loadLatestUpdates(int code) {

        JSONObject jsonObject;

        String jsonRawString = null;

        try {
            jsonRawString = Utils.getStringFromRaw(context, code);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try {
            jsonObject = new JSONObject(jsonRawString);
            JSONObject jsonObjectPage = jsonObject.optJSONObject("page");

            String title = jsonObjectPage.optString("title");
            String totalContentItems = jsonObjectPage.optString("total-content-items");
            String pageNum = jsonObjectPage.optString("page-num");
            String pageSize = jsonObjectPage.optString("page-size");

            Page page = new Page();
            page.setTitle(title);
            page.setTotalContentItems(totalContentItems);
            page.setPageNum(pageNum);
            page.setPageSize(pageSize);

            JSONObject jsonObjectContentItems = jsonObjectPage.optJSONObject("content-items");
            JSONArray jsonArrayContent = jsonObjectContentItems.getJSONArray("content");

            if (jsonArrayContent == null || jsonArrayContent.length() <= 0)
                return null;
            int length = jsonArrayContent.length();
            ArrayList<Content> arrayList = new ArrayList<>();
            for (int index = 0; index < length; index++) {
                Object object = jsonArrayContent.opt(index);
                JSONObject obj = object instanceof JSONObject ? (JSONObject) object : null;
                String name = obj.getString("name");
                String posterImage = obj.getString("poster-image");

                Content content = new Content();
                content.setName(name);
                content.setPosterImage(posterImage);
                arrayList.add(content);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
