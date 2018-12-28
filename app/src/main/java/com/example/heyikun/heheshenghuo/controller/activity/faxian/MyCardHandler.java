package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crazysunj.cardslideview.CardHandler;
import com.crazysunj.cardslideview.CardViewPager;
import com.crazysunj.cardslideview.ElasticCardView;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;


import java.util.List;

/**
 * description
 * <p>
 * Created by sunjian on 2017/6/24.
 */

public class MyCardHandler implements CardHandler<BigCastBean.DataBean.RecommendBean> {

    @Override
    public View onBind(final Context context, final BigCastBean.DataBean.RecommendBean data, int position, @CardViewPager.TransformerMode int mode) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_bigcast_image, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.bigcast_lunbo_image);
        ElasticCardView cardView = (ElasticCardView) view.findViewById(R.id.cardview);
        final boolean isCard = mode == CardViewPager.MODE_CARD;
        cardView.setPreventCornerOverlap(isCard);
        cardView.setUseCompatPadding(isCard);
        String people_pic = data.getPeople_pic();

        Glide.with(context)
                .load(people_pic)
                .centerCrop()
                .placeholder(R.drawable.jcwz)

                .into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BigCastPeopleDetailActivity.class);
                intent.putExtra("id", data.getPeople_id());
                context.startActivity(intent);

            }
        });


        return view;
    }


}
