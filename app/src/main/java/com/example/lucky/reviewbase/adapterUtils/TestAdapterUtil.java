package com.example.lucky.reviewbase.adapterUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucky.reviewbase.R;
import com.example.lucky.reviewbase.entity.TestBean;

import java.util.List;

public class TestAdapterUtil extends ArrayAdapter<TestBean>{

    private  int resourceView;

    public TestAdapterUtil(@NonNull Context context, int resource, List<TestBean> obj) {
        super(context, resource,obj);
        this.resourceView = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TestBean testBean = getItem(position);
        View view;
        ViewHolder holder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceView,parent,false);
            holder = new ViewHolder();
            holder.resourceId = view.findViewById(R.id.imageView);
            holder.resourceName = view.findViewById(R.id.textView);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.resourceId.setImageResource(testBean.getResourceId());
        holder.resourceName.setText(testBean.getResourceName());
        return view;

    }

    class ViewHolder{
        private ImageView resourceId;
        private TextView resourceName;
    }
}
