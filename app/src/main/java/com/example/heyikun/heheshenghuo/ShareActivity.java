package com.example.heyikun.heheshenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.Constants.APP_ID;

/**
 * Created by hyk on 2017/10/24.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/24
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ShareActivity extends BaseActivity {
    @BindView(R.id.Share_mBut)
    Button ShareMBut;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.Linear)
    LinearLayout Linear;
    private ImageView imageView;
    private IWXAPI api;

    private int i = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (i < 6) {
                i = 0;
            } else {

                switch (i) {
                    case 1:

                        image.setImageResource(R.drawable.shoutaiyangxiaochangjing_4woman);

                        break;
                    case 2:
                        image.setImageResource(R.drawable.shouyangmingdachangjing_4woman);

                        break;
                    case 3:
                        image.setImageResource(R.drawable.shouyangmingdachangjing_4man);

                        break;
                    case 4:
                        image.setImageResource(R.drawable.shoutaiyinfeijing_4woman);

                        break;
                    case 5:
                        image.setImageResource(R.drawable.zushaoyangdanjing_man);

                        break;
                    case 6:
                        image.setImageResource(R.drawable.zuyangmingweijing_4woman);

                        break;

                }
                Linear.invalidate();
            }
        }


    };
    private Timer timer;

    @Override
    protected int layoutId() {
        return R.layout.activity_share_umeng;
    }

    @Override
    protected void initView() {
        regToWx();

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                i++;
                Message message = new Message();
                message.what = i;
                handler.sendMessage(message);


            }
        }, 0, 500);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    //注册应用id到微信
    private void regToWx() {
        //通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        //将应用的appId注册到微信
        api.registerApp(APP_ID);
    }

    /**
     * 注：如果是分享到朋友圈，scene = SendMessageToWX.Req.WXSceneTimeline；如果分享到好友，
     * scene = SendMessageToWX.Req.WXSceneSession。
     */
    @OnClick(R.id.Share_mBut)
    public void onViewClicked() {

    }




}
