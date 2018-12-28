package com.example.heyikun.heheshenghuo.newlife;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.bean.newlife.SubscrbeBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MultiLineChooseLayout;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loction.choose.flowchooselibrary.listener.OnChooseItemClick;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by hyk on 2018/1/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/16
 * <p>
 * 3：类描述： 和合新版首页 订阅题目
 * <p>
 * 4:类功能：
 */

public class SubscribeActivity extends BaseActivity {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text_content)
    TextView textContent;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.multiChoose)
    MultiLineChooseLayout multiChoose;
    @BindView(R.id.textSize)
    TextView textSize;

    public static final String TAG = "SubscribeActivity";
    @BindView(R.id.mBut_confirm)
    ImageView mButConfirm;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;

    private List<String> stringList;
    List<Integer> multiChooseResult = new ArrayList<>();
    private List<SubscrbeBean.LabelBean> label;
    private String label_ids;

    @Override
    protected int layoutId() {
        return R.layout.activity_subscribe_timu;
    }

    @Override
    protected void initView() {
        mButConfirm.setClickable(false);
        mButConfirm.setImageResource(R.drawable.huidingyue4x);


        stringList = new ArrayList<>();
        //
        //        stringList.add("女性");
        //        stringList.add("挫折");
        //        stringList.add("男性");
        //        stringList.add("更年期");
        //        stringList.add("卵巢");
        //        stringList.add("职场");
        //        stringList.add("人才");
        //        stringList.add("谣言");
        //        stringList.add("杀伤力");
        //        stringList.add("性格");
        //        stringList.add("压力");
        //        stringList.add("能力");
        //        stringList.add("潜能");
        //        stringList.add("才能");
        //        stringList.add("梦想");
        //        stringList.add("未来");
        //        stringList.add("精神");
        //        chooseLayout.setList(stringList);


        final JSONObject object = new JSONObject();

        //
        //        multiChoose.setOnItemClickListener(new MultiLineChooseLayout.onItemClickListener() {
        //
        //            private int finalI;
        //
        //            @Override
        //            public void onItemClick(int position, String text) {
        //                mButConfirm.setClickable(true);
        //                mButConfirm.setImageResource(R.drawable.dingyue_but_man_4x);
        //
        //                multiChooseResult = multiChoose.getAllItemSelectedIndex();
        //                StringBuffer sb = new StringBuffer();
        //                int i = 0;
        //                for (Integer str : multiChooseResult) {
        //
        //                    sb.append(label.get(str).getLabel_name() + "/");
        //
        //                    try {
        //                        object.put(String.valueOf(label.get(str).getLabel_id()), String.valueOf(label.get(str).getLabel_state()));
        //
        //                    } catch (JSONException e) {
        //                        e.printStackTrace();
        //                    }
        //                    textSize.setText(i + "/6");
        //                    i++;
        //                }
        //                Log.e(TAG, object.toString());
        //
        //                label_ids = object.toString();
        //
        //                textContent.setText(sb.toString());
        //
        //                Log.e("SubscribeActivity", "sb:" + sb.toString());
        //                if (multiChooseResult != null && multiChooseResult.size() > 0) {
        //                    for (int i = 0; i < multiChooseResult.size(); i++) {
        //
        //
        //                        finalI = i;
        //                        if (finalI <= 5) {
        //                            textSelect += multiChooseResult.get(i) + " / ";
        //                            textSize.setText(finalI + 1 + "/6");
        //                            Log.d(TAG, "FinalI小于6个的:" + finalI);
        //
        //                        } else if (i > 5) {
        //                            Toast.makeText(SubscribeActivity.this, "最多选择六个", Toast.LENGTH_SHORT).show();
        //                            multiChoose.setIndexItemSelected(i, false);
        //                            multiChoose.setClickable(false);
        //                            Log.d(TAG, "FinalI这是大于六个的:" + i);
        //
        //                        }
        //                    }

        //                }


        //            }
        //        });

//        chooseLayout.setSecond(true);
//        chooseLayout.setOnChooseItemClick(new OnChooseItemClick() {
//            @Override
//            public void onItemDataListener(int position, View view, int type) {
//                mButConfirm.setClickable(true);
//                mButConfirm.setImageResource(R.drawable.dingyue_but_man_4x);
//                List<Integer> allCheckedIndex = chooseLayout.getAllCheckedIndex();
//
//                if (allCheckedIndex.size() > 6) {
//                    Toast.makeText(SubscribeActivity.this, "不能在选择了", Toast.LENGTH_SHORT).show();
//                    //                    chooseLayout.setClickable(false);
//
//                } else {
//
//                    StringBuffer sb = new StringBuffer();
//                    int i = 1;
//                    for (Integer str : allCheckedIndex) {
//
//                        sb.append(label.get(str).getLabel_name() + "/");
//
//                        try {
//                            object.put(String.valueOf(label.get(str).getLabel_id()), String.valueOf(label.get(str).getLabel_state()));
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        textSize.setText(i + "/6");
//                        i++;
//                    }
//                    Log.e(TAG, object.toString());
//
//                    label_ids = object.toString();
//
//                    textContent.setText(sb.toString());
//
//                    Log.e("SubscribeActivity", "sb:" + sb.toString());
//
//                }
//            }
//        });


    }

    @Override
    protected void initData() {
        loadData();
    }

    @Override
    protected void initLisenter() {
        ImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void loadData() {
        String user_id = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");

        String aesuserid = null;
        String aesToken = null;

        String timeToday = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(timeToday);

        String tokenTwo = user_id + "," + token + "," + timeStamp;
        try {
            if (!user_id.equals("") && !token.equals("")) {

                aesuserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
                aesToken = AESUtils.Encrypt(tokenTwo, BaseUrl.AESKey);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "InserLabel");
        params.put("user_id", aesuserid);
        params.put("token", aesToken);
        params.put("label_ids", "");

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private SubscrbeBean subscrbeBean;

            @Override
            public void onSuccess(String data) {
                Log.d(TAG, data);

                Gson gson = new Gson();
                try {
                    subscrbeBean = gson.fromJson(data, SubscrbeBean.class);

                    if (subscrbeBean.getStatus().equals("200")) {

                        label = subscrbeBean.getLabel();

                        for (SubscrbeBean.LabelBean labelBean : label) {
                            stringList.add(labelBean.getLabel_name());
                            Log.d(TAG, "labelBean.getLabel_state():" + labelBean.getLabel_name());


                        }


                        for (String s : stringList) {
                            Log.d(TAG, "sssssss" + s);
                        }


//                        chooseLayout.setList(stringList);

                        //                    chooseLayout.setList(subscrbeBean.getLabel(), new CustomDataListener<SubscrbeBean.LabelBean>() {
                        //                        @Override
                        //                        public String setListItemData(SubscrbeBean.LabelBean labelBean) {
                        //                            Log.d(TAG, labelBean.getLabel_name());
                        //
                        //                            return labelBean.getLabel_name();
                        //                        }
                        //                    });
//
//                        chooseLayout.setSelectNum(6);
//                        chooseLayout.setMaxSelectListener(new FlowChooseLayout.MaxSelectListener() {
//                            @Override
//                            public void nonSelectItem() {
//                                Toast.makeText(SubscribeActivity.this, "最多选择六个", Toast.LENGTH_SHORT).show();
//                            }
//                        });
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });

    }


    //确定订阅
    @OnClick(R.id.mBut_confirm)
    public void onViewClicked() {
        mCommit();
    }


    private void mCommit() {

        String user_id = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");

        String aesuserid = null;
        String aesToken = null;

        String timeToday = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(timeToday);

        String tokenTwo = user_id + "," + token + "," + timeStamp;
        try {
            if (!user_id.equals("") && !token.equals("")) {

                aesuserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
                aesToken = AESUtils.Encrypt(tokenTwo, BaseUrl.AESKey);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "InserLabel");
        params.put("user_id", aesuserid);
        params.put("token", aesToken);
        params.put("label_ids", label_ids);
        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                //                .enqueue(new RawResponseHandler() {
                //                    @Override
                //                    public void onSuccess(int statusCode, String response) {
                //
                //                        Log.d(TAG, response);
                //
                //                    }
                //
                //                    @Override
                //                    public void onFailure(int statusCode, String error_msg) {
                //
                //                    }
                //                });
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override

                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {

                            Toast.makeText(SubscribeActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                            finish();

                        } else if (response.getStatus().equals("80")) {
                            Toast.makeText(SubscribeActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }

}
