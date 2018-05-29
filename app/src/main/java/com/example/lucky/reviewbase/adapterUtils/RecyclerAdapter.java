package com.example.lucky.reviewbase.adapterUtils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.reviewbase.MsgActivity;
import com.example.lucky.reviewbase.R;
import com.example.lucky.reviewbase.entity.TestBean;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<TestBean> mTestBeanList;
    private Context mContext;

    public RecyclerAdapter(Context context,List<TestBean> mTestBeanList) {
        this.mTestBeanList = mTestBeanList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TestBean testBean = mTestBeanList.get(position);
        holder.textView.setText(testBean.getResourceName());
        holder.imageView.setImageResource(testBean.getResourceId());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MsgActivity.class);
                intent.putExtra("ERROR","error");
                mContext.startActivity(intent);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "465798", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTestBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }
    }
}
