package com.example.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FinishGame extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish_game);

		Button button = (Button) findViewById(R.id.btnExit);
		TextView yourTime = (TextView) findViewById(R.id.resTimeText);
		TextView rightAnswers = (TextView) findViewById(R.id.resAnswersText);

		button.setOnClickListener(this);
		Bundle bundle = getIntent().getExtras();
		yourTime.setText("Time: " + bundle.getLong("yourTime") + " ms.");
		rightAnswers.setText("Score: " + bundle.getInt("rightAnswers")*100/9 + "%");
	}

	@Override
	public void onClick(View v) {
		System.exit(0);
	}

}
