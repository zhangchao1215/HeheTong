package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.GetMiBaoBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.media.CamcorderProfile.get;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/22 18:27
 * 修改人:  张超
 * 修改内容:  把设置的密保问题进行一次验证
 * 修改时间: 完成验证之后填出ppw
 */

public class MiBaoYanZhengMsg extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.mTitle_Text)
    TextView mTitleText;
    @BindView(R.id.MiBao_questionTextOne)
    TextView MiBaoQuestionTextOne;
    @BindView(R.id.MiBao_mTextSelectorOne)
    TextView MiBaoMTextSelectorOne;
    @BindView(R.id.MiMao_question_One)
    RelativeLayout MiMaoQuestionOne;
    @BindView(R.id.MiBao_answerOne_edit)
    EditText MiBaoAnswerOneEdit;
    @BindView(R.id.MiBao_questionTextTwo)
    TextView MiBaoQuestionTextTwo;
    @BindView(R.id.MiBao_mTextSelectorTwo)
    TextView MiBaoMTextSelectorTwo;
    @BindView(R.id.MiMao_question_Two)
    RelativeLayout MiMaoQuestionTwo;
    @BindView(R.id.MiBao_answerTwo_edit)
    EditText MiBaoAnswerTwoEdit;
    @BindView(R.id.MiBao_questionTextThree)
    TextView MiBaoQuestionTextThree;
    @BindView(R.id.MiBao_mTextSelectorThree)
    TextView MiBaoMTextSelectorThree;
    @BindView(R.id.MiMao_question_Three)
    RelativeLayout MiMaoQuestionThree;
    @BindView(R.id.MiBao_answerThree_edit)
    EditText MiBaoAnswerThreeEdit;
    @BindView(R.id.MiBao_mButSubmit)
    Button MiBaoMButSubmit;
    @BindView(R.id.MiBao_ppw_TitleLinear)
    LinearLayout MiBaoPpwTitleLinear;
    private String questionEditOne;
    private String questionEditTwo;
    private String questionEditThree;
    private String AESToken;

    private String user_id;
    private String token;
    private PopupWindow popupWindow;

    @Override
    protected int layoutId() {
        return R.layout.mibao_yanzheng_msg;
    }

    @Override
    protected void initView() {
        MiBaoMTextSelectorOne.setVisibility(View.GONE);
        MiBaoMTextSelectorThree.setVisibility(View.GONE);
        MiBaoMTextSelectorTwo.setVisibility(View.GONE);

        mTextIncludeTitle.setText("验证密保问题");
        mTextIncludeTitle.setTextColor(R.color.Black);
    }

    @Override
    protected void initData() {
        mRequest();
    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.Image_Back, R.id.MiBao_mButSubmit, R.id.MiMao_question_One, R.id.MiMao_question_Two, R.id.MiMao_question_Three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                onBackPressed();
                break;
            case R.id.MiMao_question_One:
                //                showPopupWindow(view);
                //                getData(stringOne, MiBaoQuestionTextOne);

                break;
            case R.id.MiMao_question_Two:
                //                showPopupWindow(view);
                //                getData(stringTwo, MiBaoQuestionTextTwo);
                break;
            case R.id.MiMao_question_Three:
                //                showPopupWindow(view);
                //                getData(stringThree, MiBaoQuestionTextThree);
                break;
            //验证密保问题
            case R.id.MiBao_mButSubmit:
                questionEditOne = MiBaoAnswerOneEdit.getText().toString().trim();
                questionEditTwo = MiBaoAnswerTwoEdit.getText().toString().trim();
                questionEditThree = MiBaoAnswerThreeEdit.getText().toString();

                if (questionEditOne.isEmpty()) {
                    Toast.makeText(this, "问题一不能为空", Toast.LENGTH_SHORT).show();
                } else if (questionEditTwo.isEmpty()) {
                    Toast.makeText(this, "问题二不能为空", Toast.LENGTH_SHORT).show();
                } else if (questionEditThree.isEmpty()) {
                    Toast.makeText(this, "问题三不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    //                    mPopWindow();
                    submitData(questionEditOne, questionEditTwo, questionEditThree);
                }


                break;
        }
    }

    /**
     * 获取设置后的三个问题
     */
    private int flag;

    private void mRequest() {
        //获取用户id 进行AES加密
        user_id = AppUtils.get().getString("user_id", "");


        token = AppUtils.get().getString("token", "");


        String userid = null;

        String encryptAppSign = null;

        try {

            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();

            String timestamp = TimeUtils.dataOne(currentTime_today);

            String TwoToken = user_id + "," + token + "," + timestamp;

            //userID进行AES加密
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

            //生成二次token 并进行加密

            AESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            String app_sign = "CheckSecurity" + timestamp + BaseUrl.AESKey;

            String encryptMd5 = MD5Utils.encrypt(app_sign);

            encryptAppSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> parmas = new HashMap<>();
        parmas.put("action", "CheckSecurity");
        parmas.put("user_id", userid);
        parmas.put("token", AESToken);
        parmas.put("app_sign", encryptAppSign);

        if (flag == 0) {


            OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, parmas, "", new MyCallBack() {


                @Override
                public void onSuccess(String data) {
                    Log.d("MiBaoYanZhengMsg", "获取的三个问题" + data);

                    Gson gson = new Gson();

                    GetMiBaoBean miBaoBean = gson.fromJson(data, GetMiBaoBean.class);

                    if (data == null || miBaoBean == null) {
                        return;
                    } else if (miBaoBean.getStatus().equals("200")) {
                        List<GetMiBaoBean.DataBean> dataBeen = miBaoBean.getData();

                        MiBaoQuestionTextOne.setText(dataBeen.get(0).getQuestions());
                        MiBaoQuestionTextTwo.setText(dataBeen.get(1).getQuestions());
                        MiBaoQuestionTextThree.setText(dataBeen.get(2).getQuestions());


                    } else {

                        Log.d("UserSettingMiBaoActivit", miBaoBean.getMessage());
                    }

                    flag = 1;
                }

                @Override
                public void onError(String error) {

                }
            });
        }

    }


    /**
     * 提交验证的三个问题是否正确
     *
     * @param editOne
     * @param editTwo
     * @param editThree
     */
    private void submitData(String editOne, String editTwo, String editThree) {
        JSONObject object = new JSONObject();
        //获取用户id 进行AES加密

        String encryptAppSignSet = null;
        String encryptJson = null;
        String userid = null;
        try {
            userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();

            String timestamp = TimeUtils.dataOne(currentTime_today);

            String TwoToken = user_id + "," + token + "," + timestamp;

            //生成二次token 并进行加密

            AESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            String app_sign = "CheckSecurity" + timestamp + BaseUrl.AESKey;

            String encryptMd5 = MD5Utils.encrypt(app_sign);

            encryptAppSignSet = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

            object.put(MiBaoQuestionTextOne.getText().toString(), editOne);
            object.put(MiBaoQuestionTextTwo.getText().toString(), editTwo);
            object.put(MiBaoQuestionTextThree.getText().toString(), editThree);

            String jsonresult = object.toString();

            Log.d("UserSettingMiBaoActivit", "生成的json数据 0" + jsonresult.toString());

            encryptJson = AESUtils.Encrypt(jsonresult, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "CheckSecurity");
        params.put("user_id", userid);
        params.put("token", AESToken);
        params.put("app_sign", encryptAppSignSet);
        params.put("answer", encryptJson);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("MiBaoYanZhengMsg", data);
                Gson gson = new Gson();
                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (!bean.getStatus().equals("200")) {
                    Toast.makeText(MiBaoYanZhengMsg.this, bean.getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    mPopWindow();
                    Toast.makeText(MiBaoYanZhengMsg.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(String error) {

            }
        });

    }

    // TODO: 2017/9/27 弹出ppw  提交设置的密保问题，在调回到最当初的页面

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_success_mibaoyanzheng_ppw, null);

        Button button = (Button) view.findViewById(R.id.mBut_iKnow);


        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        popupWindow.setOutsideTouchable(false);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);

        //popupwindow的弹出位置
        popupWindow.showAtLocation(findViewById(R.id.main_Linear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);

                //给Intent设置启动模式，让他直接返回到上级界面，不然就重新返回来了
                Intent intent = new Intent(MiBaoYanZhengMsg.this, AccountSecurityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);


            }
        });
        popupWindow.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

}
