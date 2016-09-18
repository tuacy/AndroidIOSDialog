package com.tuacy.ios.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tuacy.ios.OnSheetItemClickListener;
import com.tuacy.ios.bean.SheetItem;
import com.tuacy.ios.bean.SheetItemColor;
import com.tuacy.iosdialoglibrary.R;

import java.util.List;

public class IOSSheetDialog extends Dialog {

	private TextView     mViewTitle;
	private ScrollView   mSLayoutContent;
	private LinearLayout mLLayoutContent;
	private int          mScreenWidth;
	private int          mScreenHeight;

	private IOSSheetDialog(Context context, int themeResId) {
		super(context, themeResId);
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
		View parentView = View.inflate(getContext(), R.layout.view_ios_sheet_dialog, null);
		parentView.setMinimumWidth(mScreenWidth);
		setContentView(parentView);
		mViewTitle = (TextView) parentView.findViewById(R.id.txt_ios_sheet_title);
		mSLayoutContent = (ScrollView) parentView.findViewById(R.id.s_Layout_content);
		mLLayoutContent = (LinearLayout) parentView.findViewById(R.id.l_Layout_content);
		TextView viewCancel = (TextView) parentView.findViewById(R.id.txt_ios_sheet_cancel);
		viewCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		Window dialogWindow = getWindow();
		if (dialogWindow != null) {
			dialogWindow.setGravity(Gravity.START | Gravity.BOTTOM);
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			lp.x = 0;
			lp.y = 0;
			dialogWindow.setAttributes(lp);
		}
	}


	public static class Builder {

		private Context        mContext;
		private IOSSheetDialog mDialog;

		public Builder(Context context) {
			mContext = context;
			mDialog = new IOSSheetDialog(mContext, R.style.ActionSheetDialogStyle);
		}

		public Builder setOnCancelListener(OnCancelListener listener) {
			mDialog.setOnCancelListener(listener);
			return this;
		}

		public Builder setOnDismessListener(OnDismissListener listener) {
			mDialog.setOnDismissListener(listener);
			return this;
		}

		public Builder setTitle(String title) {
			mDialog.mViewTitle.setVisibility(View.VISIBLE);
			mDialog.mViewTitle.setText(title);
			return this;
		}

		public Builder setSheetItems(@NonNull List<SheetItem> items, final OnSheetItemClickListener itemLisenter) {
			int size = items.size();
			WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
			DisplayMetrics dm = new DisplayMetrics();
			windowManager.getDefaultDisplay().getMetrics(dm);
			int screenHeight = dm.heightPixels;
			// 添加条目过多的时候控制高度
			if (size >= 7) {
				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mDialog.mSLayoutContent.getLayoutParams();
				params.height = screenHeight / 2;
				mDialog.mSLayoutContent.setLayoutParams(params);
			}
			// 循环添加条目
			for (int i = 1; i <= size; i++) {
				final SheetItem sheetItem = items.get(i - 1);
				String strItem = sheetItem.getStrItemName();

				TextView textView = new TextView(mContext);
				textView.setText(strItem);
				textView.setTextSize(18);
				textView.setGravity(Gravity.CENTER);

				// 背景图片
				if (size == 1) {
					textView.setBackgroundResource(R.drawable.action_sheet_bottom_selector);
				} else {
					if (i >= 1 && i < size) {
						textView.setBackgroundResource(R.drawable.action_sheet_middle_selector);
					} else {
						textView.setBackgroundResource(R.drawable.action_sheet_bottom_selector);
					}
				}
				textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));

				// 高度
				float scale = mContext.getResources().getDisplayMetrics().density;
				int height = (int) (45 * scale + 0.5f);
				textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));

				// 点击事件
				textView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						itemLisenter.onClickItem(sheetItem.getITEM_CODE());
						mDialog.dismiss();
					}
				});
				mDialog.mLLayoutContent.addView(textView);
			}

			return this;
		}

		public IOSSheetDialog show() {
			mDialog.show();
			return mDialog;
		}
	}

}
