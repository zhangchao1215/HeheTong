package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by hyk on 2017/9/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/9/28
 * <p>
 * 3：类描述： 进行验证密保
 * <p>
 * 4:类功能： 进行替换显示
 */

public class UserMiBaoVerifyFragment extends BaseFragment {
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
    Unbinder unbinder;
    private PopupWindow ppw;
    private PopupWindow popupWindow;
    private ListView listView;

    private String[] MiBaoqeustion = {"您最喜欢的历史人物姓名是", "您家的地址是", "您配偶的姓名是？", "您小学班主任的姓名是",
            "您第一次旅行的目的地是哪里？", "您暗恋的第一个人的姓名是？", "您大学的宿舍号是？ ",
            "对您影响最大人的姓名是？", "您的第一个宠物叫什么? ", "您的工号是？ "};

    private List<String> mList;
    private int mquestionid;
    private String questionEditOne;
    private String questionEditTwo;
    private String questionEditThree;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_include_mibao_message;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        init();
        manager = getFragmentManager();

        transaction = manager.beginTransaction();

    }

    @Override
    protected void initListener() {

    }

    private void init() {
        mList = new ArrayList<>();
        for (int i = 0; i < MiBaoqeustion.length; i++) {

            mList.add(MiBaoqeustion[i]);

        }
        //使EditText不可编辑
        MiBaoAnswerOneEdit.setEnabled(false);
        MiBaoAnswerTwoEdit.setEnabled(false);
        MiBaoAnswerThreeEdit.setEnabled(false);
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


    @OnClick({R.id.MiMao_question_One, R.id.MiMao_question_Two, R.id.MiMao_question_Three, R.id.MiBao_mButSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MiMao_question_One:
                showPopupWindow(view);
                break;
            case R.id.MiMao_question_Two:
                showPopupWindow(view);
                break;
            case R.id.MiMao_question_Three:
                showPopupWindow(view);
                break;
            case R.id.MiBao_mButSubmit:

                questionEditOne = MiBaoAnswerOneEdit.getText().toString().trim();
                questionEditTwo = MiBaoAnswerTwoEdit.getText().toString().trim();
                questionEditThree = MiBaoAnswerThreeEdit.getText().toString();

                if (questionEditOne.isEmpty()) {
                    Toast.makeText(getContext(), "问题一不能为空", Toast.LENGTH_SHORT).show();
                } else if (questionEditTwo.isEmpty()) {
                    Toast.makeText(getContext(), "问题二不能为空", Toast.LENGTH_SHORT).show();
                } else if (questionEditThree.isEmpty()) {
                    Toast.makeText(getContext(), "问题三不能为空", Toast.LENGTH_SHORT).show();
                } else {
//                    mPopWindow();
                    //在这一步对已经填写的问题进行验证，如果不对就不让替换到下一页面，进行验证手机

                    transaction.replace(R.id.mEmail_FrameLayout, new UserSettingNewNumberFragment());
                    transaction.commit();

                }

                break;
        }
    }


    // TODO: 2017/9/27 弹出ppw,并给里面的listview设置点击事件 。
    private void showPopupWindow(View v) {

        mquestionid = v.getId();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_question_listview, null);

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

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_ppw_iknow, null);

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
        popupWindow.showAtLocation(getView().findViewById(R.id.MiBao_ppw_TitleLinear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                backgroundAlpha(1f);
                Intent intent = new Intent(getContext(), AccountSecurityActivity.class);
                startActivity(intent);

            }
        });
        popupWindow.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);


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

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_mibao_item, null);

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
