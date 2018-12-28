package com.example.heyikun.heheshenghuo.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ChatMessageAdaoter;
import com.example.heyikun.heheshenghuo.controller.adapter.ChatQuestionAdapter;
import com.example.heyikun.heheshenghuo.controller.user.UserSettingActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ChatMessageBean;
import com.example.heyikun.heheshenghuo.modle.bean.SendMessageBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.Utils;
import com.example.heyikun.heheshenghuo.modle.util.UtilsHelper;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/28 13:31
 * description：
 */

public class DoctorChatActivity extends BaseActivity implements ChatQuestionAdapter.OnTextItemClick {
	@BindView(R.id.mText_name)
	TextView mTextName;
	@BindView(R.id.mRelative_title)
	RelativeLayout mRelativeTitle;
	@BindView(R.id.mRecycler_chat)
	RecyclerView mRecyclerChat;
	@BindView(R.id.mBut_send)
	Button mButSend;
	@BindView(R.id.mRelative_message)
	RelativeLayout mRelativeMessage;
	@BindView(R.id.mEdit_Message)
	EditText mEditMessage;
	@BindView(R.id.mImage_Back)
	ImageView mImageBack;
	@BindView(R.id.mRecycler_question)
	RecyclerView mRecyclerQuestion;
	@BindView(R.id.mBut_sendQuestion)
	Button mButSendQuestion;
	@BindView(R.id.mRelative_question)
	RelativeLayout mRelativeQuestion;
	@BindView(R.id.mRelative_bottom)
	RelativeLayout mRelativeBottom;
	@BindView(R.id.btn_more)
	Button btnMore;
	@BindView(R.id.mRelative_right)
	RelativeLayout mRelativeRight;
	@BindView(R.id.mText_image_picture)
	TextView mTextImagePicture;
	@BindView(R.id.mText_image_takepic)
	TextView mTextImageTakepic;
	@BindView(R.id.mLinear_bottom)
	LinearLayout mLinearBottom;
	@BindView(R.id.mRelative_top)
	RelativeLayout mRelativeTop;
	private String user_id;
	private int page = 1;
	private ChatMessageAdaoter messageAdaoter;
	private List<ChatMessageBean.DataBean> dataBeanList;
	private String user_image;
	private int timeData = 0;
	private ChatMessageBean.DataBean dataBean;
	private List<ChatMessageBean.DataBean> data;
	private List<String> mListquestions;
	private Button mBut;
	private List<ChatMessageBean.DataBean.VoteAskBean.ValScopeBean> strBeanList;
	private ChatQuestionAdapter questionAdapter;
	private int btn_more;

	private int maxImgCount = 1;
	private ArrayList<ImageItem> images = null;
	public static final int IMAGE_ITEM_ADD = -1;
	public static final int REQUEST_CODE_SELECT = 100;
	public static final int REQUEST_CODE_PREVIEW = 101;
	private String chat_id = "0";
	private String listStr = "";

	@Override
	protected int layoutId() {
		return R.layout.activity_doctor_chat_layout;
	}

	@Override
	protected void initView() {
		mListquestions = new ArrayList<>();
		strBeanList = new ArrayList<>();

		dataBeanList = new ArrayList<>();
		user_id = AppUtils.get().getString("user_id", "");
		user_image = AppUtils.get().getString("user_image", "");
		mRecyclerChat.setLayoutManager(new LinearLayoutManager(this));
		messageAdaoter = new ChatMessageAdaoter(dataBeanList, this);
		mRecyclerChat.setAdapter(messageAdaoter);

		Log.d("DoctorChatActivity", "user_id》》》》》》》》》》》》 " + user_id);
		/**
		 *  选择题的适配器
		 */
		mRecyclerQuestion.setLayoutManager(new LinearLayoutManager(this));
		questionAdapter = new ChatQuestionAdapter(strBeanList, this);
		mRecyclerQuestion.setAdapter(questionAdapter);


		initImagePicker();
	}


	@Override
	protected void initData() {

		getMessageList();
	}

