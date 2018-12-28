package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BigCastLookAllAdapterTwo;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastAllDescBean;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastAllLookBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by hyk on 2017/11/18.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/18
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：查看所有大咖
 */

public class AllBigCastActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.bigcastAll_recycler)
    RecyclerView bigcastAllRecycler;
    private List<BigCastAllDescBean> mList;
    private BigCastLookAllAdapterTwo allAdapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_all_bigcast;
    }

    @Override
    protected void initView() {

        mList = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);

        bigcastAllRecycler.setLayoutManager(manager);


    }

    @Override
    protected void initData() {
        AllBigcast();
    }

    @Override
    protected void initLisenter() {

    }


    private void AllBigcast() {
        Map<String, String> params = new HashMap<>();
        params.put("action", "AllBigcast");

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("AllBigCastActivity", data);
                Gson gson = new Gson();
                BigCastAllLookBean castAllLookBean = gson.fromJson(data, BigCastAllLookBean.class);
                List<BigCastAllLookBean.DataBean> data1 = castAllLookBean.getData();
                int size =    data1.size();
                for(int i = 0;i<size;i++){
                    BigCastAllLookBean.DataBean dataBean = data1.get(i);
                    List<BigCastAllLookBean.DataBean.BigMessBean> big_mess = dataBean.getBig_mess();
                    int datasize = big_mess.size();
                    BigCastAllDescBean bean = new BigCastAllDescBean();
                    bean.setType(0);
                    bean.setBig_name(dataBean.getBig_type());
                    mList.add(bean);
                    for(int j = 0;j<datasize;j++){
                        BigCastAllLookBean.DataBean.BigMessBean bigMessBean = big_mess.get(j);
                        BigCastAllDescBean bean1 = new BigCastAllDescBean();
                        bean1.setType(1);
                        bean1.setBig_name(bigMessBean.getBig_name());
                        bean1.setBig_desc(bigMessBean.getBig_desc());
                        bean1.setBig_pic(bigMessBean.getBig_pic());
                        bean1.setBig_id(bigMessBean.getBig_id());
                        mList.add(bean1);
                    }
                }


                allAdapter = new BigCastLookAllAdapterTwo(AllBigCastActivity.this,mList);

                allAdapter.setOnitemClick(new BigCastLookAllAdapterTwo.BigcastOnitemClick() {
                    @Override
                    public void onItemClick(int positon) {
                        BigCastAllDescBean bigMessBean = mList.get(positon);
                        Intent intent = new Intent(AllBigCastActivity.this, BigCastPeopleDetailActivity.class);
                        intent.putExtra("image", bigMessBean.getBig_pic());
                        intent.putExtra("desc", bigMessBean.getBig_desc());
                        intent.putExtra("name", bigMessBean.getBig_name());
                        intent.putExtra("id", bigMessBean.getBig_id());
                        startActivity(intent);

                    }
                });

                bigcastAllRecycler.setAdapter(allAdapter);


            }

            @Override
            public void onError(String error) {

            }
        });


    }


    @OnClick(R.id.Image_Back)
    public void onViewClicked() {
        finish();
    }
}
