package com.example.heyikun.heheshenghuo.newlife;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.TikuImageBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.R.attr.type;
import static cn.jpush.android.api.JPushInterface.a.p;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/7 17:04
 * description：
 */

public class ArticleTestFragment extends BaseFragment implements HeHetiKuimageAdapter.OnItemClick, HeHetiKuiResultAdapter.TikuOnClick {
	@BindView(R.id.mTap_recycler)
	RecyclerView mTapRecycler;
	@BindView(R.id.article_recycler)
	RecyclerView articleRecycler;
	Unbinder unbinder;

	private String type_id;
	private String user_id;
	private String token;
	private HeHetiKuimageAdapter imageAdapter;
	private List<TikuImageBean.DataBean.TwoClassBean> mList;
	List<TikuImageBean.DataBean.TestsBean> testsList;
	private HeHetiKuiResultAdapter resultAdapter;

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_article_test;
	}

	@Override
	protected void initData() {

		LoadData();
	}

	@Override
	protected void initView(View view) {
		mList = new ArrayList<>();
		testsList = new ArrayList<>();
		articleRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

		articleRecycler.setNestedScrollingEnabled(false);
		articleRecycler.setHasFixedSize(true);

		resultAdapter = new HeHetiKuiResultAdapter(testsList, getContext());
		articleRecycler.setAdapter(resultAdapter);

		GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
		mTapRecycler.setLayoutManager(manager);
		mTapRecycler.setNestedScrollingEnabled(false);
		mTapRecycler.setHasFixedSize(true);

		user_id = AppUtils.get().getString("user_id", "");
		token = AppUtils.get().getString("token", "");


		imageAdapter = new HeHetiKuimageAdapter(mList, getContext());
		mTapRecycler.setAdapter(imageAdapter);



	}

	@Override
	protected void initListener() {

		imageAdapter.setOnItemClick(this);
		resultAdapter.setTikuOnClick(this);
	}

	private void LoadData() {


		String token = AppUtils.get().getString("token", "");

		String aesUserid = null;
		String aesToken = null;

		String time_today = TimeUtils.getCurrentTime_Today();
		String timeStamp = TimeUtils.dataOne(time_today);

		String twoToken = user_id + "," + token + "," + timeStamp;

		try {
			if (!user_id.equals("") && !token.equals("")) {
				aesUserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
				aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> params = new HashMap<>();
		params.put("action", "GetQuestions");
//		params.put("user_id", aesUserid);
//		params.put("token", aesToken);
		params.put("ques_type", type_id);

		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {

				Log.d("ArticleTestFragment", data);
				Gson gson = new Gson();
				try {
					TikuImageBean tikuImageBean = gson.fromJson(data, TikuImageBean.class);

					mList.addAll(tikuImageBean.getData().getTwo_class());
					imageAdapter.notifyDataSetChanged();

					String class_id = tikuImageBean.getData().getTwo_class().get(0).getClass_id();


					articleData(class_id);

				} catch (Exception e) {
					e.getMessage();
				}

			}

			@Override
			public void onError(String error) {

			}
		});


	}

	/**
	 * 下面列表展示的数据
	 */

	public void articleData(String class_id) {
		testsList.clear();
		Map<String, String> params = new HashMap<>();
		params.put("action", "GetQuestions");
//		params.put("user_id", aesUserid);
//		params.put("token", aesToken);
		params.put("ques_type", type_id);
		params.put("class_id", class_id);
		App.myOkHttp
				.post()
				.url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new GsonResponseHandler<TikuImageBean>() {
					@Override
					public void onSuccess(int statusCode, TikuImageBean response) {

						if (response.getResult().equals("200")) {

							List<TikuImageBean.DataBean.TestsBean> tests = response.getData().getTests();
							testsList.addAll(tests);
							resultAdapter.notifyDataSetChanged();

						}
					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});

	}

	/**
	 * 最上面分类的点击事件
	 *
	 * @param position
	 */
	@Override
	public void onItemclick(int position) {
		TikuImageBean.DataBean.TwoClassBean twoClassBean = mList.get(position);

		articleData(twoClassBean.getClass_id());

	}

	/**
	 * 文章列表的自定义点击事件
	 *
	 * @param position
	 */
	@Override
	public void onItemClick(int position) {

		TikuImageBean.DataBean.TestsBean testsBean = testsList.get(position);


		WebViewUtils.ShareWebView(getContext(), testsBean.getVote_link(), "");

	}
}
