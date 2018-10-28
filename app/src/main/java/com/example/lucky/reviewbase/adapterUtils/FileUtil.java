package com.example.lucky.reviewbase.adapterUtils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtil {

    private static Context mContext;

    public FileUtil(Context context) {
        this.mContext = context;
    }

    // 写入文件
    public void saveData(String data){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = mContext.openFileOutput("testData",Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
                try {
                    if (writer !=null){
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    // 加载文件
    public String loadFile(){
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            inputStream = mContext.openFileInput("testData");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            if ((line = reader.readLine()) !=null){
                builder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
