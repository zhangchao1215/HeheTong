package com.example.heyikun.heheshenghuo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.controller.activity.ShangQuanActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.HeHeServeAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.ServeShangquanAdaoter;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ServeBean;
import com.example.heyikun.heheshenghuo.modle.bean.ServeShangquanBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.LocationUtils;
import com.example.heyikun.heheshenghuo.modle.util.PermissionUtils;
import com.example.heyikun.heheshenghuo.modle.util.PermissionsManager;
import com.example.heyikun.heheshenghuo.modle.util.PermissionsResultAction;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by hyk on 2017/11/20.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/20
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 和合服务
 */

public class HeHeServeFragment extends BaseFragment {


	@BindView(R.id.serve_image_sousuo)
	ImageView serveImageSousuo;
	@BindView(R.id.Serve_addLinear)
	LinearLayout ServeAddLinear;
	@BindView(R.id.Serve_Image_one)
	ImageView ServeImageOne;
	@BindView(R.id.Serve_Image_two)
	ImageView ServeImageTwo;
	@BindView(R.id.Serve_Image_three)
	ImageView ServeImageThree;
	@BindView(R.id.serve_recycler)
	RecyclerView serveRecycler;
	@BindView(R.id.Serve_Text_location)
	TextView ServeTextLocation;
	@BindView(R.id.Serve_address_but)
	Button ServeAddressBut;
	@BindView(R.id.Shangquan_recycler)
	RecyclerView ShangquanRecycler;
	@BindView(R.id.shangquan_linear)
	LinearLayout shangquanLinear;
	Unbinder unbinder;
	@BindView(R.id.loding)
	LinearLayout loding;
	@BindView(R.id.Serve_scrollview)
	NestedScrollView ServeScrollview;
	Unbinder unbinder1;
	@BindView(R.id.mRelative_title)
	RelativeLayout mRelativeTitle;
	Unbinder unbinder2;
	private List<ServeBean.DataBean.ShopsBean> shopsBeanList;
	private HeHeServeAdapter adapter;
	private double latitude;
	private double longitude;
	private String AEStoken = null;
	private String AESuserid = null;
	private List<ServeShangquanBean.DataBean.DistrictBean> districtBeanList;
	private ServeShangquanAdaoter serveShangquanAdaoter;
	private String strLatitude;
	private String strLongitude;
	private List<ServeBean.DataBean.BannerAdBean> banner_ad;
	private String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
	private ServeBean.DataBean dataBean;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_hehe_serve;
	}


	@Override
	public void onResume() {
		super.onResume();
		String sex = AppUtils.get().getString("sex", "");

		if (sex.equals("2")) {
			mRelativeTitle.setBackgroundColor(getResources().getColor(R.color.colorTextWomen));
		} else {
			mRelativeTitle.setBackgroundColor(getResources().getColor(R.color.colorManTitle));
		}

	}

	@Override
	protected void initData() {

		//                accessFineLocation();
		try {
			Location location = LocationUtils.getInstance(getContext()).showLocation();

			latitude = location.getLatitude();
			longitude = location.getLongitude();

		} catch (Exception e) {
			e.printStackTrace();

		}
		LodaData();
		getAllpermission();
	}

	@Override
	protected void initView(View view) {
		getAllpermission();

		String district_name = AppUtils.get().getString("district_Name", "");
		if (district_name.equals("")) {
			ServeTextLocation.setText("定位");
		} else
			ServeTextLocation.setText(district_name);


		ServeScrollview.smoothScrollTo(0, 20);

		districtBeanList = new ArrayList<>();
		shopsBeanList = new ArrayList<>();
		LinearLayoutManager manager = new LinearLayoutManager(getContext());
		manager.setSmoothScrollbarEnabled(true);
		manager.setAutoMeasureEnabled(true);
		serveRecycler.setHasFixedSize(true);
		serveRecycler.setNestedScrollingEnabled(false);
		serveRecycler.setLayoutManager(manager);

	}


	@TargetApi(23)
	private void getAllpermission() {
		PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(getActivity(), new PermissionsResultAction() {
			@Override
			public void onGranted() {
				//维度

			}

			@Override
			public void onDenied(String permission) {
				Log.d("HeHeServeFragment", "permission" + permission);
			}
		});


	}

	@Override
	protected void initListener() {


	}


	private void LodaData() {
		String token = AppUtils.get().getString("token", "");

		String userId = AppUtils.get().getString("user_id", "");
		//获取时间戳
		String currentTime_today = TimeUtils.getCurrentTime_Today();
		String timestamp = TimeUtils.dataOne(currentTime_today);


		//给userID 还有 生成二次token ，

		String Twotoken = userId + "," + token + "," + timestamp;


		try {
			if (!token.equals("") && !userId.equals("")) {
				AESuserid = AESUtils.Encrypt(userId, BaseUrl.AESKey);
				AEStoken = AESUtils.Encrypt(Twotoken, BaseUrl.AESKey);

			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> params = new HashMap<>();
		params.put("action", "ServicePage");
		params.put("user_id", AESuserid);
		params.put("token", AEStoken);
		params.put("longitude", String.valueOf(longitude));
		params.put("latitude", String.valueOf(latitude));


		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {
				Log.d("HeHeServeFragment", data);

				Gson gson = new Gson();
				ServeBean serveBean = null;
				try {
					serveBean = gson.fromJson(data, ServeBean.class);
					dataBean = serveBean.getData();
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				} catch (Exception c) {
					c.printStackTrace();
				}
				if (serveBean.getStatus().equals("200")) {
					loding.setVisibility(View.GONE);
					ServeScrollview.setVisibility(View.VISIBLE);

					for (int i = 0; i < dataBean.getMenu().size(); i++) {
						View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_serve_icon_item, null);

						TextView text = (TextView) view.findViewById(R.id.Serve_text_name);

						ImageView image = (ImageView) view.findViewById(R.id.serve_image_icon);
						final ServeBean.DataBean.MenuBean menuBean = dataBean.getMenu().get(i);
						text.setText(menuBean.getMenu_name());

						Glide.with(getContext())
								.load(menuBean.getMenu_img())
								.centerCrop()
								.placeholder(R.drawable.jcwz)
								.into(image);

						final int FinaI = i;

						view.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								WebViewUtils.publicWebView(getContext(),
										dataBean.getMenu().get(FinaI).getMenu_url()
												+ "&longitude=" + String.valueOf(longitude) + "&latitude=" + String.valueOf(latitude),
										dataBean.getMenu().get(FinaI).getMenu_name()
								);
							}
						});

						ServeAddLinear.addView(view);
					}

					banner_ad = dataBean.getBanner_ad();

					Glide.with(getContext())
							.load(banner_ad.get(0).getCarousel_pic())
							.into(ServeImageOne);
					mOnimageClick(ServeImageOne, 0);

					Glide.with(getContext())
							.load(banner_ad.get(1).getCarousel_pic())
							.into(ServeImageTwo);
					mOnimageClick(ServeImageTwo, 1);
					Glide.with(getContext())
							.load(banner_ad.get(2).getCarousel_pic())
							.into(ServeImageThree);
					mOnimageClick(ServeImageThree, 2);


					shopsBeanList.addAll(dataBean.getShops());
					adapter = new HeHeServeAdapter(shopsBeanList, getContext());
					serveRecycler.setAdapter(adapter);

				} else {
					loding.setVisibility(View.GONE);
					ServeScrollview.setVisibility(View.VISIBLE);
				}


			}

			@Override
			public void onError(String error) {
				loding.setVisibility(View.GONE);
				ServeScrollview.setVisibility(View.VISIBLE);
			}
		});
	}


	private void mOnimageClick(ImageView image, final int positon) {
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				ServeBean.DataBean.BannerAdBean bannerAdBean = banner_ad.get(positon);
//				WebViewUtils.publicWebView(getContext(), bannerAdBean.getCarousel_link(), "  ");


			}
		});


	}


	private void getLoaction() {

		AndPermission.with(getContext())
				.requestCode(100)
				.permission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
				.callback(new PermissionListener() {
					@Override
					public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

						//                        if (requestCode == 100) {
						//
						//                            districtBeanList.clear();
						//
						//
						//                            //经度
						//
						//                            Map<String, String> params = new HashMap<>();
						//                            params.put("action", "LocationUser");
						//                            params.put("user_id", AESuserid);
						//                            params.put("token", AEStoken);
						//                            params.put("longitude", String.valueOf(longitude));
						//                            params.put("latitude", String.valueOf(latitude));
						//
						//                            App.myOkHttp.post().url(BaseUrl.BaseUrl)
						//                                    .params(params)
						//                                    .enqueue(new GsonResponseHandler<ServeShangquanBean>() {
						//                                        @Override
						//                                        public void onSuccess(int statusCode, ServeShangquanBean response) {
						//
						//                                            if (response.getStatus().equals("200")) {
						//                                                ServeShangquanBean.DataBean data = response.getData();
						//
						//                                                ServeAddressBut.setText(data.getAddress());
						//
						//                                                districtBeanList.addAll(data.getDistrict());
						//
						//                                                serveShangquanAdaoter = new ServeShangquanAdaoter(districtBeanList, getContext());
						//                                                ShangquanRecycler.setAdapter(serveShangquanAdaoter);
						//                                                serveShangquanAdaoter.notifyDataSetChanged();
						//
						//                                            }
						//
						//                                        }
						//
						//                                        @Override
						//                                        public void onFailure(int statusCode, String error_msg) {
						//                                            Log.d("HeHeServeFragment", error_msg);
						//                                        }
						//                                    });
						//
						//                        }


					}

					@Override
					public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

					}
				}).start();


	}

	private int LocationFlag = 0;

	@OnClick({R.id.serve_image_sousuo, R.id.Serve_Image_one, R.id.Serve_Image_two, R.id.Serve_Image_three
			, R.id.Serve_Text_location
	})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.serve_image_sousuo:
				final String searchUrl = "https://hehe.heyishenghuo.com/mobile2/app2/public/search.php?";

				WebViewUtils.publicWebView(getContext(), searchUrl + "&search=5", " ");


				break;

			//定位
			case R.id.Serve_Text_location:

				Intent intent = new Intent(getContext(), ShangQuanActivity.class);

				startActivityForResult(intent, 200);
				break;

		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 200 && resultCode == 201) {
			String district_id = data.getStringExtra("district_Name");

			ServeTextLocation.setText(district_id);

		}


	}

	public boolean checkPermission(@NonNull String permission) {
		return ActivityCompat.checkSelfPermission(getContext(), permission) == PackageManager.PERMISSION_GRANTED;
	}

	public void accessFineLocation() {
		PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_ACCESS_COARSE_LOCATION, mPermissionGrant);
	}


	private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
		@Override
		public void onPermissionGranted(int requestCode) {
			switch (requestCode) {
				case PermissionUtils.CODE_RECORD_AUDIO:
					break;
				case PermissionUtils.CODE_GET_ACCOUNTS:
					break;
				case PermissionUtils.CODE_READ_PHONE_STATE:
					break;
				case PermissionUtils.CODE_CALL_PHONE:
					break;
				case PermissionUtils.CODE_CAMERA:
					break;
				case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
					break;
				case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:

					break;
				case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
					break;
				case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
					break;
				case PermissionUtils.CODE_MULTI_PERMISSION:
					break;
				default:
					break;
			}
		}
	};

	@Override
	public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
		PermissionUtils.requestPermissionsResult(getActivity(), requestCode, permissions, grantResults, mPermissionGrant);
	}


}
