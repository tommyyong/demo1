package com.hxxn.emmspro.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hxxn.emmspro.R;
import com.hxxn.emmspro.bean.RealTimeDataBean;
import com.hxxn.emmspro.tools.PerfectClickListener;
import com.hxxn.emmspro.ui.electricity.child.RTDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11 0011.
 */

public class RealTimeDataAdapter extends RecyclerView.Adapter<RealTimeDataAdapter.MyViewHolder> {

    public List<RealTimeDataBean> datas = null;
    private Context context;
    public RealTimeDataAdapter(List<RealTimeDataBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    public interface  onItemClickListener{
        void onItemClick(View view, int position);
        void  onItemLongClick(View view, int position);

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

       holder.rt_ll.setOnClickListener(new PerfectClickListener() {
           @Override
           protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(v.getContext(), RTDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("item_detail_rt_objectname", datas.get(position).getName());
                bundle.putString("item_detail_rt_ymd", String.valueOf(datas.get(position).getYmd()));
                bundle.putString("item_detail_rt_hm", String.valueOf(datas.get(position).getHour()) + "点" + String.valueOf(datas.get(position).getMinute()));
                bundle.putString("item_detail_rt_phase_voltage_a", String.valueOf(datas.get(position).getUa()));
                bundle.putString("item_detail_rt_phase_voltage_b", String.valueOf(datas.get(position).getUb()));
                bundle.putString("item_detail_rt_phase_voltage_c", String.valueOf(datas.get(position).getUc()));
                bundle.putString("item_detail_rt_line_voltage_ab", String.valueOf(datas.get(position).getUab()));
                bundle.putString("item_detail_rt_phase_voltage_bc", String.valueOf(datas.get(position).getUbc()));
                bundle.putString("item_detail_rt_line_voltage_ca", String.valueOf(datas.get(position).getUca()));
                bundle.putString("item_detail_rt_phase_currente_a", String.valueOf(datas.get(position).getIa()));
                bundle.putString("item_detail_rt_phase_current_b", String.valueOf(datas.get(position).getIb()));
                bundle.putString("item_detail_rt_phase_current_c", String.valueOf(datas.get(position).getIc()));
                bundle.putString("item_detail_rt_frequency", String.valueOf(datas.get(position).getFf()));
                bundle.putString("item_detail_rt_total_kWh", String.valueOf(datas.get(position).getWp()));

                bundle.putString("ass","dddd");

//                Log.d(TAG,"bundle"+"\t"+ bundle.size()+"\t"+realTimeData.get(position).getName());

                intent.putExtras(bundle);
               v.getContext().startActivity(intent);

//               RTDetailActivity.start(v.getContext());
           }
       });

//        if(onItemClickListener!=null){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int layoutPos=holder.getLayoutPosition();
//                    onItemClickListener.onItemClick(holder.itemView,layoutPos);
//
//                }
//            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int layoutPos=holder.getLayoutPosition();
//                    onItemClickListener.onItemLongClick(holder.itemView,layoutPos);
//                    return false;
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class  MyViewHolder extends RecyclerView.ViewHolder {
       TextView item_rt_titlename,item_rt_phase_voltage,item_rt_line_voltage;
       TextView item_rt_phase_current,item_rt_total_kWh;
       LinearLayout rt_ll;
         MyViewHolder(View view){
            super(view);
            item_rt_titlename = (TextView) view.findViewById(R.id.item_rt_titlename);
            item_rt_phase_voltage = (TextView) view.findViewById(R.id.item_rt_phase_voltage);
            item_rt_line_voltage = (TextView) view.findViewById(R.id.item_rt_line_voltage);

            item_rt_phase_current = (TextView) view.findViewById(R.id.item_rt_phase_current);
            item_rt_total_kWh = (TextView) view.findViewById(R.id.item_rt_total_kWh);
            rt_ll=view.findViewById(R.id.rt_ll);
        }
    }
}
