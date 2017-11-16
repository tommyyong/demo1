package com.hxxn.eems.ui.realtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hxxn.eems.MainActivity;
import com.hxxn.eems.R;

import com.hxxn.eems.adapter.RealTimeDataAdapter;
import com.hxxn.eems.base.Constants;
import com.hxxn.eems.dataBean.RealTimeData;
import com.hxxn.eems.utils.SPUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;



public class RealTimeFragment extends Fragment {
    private String TAG=getClass().getSimpleName();
    private  List<RealTimeData> realTimeData;
    private XRecyclerView mRecyclerView;
    private RealTimeDataAdapter realTimeDataAdapter;
    private MainActivity mActivity;

//    private SendRTdata sendRTdata;
//    public interface SendRTdata{
//        public void send(Bundle bundle);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_real_time, container, false);
        initView(view);
        initData();


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("实时数据");





//        setAdapter();
    }



    private void initView(View v) {

        mRecyclerView = (XRecyclerView)v.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setPullRefreshEnabled(false);




    }

    private void initData() {
        if(SPUtils.getBoolean("isFirst",false)!=false) {
            OkGo.post(Constants.BASEURL_REAL_TIME_DATA)
                    .tag(this)
                    .cacheKey("cachePostKey")
                    .cacheMode(CacheMode.DEFAULT)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Gson gson = new Gson();
                           realTimeData = gson.fromJson(s, new TypeToken<List<RealTimeData>>() {
                            }.getType());
                            Log.d(TAG, realTimeData.get(0).getName()+"\t"+realTimeData.size());

                            realTimeDataAdapter=new RealTimeDataAdapter(realTimeData,getContext());
                            mRecyclerView.setAdapter(realTimeDataAdapter);

                            realTimeDataAdapter.notifyDataSetChanged();

                            realTimeDataAdapter.setOnItemClickListener(new RealTimeDataAdapter.onItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {

                                    Toast.makeText(getContext(),""+position,Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getContext(), RTDetailActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString("item_detail_rt_objectname",realTimeData.get(position).getName());
                                    bundle.putString("item_detail_rt_ymd", String.valueOf(realTimeData.get(position).getYmd()));
                                    bundle.putString("item_detail_rt_hm", String.valueOf(realTimeData.get(position).getHour())+"点"+String.valueOf(realTimeData.get(position).getMinute()));
                                    bundle.putString("item_detail_rt_phase_voltage_a", String.valueOf(realTimeData.get(position).getUa()));
                                    bundle.putString("item_detail_rt_phase_voltage_b", String.valueOf(realTimeData.get(position).getUb()));
                                    bundle.putString("item_detail_rt_phase_voltage_c", String.valueOf(realTimeData.get(position).getUc()));
                                    bundle.putString("item_detail_rt_line_voltage_ab", String.valueOf(realTimeData.get(position).getUab()));
                                    bundle.putString("item_detail_rt_phase_voltage_bc", String.valueOf(realTimeData.get(position).getUbc()));
                                    bundle.putString("item_detail_rt_line_voltage_ca", String.valueOf(realTimeData.get(position).getUca()));
                                    bundle.putString("item_detail_rt_phase_currente_a", String.valueOf(realTimeData.get(position).getIa()));
                                    bundle.putString("item_detail_rt_phase_current_b", String.valueOf(realTimeData.get(position).getIb()));
                                    bundle.putString("item_detail_rt_phase_current_c", String.valueOf(realTimeData.get(position).getIc()));
                                    bundle.putString("item_detail_rt_frequency", String.valueOf(realTimeData.get(position).getFf()));
                                    bundle.putString("item_detail_rt_total_kWh", String.valueOf(realTimeData.get(position).getWp()));

                                    intent.putExtras(bundle);


//                                    intent.putExtra("item_detail_rt_objectname",realTimeData.get(position).getName());
//                                    intent.putExtra("item_detail_rt_ymd", String.valueOf(realTimeData.get(position).getYmd()));
//                                    intent.putExtra("item_detail_rt_hm", String.valueOf(realTimeData.get(position).getHour())+"点"+String.valueOf(realTimeData.get(position).getMinute()));
//                                    intent.putExtra("item_detail_rt_phase_voltage_a", String.valueOf(realTimeData.get(position).getUa()));
//                                    intent.putExtra("item_detail_rt_phase_voltage_b", String.valueOf(realTimeData.get(position).getUb()));
//                                    intent.putExtra("item_detail_rt_phase_voltage_c", String.valueOf(realTimeData.get(position).getUc()));
//                                    intent.putExtra("item_detail_rt_line_voltage_ab", String.valueOf(realTimeData.get(position).getUab()));
//                                    intent.putExtra("item_detail_rt_phase_voltage_bc", String.valueOf(realTimeData.get(position).getUbc()));
//                                    intent.putExtra("item_detail_rt_line_voltage_ca", String.valueOf(realTimeData.get(position).getUca()));
//                                    intent.putExtra("item_detail_rt_phase_currente_a", String.valueOf(realTimeData.get(position).getIa()));
//                                    intent.putExtra("item_detail_rt_phase_current_b", String.valueOf(realTimeData.get(position).getIb()));
//                                    intent.putExtra("item_detail_rt_phase_current_c", String.valueOf(realTimeData.get(position).getIc()));
//                                    intent.putExtra("item_detail_rt_frequency", String.valueOf(realTimeData.get(position).getFf()));
//                                    intent.putExtra("item_detail_rt_total_kWh", String.valueOf(realTimeData.get(position).getWp()));

//                                    Log.d(TAG,String.valueOf(realTimeData.get(position).getYmd()));
//                                    EventBus.getDefault().post(bundle);
//                                    Intent intent = new Intent(getContext(), RTDetailActivity.class);


                                    getContext().startActivity(intent);


//                                    getContext().startActivity(rt_detail);
//                                    getActivity().getSupportFragmentManager()
//                                            .beginTransaction()
//
//                                            .addToBackStack(null)
//                                            .commit();
//
//
//
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {

                                }
                            });

                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            Log.d(TAG, e.toString());
                        }
                    });
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;

    }

}