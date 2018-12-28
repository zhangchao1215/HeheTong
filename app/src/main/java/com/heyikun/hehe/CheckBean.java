package com.heyikun.hehe;

import com.google.gson.annotations.Expose;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/12/11 14:43
 * description：
 */

public class CheckBean {
	private String name;
	@Expose
	private boolean isCheck;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean check) {
		isCheck = check;
	}
}
