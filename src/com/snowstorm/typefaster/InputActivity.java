package com.snowstorm.typefaster;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.rick.typespeed.R;

public class InputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		TextView oriText = (TextView)findViewById(R.id.originalText);
		//TODO: ignore text persistence currently
		oriText.setText("大量中文输入法的存在让用户无所适从或者忽视了最佳的选择，打字本来就是为了快捷方便，没有对快的衡量怎么能更全面地评价输入法的好坏呢？abcdefghijklmnopqrstuvwxyz012345678\nabcdefghijklmnopqrstuvwxyz012345678");
		
//		SpannableString ss = new SpannableString("红色打电话斜体删除线绿色下划线图片:.");
//	    ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //设置2-5的字符链接到电话簿，点击时触发拨号
//	    ss.setSpan(new URLSpan("tel:4155551212"), 2, 5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //设置9-11的字符为网络链接，点击时打开页面
//	    ss.setSpan(new URLSpan("http://www.hao123.com"), 9, 11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //设置13-15的字符点击时，转到写短信的界面，发送对象为10086
//	    ss.setSpan(new URLSpan("sms:10086"), 13, 15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //粗体
//	    ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //斜体
//	    ss.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 7, 10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	  //下划线
//	    ss.setSpan(new UnderlineSpan(), 10, 16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //设置文本内容到textView
//	    oriText.setText(ss);
//	    //不添加这一句，拨号，http，发短信的超链接不能执行.
//	    oriText.setMovementMethod(LinkMovementMethod.getInstance());
//		oriText.setClickable(true);
		EditText userInputText = (EditText)findViewById(R.id.userText);
		InputWatcher iw = new InputWatcher(oriText, userInputText);
		userInputText.addTextChangedListener(iw); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input, menu);
		return true;
	}

}

class InputWatcher implements TextWatcher {
	private TextView tv;
	private EditText et;
	
	public InputWatcher(TextView tv, EditText et) {
		this.tv = tv;
		this.et = et;
	}
	
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		System.out.println("after text Changed");
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		System.out.println("before text Changed");
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		System.out.println("text Changing");
		if(this.et.getText().length() == this.tv.getText().length()) {
			// TODO: finish, show result dialog
			System.out.println("User input completed");
		}
		if(count > 0) {
			List diffs = this.diff(this.tv.getText().toString(), this.et.getText().toString());
			SpannableString ss = new SpannableString(s.toString());
			for(int i = 0; i < diffs.size(); i++) {
				ss.setSpan(new ForegroundColorSpan(Color.RED), Integer.parseInt(diffs.get(i).toString()), 
						Integer.parseInt(diffs.get(i).toString()) + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			this.et.setText(ss);
//			this.et.getText().setSpan(new ForegroundColorSpan(Color.RED), start, start + count, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
	}
	
	private List diff(String oriText, String newText) {
		List wrongTexts = new ArrayList();
		for(int i = 0; i < newText.length(); i++) {
			if(newText.charAt(i) != oriText.charAt(i)) {
				wrongTexts.add(i);
			}
		}
		return wrongTexts;
	}
}
