package ru.sigil.eeda.dangerouslevel;

import java.util.ArrayList;
import java.util.List;

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

public class DangerousLevelActivity extends Activity {

	private static int current_menu;
	private DangerousAdapter adapter;
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
		tv.setText(getString(R.string.by_type));
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
		Resources res = getResources();
		String[] item = res.getStringArray(R.array.dangerous_levels);
		List<DangerousEntity> list = new ArrayList<DangerousEntity>();
		int i = 0;
		for (String str : item) {
			DangerousEntity de = new DangerousEntity();
			de.setName(str);
			de.setDanger(i);
			list.add(de);
			i += 1;
		}
		adapter = new DangerousAdapter(getBaseContext(),
				R.layout.list_item_layout, list);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(),
						DeterminesListActivity.class);
				DangerousEntity de = (DangerousEntity) arg1.getTag();
				i.putExtra("filter", AdditivesBaseAdapter.DANGEROUS);
				i.putExtra("danger", de.getDanger());
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
