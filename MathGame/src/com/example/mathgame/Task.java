package com.example.mathgame;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

public class Task extends Activity implements OnClickListener {
	
	String question;
	Random randomGenerator = new Random();
	int operand1 = randomGenerator.nextInt(100);
	int operand2 = randomGenerator.nextInt(100);
	int result = resultInit();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task);
		
	    Bundle b = getIntent().getExtras(); 

	    int x = b.getInt("X");              
	    int y = b.getInt("Y");              
	    int xh = b.getInt("XH");            
	    int yh = b.getInt("YH");            

	    AnimationSet set = new AnimationSet(true);

	    DisplayMetrics displaymetrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	    int height = displaymetrics.heightPixels;
	    int width = displaymetrics.widthPixels;

	    Animation scale = new ScaleAnimation( (float)xh/width, 1f, (float)yh/height , 1f, x, y);
	    Animation alpha = new AlphaAnimation(.75f, 1f);
	    
	    set.addAnimation(scale);
	    set.addAnimation(alpha);
	    set.setDuration(300);
	    
	    View layout = (View) findViewById(android.R.id.content);
	    layout.startAnimation(set);
		
		Button btnTrue = (Button) findViewById(R.id.btnTrue);
		Button btnFalse = (Button) findViewById(R.id.btnFalse);
		btnTrue.setOnClickListener(this);
		btnFalse.setOnClickListener(this);
		TextView questionLabel = (TextView) findViewById(R.id.qwText);
		
		question = operand1 + " + " + operand2 + " = " + result + " ?";
		questionLabel.setText(question);
		
	}
	
	@Override
	public void finish() {
	
		Bundle b = getIntent().getExtras();
	    int x = b.getInt("X");
	    int y = b.getInt("Y");
	    int xh = b.getInt("XH");
	    int yh = b.getInt("YH");
	    
	    AnimationSet set = new AnimationSet(true);
	    
	    DisplayMetrics displaymetrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	    int height = displaymetrics.heightPixels;
	    int width = displaymetrics.widthPixels;
	    
	    Animation scale = new ScaleAnimation(1f, (float) xh/width, 1f, (float) yh/height, x, y);
	    Animation alpha = new AlphaAnimation(.75f, 1f);
	    
	    set.addAnimation(scale);
	    set.addAnimation(alpha);
	    set.setDuration(300);
	    
	    View layout = (View) findViewById(android.R.id.content);
	    layout.startAnimation(set); 
	    
	    super.finish();
	    overridePendingTransition(0, 0);
	}
	
	private int resultInit() {
		Random rnd = new Random();
		int res = rnd.nextInt(10);
		
		if (res < 5) {
			return operand1 + operand2;
		} else {
			return operand1 + operand2 + 5;
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		if (((operand1 + operand2) == result && v.getId() == R.id.btnTrue) || 
				((operand1 + operand2) != result && v.getId() == R.id.btnFalse)) {
					intent.putExtra("answer", true);
					setResult(RESULT_OK, intent);
					finish();
		} else {
			intent.putExtra("answer", false);
			setResult(RESULT_OK, intent);
			finish();
		}

	}
}
