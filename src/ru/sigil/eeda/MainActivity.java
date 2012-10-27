package ru.sigil.eeda;

import legend.LegendActivity;
import ru.sigil.eeda.ElementLevel.ElementActivity;
import ru.sigil.eeda.dangerouslevel.DangerousLevelActivity;
import ru.sigil.eeda.determinednumberslist.DeterminedsAdapter;
import ru.sigil.eeda.determinednumberslist.DeterminesListActivity;
import ru.sigil.eeda.hundredslevel.ListActivityLevel1;
import ru.sigil.eeda.utils.AdditivesBaseAdapter;
import ru.sigil.eeda.utils.EAdditiveEntity;
import ru.sigil.eeda.utils.Unpacker;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static int current_menu;
	private MainlistAdapter adapter;
	private ListView lv;
	private Intent[] intents;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AdditivesBaseAdapter.setContext(getBaseContext());
		this.overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);
		setContentView(R.layout.activity_main);
		current_menu = R.menu.main_menu;
		EditText et = (EditText) findViewById(R.id.mainSearchEditText);
		et.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s.length() > 0) {
					search(s.toString());
				} else {
					createMainMenu();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		lv = (ListView) findViewById(R.id.MainListView);
		intents = new Intent[4];
		intents[0] = new Intent(getApplicationContext(),
				ListActivityLevel1.class);
		intents[1] = new Intent(getApplicationContext(),
				DangerousLevelActivity.class);
		intents[2] = new Intent(getApplicationContext(),
				DeterminesListActivity.class);
		intents[2].putExtra("filter", AdditivesBaseAdapter.FAVORITES);
		intents[3] = new Intent(getApplicationContext(), LegendActivity.class);
		Unpacker unp = new Unpacker();
		unp.setAssetManager(getAssets());
		unp.setContext(getBaseContext());
		unp.copyAssets();
		createMainMenu();
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

	private void ToastMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	private void createMainMenu() {
		Resources res = getResources();
		String[] item = res.getStringArray(R.array.main_list);
		MainListEntityCollection mlec = new MainListEntityCollection();
		MainListEntity mle = new MainListEntity();
		mle.setLeftImage(R.drawable.categories);
		mle.setText(item[0]);
		mlec.add(mle);
		mle = new MainListEntity();
		mle.setLeftImage(R.drawable.bookmarks_main);
		mle.setText(item[1]);
		mlec.add(mle);
		mle = new MainListEntity();
		mle.setLeftImage(R.drawable.bookmarks_main);
		mle.setText(item[2]);
		mlec.add(mle);
		mle = new MainListEntity();
		mle.setLeftImage(R.drawable.bookmarks_main);
		mle.setText(item[3]);
		mlec.add(mle);
		adapter = new MainlistAdapter(getBaseContext(),
				R.layout.list_item_layout, mlec.getEntities());
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.v(String.valueOf(arg1), String.valueOf(arg2));
				// Тут переход на следующий уровень списк
				startActivity(intents[arg2]);
			}
		});
	}

	private void search(String filter) {
		AdditivesBaseAdapter aba = new AdditivesBaseAdapter();
		aba.LoadByTextFilter(filter);
		DeterminedsAdapter dAdapter = new DeterminedsAdapter(getBaseContext(),
				R.layout.list_item_layout, aba.getEAdditiveEntityes());
		lv.setAdapter(dAdapter);
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
				// Тут стартуем активити с подробным описанием в-ва
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			EditText et = (EditText) findViewById(R.id.mainSearchEditText);
			if (et.getText().length() > 0) {
				et.setText("");
			} else {
				SharedPreferences settings = getPreferences(0);
				if (settings.getFloat("version", -1) < 0) {
					SharedPreferences.Editor editor = settings.edit();
					editor.putFloat("version",
							Float.parseFloat(getString(R.string.version)));
					editor.commit();
					Intent i = new Intent(getApplicationContext(), Please.class);
					startActivity(i);
					// Тут выводим окошко
				} else {
					finish();
					this.overridePendingTransition(
							android.R.anim.slide_in_left,
							android.R.anim.slide_out_right);
				}
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
