package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyk on 2017/9/14.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {
    public void setmList(List<T> mList) {
        this.mList = mList;
    }

    public List<T> getmList() {
        return mList;
    }

    protected List<T> mList;
    protected Context context;
    protected int layoutId;
    protected LayoutInflater inflater;
    //这个值用来指定某个用来设置监听的视图ID
    protected int itemId = 0;
    private OnItemClickListener clickListener;
    private OnTouchListener touchListener;

    public BaseRecyclerAdapter(List<T> mList, Context context, int layoutId) {
        this.mList = mList;
        this.context = context;
        this.layoutId = layoutId;
        inflater = inflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.getViewHolder(context, parent, layoutId);
    }

    //删除一条指定索引的item
    public void removeItem(int position) {
        if (mList != null && mList.size() > position) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    //设置一个用来设置监听的视图ID
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    //设置一个视图的监听事件
    public void setOnItemClickListener(int itemId, OnItemClickListener clickListener) {
        this.itemId = itemId;
        this.clickListener = clickListener;
    }

    //设置一个视图的滑动监听
    public void setOnTouchClickListener(OnTouchListener touchClickListener) {
        this.touchListener = touchClickListener;
    }

    //清空所有数据
    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }

    //添加一个集合
    public void addList(List<T> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        int data = mList.size();
        mList.addAll(list);
        notifyItemInserted(data + 1);
    }

    //添加一条数据
    public void addData(T t) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.add(t);
        notifyDataSetChanged();
    }

    //在指定索引处添加一条数据
    public void addData(T t, int position) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.add(position, t);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        convert(holder, mList.get(position), position);
        //当视图ID不为0的时候，设置监听回调,这个值可以通过继承以后在继承类里面指定ID，也可以通过set方法设置
        if (itemId != 0) {
            if (clickListener != null) {
                holder.setOnclickListener(itemId, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onClick(v, position);
                    }
                });
                holder.setOnLongClickListener(itemId, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return clickListener.onLongClick(v, position);
                    }
                });
            }
            if (touchListener != null) {
                holder.setOnTouchListener(itemId, new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return touchListener.onTouch(v, event, position);
                    }
                });
            }
        }
    }

    //用来设置视图的显示
    protected abstract void convert(ViewHolder holder, T t, int Position);

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else
            return mList.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> views;
        private View convertView;
        private Context context;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            convertView = itemView;
            views = new SparseArray<>();
        }

        public static ViewHolder getViewHolder(Context context, ViewGroup viewGroup, int layoutId) {
            View itemView = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
            return new ViewHolder(context, itemView);
        }

        //获得指定视图ID的View对象
        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = convertView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }

        //设置TextView的Text文本
        public ViewHolder setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        //得到TextView的Text文本
        public String getText(int viewId) {
            TextView tv = getView(viewId);
            return tv.getText().toString();
        }

        //设置文本颜色
        public ViewHolder setTextColor(int viewId, int color) {
            TextView tv = getView(viewId);
            tv.setTextColor(color);
            return this;
        }

        //设置ImageView的图片
        public ViewHolder setImageResource(int viewId, int resId) {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        //设置视图显示隐藏
        public ViewHolder setViewVisiable(int viewId, int visibility) {
            getView(viewId).setVisibility(visibility);
            return this;
        }

        //设置视图背景图片
        public ViewHolder setViewBackgroundResource(int viewId, int resId) {
            getView(viewId).setBackgroundResource(resId);
            return this;
        }

        //设置视图点击事件
        public ViewHolder setOnclickListener(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        //设置视图长按事件
        public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }

        //设置ViewGroup的单选事件
        public ViewHolder setOnCheckedChangeListener(int viewId, RadioGroup.OnCheckedChangeListener listener) {
            RadioGroup group = getView(viewId);
            group.setOnCheckedChangeListener(listener);
            return this;
        }

        public ViewHolder setDrawAbleLeft(int viewId, int DrawId) {
            TextView view = getView(viewId);
            Drawable drawable = context.getResources().getDrawable(DrawId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            view.setCompoundDrawables(drawable, null, null, null);
            return this;
        }

        public ViewHolder setDrawAbleRight(int viewId, int DrawId) {
            TextView view = getView(viewId);
            Drawable drawable = context.getResources().getDrawable(DrawId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            view.setCompoundDrawables(null, null, drawable, null);
            return this;
        }

        public ViewHolder setDrawAbleTop(int viewId, int DrawId) {
            TextView view = getView(viewId);
            Drawable drawable = context.getResources().getDrawable(DrawId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            view.setCompoundDrawables(null, drawable, null, null);
            return this;
        }

        public ViewHolder setDrawAbleBottom(int viewId, int DrawId) {
            TextView view = getView(viewId);
            Drawable drawable = context.getResources().getDrawable(DrawId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            view.setCompoundDrawables(null, null, null, drawable);
            return this;
        }

    }

    public static interface OnItemClickListener {
        void onClick(View v, int position);

        boolean onLongClick(View v, int position);

    }

    public static interface OnTouchListener {
        boolean onTouch(View v, MotionEvent event, int position);
    }
}