	@Override
	protected void initLisenter() {

		questionAdapter.setOnTextItemClick(this);

		// 获得edittext的监听
		mEditMessage.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {


			}

			@Override
			public void afterTextChanged(Editable s) {

				if (s.toString().equals("")) {
					btnMore.setVisibility(View.VISIBLE);
					mButSend.setVisibility(View.GONE);
				} else {
					btnMore.setVisibility(View.GONE);
					mButSend.setVisibility(View.VISIBLE);
				}

				if (mLinearBottom.getVisibility() == VISIBLE) {
					mLinearBottom.setVisibility(View.GONE);

				}
			}
		});


	}

	/**
	 * 上传头像
	 */


	private void initImagePicker() {
		ImagePicker imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
		imagePicker.setShowCamera(true);                      //显示拍照按钮
//		imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
		imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
		imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
		imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
		imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
		imagePicker.setOutPutY(1000); //保存文件的高度。单位像素
	}


	/**
	 * 获取消息列表
	 */
	private void getMessageList() {
		strBeanList.clear();
		dataBeanList.clear();
		Map<String, String> params = new HashMap<>();
		params.put("action", "GetChatRecord");
		params.put("staff_id", "12498");
		params.put("patient_id", user_id);
		params.put("chat_id", chat_id);
		App.myOkHttp
				.post()
				.params(params)
				.url(BaseUrl.BaseUrl)
				.enqueue(new GsonResponseHandler<ChatMessageBean>() {
					@Override
					public void onSuccess(int statusCode, ChatMessageBean response) {

						if (response.getStatus().equals("200")) {

							data = response.getData();

							if (data == null) {

								return;
							}
							/**
							 *  倒叙添加数据
							 */
							for (int i = data.size() - 1; i >= 0; i--) {
								dataBeanList.add(data.get(i));
								dataBean = data.get(i);
							}


							// 使数据位于列表最下方
							if (data != null) {

								mRecyclerChat.scrollToPosition(data.size() - 1);


							}

							messageAdaoter.notifyDataSetChanged();

							/**    // 获取到最新的一条数据
							 * chat_type 为2 的时候是测试题 , 为 3 的时候是患者自己发的消息
							 *
							 */
							if (data == null) {

								return;
							}

							ChatMessageBean.DataBean dataBean = data.get(0);

							/**
							 *  判断最新的小时时候已经结束。否则就不让发消息了。隐藏
							 */
							if (dataBean.getChat_content().contains("测试题问答结束")) {

								mRelativeBottom.setVisibility(View.GONE);
							}

							// chat_type 为2 的时候是测试题 ,
							if (dataBean.getChat_type().equals("2")) {

								if (dataBean.getVote_ask() == null) {

									return;
								}


								//判断 type为1的时候是选择题，这时候就要去做选择题，把底部输入框隐藏
								//getType() 1 是单选 ，2是多选 ， 3是填空
								if (dataBean.getVote_ask().getType().equals("1")) {
									/**
									 *  添加选择题的适配器
									 */
									strBeanList.addAll(dataBean.getVote_ask().getVal_scope());

									questionAdapter.notifyDataSetChanged();


									mRelativeMessage.setVisibility(View.GONE);

									mRelativeQuestion.setVisibility(View.VISIBLE);
								} else {
									mRelativeMessage.setVisibility(View.VISIBLE);

									mRelativeQuestion.setVisibility(View.GONE);
								}

							}


						}
					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});


		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {

				Log.d("DoctorChatActivity", " 收取到的消息》》》》》  " + data);

			}

			@Override
			public void onError(String error) {

			}
		});

	}


	@OnClick({R.id.mImage_Back, R.id.mBut_send, R.id.mBut_sendQuestion, R.id.mText_image_picture, R.id.mText_image_takepic, R.id.btn_more, R.id.mRelative_top, R.id.mEdit_Message})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.mImage_Back:
				finish();
				break;

			case R.id.mEdit_Message:

				// 点击输入框 如果下方布局在显示就直接隐藏
				if (mLinearBottom.getVisibility() == VISIBLE) {
					mLinearBottom.setVisibility(View.GONE);
				}

				break;

			case R.id.mBut_send:
				String message = mEditMessage.getText().toString().trim();
				if (message.isEmpty()) {
					Toast.makeText(this, "请先输入消息", Toast.LENGTH_SHORT).show();
					return;
				}


