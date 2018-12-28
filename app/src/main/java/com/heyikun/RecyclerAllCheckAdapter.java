package com.heyikun;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.heyikun.hehe.CheckBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/12/6 16:18
 * description：
 */

public class RecyclerAllCheckAdapter extends RecyclerView.Adapter<RecyclerAllCheckAdapter.MyHolder> {

	private ArrayList<CheckBean> mList;
	private Context context;
	private List<CheckBean> checkBeanList;

	public RecyclerAllCheckAdapter(ArrayList<CheckBean> mList, Context context) {
		this.mList = mList;
		this.context = context;
		checkBeanList = new ArrayList<>();
	}


	@Override
	public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, null);

		return new MyHolder(view);
	}

	@Override
	public void onBindViewHolder(final MyHolder holder, final int position) {

		final CheckBean checkBean = mList.get(position);

		holder.mainTitle.setText(checkBean.getName());

		// 默认为CheckBox从实体类中赋值
		holder.checkBox.setChecked(checkBean.isCheck());

		holder.mLinearItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				holder.checkBox.toggle();

				if (holder.checkBox.isChecked()) {

					checkBeanList.add(checkBean);

				} else {
					checkBeanList.remove(checkBean);
				}
				// 点击之后为实体类中CheckBox赋值
				checkBean.setCheck(holder.checkBox.isChecked());

			}
		});

	}


	@Override
	public int getItemCount() {
		return mList.size();
	}



	public List<CheckBean> getCheckBeanList() {
		return checkBeanList;
	}


	class MyHolder extends RecyclerView.ViewHolder {
		CheckBox checkBox;
		TextView mainTitle;
		LinearLayout mLinearItem;

		public MyHolder(View itemView) {
			super(itemView);
			mainTitle = (TextView) itemView.findViewById(R.id.text);
			checkBox = (CheckBox) itemView.findViewById(R.id.select_checkbox);
			mLinearItem = (LinearLayout) itemView.findViewById(R.id.mLinear_item);
		}
	}
}
