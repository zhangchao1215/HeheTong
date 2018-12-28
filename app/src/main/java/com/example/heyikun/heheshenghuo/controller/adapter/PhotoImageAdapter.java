package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;

import java.util.List;

/**
 * Created by hyk on 2017/10/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/13
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class PhotoImageAdapter extends RecyclerView.Adapter<PhotoImageAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> result;
    private final static String TAG = "Adapter";

    public PhotoImageAdapter(Context context, List<String> result) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.activity_phone_imageitem, null);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(result.get(position))
                .centerCrop()
                .placeholder(R.drawable.tianjiazp4x)
                .into(holder.image);

    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.Photo_Image);
        }

    }

}
