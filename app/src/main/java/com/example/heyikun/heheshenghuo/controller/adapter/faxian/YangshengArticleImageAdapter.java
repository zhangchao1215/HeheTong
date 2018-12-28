package com.example.heyikun.heheshenghuo.controller.adapter.faxian;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ArticleCollectAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.MasterDetailBean;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;
import static com.alipay.sdk.app.statistic.c.G;
import static com.alipay.sdk.app.statistic.c.e;

/**
 * Created by hyk on 2017/12/19.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/19
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class YangshengArticleImageAdapter extends RecyclerView.Adapter {
    private List<MasterDetailBean.DataBean.ArticleBean> articleBeanList;
    private Context context;
    private View view;
    private ArticleOnClick articleOnClick;

    public void setArticleOnClick(ArticleOnClick articleOnClick) {
        this.articleOnClick = articleOnClick;
    }

    public YangshengArticleImageAdapter(List<MasterDetailBean.DataBean.ArticleBean> articleBeanList, Context context) {
        this.articleBeanList = articleBeanList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        switch (viewType) {

            case 0:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.activity_yangsheng_article_item1, null);
                view.setLayoutParams(params);

                holder = new MyHodleOne(view);

                break;


            case 1:

                view = LayoutInflater.from(context)
                        .inflate(R.layout.activity_yangsheng_article_item1, null);
                view.setLayoutParams(params);

                holder = new MyHodleOne(view);

                break;
            case 2:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.activity_yangsheng_article_item2, null);
                view.setLayoutParams(params);

                holder = new MyHodleTwo(view);

                break;
            case 3:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.activity_yangsheng_article_item3, null);
                view.setLayoutParams(params);

                holder = new MyHodleThree(view);

                break;
            case 4:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.activity_yangsheng_article_item4, null);
                view.setLayoutParams(params);

                holder = new MyHodleFour(view);

                break;
            case 5:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.activity_yangsheng_article_item5, null);
                view.setLayoutParams(params);

                holder = new MyHodleFive(view);

                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                MyHodleOne one = (MyHodleOne) holder;
                bindOne(one, position);
                break;

            case 1:

                MyHodleOne one1 = (MyHodleOne) holder;
                bindOne(one1, position);

                break;
            case 2:
                MyHodleTwo two = (MyHodleTwo) holder;

                bindTwo(two, position);

                break;
            case 3:
                MyHodleThree three = (MyHodleThree) holder;
                bindThree(three, position);
                break;
            case 4:
                MyHodleFour four = (MyHodleFour) holder;
                bindFour(four, position);
                break;
            case 5:

                MyHodleFive five = (MyHodleFive) holder;

                bindFive(five, position);
                break;


        }
    }

    @Override
    public int getItemViewType(int position) {

        List<String> article_pic = articleBeanList.get(position).getArticle_pic();

        int size = article_pic.size();

        if (size == 0) {
            return 0;
        }
        Log.d("YangshengArticleImageAd", "size:" + size);

        if (size == 1) {

            return 1;


        } else if (size == 2) {

            return 2;
        } else if (size == 3) {

            return 3;
        } else if (size == 4) {

            return 4;
        } else if (size == 5) {

            return 5;
        } else if (size == 6) {

            return 5;
        } else if (size == 7) {

            return 5;
        } else if (size == 8) {

            return 5;
        } else if (size == 9) {

            return 5;
        }


        return size;
    }

    @Override
    public int getItemCount() {
        return articleBeanList.isEmpty() ? 0 : articleBeanList.size();
    }


    private void bindOne(final MyHodleOne holder, final int position) {
        MasterDetailBean.DataBean.ArticleBean articleBean = articleBeanList.get(position);
        holder.time.setText(articleBean.getArticle_addtime());
        holder.title.setText(articleBean.getArticle_title());
        holder.content.setText(articleBean.getArticle_desc());
        holder.textName.setText(articleBean.getMaster_name());
        Glide.with(context)
                .load(articleBean.getMaster_headimg())
                .asBitmap()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(holder.headImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);

                        holder.headImage.setImageDrawable(drawable);
                    }
                });
        if (articleBean.getArticle_pic().size() == 0) {
            holder.ImageOne.setVisibility(View.GONE);
        } else if (articleBean.getArticle_pic().size() == 1) {

            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(0))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageOne);
        }

        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleOnClick.deleteOnitemClick(position);

            }
        });
    }


    private void bindTwo(final MyHodleTwo holder, final int position) {
        MasterDetailBean.DataBean.ArticleBean articleBean = articleBeanList.get(position);
        holder.time.setText(articleBean.getArticle_addtime());
        holder.title.setText(articleBean.getArticle_title());
        holder.content.setText(articleBean.getArticle_desc());
       holder.textName.setText(articleBean.getMaster_name());
        Glide.with(context)
                .load(articleBean.getMaster_headimg())
                .asBitmap()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(holder.headImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);

                        holder.headImage.setImageDrawable(drawable);
                    }
                });

        Glide.with(context)
                .load(articleBean.getArticle_pic().get(1))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageTwo);
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(0))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageOne);
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleOnClick.deleteOnitemClick(position);

            }
        });
    }

    private void bindThree(final MyHodleThree holder, final int position) {
        MasterDetailBean.DataBean.ArticleBean articleBean = articleBeanList.get(position);
        holder.time.setText(articleBean.getArticle_addtime());
        holder.title.setText(articleBean.getArticle_title());
        holder.content.setText(articleBean.getArticle_desc());
        holder.textName.setText(articleBean.getMaster_name());
        Glide.with(context)
                .load(articleBean.getMaster_headimg())
                .asBitmap()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(holder.headImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);

                        holder.headImage.setImageDrawable(drawable);
                    }
                });
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(0))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageOne);
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(1))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageTwo);
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(2))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageThree);
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleOnClick.deleteOnitemClick(position);

            }
        });
    }

    private void bindFour(final MyHodleFour holder, final int position) {
        MasterDetailBean.DataBean.ArticleBean articleBean = articleBeanList.get(position);
        holder.time.setText(articleBean.getArticle_addtime());
        holder.title.setText(articleBean.getArticle_title());
        holder.content.setText(articleBean.getArticle_desc());
        holder.textName.setText(articleBean.getMaster_name());
        Glide.with(context)
                .load(articleBean.getMaster_headimg())
                .asBitmap()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(holder.headImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);

                        holder.headImage.setImageDrawable(drawable);
                    }
                });
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(0))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageOne);
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(1))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageTwo);
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(2))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageThree);
        Glide.with(context)
                .load(articleBean.getArticle_pic().get(3))
                .placeholder(R.drawable.jcwz)
                .into(holder.ImageFour);
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleOnClick.deleteOnitemClick(position);

            }
        });
    }

    private void bindFive(final MyHodleFive holder, final int position) {
        MasterDetailBean.DataBean.ArticleBean articleBean = articleBeanList.get(position);
        holder.time.setText(articleBean.getArticle_addtime());
        holder.title.setText(articleBean.getArticle_title());
        holder.content.setText(articleBean.getArticle_desc());
        holder.textName.setText(articleBean.getMaster_name());
        Glide.with(context)
                .load(articleBean.getMaster_headimg())
                .asBitmap()
                .placeholder(R.drawable.jcwz)
                .into(new BitmapImageViewTarget(holder.headImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);

                        holder.headImage.setImageDrawable(drawable);
                    }
                });
        int size = articleBean.getArticle_pic().size();

        if (size == 5) {
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(1))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageTwo);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(0))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageOne);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(2))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageThree);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(3))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFour);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(4))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFive);

        } else if (size == 6) {

            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(1))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageTwo);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(0))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageOne);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(2))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageThree);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(3))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFour);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(4))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFive);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(5))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageSix);


        } else if (size == 7) {

            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(1))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageTwo);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(0))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageOne);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(2))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageThree);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(3))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFour);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(4))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFive);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(5))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageSix);

            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(6))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageSenve);


        } else if (size == 8) {
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(1))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageTwo);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(0))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageOne);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(2))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageThree);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(3))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFour);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(4))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFive);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(5))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageSix);

            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(6))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageSenve);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(7))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageEight);

        } else if (size == 9) {
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(1))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageTwo);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(0))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageOne);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(2))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageThree);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(3))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFour);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(4))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageFive);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(5))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageSix);

            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(6))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageSenve);
            Glide.with(context)
                    .load(articleBean.getArticle_pic().get(8))
                    .placeholder(R.drawable.jcwz)
                    .into(holder.ImageNine);

        }
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleOnClick.deleteOnitemClick(position);

            }
        });

    }


    private void lodaData() {


    }


    public class MyHodleOne extends RecyclerView.ViewHolder {
        private TextView time, title, content,textName;
        private ImageView headImage, ImageOne, deleteImage;

        public MyHodleOne(View v) {
            super(v);

            textName = (TextView) v.findViewById(R.id.master_article_Name);
            time = (TextView) v.findViewById(R.id.master_article_Time);
            title = (TextView) v.findViewById(R.id.master_article_title);
            content = (TextView) v.findViewById(R.id.master_article_content);
            deleteImage = (ImageView) v.findViewById(R.id.master_delete_article);
            headImage = (ImageView) v.findViewById(R.id.master_article_imageName);
            ImageOne = (ImageView) v.findViewById(R.id.master_article_imageOne);
        }
    }

    public class MyHodleTwo extends RecyclerView.ViewHolder {
        private TextView time, title, content,textName;
        private ImageView headImage, ImageOne, ImageTwo, deleteImage;

        public MyHodleTwo(View v) {
            super(v);
            textName = (TextView) v.findViewById(R.id.master_article_Name);
            time = (TextView) v.findViewById(R.id.master_article_Time);
            title = (TextView) v.findViewById(R.id.master_article_title);
            content = (TextView) v.findViewById(R.id.master_article_content);
            deleteImage = (ImageView) v.findViewById(R.id.master_delete_article);
            headImage = (ImageView) v.findViewById(R.id.master_article_imageName);
            ImageOne = (ImageView) v.findViewById(R.id.master_article_imageOne);
            ImageTwo = (ImageView) v.findViewById(R.id.master_article_imageTwo);
        }
    }

    public class MyHodleThree extends RecyclerView.ViewHolder {
        private TextView time, title, content,textName;
        private ImageView headImage, ImageOne, ImageTwo, ImageThree, deleteImage;

        public MyHodleThree(View v) {
            super(v);
            textName = (TextView) v.findViewById(R.id.master_article_Name);
            time = (TextView) v.findViewById(R.id.master_article_Time);
            title = (TextView) v.findViewById(R.id.master_article_title);
            content = (TextView) v.findViewById(R.id.master_article_content);
            deleteImage = (ImageView) v.findViewById(R.id.master_delete_article);
            headImage = (ImageView) v.findViewById(R.id.master_article_imageName);
            ImageOne = (ImageView) v.findViewById(R.id.master_article_imageOne);
            ImageTwo = (ImageView) v.findViewById(R.id.master_article_imageTwo);
            ImageThree = (ImageView) v.findViewById(R.id.master_article_imageThree);
        }
    }

    public class MyHodleFour extends RecyclerView.ViewHolder {
        private TextView time, title, content,textName;
        private ImageView headImage, ImageOne, ImageTwo, ImageThree, ImageFour, deleteImage;

        public MyHodleFour(View v) {
            super(v);
            textName = (TextView) v.findViewById(R.id.master_article_Name);
            time = (TextView) v.findViewById(R.id.master_article_Time);
            title = (TextView) v.findViewById(R.id.master_article_title);
            content = (TextView) v.findViewById(R.id.master_article_content);
            deleteImage = (ImageView) v.findViewById(R.id.master_delete_article);
            headImage = (ImageView) v.findViewById(R.id.master_article_imageName);
            ImageOne = (ImageView) v.findViewById(R.id.master_article_imageOne);
            ImageTwo = (ImageView) v.findViewById(R.id.master_article_imageTwo);
            ImageThree = (ImageView) v.findViewById(R.id.master_article_imageThree);
            ImageFour = (ImageView) v.findViewById(R.id.master_article_imageFour);
        }
    }

    public class MyHodleFive extends RecyclerView.ViewHolder {
        private TextView time, title, content,textName;
        private ImageView headImage, ImageOne, ImageTwo, ImageThree, ImageFour, ImageFive, ImageSix,
                ImageSenve, ImageEight, ImageNine, deleteImage;

        public MyHodleFive(View v) {
            super(v);
            textName = (TextView) v.findViewById(R.id.master_article_Name);
            time = (TextView) v.findViewById(R.id.master_article_Time);
            title = (TextView) v.findViewById(R.id.master_article_title);
            content = (TextView) v.findViewById(R.id.master_article_content);

            deleteImage = (ImageView) v.findViewById(R.id.master_delete_article);

            headImage = (ImageView) v.findViewById(R.id.master_article_imageName);
            ImageOne = (ImageView) v.findViewById(R.id.master_article_imageOne);
            ImageTwo = (ImageView) v.findViewById(R.id.master_article_imageTwo);
            ImageThree = (ImageView) v.findViewById(R.id.master_article_imageThree);
            ImageFour = (ImageView) v.findViewById(R.id.master_article_imageFour);
            ImageFive = (ImageView) v.findViewById(R.id.master_article_imageFive);
            ImageSix = (ImageView) v.findViewById(R.id.master_article_imageSix);
            ImageSenve = (ImageView) v.findViewById(R.id.master_article_imageSenve);
            ImageEight = (ImageView) v.findViewById(R.id.master_article_imageEight);
            ImageNine = (ImageView) v.findViewById(R.id.master_article_imageNine);
        }
    }

    public interface ArticleOnClick {
        void deleteOnitemClick(int position);

        void editonItemClick(int position);


    }
}
