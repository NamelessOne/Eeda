package ru.sigil.eeda.ElementLevel;

import java.util.StringTokenizer;

import ru.sigil.eeda.About;
import ru.sigil.eeda.R;
import ru.sigil.eeda.utils.AdditivesBaseAdapter;
import ru.sigil.eeda.utils.EAdditiveEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class ElementActivity extends Activity {

	private static int current_menu;
	private EAdditiveEntity entity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);
		setContentView(R.layout.element_layout);
		entity = new EAdditiveEntity();
		entity.setDanger(getIntent().getIntExtra("Danger", 0));
		entity.setID(getIntent().getIntExtra("ID", 0));
		entity.setName(getIntent().getStringExtra("Name"));
		entity.setNumber(getIntent().getIntExtra("Number", 0));
		// entity.setText(getIntent().getStringExtra("Text"));
		// entity.setFavorite(getIntent().getBooleanExtra("Favorite", false));
		entity.setFavorite(AdditivesBaseAdapter.getFavoriteByEntityId(entity
				.getID()));
		WebView wv = (WebView) findViewById(R.id.element_text);
		wv.setBackgroundColor(getResources().getColor(
				R.color.element_background));
		wv.getSettings().setBuiltInZoomControls(true);
		if (android.os.Build.VERSION.SDK_INT >= 11) {
			wv.getSettings().setDisplayZoomControls(false);
		} else {
			// Do something different to support older versions
		}
		wv.loadDataWithBaseURL(null,
				"<h2 style=\"color:#5B5B5B\">" + entity.getName() + "</h2>"
						+ "<p1 style=\"color:#5B5B5B\">" + entity.getText()
						+ "</p1>", "text/html", "utf-8", null);
		TextView tv2 = (TextView) findViewById(R.id.element_head_text);
		StringTokenizer st = new StringTokenizer(entity.getName());
		tv2.setText(st.nextToken());
		// TextView tv3 = (TextView) findViewById(R.id.element_name_text);
		// tv3.setText(entity.getName());
		ImageView im = (ImageView) findViewById(R.id.head_star);
		if (entity.isFavorite()) {
			im.setImageResource(R.drawable.bookmarks_main);
		} else {
			im.setImageResource(R.drawable.bookmarks_add);
		}
		ImageView dangerImg = (ImageView) findViewById(R.id.element_danger);
		if (entity.getDanger() == 0) {
			dangerImg.setImageResource(R.drawable.accepted);
		}
		if (entity.getDanger() == 1) {
			dangerImg.setImageResource(R.drawable.warning);
		}
		if (entity.getDanger() == 2) {
			dangerImg.setImageResource(R.drawable.banned);
		}
		current_menu = R.menu.main_menu;
	}

	public void headBackClick(View v) {
		finish();
		this.overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	// -------------------------------------------MENU------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(getCurrent_menu(), menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.clear();
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(getCurrent_menu(), menu);
		return super.onPrepareOptionsMenu(menu);
	}

	public static int getCurrent_menu() {
		return current_menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			Intent i = new Intent(this, About.class);
			try {
				startActivity(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// -------------------------MENU-----------------------------------------------------------
	public void headStarClick(View v) {
		ImageView im = (ImageView) findViewById(R.id.head_star);
		if (entity.isFavorite()) {
			entity.setFavorite(false);
			im.setImageResource(R.drawable.bookmarks_add);
		} else {
			entity.setFavorite(true);
			im.setImageResource(R.drawable.bookmarks_main);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
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
