package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.ChatMessageBean;

import java.util.List;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/30 10:17
 * description：
 */

public class ChatQuestionAdapter extends BaseRecyclerAdapter<ChatMessageBean.DataBean.VoteAskBean.ValScopeBean> {
	private OnTextItemClick onTextItemClick;
	private int posi = -1;

	public void setOnTextItemClick(OnTextItemClick onTextItemClick) {
		this.onTextItemClick = onTextItemClick;
	}

	public ChatQuestionAdapter(List<ChatMessageBean.DataBean.VoteAskBean.ValScopeBean> mList, Context context) {
		super(mList, context, R.layout.activity_chat_question_item);
	}

	@Override
	protected void convert(final ViewHolder holder, ChatMessageBean.DataBean.VoteAskBean.ValScopeBean valScopeBean, final int Position) {

		holder.setText(R.id.mText_name, valScopeBean.getName());

		final TextView mtext = holder.getView(R.id.mText_name);

		mtext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				//记录下当然的点击事件
				posi = holder.getAdapterPosition();

				mtext.setTextColor(context.getResources().getColor(R.color.blue));

				notifyDataSetChanged();


				onTextItemClick.onItemClick(Position);

			}
		});

		if(posi == Position){

			mtext.setTextColor(context.getResources().getColor(R.color.blue));

		}else {
			mtext.setTextColor(context.getResources().getColor(R.color.background));
		}

	}


	public interface OnTextItemClick {
		void onItemClick(int position);

	}

}
