package com.hxxn.emmspro.ui.electricity.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.hxxn.emmspro.R;


/**
 * Created by Administrator on 2017/11/11 0011.
 */

public class RTDetailActivity extends FragmentActivity {

    private String TAG=getClass().getSimpleName();
    private TextView item_detail_rt_objectname,item_detail_rt_ymd,item_detail_rt_hm;
    private TextView item_detail_rt_phase_voltage_a,item_detail_rt_phase_voltage_b,item_detail_rt_phase_voltage_c;
    private TextView item_detail_rt_line_voltage_ab,item_detail_rt_phase_voltage_bc,item_detail_rt_line_voltage_ca;
    private TextView item_detail_rt_phase_currente_a,item_detail_rt_phase_current_b,item_detail_rt_phase_current_c;
    private TextView item_detail_rt_frequency,item_detail_rt_total_kWh;

//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.item_detail_real_time, container, false);
//        initView(view);
//
//
//        EventBus.getDefault().register(this);
//        return view;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_real_time);

        initView();
        initData();
    }



    private void initData() {
        Bundle bundle = getIntent().getExtras();
//        getIntent().getStringExtra()

        //key  空格敏感
        item_detail_rt_objectname.setText(bundle.getString("item_detail_rt_objectname"));
        item_detail_rt_ymd.setText(bundle.getString("item_detail_rt_ymd"));
        item_detail_rt_hm.setText(bundle.getString("item_detail_rt_hm"));

        item_detail_rt_phase_voltage_a.setText(bundle.getString("item_detail_rt_phase_voltage_a"));
        item_detail_rt_phase_voltage_b.setText(bundle.getString("item_detail_rt_phase_voltage_b"));
        item_detail_rt_phase_voltage_c.setText(bundle.getString("item_detail_rt_phase_voltage_c"));

        item_detail_rt_line_voltage_ab.setText(bundle.getString("item_detail_rt_line_voltage_ab"));
        item_detail_rt_phase_voltage_bc.setText(bundle.getString("item_detail_rt_phase_voltage_bc"));
        item_detail_rt_line_voltage_ca.setText(bundle.getString("item_detail_rt_line_voltage_ca"));

        item_detail_rt_phase_currente_a.setText(bundle.getString("item_detail_rt_phase_currente_a"));
        item_detail_rt_phase_current_b.setText(bundle.getString("item_detail_rt_phase_current_b"));
        item_detail_rt_phase_current_c.setText(bundle.getString("item_detail_rt_phase_current_c"));

        item_detail_rt_frequency.setText(bundle.getString("item_detail_rt_frequency"));
        item_detail_rt_total_kWh.setText(bundle.getString("item_detail_rt_total_kWh"));


        String ass=bundle.getString("ass");

        Log.d(TAG,"cc  :"+  bundle.getString("item_detail_rt_objectname")+"\t"+ass);
    }


private void initView() {
    item_detail_rt_objectname=findViewById(R.id.item_detail_rt_objectname);
    item_detail_rt_ymd=findViewById(R.id.item_detail_rt_ymd);
    item_detail_rt_hm=findViewById(R.id.item_detail_rt_hm);

    item_detail_rt_phase_voltage_a=findViewById(R.id.item_detail_rt_phase_voltage_a);
    item_detail_rt_phase_voltage_b=findViewById(R.id.item_detail_rt_phase_voltage_b);
    item_detail_rt_phase_voltage_c=findViewById(R.id.item_detail_rt_phase_voltage_c);

    item_detail_rt_line_voltage_ab=findViewById(R.id.item_detail_rt_line_voltage_ab);
    item_detail_rt_phase_voltage_bc=findViewById(R.id.item_detail_rt_phase_voltage_bc);
    item_detail_rt_line_voltage_ca=findViewById(R.id.item_detail_rt_line_voltage_ca);

    item_detail_rt_phase_currente_a=findViewById(R.id.item_detail_rt_phase_currente_a);
    item_detail_rt_phase_current_b=findViewById(R.id.item_detail_rt_phase_current_b);
    item_detail_rt_phase_current_c=findViewById(R.id.item_detail_rt_phase_current_c);

    item_detail_rt_frequency=findViewById(R.id.item_detail_rt_frequency);
    item_detail_rt_total_kWh=findViewById(R.id.item_detail_rt_total_kWh);

}

//    public static void start(Context mContext) {
//        Intent intent = new Intent(mContext, RTDetailActivity.class);
//        mContext.startActivity(intent);
//    }
}
