package com.hxxn.emmspro.ui.electricity.child;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hxxn.emmspro.MainActivity;
import com.hxxn.emmspro.R;
import com.hxxn.emmspro.adapter.RealTimeDataAdapter;
import com.hxxn.emmspro.app.Constants;
import com.hxxn.emmspro.base.BaseFragment;
import com.hxxn.emmspro.bean.RealTimeDataBean;
import com.hxxn.emmspro.databinding.FragmentRealtimeBinding;
import com.hxxn.emmspro.tools.DebugUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/16 0016.
 */

public class RealTimeFragment extends BaseFragment<FragmentRealtimeBinding> {

    private String TAG=getClass().getSimpleName();
    // 初始化完成后加载数据
    private boolean isPrepared = false;
    // 第一次显示时加载数据，第二次不显示
    private boolean isFirst = true;
    // 是否正在刷新（用于刷新数据时返回页面不再刷新）
    private boolean mIsLoading = false;
    private RealTimeDataAdapter realTimeDataAdapter;
//    private RealTimeDataBean realTimeDataBean;
    private MainActivity activity;
    private  List< RealTimeDataBean> realTimeDataBean;

    private View mHeaderView = null;

    @Override
    public int setContent() {
        return R.layout.fragment_realtime;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        Log.d(TAG,"zzz");
//        realTimeDataAdapter = new RealTimeDataAdapter(activity);

        isPrepared = true;
        DebugUtil.error("---OneFragment   --onActivityCreated");

        postDelayLoad();
    }

//    @Override
//    protected void loadData() {
//        if (!isPrepared || !mIsVisible) {
//            return;
//        }
//        Log.d(TAG,"xxx");
//        showLoading();
//        postDelayLoad();
//    }

    private void postDelayLoad() {
        synchronized (this) {
            if (!mIsLoading) {
                mIsLoading = true;
                bindingView.rtRcv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadRealTimeData();
                    }
                }, 150);
            }
        }
    }
    private void loadRealTimeData(){
        OkGo.post(Constants.BASEURL_REAL_TIME_DATA)
                .tag(this)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        realTimeDataBean = gson.fromJson(s, new TypeToken<List<RealTimeDataBean>>() {
                        }.getType());

             Log.d(TAG,"123"+"\t"+realTimeDataBean.size());

                        setAdapter(realTimeDataBean);
                        // 刷新结束
                        mIsLoading = false;







                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.d(TAG, e.toString());
                    }
                });
    }

    private void setAdapter(final List< RealTimeDataBean> realTimeData) {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bindingView.rtRcv.setLayoutManager(mLayoutManager);

        // 加上这两行代码，下拉出提示才不会产生出现刷新头的bug，不加拉不下来
        bindingView.rtRcv.setPullRefreshEnabled(false);
        bindingView.rtRcv.clearHeader();

        bindingView.rtRcv.setLoadingMoreEnabled(false);
        // 需加，不然滑动不流畅
        bindingView.rtRcv.setNestedScrollingEnabled(false);
        bindingView.rtRcv.setHasFixedSize(false);

        realTimeDataAdapter=new RealTimeDataAdapter(realTimeData,getContext());
//        realTimeDataAdapter.clear();
//        realTimeDataAdapter.addAll(realTimeDataBean);
        bindingView.rtRcv.setAdapter(realTimeDataAdapter);
        realTimeDataAdapter.notifyDataSetChanged();

        if (mHeaderView == null) {
            mHeaderView = View.inflate(getContext(), R.layout.header_item_realtime, null);
            View llMovieTop = mHeaderView.findViewById(R.id.ll_realtime_top);
            TextView rt_tx=mHeaderView.findViewById(R.id.rt_currenttime);
            rt_tx.setText("上次刷新时间: "+realTimeData.get(1).getYmd()+"\t"+realTimeData.get(1).getHour()+":"+realTimeData.get(1).getMinute());
//            llMovieTop.setOnClickListener(new PerfectClickListener() {
//                @Override
//                protected void onNoDoubleClick(View v) {
//                    DoubanTopActivity.start(v.getContext());
//                }
//            });
        }
        bindingView.rtRcv.addHeaderView(mHeaderView);
                isFirst = false;
    }
}
