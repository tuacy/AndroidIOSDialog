package com.tuacy.androidiosdialog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tuacy.ios.OnSheetItemClickListener;
import com.tuacy.ios.bean.SheetItem;
import com.tuacy.ios.widget.IOSAlertDialog;
import com.tuacy.ios.widget.IOSSheetDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private Context mContext;
	private Button  mBtnSheet;
	private Button  mBtnAlert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		initView();
		initEvent();
		initData();
	}

	private void initView() {
		mBtnSheet = (Button) findViewById(R.id.btn_sheet_ios_button);
		mBtnAlert = (Button) findViewById(R.id.btn_alert_ios_button);
	}

	private void initEvent() {
		mBtnSheet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<SheetItem> listSheetItems = new ArrayList<>();
				listSheetItems.add(new SheetItem("shabi001", 1));
				listSheetItems.add(new SheetItem("shabi002", 2));
				listSheetItems.add(new SheetItem("shabi003", 3));
				listSheetItems.add(new SheetItem("shabi004", 4));
				listSheetItems.add(new SheetItem("shabi005", 5));
				listSheetItems.add(new SheetItem("shabi006", 6));
				listSheetItems.add(new SheetItem("shabi007", 7));
				listSheetItems.add(new SheetItem("shabi008", 8));
				listSheetItems.add(new SheetItem("shabi009", 9));
				IOSSheetDialog.Builder builder = new IOSSheetDialog.Builder(mContext).setSheetItems(listSheetItems, mItemListener);
				builder.show();
			}
		});

		mBtnAlert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IOSAlertDialog.Builder builder = new IOSAlertDialog.Builder(mContext).setTitle("退出当前账号")
																					 .setMessage("退出当前账号，退出当前账号，退出当前账号，退出当前账号")
					.setNegative("取消").setPositive("确定");
				builder.show();
			}
		});
	}

	private void initData() {

	}

	private OnSheetItemClickListener mItemListener = new OnSheetItemClickListener() {
		@Override
		public void onClickItem(int which) {

		}
	};
}
