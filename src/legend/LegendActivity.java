package legend;

import ru.sigil.eeda.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

public class LegendActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);
		setContentView(R.layout.legend_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	public void headBackClick(View v) {
		finish();
		this.overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			finish();
			this.overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
