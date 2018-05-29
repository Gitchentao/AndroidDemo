package com.example.lucky.reviewbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lucky.reviewbase.adapterUtils.MsgAdapter;
import com.example.lucky.reviewbase.entity.MsgBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 消息发送小dome
 */
public class MsgActivity extends BaseActivity {

    private List<MsgBean> msgBeanList = new ArrayList<>();;
    private EditText editText;
    private Button button;
    private RecyclerView recyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        String error = getIntent().getStringExtra("ERROR");

            recyclerView = findViewById(R.id.cyc_layout);
            button = findViewById(R.id.btn_send_msg);
            editText = findViewById(R.id.text_send_msg);
            initMsg();
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);

            adapter = new MsgAdapter(msgBeanList);
            recyclerView.setAdapter(adapter);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String inputText = editText.getText().toString();
                    if (!"".equals(inputText)){
                        MsgBean msgBean = new MsgBean(inputText,MsgBean.TYPE_SENT);
                        msgBeanList.add(msgBean);
                        adapter.notifyItemInserted(msgBeanList.size()-1);
                        recyclerView.scrollToPosition(msgBeanList.size()-1);
                        editText.setText("");
                    }
                }
            });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent("com.example.lucky.reviewbase.FPRCE_OFFLINE");
        sendBroadcast(intent);
    }



    public void initMsg(){
        msgBeanList.clear();
        MsgBean msgBean1 = new MsgBean("1111",MsgBean.TYPE_RECEIVED);
        msgBeanList.add(msgBean1);
        MsgBean msgBean2 = new MsgBean("2222",MsgBean.TYPE_SENT);
        msgBeanList.add(msgBean2);
        MsgBean msgBean3 = new MsgBean("3333",MsgBean.TYPE_RECEIVED);
        msgBeanList.add(msgBean3);
        MsgBean msgBean4 = new MsgBean("4444",MsgBean.TYPE_SENT);
        msgBeanList.add(msgBean4);
    }
}
