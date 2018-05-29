package com.example.lucky.reviewbase.adapterUtils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * activity 管理
 */
public class ActivityCollector {

    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        activityList.add(activity);
    }

    public static void finishAll(){
        for (Activity activity : activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
