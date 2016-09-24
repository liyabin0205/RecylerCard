package com.app.recylercard.adapter.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.recylercard.R;

import java.util.List;
import java.util.Map;

/**
 * @author: liyabin
 * @description:
 * @projectName: RecylerCard
 * @date: 2016-09-23
 * @time: 15:50
 */

public class DayReadAdapter extends RecyclerView.Adapter<DayReadAdapter.ViewHolder> {
    List<Map<String, String>> parselist;
    private Activity activity;
    public List<Map<String, String>> getParselist() {
        return parselist;
    }
    public DayReadAdapter(Activity activity, List<Map<String, String>> parselist) {
        this.activity = activity;
        this.parselist = parselist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(Html.fromHtml("<b>" + parselist.get(position).get("title") + "</b>"));
        holder.content.setText(Html.fromHtml("发布时间："
                + parselist.get(position).get("date") + "<br>"
                + parselist.get(position).get("postContent")));
    }
    @Override
    public int getItemCount() {
        return parselist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //点击事件
                    Toast.makeText(activity, "点击无效", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
