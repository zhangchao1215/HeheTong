package com.heyikun.hehe.wxapi;



//import com.umeng.socialize.weixin.view.WXCallbackActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.heyikun.heheshenghuo.Constants;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI api;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {

		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
		api.handleIntent(getIntent(), this);
		super.onCreate(savedInstanceState);

	}


	@Override
	public void onReq(BaseReq baseReq) {

	}

	@Override
	public void onResp(BaseResp baseResp) {

		Log.d("WXEntryActivity", "baseResp.errCode:" + baseResp.errCode);
	}
}
