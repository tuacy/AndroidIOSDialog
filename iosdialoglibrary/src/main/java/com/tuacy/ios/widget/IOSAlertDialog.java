package com.tuacy.ios.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuacy.iosdialoglibrary.R;

public class IOSAlertDialog extends Dialog {

	private int          mScreenWidth;
	private int          mScreenHeight;
	private LinearLayout mLLayoutDialog;
	private TextView     mViewTitle;
	private TextView     mViewMsg;
	private Button       mViewNegative;
	private Button       mViewPositive;

	public IOSAlertDialog(Context context, int themeResId) {
		super(context, themeResId);

		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;

		View parentView = View.inflate(getContext(), R.layout.view_ios_alert_dialog, null);
		mLLayoutDialog = (LinearLayout) parentView.findViewById(R.id.l_layout_alert_dialog);
		mViewTitle = (TextView) parentView.findViewById(R.id.txt_ios_alert_title);
		mViewTitle.setVisibility(View.GONE);
		mViewMsg = (TextView) parentView.findViewById(R.id.txt_ios_alert_msg);
		mViewNegative = (Button) parentView.findViewById(R.id.btn_negative);
		mViewPositive = (Button) parentView.findViewById(R.id.btn_positive);

		setContentView(parentView);

		mLLayoutDialog.setLayoutParams(new FrameLayout.LayoutParams((int) (mScreenWidth * 0.85), FrameLayout.LayoutParams.WRAP_CONTENT));
	}

	public static class Builder {

		private Context        mContext;
		private IOSAlertDialog mDialog;

		public Builder(Context context) {
			mContext = context;
			mDialog = new IOSAlertDialog(mContext, R.style.AlertDialogStyle);
		}

		public Builder setTitle(String title) {
			mDialog.mViewTitle.setVisibility(View.VISIBLE);
			mDialog.mViewTitle.setText(title);
			return this;
		}

		public Builder setMessage(String message) {
			mDialog.mViewMsg.setVisibility(View.VISIBLE);
			mDialog.mViewMsg.setText(message);
			return this;
		}

		public Builder setNegative(String title) {
			mDialog.mViewNegative.setVisibility(View.VISIBLE);
			mDialog.mViewNegative.setText(title);
			return this;
		}

		public Builder setPositive(String title) {
			mDialog.mViewPositive.setVisibility(View.VISIBLE);
			mDialog.mViewPositive.setText(title);
			return this;
		}

		public IOSAlertDialog show() {
			mDialog.show();
			return mDialog;
		}
	}
}
