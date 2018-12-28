package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.UserFragmentBean;
import com.example.heyikun.heheshenghuo.modle.util.DpUtils;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.drawableLeft;
import static android.R.attr.id;
import static android.R.attr.pointerIcon;

/**
 * Created by hyk on 2017/12/6.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/6
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class UserRecyclerAdapter extends BaseRecyclerAdapter<UserFragmentBean.DataBean.TestQueBean> {
	private OnitemClick onitemClick;

	private List<UserFragmentBean.DataBean.TestQueBean> mPoiItems;

	public void setOnitemClick(OnitemClick onitemClick) {
		this.onitemClick = onitemClick;
	}


	public UserRecyclerAdapter(List<UserFragmentBean.DataBean.TestQueBean> mList, Context context) {
		super(mList, context, R.layout.activity_user_item);
		mPoiItems = new ArrayList<>();
	}

	@Override
	protected void convert(ViewHolder holder, UserFragmentBean.DataBean.TestQueBean testQueBean, final int Position) {

		LinearLayout ceshiLinear = holder.getView(R.id.user_shen_ceshiLinear);
		RelativeLayout relative = holder.getView(R.id.user_shen_text_relative);
		LinearLayout nullLinear = holder.getView(R.id.user_shen_null_linear);


		List<UserFragmentBean.DataBean.TestQueBean.DatarrBean> datarr = testQueBean.getDatarr();


		if (datarr == null || datarr.size() == 0) {
			nullLinear.setVisibility(View.VISIBLE);
			ceshiLinear.setVisibility(View.GONE);
			relative.setVisibility(View.GONE);
		} else {

			ceshiLinear.setVisibility(View.VISIBLE);
			relative.setVisibility(View.VISIBLE);
			nullLinear.setVisibility(View.GONE);

			holder.setText(R.id.user_item_text1, datarr.get(0).getTest_res() + "");
			holder.setText(R.id.user_item_text2, datarr.get(1).getTest_res() + "");
			holder.setText(R.id.user_item_text3, datarr.get(2).getTest_res() + "");

			holder.setText(R.id.user_item_ceshi1, datarr.get(0).getTest_name() + "");
			holder.setText(R.id.user_item_ceshi2, datarr.get(1).getTest_name() + "");
			holder.setText(R.id.user_item_ceshi3, datarr.get(2).getTest_name() + "");


			holder.setOnclickListener(R.id.user_linear, new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					onitemClick.OnClickListener(v, Position);

				}
			});


			if (Position == 0) {
				holder.setText(R.id.user_item_titleText, "身体数据");
				holder.setImageResource(R.id.user_item_titleImage, R.drawable.yangshen4x);


				getHeight(holder, datarr);

			} else if (Position == 1) {
				holder.setText(R.id.user_item_titleText, "心理数据");
				holder.setImageResource(R.id.user_item_titleImage, R.drawable.yangxin4x);

				drawImage(holder, R.drawable.fang_four_big, R.drawable.fang_five_big, R.drawable.fang_six_big,
						R.drawable.four_quan4x, R.drawable.five_quan4x, R.drawable.sic_quan4x,
						R.drawable.fang_four_small, R.drawable.fang_four_small, R.drawable.fang_hui,
						R.drawable.four_zhu, R.drawable.five_zhu, R.drawable.six_zhux


				);
				getHeight(holder, datarr);
			} else if (Position == 2) {
				holder.setText(R.id.user_item_titleText, "财富数据");
				holder.setImageResource(R.id.user_item_titleImage, R.drawable.yangcai4x);

				drawImage(holder, R.drawable.fang_senve_big, R.drawable.fang_eight_big, R.drawable.fang_jiu9,
						R.drawable.senve_quan4x, R.drawable.eight_quan4x, R.drawable.nine_quan4x,
						R.drawable.fang_senve_small, R.drawable.fang_eight_small, R.drawable.fang_nine_small,
						R.drawable.senve_zhux, R.drawable.eight_zhu, R.drawable.nine_zhu


				);

				getHeight(holder, datarr);
			} else if (Position == 3) {
				holder.setText(R.id.user_item_titleText, "财富数据");
				holder.setImageResource(R.id.user_item_titleImage, R.drawable.zhihui_image);

				nullLinear.setVisibility(View.VISIBLE);
				ceshiLinear.setVisibility(View.GONE);
				relative.setVisibility(View.GONE);

			}

		}
	}


	private void drawImage(ViewHolder holder,
						   int image1, int image2, int image3,
						   int quan1, int quan2, int quan3,
						   int fang1, int fang2, int fang3,
						   int zhu1, int zhu2, int zhu3
	) {

		holder.setImageResource(R.id.one_quan, quan1);
		holder.setImageResource(R.id.two_quan, quan2);
		holder.setImageResource(R.id.three_quan, quan3);

		holder.setImageResource(R.id.one_fang_image, fang1);
		holder.setImageResource(R.id.two_fang_image, fang2);
		holder.setImageResource(R.id.three_fang_image, fang3);

		holder.setImageResource(R.id.One_zhuImage, zhu1);
		holder.setImageResource(R.id.Two_zhuImage, zhu2);
		holder.setImageResource(R.id.Three_zhuImage, zhu3);


		TextView textView1 = holder.getView(R.id.user_item_ceshi1);
		TextView textView2 = holder.getView(R.id.user_item_ceshi2);
		TextView textView3 = holder.getView(R.id.user_item_ceshi3);

		Drawable drawable1 = context.getResources().getDrawable(image1);
		Drawable drawable2 = context.getResources().getDrawable(image2);
		Drawable drawable3 = context.getResources().getDrawable(image3);

		textView1.setCompoundDrawablePadding(11);
		textView2.setCompoundDrawablePadding(11);
		textView3.setCompoundDrawablePadding(11);

		textView1.setCompoundDrawablesWithIntrinsicBounds(drawable1,
				null, null, null);
		textView2.setCompoundDrawablesWithIntrinsicBounds(drawable2, null, null, null);
		textView3.setCompoundDrawablesWithIntrinsicBounds(drawable3, null, null, null);

	}

//    public void notifyData(List<UserFragmentBean.DataBean.TestQueBean> poiItemList) {
//        if (poiItemList != null) {
//            int previousSize = poiItemList.size();
////            mPoiItems.clear();
////            notifyItemRangeRemoved(0, previousSize);
//            mPoiItems.addAll(poiItemList);
////            notifyItemRangeInserted(0, poiItemList.size());
//            notifyDataSetChanged();
//        }
//    }


	public interface OnitemClick {
		void OnClickListener(View view, int position);

	}


	private void getHeight(ViewHolder holder, List<UserFragmentBean.DataBean.TestQueBean.DatarrBean> datarr) {

		ImageView imag1 = holder.getView(R.id.One_zhuImage);
		ImageView imag2 = holder.getView(R.id.Two_zhuImage);
		ImageView imag3 = holder.getView(R.id.Three_zhuImage);

		RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) imag1.getLayoutParams();

		int test_res = datarr.get(0).getTest_res();

		int i = DpUtils.dip2px(context, test_res);

		params1.height = i;
		imag1.setLayoutParams(params1);

		RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) imag2.getLayoutParams();
		int test_res1 = datarr.get(1).getTest_res();

		int i1 = DpUtils.dip2px(context, test_res1);

		params2.height = i1;
		imag2.setLayoutParams(params2);

		RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) imag3.getLayoutParams();
		int test_res2 = datarr.get(2).getTest_res();

		int i2 = DpUtils.dip2px(context, test_res2);

		params3.height = i2;
		imag3.setLayoutParams(params3);
	}
}
