package com.example.lucky.reviewbase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lucky.reviewbase.adapterUtils.RecyclerAdapter;
import com.example.lucky.reviewbase.entity.TestBean;

import java.util.ArrayList;
import java.util.List;


/**
 * linearLayout、recyclerView使用
 */
public class MainActivity extends BaseActivity{

    private List<TestBean> testBeanList;
    private RecyclerView mListView;

    private  ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.cyc_item);
        initList();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
//        TestAdapterUtil testAdapterUtil = new TestAdapterUtil(MainActivity.this,R.layout.test_item_layout,testBeanList);
//
//        mListView.setAdapter(testAdapterUtil);
//
//        mListView.setOnItemClickListener(this);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
       // StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        mListView.setLayoutManager(manager);
        RecyclerAdapter adapter = new RecyclerAdapter(this,testBeanList);
        mListView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent("com.example.lucky.reviewbase.FPRCE_OFFLINE");
        sendBroadcast(intent);
    }

    /**
     * 初始化数据
     */
    public void initList(){
        testBeanList = new ArrayList<>();
        for (int i= 0;i<10;i++){
            TestBean testBean = new TestBean();
            testBean.setResourceId(R.drawable.ic_launcher_background);
            testBean.setResourceName("哈哈");
            testBeanList.add(testBean);
        }
    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
//
//        TestBean testBean = testBeanList.get(postion);
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.setTitle(testBean.getResourceName());
//        dialog.setCancelable(false);
//        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        dialog.setNegativeButton("colse", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        dialog.show();
//    }

    public void showProgress(){
        mProgressDialog.show();
    }

    public void dissMissProgress(){
        mProgressDialog.dismiss();
    }
}
