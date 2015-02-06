package com.zhaohg.emojiview;

import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EmojiView extends LinearLayout {
	
	private EditText edit;
	private boolean initialized = false;
	private boolean isHidden;
	private int rowNum;
	private int colNum;
	
	public EmojiView(Context context) {
		super(context);
		this.initView();
	}
	
	public EmojiView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initView();
	}
	
	public EmojiView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.initView();
	}

	private void initView() {
		this.setOrientation(VERTICAL);
		this.hide();
	}
	
	public void init(EditText edit) {
		this.init(edit, 3, 7);
	}
	
	public void init(EditText edit, int rowNum, int colNum) {
		this.hide();
		this.edit = edit;
		this.rowNum = rowNum;
		this.colNum = colNum;
	}
	
	private void initPages() {
		EmojiResourceManager manager = EmojiResourceManager.getInstance();
		List<View> views = manager.getViews(rowNum, colNum);
		ViewPager viewPager = new ViewPager(this.getContext());
		viewPager.setAdapter(new EmojiPagerAdapter(views));
	}
	
	public void show() {
		if (!this.initialized) {
			initPages();
		}
		this.isHidden = false;
		this.setVisibility(VISIBLE);
	}
	
	public void hide() {
		this.isHidden = true;
		this.setVisibility(GONE);
	}
	
	public void toggle() {
		if (this.isHidden) {
			show();
		} else {
			hide();
		}
	}
}
