//package com.hxxn.eems.api;
//
//import com.hxxn.eems.base.Constants;
//
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by Administrator on 2017/11/6 0006.
// */
//
//public class RetrofitApi {
//
//
//    private static GetService service;
//    public RetrofitApi() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASEURL_LOGING)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        service = retrofit.create(GetService.class);
//    }
//    //单例
//    public static GetService getService() {
//        if (service == null) {
//            synchronized (RetrofitApi.class) {
//                if (service == null) {
//                    new RetrofitApi();
//                }
//            }
//        }
//        return service;
//    }
//}
