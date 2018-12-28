package com.example.heyikun.heheshenghuo.controller.life;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.bean.MoneyShengJiBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by hyk on 2017/9/12.
 * <p>
 * 这是首页面的财富升级
 */

public class MoneyShengJiFragment extends BaseFragment {


    @BindView(R.id.Life_UserCode)
    TextView LifeUserCode;
    @BindView(R.id.Life_UserCodeText)
    TextView LifeUserCodeText;
    @BindView(R.id.Life_Money)
    TextView LifeMoney;
    @BindView(R.id.Life_MoneyText)
    TextView LifeMoneyText;
    @BindView(R.id.Life_KeYongTicekt)
    TextView LifeKeYongTicekt;
    @BindView(R.id.Life_Text)
    TextView LifeText;
    @BindView(R.id.Life_ZengZhiTicket)
    TextView LifeZengZhiTicket;
    @BindView(R.id.Life_ZengZhiTicketText)
    TextView LifeZengZhiTicketText;
    @BindView(R.id.Life_Vip)
    TextView LifeVip;
    @BindView(R.id.Life_VipText)
    TextView LifeVipText;
    @BindView(R.id.Life_HeHuoMoney)
    TextView LifeHeHuoMoney;
    @BindView(R.id.Life_HeHuoMoneyText)
    TextView LifeHeHuoMoneyText;
    @BindView(R.id.Life_DongJieTicket)
    TextView LifeDongJieTicket;
    @BindView(R.id.Life_DongJieTickerText)
    TextView LifeDongJieTickerText;
    @BindView(R.id.Life_guQuanCount)
    TextView LifeGuQuanCount;
    @BindView(R.id.Life_guQuanConutText)
    TextView LifeGuQuanConutText;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_life_money_upgrade;
    }

    @Override
    protected void initData() {

        mRequest();
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }


    private void mRequest() {
        String user_id = AppUtils.get().getString("user_id", "");

        Map<String, String> params = new HashMap<>();


        params.put("hehe_user_id", user_id);

        String url = "http://gupiao.heyishenghuo.com/getuserinfo";

        OkHttpUtils.getInstands().OKhttpGet(url, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {

                Log.d("MoneyShengJiFragment", data);


                Gson gson = new Gson();
                MoneyShengJiBean bean = gson.fromJson(data, MoneyShengJiBean.class);

                if (bean == null || data == null) {
                    return;
                } else {

                    LifeUserCodeText.setText(bean.getUser_code());
                    //合伙人收益
                    LifeHeHuoMoneyText.setText(bean.getHhrsy());

                    //可用资产券
                    LifeText.setText(bean.getKyzcj());

                    //冻结资产券
                    LifeDongJieTickerText.setText(bean.getDjzcj());

                    //股权数量
                    LifeGuQuanConutText.setText(bean.getGqsl());

                    //增值券
                    LifeZengZhiTicketText.setText(bean.getZzj());

                    //是否是VIP
                    LifeVipText.setText(bean.getNames());

                    //自己的钱
                    LifeMoneyText.setText(bean.getMoney());
                }
            }

            @Override
            public void onError(String error) {

            }
        });


    }


}
