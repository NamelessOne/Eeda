package ru.sigil.eeda.determinednumberslist;

import ru.sigil.eeda.About;
import ru.sigil.eeda.R;
import ru.sigil.eeda.ElementLevel.ElementActivity;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DeterminesListActivity extends Activity {

	private static int current_menu;
	private DeterminedsAdapter adapter;
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);
		setContentView(R.layout.list_level_1);
		current_menu = R.menu.main_menu;
		lv = (ListView) findViewById(R.id.MainListView);
		// createMainMenu();
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

	private void createMainMenu() {
		AdditivesBaseAdapter aba = new AdditivesBaseAdapter();
		aba.setContext(getBaseContext());
		if (getIntent().getIntExtra("filter", AdditivesBaseAdapter.MINMAX) == AdditivesBaseAdapter.MINMAX) {
			TextView tv = (TextView) findViewById(R.id.hundreds_head_text);
			int min = getIntent().getIntExtra("min", 0);
			int max = getIntent().getIntExtra("max", 0);
			tv.setText("E" + min + "-" + max);
			aba.LoadBetween(min, max);
		}
		if (getIntent().getIntExtra("filter", AdditivesBaseAdapter.MINMAX) == AdditivesBaseAdapter.FAVORITES) {
			TextView tv = (TextView) findViewById(R.id.hundreds_head_text);
			tv.setText(getString(R.string.favorites));
			aba.LoadFavorites();
		}
		if (getIntent().getIntExtra("filter", AdditivesBaseAdapter.MINMAX) == AdditivesBaseAdapter.DANGEROUS) {
			int danger = getIntent().getIntExtra("danger", 0);
			aba.LoadByDangerous(danger);
		}
		adapter = new DeterminedsAdapter(getBaseContext(),
				R.layout.list_item_layout, aba.getEAdditiveEntityes());
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(),
						ElementActivity.class);
				EAdditiveEntity entity = (EAdditiveEntity) arg1.getTag();
				i.putExtra("Danger", entity.getDanger());
				i.putExtra("ID", entity.getID());
				i.putExtra("Name", entity.getName());
				i.putExtra("Number", entity.getNumber());
				i.putExtra("Text", entity.getText());
				i.putExtra("Favorite", entity.isFavorite());
				startActivity(i);
			}
		});
	}

	// -------------------------MENU-----------------------------------------------------------
	@Override
	protected void onResume() {
		createMainMenu();
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
