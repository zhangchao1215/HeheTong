package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.MiBaoBean;
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

/**
 * Created by hyk on 2017/9/27.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/27
 * <p>
 * 3：类描述： 设置密保问题
 * <p>
 * 4:类功能：
 */
public class UserSettingMiBaoActivity extends BaseActivity {


    @BindView(R.id.MiMao_question_One)
    RelativeLayout MiMaoQuestionOne;
    @BindView(R.id.MiMao_question_Two)
    RelativeLayout MiMaoQuestionTwo;
    @BindView(R.id.MiMao_question_Three)
    RelativeLayout MiMaoQuestionThree;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.MiBao_answerOne_edit)
    EditText MiBaoAnswerOneEdit;
    @BindView(R.id.MiBao_answerTwo_edit)
    EditText MiBaoAnswerTwoEdit;
    @BindView(R.id.MiBao_answerThree_edit)
    EditText MiBaoAnswerThreeEdit;
    @BindView(R.id.MiBao_questionTextOne)
    TextView MiBaoQuestionTextOne;
    @BindView(R.id.MiBao_mTextSelectorOne)
    TextView MiBaoMTextSelectorOne;
    @BindView(R.id.MiBao_questionTextTwo)
    TextView MiBaoQuestionTextTwo;
    @BindView(R.id.MiBao_mTextSelectorTwo)
    TextView MiBaoMTextSelectorTwo;
    @BindView(R.id.MiBao_questionTextThree)
    TextView MiBaoQuestionTextThree;
    @BindView(R.id.MiBao_mTextSelectorThree)
    TextView MiBaoMTextSelectorThree;
    @BindView(R.id.MiBao_mButSubmit)
    Button MiBaoMButSubmit;
    @BindView(R.id.MiBao_SettingLinear)
    LinearLayout MiBaoSettingLinear;
    private PopupWindow ppw;
    private PopupWindow popupWindow;
    private ListView listView;

    private List<String> mList;
    private int mquestionid;
    private String questionEditOne;
    private String questionEditTwo;
    private String questionEditThree;
    private String userid;
    private String AESToken;
    private String encryptAppSign;
    private List<String> stringOne;
    private List<String> stringTwo;
    private List<String> stringThree;
    private String token;
    private String user_id;
    private String encryptAppSignSet;
    private String jsonresult = "";
    private String encryptJson;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_setting_mi_bao;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        mRequest();


    }

    @Override
    protected void initLisenter() {


    }

    private void init() {

        //使EditText不可编辑
        questionEditOne = MiBaoAnswerOneEdit.getText().toString().trim();
        questionEditTwo = MiBaoAnswerTwoEdit.getText().toString().trim();
        questionEditThree = MiBaoAnswerThreeEdit.getText().toString();

        //Edittext的滑动监听,让button变换颜色
        MiBaoAnswerOneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (MiBaoAnswerOneEdit.getText().length() > 0) {
                    MiBaoMButSubmit.setBackground(getResources().getDrawable(R.drawable.queding_but));

                } else {
                    MiBaoMButSubmit.setBackground(getResources().getDrawable(R.drawable.next_but));
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.MiMao_question_One, R.id.MiMao_question_Two, R.id.MiMao_question_Three, R.id.Image_Back, R.id.MiBao_mButSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();
                break;

            case R.id.MiMao_question_One:
                //                showPopupWindow(view);
                getData(stringOne, MiBaoQuestionTextOne, MiBaoMTextSelectorOne);

                break;
            case R.id.MiMao_question_Two:
                //                showPopupWindow(view);
                getData(stringTwo, MiBaoQuestionTextTwo, MiBaoMTextSelectorTwo);
                break;
            case R.id.MiMao_question_Three:
                //                showPopupWindow(view);
                getData(stringThree, MiBaoQuestionTextThree, MiBaoMTextSelectorThree);
                break;
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

    private void mRequest() {
        //获取用户id 进行AES加密
        user_id = AppUtils.get().getString("user_id", "");


        token = AppUtils.get().getString("token", "");


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
            String app_sign = "GetSecurity" + timestamp + BaseUrl.AESKey;

            String encryptMd5 = MD5Utils.encrypt(app_sign);

            encryptAppSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> parmas = new HashMap<>();
        parmas.put("action", "GetSecurity");
        parmas.put("user_id", userid);
        parmas.put("token", AESToken);
        parmas.put("app_sign", encryptAppSign);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, parmas, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("UserSettingMiBaoActivit", data);

                Gson gson = new Gson();

                MiBaoBean miBaoBean = gson.fromJson(data, MiBaoBean.class);

                if (data == null || miBaoBean == null) {
                    return;
                } else if (miBaoBean.getStatus().equals("200")) {
                    stringOne = miBaoBean.getData().get_$1();

                    stringTwo = miBaoBean.getData().get_$2();

                    stringThree = miBaoBean.getData().get_$3();

                } else {

                    Log.d("UserSettingMiBaoActivit", miBaoBean.getMessage());
                }


            }

            @Override
            public void onError(String error) {

            }
        });

    }

    /**
     * 设置弹出的数据
     *
     * @param list     数据源
     * @param textView 设置在文本上
     * @param tv       更改另一个文本中的内容
     */
    private void getData(final List<String> list, final TextView textView, final TextView tv) {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String s = list.get(options1);

                textView.setText(s); //这是把选择的数据设置上去，

                //在这里做网络请求，把更改的数据上传到后台

                tv.setText("更改");

            }
        })
                //                                        .setSubmitText("确定")//确定按钮文字
                //                                        .setCancelText("取消")//取消按钮文字
                //                                        .setTitleText("城市选择")//标题
                .setSubCalSize(20)//确定和取消文字大小
                //                        .setTitleSize(20)//标题文字大小
                //                        .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                //                        .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
                //                                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                //                                        .setContentTextSize(18)//滚轮文字大小
                //                                        .setTextColorCenter(Color.BLUE)//设置选中项的颜色
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                //                                        .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                //                                        .setLinkage(false)//设置是否联动，默认true
                //                                        .setLabels("省", "市", "区")//设置选择的三级单位
                //                                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                //                                        .setCyclic(true, true, true)//循环与否
                //                                        .setSelectOptions(1, 1, 1)  //设置默认选中项
                //                                        .setOutSideCancelable(false)//点击外部dismiss default true
                //                        .isDialog(true)//是否显示为对话框样式
                .build();
        if (list.size() < 0 || list == null) {
            return;
        }
        pvOptions.setPicker(list);
        pvOptions.show();


    }


    private void submitData(String editOne, String editTwo, String editThree) {
        JSONObject object = new JSONObject();

        try {

            //获取时间戳
            String currentTime_today = TimeUtils.getCurrentTime_Today();

            String timestamp = TimeUtils.dataOne(currentTime_today);

            String TwoToken = user_id + "," + token + "," + timestamp;

            //生成二次token 并进行加密

            AESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);

            //生成签名并进行加密 先MD5 在进行AES
            String app_sign = "SetSecurity" + timestamp + BaseUrl.AESKey;

            String encryptMd5 = MD5Utils.encrypt(app_sign);

            encryptAppSignSet = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);

            object.put(MiBaoQuestionTextOne.getText().toString(), editOne);
            object.put(MiBaoQuestionTextTwo.getText().toString(), editTwo);
            object.put(MiBaoQuestionTextThree.getText().toString(), editThree);

            jsonresult = object.toString();

            Log.d("UserSettingMiBaoActivit", "生成的json数据 0" + jsonresult.toString());

            encryptJson = AESUtils.Encrypt(jsonresult, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "SetSecurity");
        params.put("user_id", userid);
        params.put("token", AESToken);
        params.put("app_sign", encryptAppSignSet);
        params.put("questions", encryptJson);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("UserSettingMiBaoActivit", "提交的密保问题  " + data);
                Gson gson = new Gson();
                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (!bean.getStatus().equals("200")) {
                    Toast.makeText(UserSettingMiBaoActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    mPopWindow();
                    Toast.makeText(UserSettingMiBaoActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();


                }


            }

            @Override
            public void onError(String error) {

            }
        });

    }


    // TODO: 2017/9/27 弹出ppw,并给里面的listview设置点击事件 。
    private void showPopupWindow(View v) {

        mquestionid = v.getId();

        View view = LayoutInflater.from(this).inflate(R.layout.activity_question_listview, null);

        listView = (ListView) view.findViewById(R.id.MiBao_Listview);

        listView.setAdapter(new MyListView(mList));


        ppw = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        //设置背景颜色
        ppw.setBackgroundDrawable(new BitmapDrawable());

        //设置外部可点击
        ppw.setOutsideTouchable(true);

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);

        ppw.showAsDropDown(v, 50, 0, Gravity.CENTER);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String str = mList.get(position);


                if (mquestionid == R.id.MiMao_question_One) {
                    MiBaoQuestionTextOne.setText(str);

                    MiBaoAnswerOneEdit.setEnabled(true);
                } else if (mquestionid == R.id.MiMao_question_Two) {

                    MiBaoQuestionTextTwo.setText(str);
                    MiBaoAnswerTwoEdit.setEnabled(true);

                } else if (mquestionid == R.id.MiMao_question_Three) {

                    MiBaoQuestionTextThree.setText(str);

                    MiBaoAnswerThreeEdit.setEnabled(true);
                }

                ppw.dismiss();
            }
        });

    }

    // TODO: 2017/9/27 弹出ppw  提交设置的密保问题，在调回到最当初的页面

    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_ppw_iknow, null);

        Button button = (Button) view.findViewById(R.id.mBut_IKnow);

        TextView Tv = (TextView) view.findViewById(R.id.mText_LoginPwd);

        Tv.setText("请牢记您的密保答案");

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
        popupWindow.showAtLocation(findViewById(R.id.MiBao_SettingLinear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);

                //给Intent设置启动模式，让他直接返回到上级界面，不然就重新返回来了
                Intent intent = new Intent(UserSettingMiBaoActivity.this, AccountSecurityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("SuccessAnswer","已设置");
                startActivityForResult(intent,100);


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

    // TODO: 2017/9/27 listview的内部类适配器
    class MyListView extends BaseAdapter {
        private List<String> list;

        public MyListView(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHodler hodler = null;


            if (convertView == null) {

                hodler = new MyHodler();

                convertView = LayoutInflater.from(UserSettingMiBaoActivity.this).inflate(R.layout.activity_user_mibao_item, null);

                hodler.mText = (TextView) convertView.findViewById(R.id.MiBao_questionItemText);

                convertView.setTag(hodler);
            } else {
                hodler = (MyHodler) convertView.getTag();
            }

            String s = mList.get(position);

            hodler.mText.setText(s);

            return convertView;
        }

        class MyHodler {
            private TextView mText;
        }

    }


}
