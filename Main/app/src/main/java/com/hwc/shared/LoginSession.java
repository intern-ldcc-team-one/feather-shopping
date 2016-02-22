package com.hwc.shared;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by K on 2016-02-19.
 */
public class LoginSession {

    private SharedCommon sharedCommon;
    private Context context;
    private String prefName;
    private HashMap<String, String> infoList;
    private ArrayList<String> keyList;

    public LoginSession(Context context) {
        // 멤버 변수 초기화
        this.context = context;
        prefName = "login";

        // SharedPreference 생성
        sharedCommon = new SharedCommon();

        keyList = new ArrayList<String>();
        keyList.add("id");
    }

    public LoginSession(Context context, HashMap<String, String> infoList) {
        // 멤버 변수 초기화
        this.context = context;
        prefName = "login";

        // SharedPreference 생성
        sharedCommon = new SharedCommon();

        keyList = new ArrayList<String>();
        keyList.add("id");

        // 필요한 정보 put
        this.infoList = infoList;

        // 실제 editor에 SharedPreference 삽입
        sharedCommon.putPreferences(this.context, prefName, this.infoList, keyList);
    }

    public HashMap<String, String> getPreferencesResultHashMap()
    {
        HashMap<String, String> resultHashMap = sharedCommon.getPreferences(context, prefName, keyList);
        return resultHashMap;
    }

    public void removePreferences()
    {
        sharedCommon.removePreferences(context, prefName, keyList);
    }

    public void clearPreferences()
    {
        sharedCommon.clearPreferences(context, prefName);
    }

}