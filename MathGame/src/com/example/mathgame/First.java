package com.example.mathgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class First extends Activity implements OnClickListener {
	
	private TextView timer;
	private long startTime;
	private int buttonCounter = 9;
	private int rightAnswers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		timer = (TextView) findViewById(R.id.textView1);
	}

	@Override
	public void onClick(View v) {
		Bundle bundle = new Bundle();
		int[] xy = new int[2];

		v.getLocationOnScreen(xy);

		bundle.putInt("X", xy[0] + (v.getWidth() / 2));
		bundle.putInt("Y", xy[1] + (v.getHeight() / 2));
		bundle.putInt("XH", v.getWidth());
		bundle.putInt("YH", v.getHeight());
		
		if (buttonCounter == 9) startTime = System.currentTimeMillis();
		v.setEnabled(false);
		Intent intent = new Intent(this, Task.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, 1);
		overridePendingTransition(0, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data.getBooleanExtra("answer", false) == true) {rightAnswers++;}
		if (buttonCounter <= 1) {
			finishThisGame();
		}
		
		timer.setText((System.currentTimeMillis() - startTime) + " ms ...");
		buttonCounter--;
	}

	private void finishThisGame() {
		long yourTime = System.currentTimeMillis() - startTime;
		
		Intent intent = new Intent(this, FinishGame.class);
		intent.putExtra("yourTime", yourTime);
		intent.putExtra("rightAnswers", rightAnswers);
		
		startActivity(intent);
		overridePendingTransition(0, 0);
	}
	
}
