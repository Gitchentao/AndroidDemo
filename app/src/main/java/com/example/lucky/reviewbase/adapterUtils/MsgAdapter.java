package com.example.lucky.reviewbase.adapterUtils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lucky.reviewbase.R;
import com.example.lucky.reviewbase.entity.MsgBean;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{

    private List<MsgBean> msgBeanList;

    public MsgAdapter(List<MsgBean> msgBeanList) {
        this.msgBeanList = msgBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MsgBean msgBean = msgBeanList.get(position);
        if (msgBean.getType() == MsgBean.TYPE_RECEIVED){
            holder.left_linear.setVisibility(View.VISIBLE);
            holder.right_linear.setVisibility(View.GONE);
            holder.left_msg.setText(msgBean.getContent());
        }else if(msgBean.getType() == MsgBean.TYPE_SENT){
            holder.left_linear.setVisibility(View.GONE);
            holder.right_linear.setVisibility(View.VISIBLE);
            holder.right_msg.setText(msgBean.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout left_linear;
        private LinearLayout right_linear;
        private TextView left_msg;
        private TextView right_msg;

        public ViewHolder(View itemView) {
            super(itemView);

            left_linear = itemView.findViewById(R.id.left_layout);
            right_linear = itemView.findViewById(R.id.right_layout);
            left_msg = itemView.findViewById(R.id.left_msg);
            right_msg = itemView.findViewById(R.id.right_msg);
        }
    }
}
