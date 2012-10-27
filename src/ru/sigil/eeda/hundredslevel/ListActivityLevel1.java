package ru.sigil.eeda.hundredslevel;

import ru.sigil.eeda.About;
import ru.sigil.eeda.R;
import ru.sigil.eeda.determinednumberslist.DeterminesListActivity;
import ru.sigil.eeda.utils.AdditivesBaseAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ListActivityLevel1 extends Activity {

	private static int current_menu;
	private HundredsLevelAdapter adapter;
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);
		setContentView(R.layout.list_level_1);
		current_menu = R.menu.main_menu;
		lv = (ListView) findViewById(R.id.MainListView);
		TextView tv = (TextView) findViewById(R.id.hundreds_head_text);
		tv.setText(getString(R.string.by_number));
		createMainMenu();
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
		Resources res = getResources();
		String[] item = res.getStringArray(R.array.numbers_level1);
		NumbersLevel1EntitiesCollection mlec = new NumbersLevel1EntitiesCollection();
		NumbersLevel1Entity mle = new NumbersLevel1Entity();
		mle.setText(item[0]);
		mle.setMinNum(100);
		mle.setMaxNum(199);
		mlec.add(mle);
		mle = new NumbersLevel1Entity();
		mle.setText(item[1]);
		mle.setMinNum(200);
		mle.setMaxNum(299);
		mlec.add(mle);
		mle = new NumbersLevel1Entity();
		mle.setText(item[2]);
		mle.setMinNum(300);
		mle.setMaxNum(399);
		mlec.add(mle);
		mle = new NumbersLevel1Entity();
		mle.setText(item[3]);
		mle.setMinNum(400);
		mle.setMaxNum(599);
		mlec.add(mle);
		mle = new NumbersLevel1Entity();
		mle.setText(item[4]);
		mle.setMinNum(600);
		mle.setMaxNum(699);
		mlec.add(mle);
		mle = new NumbersLevel1Entity();
		mle.setText(item[5]);
		mle.setMinNum(900);
		mle.setMaxNum(999);
		mlec.add(mle);
		mle = new NumbersLevel1Entity();
		mle.setText(item[6]);
		mle.setMinNum(1000);
		mle.setMaxNum(1600);
		mlec.add(mle);
		adapter = new HundredsLevelAdapter(getBaseContext(),
				R.layout.list_item_layout, mlec.getEntities());
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				NumbersLevel1Entity entity = (NumbersLevel1Entity) arg1
						.getTag();
				Intent i = new Intent(getApplicationContext(),
						DeterminesListActivity.class);
				i.putExtra("filter", AdditivesBaseAdapter.MINMAX);
				i.putExtra("min", entity.getMinNum());
				i.putExtra("max", entity.getMaxNum());
				startActivity(i);
			}
		});
	}

	// -------------------------MENU-----------------------------------------------------------
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
