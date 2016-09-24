package com.app.recylercard.adapter.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.recylercard.R;
import com.app.recylercard.adapter.Main2Activity;
import com.app.recylercard.adapter.bean.Root;

import java.util.List;

import loopj.android.image.SmartImageView;

/**
 * @author: liyabin
 * @description:
 * @projectName: RecylerCard
 * @date: 2016-09-24
 * @time: 16:03
 */

public class DayFoodAdapter extends RecyclerView.Adapter<DayFoodAdapter.ViewHolder> {
    List<Root.ListObject> list;
    private Activity activity;

    public DayFoodAdapter(Activity activity, List<Root.ListObject> list) {
        this.activity = activity;
        this.list = list;
    }
    @Override
    public DayFoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new DayFoodAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(DayFoodAdapter.ViewHolder holder, int position) {
        holder.first_img.setImageUrl(list.get(position).getFirstImg());
        holder.title_name.setText(list.get(position).getTitle());
        holder.source.setText(list.get(position).getSource());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        SmartImageView first_img;
        TextView title_name, source;
        public ViewHolder(final View itemView) {
            super(itemView);
            first_img = (SmartImageView) itemView.findViewById(R.id.first_img);
            title_name = (TextView) itemView.findViewById(R.id.title_name);
            source = (TextView) itemView.findViewById(R.id.source);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //点击事件
                    Toast.makeText(activity, "点击无效"+getPosition(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(activity, Main2Activity.class);
                    intent.putExtra("extra1", list.get(getPosition()).getUrl());
                    activity.startActivity(intent);
                }
            });
        }
    }
}
