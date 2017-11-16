package com.hxxn.emmspro.tools;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hxxn.emmspro.R;


import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Created by jingbin on 2016/11/26.
 */

public class ImgLoadUtil {

    private static ImgLoadUtil instance;

    private ImgLoadUtil() {
    }

    public static ImgLoadUtil getInstance() {
        if (instance == null) {
            instance = new ImgLoadUtil();
        }
        return instance;
    }





//--------------------------------------







    /**
     * 加载圆角图,暂时用到显示头像
     */
    public static void displayCircle(ImageView imageView, int drawable) {
        Glide.with(imageView.getContext())
                .load(drawable)
                .crossFade(500)
                .error(R.drawable.ic_avatar_default)
                .transform(new GlideCircleTransform(imageView.getContext()))
                .into(imageView);
    }





}
