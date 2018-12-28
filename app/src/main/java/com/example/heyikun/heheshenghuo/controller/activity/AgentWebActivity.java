package com.example.heyikun.heheshenghuo.controller.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ImagePickerAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.PhotoImageAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.SendArticleBean;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.PermissionsChecker;
import com.example.heyikun.heheshenghuo.modle.util.SelectDialog;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.UriUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.tsy.sdk.myokhttp.builder.UpLoadImagesBuilder;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
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
 * Created by hyk on 2017/12/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class AgentWebActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private static final int REQUEST_VIDEO_CODE = 100;
    public static final int REQUEST_CODE = 1000;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.type_recycler)
    RecyclerView typeRecycler;
    @BindView(R.id.edit_article)
    EditText editArticle;
    @BindView(R.id.send_shipinImage)
    ImageView sendShipinImage;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.but_image)
    ImageView but_image;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text_send)
    TextView textSend;
    @BindView(R.id.meiyong_text1)
    TextView meiyongText1;
    @BindView(R.id.xiushen_text2)
    TextView xiushenText2;
    @BindView(R.id.muying_text3)
    TextView muyingText3;
    @BindView(R.id.qiaomen_text4)
    TextView qiaomenText4;
    @BindView(R.id.fuwu_text5)
    TextView fuwuText5;
    @BindView(R.id.yundong_text6)
    TextView yundongText6;
    @BindView(R.id.miji_text7)
    TextView mijiText7;
    @BindView(R.id.title_edit)
    EditText titleEdit;
    private ArrayList<String> pathList = new ArrayList<>();
    private File file;
    private File videoFile;
    private File imagefile;
    private List<SendArticleBean.DataBean> dataBeanList;
    //    private SendArticelAdaoter adapter;
    private PhotoImageAdapter imageAdapter;
    private ArrayList<String> mPathList;
    private List<SendArticleBean.DataBean> data;
    private int cat_id = 8;
    private int imageFlag = -1;
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA
    };
    private PermissionsChecker checker;


    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private int maxImgCount = 9;               //允许选择图片最大数
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private ArrayList<ImageItem> images = null;


    @Override
    protected int layoutId() {
        return R.layout.activity_send_article;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        dataBeanList = new ArrayList<>();
        mPathList = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        typeRecycler.setLayoutManager(manager);
        checker = new PermissionsChecker(this);


    }

    @Override
    protected void initData() {
        getType();
        initImagePicker();
        initWidget();
    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.send_shipinImage, R.id.but_image, R.id.text_send
            , R.id.meiyong_text1, R.id.xiushen_text2, R.id.muying_text3,
            R.id.qiaomen_text4, R.id.fuwu_text5, R.id.yundong_text6, R.id.miji_text7
            , R.id.Image_Back

    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_shipinImage:
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent
                        , REQUEST_VIDEO_CODE);

                imageFlag = 0;
                break;
            case R.id.but_image:


                break;

            case R.id.text_send:


                String title = titleEdit.getText().toString().trim();
                String content = editArticle.getText().toString().trim();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(this, "输入的内容不能为空", Toast.LENGTH_SHORT).show();
                } else if (imageFlag == -1) {

                    Toast.makeText(this, "请选择上传视频或者图片", Toast.LENGTH_SHORT).show();
                } else if (imageFlag == 1) {
                    if (selImageList.size() == 0 || selImageList == null) {
                        Toast.makeText(this, "请选择图片", Toast.LENGTH_SHORT).show();
                    } else
                        UplodingImage(title, content);
                }


                break;
            case R.id.meiyong_text1:
                this.cat_id = Integer.parseInt(data.get(0).getCat_id());
                mDrawable(meiyongText1);
                mNoDrawble(xiushenText2, muyingText3, qiaomenText4, fuwuText5, yundongText6, mijiText7);

                break;
            case R.id.xiushen_text2:
                this.cat_id = Integer.parseInt(data.get(1).getCat_id());
                mDrawable(xiushenText2);
                mNoDrawble(meiyongText1, muyingText3, qiaomenText4, fuwuText5, yundongText6, mijiText7);
                break;
            case R.id.muying_text3:
                this.cat_id = Integer.parseInt(data.get(2).getCat_id());
                mDrawable(muyingText3);
                mNoDrawble(xiushenText2, meiyongText1, qiaomenText4, fuwuText5, yundongText6, mijiText7);
                break;
            case R.id.qiaomen_text4:
                this.cat_id = Integer.parseInt(data.get(3).getCat_id());
                mDrawable(qiaomenText4);
                mNoDrawble(xiushenText2, muyingText3, meiyongText1, fuwuText5, yundongText6, mijiText7);
                break;
            case R.id.fuwu_text5:
                this.cat_id = Integer.parseInt(data.get(4).getCat_id());
                mDrawable(fuwuText5);
                mNoDrawble(xiushenText2, muyingText3, qiaomenText4, meiyongText1, yundongText6, mijiText7);
                break;
            case R.id.yundong_text6:
                this.cat_id = Integer.parseInt(data.get(5).getCat_id());
                mDrawable(yundongText6);
                mNoDrawble(xiushenText2, muyingText3, qiaomenText4, fuwuText5, meiyongText1, mijiText7);
                break;
            case R.id.miji_text7:
                this.cat_id = Integer.parseInt(data.get(6).getCat_id());
                mDrawable(mijiText7);
                mNoDrawble(xiushenText2, muyingText3, qiaomenText4, fuwuText5, yundongText6, meiyongText1);
                break;
            case R.id.Image_Back:
                finish();
                break;
        }
    }


    private void mDrawable(TextView textView) {

        Drawable drawable = getResources().getDrawable(R.drawable.fankui_man_4x);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
        textView.setCompoundDrawablePadding(6);

        textView.setTextColor(getResources().getColor(R.color.colorTextYangXIn));
    }

    private void mNoDrawble(TextView text1, TextView text2, TextView text3, TextView text4, TextView text5, TextView text6) {

        text1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        text1.setTextColor(getResources().getColor(R.color.colorPPw));

        text2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        text2.setTextColor(getResources().getColor(R.color.colorPPw));

        text3.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        text3.setTextColor(getResources().getColor(R.color.colorPPw));

        text4.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        text4.setTextColor(getResources().getColor(R.color.colorPPw));

        text5.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        text5.setTextColor(getResources().getColor(R.color.colorPPw));

        text6.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        text6.setTextColor(getResources().getColor(R.color.colorPPw));


    }

    /**
     * 视频回调
     */
    String path;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_VIDEO_CODE) {


                Uri uri = data.getData();
                if ("file".equalsIgnoreCase(uri.getScheme())) {//使用第三方应用打开
                    path = uri.getPath();
                    text.setText(path);
                    Toast.makeText(this, path + "11111", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                    path = UriUtils.getPath(this, uri);
                    text.setText(path);
                    videoFile = new File(path);
                    Log.d("AgentWebActivity", "videoFile:" + videoFile);

                    textSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String title = titleEdit.getText().toString().trim();
                            String content = editArticle.getText().toString().trim();

                            if (imageFlag == 0) {

                                upLoadVideo(videoFile, title, content);
                            }
                        }
                    });
                }
            }

        }

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
    }

    private void upLoadVideo(File videofile, String title, String content) {
        AppUtils.dialog("正在上传中");

        if (videofile.getName().equals("") || videofile == null) {

            Toast.makeText(this, "上传的视频不能为空", Toast.LENGTH_SHORT).show();
        }

        String user_id = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");

        String aesuser = null;

        String aesToken = null;

        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timeStame = TimeUtils.dataOne(currentTime_today);


        try {
            aesuser = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            String mToken = user_id + "," + token + "," + timeStame;
            aesToken = AESUtils.Encrypt(mToken, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        UpLoadImagesBuilder builder = new UpLoadImagesBuilder(App.myOkHttp);

        builder.url(BaseUrl.BaseUrl)
                .addPamars("action", "UpMasArticle")
                .addPamars("user_id", aesuser)
                .addPamars("token", aesToken)
                .addPamars("article_id", "")
                .addPamars("cat_id", String.valueOf(cat_id))
                .addPamars("title", title)
                .addPamars("content", content)
                .addPamars("video_url", "")
                .addFile("video_url2", videofile) //上传视频的参数
                .enqueue(new GsonResponseHandler<SendArticleBean>() {
                    @Override
                    public void onSuccess(int statusCode, SendArticleBean response) {

                        if (response.getStatus().equals("200")) {
                            mIntent();
                            AppUtils.dismiss();

                            finish();
                        } else {
                            AppUtils.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

        //                .enqueue(new RawResponseHandler() {
        //                    @Override
        //                    public void onSuccess(int statusCode, String response) {
        //                        Log.d("AgentWebActivity", response);
        //                        AppUtils.dismiss();
        //
        //                    }
        //
        //                    @Override
        //                    public void onFailure(int statusCode, String error_msg) {
        //
        //                    }
        //                });


    }

    private void UplodingImage(String title, String content) {

        String user_id = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");

        String aesuser = null;

        String aesToken = null;

        String currentTime_today = TimeUtils.getCurrentTime_Today();

        String timeStame = TimeUtils.dataOne(currentTime_today);

        Map<String, Object> params = new HashMap<>();

        try {
            aesuser = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
            String mToken = user_id + "," + token + "," + timeStame;
            aesToken = AESUtils.Encrypt(mToken, BaseUrl.AESKey);

            params.put("action", "UpMasArticle");
            params.put("user_id", aesuser);
            params.put("token", aesToken);
            params.put("article_id", "");
            params.put("cat_id", String.valueOf(cat_id));
            params.put("title", title);
            params.put("content", content);

            Log.d("AgentWebActivity", "cat_id:" + cat_id);
            post_file_much(BaseUrl.BaseUrl, params, images);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    protected void post_file_much(final String url, final Map<String, Object> map, List<ImageItem> list) {
        if (list.size() < 0 || list == null) {
            Toast.makeText(this, "上传文章图片不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
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

                runOnUiThread(  new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        try {
                            SendArticleBean sendArticleBean = gson.fromJson(string, SendArticleBean.class);
                            if (sendArticleBean.getStatus().equals("200")) {

                                mIntent();
                                AppUtils.dismiss();


                            } else {
                                AppUtils.dismiss();
                                Toast.makeText(AgentWebActivity.this, sendArticleBean.getMessage(), Toast.LENGTH_SHORT).show();
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
        //

    }

    private void mIntent() {
        String caId = String.valueOf(cat_id);
        if (caId.equals(data.get(0).getCat_id())) {
            WebViewUtils.publicWebView(AgentWebActivity.this, data.get(0).getCat_link(), "美容瘦身");
        } else if (caId.equals(data.get(1).getCat_id())) {
            WebViewUtils.publicWebView(AgentWebActivity.this, data.get(1).getCat_link(), "修身养性");
        } else if (caId.equals(data.get(2).getCat_id())) {
            WebViewUtils.publicWebView(AgentWebActivity.this, data.get(2).getCat_link(), "母婴保健");
        } else if (caId.equals(data.get(3).getCat_id())) {
            WebViewUtils.publicWebView(AgentWebActivity.this, data.get(3).getCat_link(), "生活窍门");
        } else if (caId.equals(data.get(4).getCat_id())) {
            WebViewUtils.publicWebView(AgentWebActivity.this, data.get(4).getCat_link(), "养生厨房");
        } else if (caId.equals(data.get(5).getCat_id())) {
            WebViewUtils.publicWebView(AgentWebActivity.this, data.get(5).getCat_link(), "养生运动");
        } else if (caId.equals(data.get(6).getCat_id())) {
            WebViewUtils.publicWebView(AgentWebActivity.this, data.get(6).getCat_link(), "养生秘笈");
        }

        finish();
    }

    private void getType() {
        Map<String, String> params = new HashMap<>();
        params.put("action", "GetMasType");

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<SendArticleBean>() {
                    @Override
                    public void onSuccess(int statusCode, SendArticleBean response) {

                        if (response.getStatus().equals("200")) {

                            data = response.getData();
                            //                            dataBeanList.addAll(data);
                            //                            adapter = new SendArticelAdaoter(dataBeanList, AgentWebActivity.this);
                            //                            typeRecycler.setAdapter(adapter);

                            meiyongText1.setText(data.get(0).getCat_name());
                            xiushenText2.setText(data.get(1).getCat_name());
                            muyingText3.setText(data.get(2).getCat_name());
                            qiaomenText4.setText(data.get(3).getCat_name());
                            fuwuText5.setText(data.get(4).getCat_name());
                            yundongText6.setText(data.get(5).getCat_name());
                            mijiText7.setText(data.get(6).getCat_name());


                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

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
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
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
        imageFlag = 1;

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
                                Intent intent = new Intent(AgentWebActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(AgentWebActivity.this, ImageGridActivity.class);
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