//				// 为0 时是发送普通聊天
				if (dataBean == null || dataBean.getChat_type().equals("0")) {

					mSendMsg(message, "2", "0");

					Log.d("DoctorChatActivity", "发送普通     聊天");
				} else {
					// 这是发送答案聊天
					mSendMsg(message, "2", "3");
					Log.d("DoctorChatActivity", "发送答案聊天");
				}

				mEditMessage.setText("");
				break;

			// 进入相册选择图片
			case R.id.mText_image_picture:
				Intent intent1 = new Intent(this, ImageGridActivity.class);

				startActivityForResult(intent1, REQUEST_CODE_SELECT);

				break;

			// 拍照 直接打开相机
			case R.id.mText_image_takepic:
				Intent intent = new Intent(this, ImageGridActivity.class);
				intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
				startActivityForResult(intent, REQUEST_CODE_SELECT);
				break;

			// 加号 显示出选择拍照 相册view
			case R.id.btn_more:

				// 判断如果最下方view没显示就让他显示
				UtilsHelper.hideSoftInput(this, view);

				if (btn_more == 0) {
					if (mLinearBottom.getVisibility() == View.GONE) {
						mLinearBottom.setVisibility(View.VISIBLE);
						// 点击加号的时候如果软键盘在显示 就隐藏
						if (UtilsHelper.isShowSoftInput(this, view) == true) {

							UtilsHelper.hideSoftInput(this, view);

						}

					}
					btn_more = 1;
				} else if (btn_more == 1) {
					if (mLinearBottom.getVisibility() == VISIBLE) {
						mLinearBottom.setVisibility(View.GONE);

					}

					btn_more = 0;
				}


				break;

			//最顶部的布局 ，点击隐藏软键盘的
			case R.id.mRelative_top:
				btn_more = 0; // 全部隐藏

				if (UtilsHelper.isShowSoftInput(this, view) == true) {
					UtilsHelper.hideSoftInput(this, view);
				}

				if (mLinearBottom.getVisibility() == View.VISIBLE) {
					mLinearBottom.setVisibility(View.GONE);
				}

				break;

			/**
			 *  发送单选问答答案
			 */
			case R.id.mBut_sendQuestion:

				if (listStr.equals("")) {
					Toast.makeText(this, "请选择答案", Toast.LENGTH_SHORT).show();
					return;
				}

				mSendMsg(listStr, "2", "3");


				break;

		}
	}

	/**
	 * @param message
	 * @param chat_sender 发送人  0 小和， 1 医生， 2 患者
	 */
	private void mSendMsg(final String message, final String chat_sender, String chat_type) {
		Map<String, String> params = new HashMap<>();
		params.put("action", "AddChatRecord");
		params.put("staff_id", "12498");
		params.put("patient_id", "5947");
		params.put("chat_sender", chat_sender);//发送人  0小和，1医生，2患者
		params.put("chat_type", chat_type); //消息类型 0文字，1图片，2测试题，3答案
		params.put("chat_content", message);
		params.put("receiver", "aiscm");
		if (chat_type.equals("3")) {
			params.put("chat_vote", dataBean.getChat_vote());

		} else if (chat_type.equals("1")) {
			if (!images.get(0).path.equals("")) {

				params.put("file", images.get(0).path);
			}
		}
//
//		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
//			@Override
//			public void onSuccess(String response) {
//
//				Log.d("DoctorChatActivity", "data>>》》》》》发送的消息  " + response);
//				try {
//					JSONObject jsonObject = new JSONObject(response);
//					String status = jsonObject.getString("status");
//					String message1 = jsonObject.getString("message");
//					if (status.equals("200")) {
//						getMessageList();
//						// 让RecyclerView位于最底部，数据索引的最后一条
//						if (data != null) {
//							mRecyclerChat.scrollToPosition(data.size() - 1);
//						}
//					} else {
//						Toast.makeText(DoctorChatActivity.this, message1, Toast.LENGTH_SHORT).show();
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
//
//			@Override
//			public void onError(String error) {
//
//			}
//		});


		App.myOkHttp
				.post()
				.params(params)
				.url(BaseUrl.BaseUrl)
				.enqueue(new RawResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String response) {

						Log.d("DoctorChatActivity", "发送的消息response》》》》》》  " + response);

						try {
							JSONObject jsonObject = new JSONObject(response);

							String status = jsonObject.getString("status");
							String message1 = jsonObject.getString("message");

							if (status.equals("200")) {
								getMessageList();

								// 让RecyclerView位于最底部，数据索引的最后一条
								if (data != null) {

									mRecyclerChat.scrollToPosition(data.size() - 1);
								}
							} else {
								Toast.makeText(DoctorChatActivity.this, message1, Toast.LENGTH_SHORT).show();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});


	}

	private void getHeightDialog(final List<String> list) {
		OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int option2, int options3, View v) {
				//返回的分别是三个级别的选中位置
				String s = list.get(options1);
// 这是发送答案聊天
				mSendMsg(s, "2", "3");

			}
		})
				.setSubCalSize(20)//确定和取消文字大小
				.setSubmitColor(Color.BLUE)//确定按钮文字颜色
				.setCancelColor(Color.BLUE)//取消按钮文字颜色
				.setTextColorCenter(Color.BLACK)//设置选中项的颜色
				.build();
		pvOptions.setKeyBackCancelable(true);
		pvOptions.setPicker(list);
		pvOptions.show();


	}


	/**
	 * 点击事件
	 *
	 * @param position
	 */
	@Override
	public void onItemClick(int position) {
		ChatMessageBean.DataBean.VoteAskBean.ValScopeBean valScopeBean = strBeanList.get(position);

		listStr = valScopeBean.getName();


	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
			//添加图片返回
			if (data != null && requestCode == REQUEST_CODE_SELECT) {
				images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				if (images != null) {
					//                    selImageList.addAll(images);

					mSendMsg("", "2", "1");

				}
			}
		} else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
			//预览图片返回
			if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
				images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
				if (images != null) {


				}
			}
		}

	}
}
