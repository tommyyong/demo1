package com.hxxn.eems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxxn.eems.R;
import com.hxxn.eems.dataBean.RealTimeData;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11 0011.
 */

public class RealTimeDataAdapter extends RecyclerView.Adapter<RealTimeDataAdapter.MyViewHolder> {

    public List<RealTimeData> datas = null;
    private Context context;
    public RealTimeDataAdapter(List<RealTimeData> datas,Context context) {
        this.datas = datas;
        this.context = context;
    }
    public interface  onItemClickListener{
        void onItemClick(View view ,int position);
        void  onItemLongClick(View view,int position);

    }
    private onItemClickListener onItemClickListener;
    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_real_time,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {

       holder.item_rt_titlename.setText(datas.get(position).getName());
       holder.item_rt_phase_voltage.setText(String.valueOf(datas.get(position).getUa()) );
       holder.item_rt_line_voltage.setText( String.valueOf(datas.get(position).getUab()));
       holder.item_rt_phase_current.setText(String.valueOf( datas.get(position).getIa()));
       holder.item_rt_total_kWh.setText(String.valueOf( datas.get(position).getWp()));

        if(onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPos=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,layoutPos);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPos=holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView,layoutPos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class  MyViewHolder extends RecyclerView.ViewHolder {
       TextView item_rt_titlename,item_rt_phase_voltage,item_rt_line_voltage;
       TextView item_rt_phase_current,item_rt_total_kWh;
         MyViewHolder(View view){
            super(view);
            item_rt_titlename = (TextView) view.findViewById(R.id.item_rt_titlename);
            item_rt_phase_voltage = (TextView) view.findViewById(R.id.item_rt_phase_voltage);
            item_rt_line_voltage = (TextView) view.findViewById(R.id.item_rt_line_voltage);

            item_rt_phase_current = (TextView) view.findViewById(R.id.item_rt_phase_current);
            item_rt_total_kWh = (TextView) view.findViewById(R.id.item_rt_total_kWh);
        }
    }
}
