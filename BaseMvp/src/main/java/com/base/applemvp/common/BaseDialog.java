/**
 * 公共父类弹出框
 */
package com.base.applemvp.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;


/**
 * @author applehsp
 */
public abstract class BaseDialog extends Dialog  {

	public Activity  activity;

	public  View contentView;

	public BaseDialog(Activity mActivity, int style) {
		super(mActivity, style);
		activity = mActivity;
		contentView=getLayoutInflater().inflate(layoutId(),null);
		setContentView(contentView);
		initView();
		initData();
	}



	@Override
	public void dismiss() {
		super.dismiss();
	}



	private float getDensity(Context context) {
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.density;
	}


	protected abstract int layoutId();

	protected abstract void initView();

	protected abstract void initData();

	/**
	 * 显示对话框
	 * */
	public void goShow() {
		this.setCanceledOnTouchOutside(true);
		getWindow().setLayout(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		show();
	}






}
