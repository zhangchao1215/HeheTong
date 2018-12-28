package com.heyikun.hehe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.heyikun.RecyclerAllCheckAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/12/6 16:17
 * description：
 */

public class RecyclerAllCheck extends BaseActivity {
	@BindView(R.id.mText)
	TextView mText;
	@BindView(R.id.Base_RecyclerView)
	RecyclerView BaseRecyclerView;

	private ArrayList<CheckBean> arrayList = new ArrayList<>();
	private RecyclerAllCheckAdapter adapter;

	@Override
	protected int layoutId() {
		return R.layout.activity_base_recyclerview;
	}

	@Override
	protected void initView() {

		for (int i = 0; i < 50; i++) {
			CheckBean bean = new CheckBean();
			bean.setName("超哥" + i + "");
			arrayList.add(bean);


		}
		BaseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new RecyclerAllCheckAdapter(arrayList, this);
		BaseRecyclerView.setAdapter(adapter);


	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initLisenter() {
		mText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				List<CheckBean> checkBeanList = adapter.getCheckBeanList();


				for (CheckBean bean : checkBeanList) {

					Log.d("RecyclerAllCheck", bean.getName() + ",");
				}
			}
		});
	}


}
