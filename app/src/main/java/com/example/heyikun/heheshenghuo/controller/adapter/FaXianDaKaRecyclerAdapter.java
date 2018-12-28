package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.ShareWebView;
import com.example.heyikun.heheshenghuo.controller.activity.faxian.BigCastActivity;
import com.example.heyikun.heheshenghuo.controller.activity.faxian.YangShenPeopleActivity;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeFaxianBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

/**
 * Created by hyk on 2017/10/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/28
 * <p>
 * 3：类描述： 健康发现广告位的适配器
 * <p>
 * 4:类功能：
 */

public class FaXianDaKaRecyclerAdapter extends BaseRecyclerAdapter<HeHeFaxianBean.DataBean.PlateBean> {
	public FaXianDaKaRecyclerAdapter(List<HeHeFaxianBean.DataBean.PlateBean> mList, Context context) {
		super(mList, context, R.layout.activity_faxian_guanggao_item);
	}

	@Override
	public void convert(final ViewHolder holder, final HeHeFaxianBean.DataBean.PlateBean plateBean, final int Position) {

		ImageView imageView = holder.getView(R.id.Faxian_DakaImage);

		Glide.with(context)
				.load(plateBean.getPlate_pic())
				.placeholder(R.drawable.jcwz)
				.into(imageView);


		Log.d("FaXianDaKaRecyclerAdapt", "广告位图片：：    " + plateBean.getPlate_pic());

		if (Position == 0) {

			holder.setText(R.id.Faxian_TextDakaOne, "大医说");
		} else {

			holder.setText(R.id.Faxian_TextDakaOne, plateBean.getPlate_name() + "");
		}
		holder.setText(R.id.Faxian_TextDakaTwo, plateBean.getPlate_desc() + "");

		//

		holder.setOnclickListener(R.id.Faxian_Linear, new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (Position == 0) {
					//                    mIntent(plateBean.getPlate_link(), plateBean.getPlate_name());

					Intent intent = new Intent(context, BigCastActivity.class);
					context.startActivity(intent);
				}
				if (Position == 1) {
					//                    mIntent(plateBean.getPlate_link(), plateBean.getPlate_name());

					Intent intent = new Intent(context, YangShenPeopleActivity.class);
					context.startActivity(intent);

				}
				if (Position == 2) {
					mIntent(plateBean.getPlate_link(), plateBean.getPlate_name());
				}
				if (Position == 3) {
					mIntent(plateBean.getPlate_link(), plateBean.getPlate_name());
				}
				if (Position == 4) {
					mIntent(plateBean.getPlate_link(), plateBean.getPlate_name());
				}
				if (Position == 5) {
					mIntent(plateBean.getPlate_link(), plateBean.getPlate_name());
				}


			}
		});

	}

	private void mIntent(String url, String title) {
		//        WebViewUtils.WebFrom(context, url, title);
		//
		//        Intent intent = new Intent(context, ShareWebView.class);
		//        intent.putExtra("url", url + "&from=1");
		//        intent.putExtra("title", title);
		//        context.startActivity(intent);


		WebViewUtils.publicWebView(context, url, title);

	}
}
