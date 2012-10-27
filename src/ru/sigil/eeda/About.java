package ru.sigil.eeda;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class About extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//111
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		TextView tv = (TextView) findViewById(R.id.about_content);
		tv.append(getString(R.string.versiontext) + " "
				+ getString(R.string.version));
		tv.setOnClickListener(CloselickListener);
	}

	View.OnClickListener CloselickListener = new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stop();
		}
	};

	private void stop() {
		finish();
	}
}