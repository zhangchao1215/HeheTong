package com.example.heyikun.heheshenghuo;

import android.os.Bundle;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.util.PayPwdEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hyk on 2017/12/26.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/26
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class EditActivity extends BaseActivity {

    @BindView(R.id.mytext)
    PayPwdEditText mytext;

    @Override
    protected int layoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected void initView() {
        mytext.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.colorAccent,
                R.color.black, 20);
        mytext.setShowPwd(true);
        mytext.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                Toast.makeText(EditActivity.this, str, Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


}
