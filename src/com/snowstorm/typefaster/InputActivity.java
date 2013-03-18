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
		oriText.setText("�����������뷨�Ĵ������û������ʴӻ��ߺ�������ѵ�ѡ�񣬴��ֱ�������Ϊ�˿�ݷ��㣬û�жԿ�ĺ�����ô�ܸ�ȫ����������뷨�ĺû��أ�abcdefghijklmnopqrstuvwxyz012345678\nabcdefghijklmnopqrstuvwxyz012345678");
		
//		SpannableString ss = new SpannableString("��ɫ��绰б��ɾ������ɫ�»���ͼƬ:.");
//	    ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //����2-5���ַ����ӵ��绰�������ʱ��������
//	    ss.setSpan(new URLSpan("tel:4155551212"), 2, 5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //����9-11���ַ�Ϊ�������ӣ����ʱ��ҳ��
//	    ss.setSpan(new URLSpan("http://www.hao123.com"), 9, 11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //����13-15���ַ����ʱ��ת��д���ŵĽ��棬���Ͷ���Ϊ10086
//	    ss.setSpan(new URLSpan("sms:10086"), 13, 15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //����
//	    ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //б��
//	    ss.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 7, 10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	  //�»���
//	    ss.setSpan(new UnderlineSpan(), 10, 16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//	    //�����ı����ݵ�textView
//	    oriText.setText(ss);
//	    //�������һ�䣬���ţ�http�������ŵĳ����Ӳ���ִ��.
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
