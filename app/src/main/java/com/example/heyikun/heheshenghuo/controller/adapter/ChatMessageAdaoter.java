package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.ChatMessageBean;
import com.example.heyikun.heheshenghuo.modle.util.ImageLoader;

import java.util.List;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/28 14:13
 * description：
 */

public class ChatMessageAdaoter extends RecyclerView.Adapter {
	private List<ChatMessageBean.DataBean> dataBeanList;
	private Context context;
	public static final int CHAT_TYPE_SNED = 2;
	public static final int CHAT_TYPE_RECEIVED = 0;

	public static final int CHAT_TYPE_SEND_IMAGE = 0X001;

	public ChatMessageAdaoter(List<ChatMessageBean.DataBean> dataBeanList, Context context) {
		this.dataBeanList = dataBeanList;
		this.context = context;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		switch (viewType) {

			case CHAT_TYPE_SNED:

				View sendView = LayoutInflater.from(context).inflate(R.layout.ease_row_sent_message, null);

				return new SendMsgHolder(sendView);

			case CHAT_TYPE_RECEIVED:

				View receivedView = LayoutInflater.from(context).inflate(R.layout.ease_row_received_message, null);
				return new ReceivedHolder(receivedView);

			case CHAT_TYPE_SEND_IMAGE:
				View imageView = LayoutInflater.from(context).inflate(R.layout.ease_row_sent_message_image, null);


				return new ImageHolder(imageView);
		}


		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		ChatMessageBean.DataBean dataBean = dataBeanList.get(position);

		if (holder instanceof SendMsgHolder) {
			//这个是自己发送的图片消息


			// 这是发来的文本消息 自己发送的消息
			((SendMsgHolder) holder).mText.setText(dataBean.getChat_content());
			ImageLoader.getInstance().displayCricleImage(context, dataBean.getSend_headimg(), ((SendMsgHolder) holder).mImage, R.drawable.ease_default_avatar);
		} else if (holder instanceof ReceivedHolder) {
			((ReceivedHolder) holder).mText.setText(dataBean.getChat_content());

			ImageLoader.getInstance().displayCricleImage(context, dataBean.getSend_headimg(), ((ReceivedHolder) holder).mImage, R.drawable.ease_default_avatar);

		}
		// 图片
		else if (holder instanceof ImageHolder) {

			ImageLoader.getInstance().displayCricleImage(context,dataBean.getSend_headimg(),((ImageHolder) holder).mImageHead,R.drawable.ease_default_avatar);

			Glide.with(context)
					.load(dataBean.getChat_content())
					.placeholder(R.drawable.jcwz)
					.into(((ImageHolder) holder).mIvImage);

		}


	}

	@Override
	public int getItemViewType(int position) {
		ChatMessageBean.DataBean dataBean = dataBeanList.get(position);
		String chat_sender = dataBean.getChat_sender();
		String chat_type = dataBean.getChat_type();
		// 发送的消息类型如果是2 的话就代表着是患者自己发送的
		if (chat_sender.equals("2")) {

			if (chat_type.equals("1")) {

				return CHAT_TYPE_SEND_IMAGE;
			}

			return CHAT_TYPE_SNED;
		}
		return CHAT_TYPE_RECEIVED;
	}

	@Override
	public int getItemCount() {
		return dataBeanList.size();
	}


	// 发送消息类型
	class SendMsgHolder extends RecyclerView.ViewHolder {
		private TextView mText;
		private ImageView mImage;

		public SendMsgHolder(View itemView) {
			super(itemView);
			mText = (TextView) itemView.findViewById(R.id.tv_chatcontent);
			mImage = (ImageView) itemView.findViewById(R.id.iv_userhead);
		}
	}

	// 收取类型
	class ReceivedHolder extends RecyclerView.ViewHolder {

		private TextView mText;
		private ImageView mImage;

		public ReceivedHolder(View itemView) {
			super(itemView);
			mText = (TextView) itemView.findViewById(R.id.tv_chatcontent);
			mImage = (ImageView) itemView.findViewById(R.id.iv_userhead);
		}


	}

	// 图片类型
	class ImageHolder extends RecyclerView.ViewHolder {
		private ImageView mIvImage, mImageHead;

		public ImageHolder(View itemView) {
			super(itemView);
			mIvImage = (ImageView) itemView.findViewById(R.id.iv_chatImage);
			mImageHead = (ImageView) itemView.findViewById(R.id.iv_userhead);

		}
	}


}
