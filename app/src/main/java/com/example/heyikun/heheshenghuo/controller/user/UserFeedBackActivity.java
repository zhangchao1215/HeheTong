package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.AgentWebActivity;
import com.example.heyikun.heheshenghuo.controller.activity.GlideImageLoader;
import com.example.heyikun.heheshenghuo.controller.adapter.ImagePickerFeedAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.SendArticleBean;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.SelectDialog;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/5 20:05
 * 修改人:  张超
 * 修改内容:  意见反馈类
 * 修改时间:
 */

public class UserFeedBackActivity extends BaseActivity implements ImagePickerFeedAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Text_Commit)
    TextView TextCommit;
    @BindView(R.id.mText_accountNumber_message)
    TextView mTextAccountNumberMessage;
    @BindView(R.id.Lines_accountNumber)
    ImageView LinesAccountNumber;
    @BindView(R.id.mText_small_instrument)
    TextView mTextSmallInstrument;
    @BindView(R.id.Lines_small_instrument)
    ImageView LinesSmallInstrument;
    @BindView(R.id.mText_FaTie)
    TextView mTextFaTie;
    @BindView(R.id.Lines_FaTie)
    ImageView LinesFaTie;
    @BindView(R.id.mText_order)
    TextView mTextOrder;
    @BindView(R.id.Lines_order)
    ImageView LinesOrder;
    @BindView(R.id.mText_JianCe)
    TextView mTextJianCe;
    @BindView(R.id.Lines_JianCe)
    ImageView LinesJianCe;
    @BindView(R.id.mText_other)
    TextView mTextOther;
    @BindView(R.id.Lines_other)
    ImageView LinesOther;
    @BindView(R.id.mEdit_Message)
    EditText mEditMessage;
    @BindView(R.id.mEdit_Text)
    TextView mEditText;
    @BindView(R.id.mText_Photo)
    ImageView mTextPhoto;
    @BindView(R.id.mText_ImageSize)
    TextView mTextImageSize;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //    private PhotoImageAdapter adapter;

    private ArrayList<String> path;

    private int FeedFlag = -1;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerFeedAdapter adapter;
    private int maxImgCount = 9;               //允许选择图片最大数
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private ArrayList<ImageItem> images = null;


    @Override
    protected int layoutId() {
        return R.layout.user_hehe_feedback;
    }

    @Override
    protected void initView() {
        path = new ArrayList<>();

        initImagePicker();
        initWidget();

        //        TextCommit.setTextColor(getResources().getColor(R.color.color_d8d8d8));
        //
        //        TextCommit.setClickable(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {


        //editText的内容监听
        mEditMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mEditText.setText(s.length() + "/400");

                if (s.length() > 7) {
                    TextCommit.setTextColor(getResources().getColor(R.color.colorTextYangXIn));
                    TextCommit.setClickable(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @OnClick({R.id.Image_Back, R.id.Text_Commit, R.id.mText_accountNumber_message, R.id.mText_small_instrument,
            R.id.mText_Photo, R.id.mText_FaTie, R.id.mText_order, R.id.mText_JianCe, R.id.mText_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;

            //把所有的问题进行提交
            case R.id.Text_Commit:

                String content = mEditMessage.getText().toString().trim();

                if (FeedFlag == -1) {
                    Toast.makeText(this, "请选择需要反馈的问题", Toast.LENGTH_SHORT).show();
                } else if (content.isEmpty()) {
                    Toast.makeText(this, "输入的内容不能为空", Toast.LENGTH_SHORT).show();
                } else if (selImageList.size() == 0 || selImageList == null) {
                    Toast.makeText(this, "上传图片不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    SummitMessage(content, FeedFlag);
                }


                break;

            //账号问题
            case R.id.mText_accountNumber_message:
                TextCommit.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                TextCommit.setClickable(true);

                FeedFlag = 1;

                mTextAccountNumberMessage.getResources().getColor(R.color.ZhenDuanText);
                LinesAccountNumber.setVisibility(View.VISIBLE);

                mTextSmallInstrument.getResources().getColor(R.color.Black);
                LinesSmallInstrument.setVisibility(View.GONE);

                mTextFaTie.getResources().getColor(R.color.Black);
                LinesFaTie.setVisibility(View.GONE);

                mTextOrder.getResources().getColor(R.color.Black);
                LinesOrder.setVisibility(View.GONE);

                mTextJianCe.getResources().getColor(R.color.Black);
                LinesJianCe.setVisibility(View.GONE);

                mTextOther.getResources().getColor(R.color.Black);
                LinesOther.setVisibility(View.GONE);
                break;

            //小工具使用问题
            case R.id.mText_small_instrument:
                TextCommit.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                TextCommit.setClickable(true);

                FeedFlag = 2;

                mTextSmallInstrument.getResources().getColor(R.color.ZhenDuanText);
                LinesSmallInstrument.setVisibility(View.VISIBLE);

                mTextFaTie.getResources().getColor(R.color.Black);
                LinesFaTie.setVisibility(View.GONE);

                mTextOrder.getResources().getColor(R.color.Black);
                LinesOrder.setVisibility(View.GONE);

                mTextJianCe.getResources().getColor(R.color.Black);
                LinesJianCe.setVisibility(View.GONE);

                mTextOther.getResources().getColor(R.color.Black);
                LinesOther.setVisibility(View.GONE);

                mTextAccountNumberMessage.getResources().getColor(R.color.Black);
                LinesAccountNumber.setVisibility(View.GONE);
                break;

            //发帖回复类问题
            case R.id.mText_FaTie:
                TextCommit.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                TextCommit.setClickable(true);

                FeedFlag = 3;

                mTextFaTie.getResources().getColor(R.color.ZhenDuanText);
                LinesFaTie.setVisibility(View.VISIBLE);

                mTextSmallInstrument.getResources().getColor(R.color.Black);
                LinesSmallInstrument.setVisibility(View.GONE);

                mTextOrder.getResources().getColor(R.color.Black);
                LinesOrder.setVisibility(View.GONE);

                mTextJianCe.getResources().getColor(R.color.Black);
                LinesJianCe.setVisibility(View.GONE);

                mTextOther.getResources().getColor(R.color.Black);
                LinesOther.setVisibility(View.GONE);

                mTextAccountNumberMessage.getResources().getColor(R.color.Black);
                LinesAccountNumber.setVisibility(View.GONE);
                break;

            //订单信息问题
            case R.id.mText_order:
                TextCommit.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                TextCommit.setClickable(true);

                FeedFlag = 4;

                mTextOrder.getResources().getColor(R.color.ZhenDuanText);
                LinesOrder.setVisibility(View.VISIBLE);


                mTextSmallInstrument.getResources().getColor(R.color.Black);
                LinesSmallInstrument.setVisibility(View.GONE);

                mTextJianCe.getResources().getColor(R.color.Black);
                LinesJianCe.setVisibility(View.GONE);

                mTextOther.getResources().getColor(R.color.Black);
                LinesOther.setVisibility(View.GONE);

                mTextAccountNumberMessage.getResources().getColor(R.color.Black);
                LinesAccountNumber.setVisibility(View.GONE);

                mTextFaTie.getResources().getColor(R.color.Black);
                LinesFaTie.setVisibility(View.GONE);
                break;

            //检测身体问题
            case R.id.mText_JianCe:
                TextCommit.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                TextCommit.setClickable(true);

                FeedFlag = 5;

                mTextJianCe.getResources().getColor(R.color.ZhenDuanText);
                LinesJianCe.setVisibility(View.VISIBLE);

                mTextSmallInstrument.getResources().getColor(R.color.Black);
                LinesSmallInstrument.setVisibility(View.GONE);

                mTextOrder.getResources().getColor(R.color.Black);
                LinesOrder.setVisibility(View.GONE);

                mTextOther.getResources().getColor(R.color.Black);
                LinesOther.setVisibility(View.GONE);

                mTextAccountNumberMessage.getResources().getColor(R.color.Black);
                LinesAccountNumber.setVisibility(View.GONE);

                mTextFaTie.getResources().getColor(R.color.Black);
                LinesFaTie.setVisibility(View.GONE);
                break;

            //其他反馈问题
            case R.id.mText_other:
                TextCommit.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                TextCommit.setClickable(true);

                FeedFlag = 6;

                mTextOther.getResources().getColor(R.color.ZhenDuanText);
                LinesOther.setVisibility(View.VISIBLE);

                mTextSmallInstrument.getResources().getColor(R.color.Black);
                LinesSmallInstrument.setVisibility(View.GONE);

                mTextOrder.getResources().getColor(R.color.Black);
                LinesOrder.setVisibility(View.GONE);

                mTextJianCe.getResources().getColor(R.color.Black);
                LinesJianCe.setVisibility(View.GONE);

                mTextAccountNumberMessage.getResources().getColor(R.color.Black);
                LinesAccountNumber.setVisibility(View.GONE);

                mTextFaTie.getResources().getColor(R.color.Black);
                LinesFaTie.setVisibility(View.GONE);
                break;
            case R.id.mText_Photo:
                TextCommit.setTextColor(getResources().getColor(R.color.ZhenDuanText));
                TextCommit.setClickable(true);


        }
    }

    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
                for (ImageItem image : images) {
                    Log.d("WxDemoActivity", image.path);
                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
        //            TextCommit.setOnClickListener(new View.OnClickListener() {
        //                @Override
        //                public void onClick(View v) {
        //                    String editContent = mEditMessage.getText().toString().trim();
        //                    if (editContent.isEmpty()) {
        //                        Toast.makeText(UserFeedBackActivity.this, "请填写内容", Toast.LENGTH_SHORT).show();
        //                    } else {
        //
        //                        pathList.clear();
        //
        //
        //                    }
        //
        //
        //                }
        //            });


    }


    private void SummitMessage(final String content, int type) {

        if (content.equals("")) {
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
        }

        final Map<String, Object> params = new HashMap<>();

        String user_id = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");

        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timestrap = TimeUtils.dataOne(currentTime_today);


        String AESId = null;
        String AESToken = null;
        String Twotoken = user_id + "," + token + "," + timestrap;

        try {
            AESId = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            AESToken = AESUtils.Encrypt(Twotoken, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

        params.put("action", "Feedback");
        params.put("user_id", AESId);
        params.put("token", AESToken);
        params.put("content", content);
        params.put("type", String.valueOf(type));
        post_file_much(BaseUrl.BaseUrl, params, images);

    }


    protected void post_file_much(final String url, final Map<String, Object> map, List<ImageItem> list) {

        AppUtils.dialog("正在上传中");

        int n = 0;
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < list.size(); i++) {
            File f = new File(list.get(i).path);
            if (f != null) {
                // MediaType.parse() 里面是上传的文件类型。
                RequestBody body = RequestBody.create(MediaType.parse("image/*"), f);
                String filename = f.getName();
                // 参数分别为， 请求key ，文件名称 ， RequestBody
                requestBody.addFormDataPart("file" + n, filename, body);


                n++;

                Log.d("AgentWebActivity", "n:" + n);
                Log.d("AgentWebActivity", filename);

            }
            if (map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    requestBody.addFormDataPart(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
                }
            }
        }

        Request request = new Request.Builder().url(url).post(requestBody.build()).tag(this).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        try {
                            SendArticleBean sendArticleBean = gson.fromJson(string, SendArticleBean.class);
                            if (sendArticleBean.getStatus().equals("200")) {
                                AppUtils.dismiss();
                                finish();
                            } else {
                                AppUtils.dismiss();
                                Toast.makeText(UserFeedBackActivity.this, sendArticleBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

                Log.d("AgentWebActivity", "上传的方式2  +" + string);
            }
        });


    }


    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000); //保存文件的高度。单位像素
    }

    private void initWidget() {

        selImageList = new ArrayList<>();
        adapter = new ImagePickerFeedAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onItemClick(View view, int position) {

        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(UserFeedBackActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(UserFeedBackActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
                                //                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }


}
