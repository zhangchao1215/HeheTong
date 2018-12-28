package com.example.heyikun.heheshenghuo.controller.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppManager;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.SPUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.modle.util.AESUtils.Encrypt;
import static java.security.AccessController.getContext;

/**
 * 类描述:   合和生活的设置
 * 创建人: Administrator
 * 创建时间: 2017/10/5 18:14
 * 修改人:  张超
 * 修改内容:  关于合和生活以及清理缓存 ，消息提醒等
 * 修改时间:
 */
//这是设置页面  你是congnageyem
public class UserSetSettingActivity extends BaseActivity {
    @BindView(R.id.mImage_Back)
    ImageView mImageBack;
    @BindView(R.id.Clear_cache)
    RelativeLayout ClearCache;
    @BindView(R.id.About_HeheLife)
    RelativeLayout AboutHeheLife;
    @BindView(R.id.set_exit_login)
    Button setExitLogin;

    @Override
    protected int layoutId() {
        return R.layout.activity_set_setting;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.mImage_Back, R.id.Clear_cache, R.id.About_HeheLife, R.id.set_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mImage_Back:

                finish();
                break;
            case R.id.Clear_cache:


                break;
            case R.id.About_HeheLife:
                Intent intent = new Intent(this, UserAboutHeheActivity.class);
                startActivity(intent);

                break;

            case R.id.set_exit_login:



                 /*

                  */


                //                dialog();


                //                dialog();
                //
                //                outLogin();

                showDialog(this);
                break;
        }
    }


    public void showDialog(Activity activity) {
        //AlertDialog的创建用到AlertDialog.Builder，AlertDialog.Builder构造函数中的Context必须传Activity的实例(先记着)
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //设置对话框标题，该标题会显示在标题区域中
        builder.setTitle("确定要退出吗")
                //设置对话框图标，该标题会显示在标题区域中
                .setIcon(null)
                //setMessage方法中的内容会显示在内容区域中
                /*以下三个setXXXButton(CharSequence text, final OnClickListener listener)方法
                   都向对话框的按钮区域添加了一个按钮，方法的第一个参数是按钮文本，第二个是按钮点击监听器。
                   注意按钮的顺序和代码的添加顺序无关，按钮的位置是固定的(如图1)，只有调用了对应的setXXXButton()
                   方法该按钮才显示。
                */
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        outLogin();
                        SPUtils.remove(UserSetSettingActivity.this, "token");
                        //                SPUtils.remove(this, "user_id");
                        SPUtils.clear(UserSetSettingActivity.this);
                        Intent Login = new Intent(UserSetSettingActivity.this, LoginActivity.class);
                        Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Login);

                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                })
                //真正实例化AlertDialog对象
                .create()
                //显示对话框
                .show();
    }

    /**
     * 退出登录
     */

    private void outLogin() {
        String user_id = AppUtils.get().getString("user_id", "");

        String encryptUserId = null;

        try {
            encryptUserId = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "LoginOut");
        params.put("action", encryptUserId);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("HeHeUserFragment", "退出登录   " + data);

                Gson gson = new Gson();
                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (bean == null || data == null) {
                    return;
                } else if (bean.getStatus().equals("200")) {

//                    SPUtils.remove(UserSettingActivity.this, "token");
//                    SPUtils.clear(getContext());
                }


            }

            @Override
            public void onError(String error) {

            }
        });

    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    SPUtils.remove(UserSetSettingActivity.this, "token");
                    Intent intent = new Intent(UserSetSettingActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    dialog.dismiss();

                }


            }
        });
        //有模拟机吗  我看一下效果
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
