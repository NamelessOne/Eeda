package ru.sigil.eeda.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AdditivesBaseAdapter {
	private static Context context;
	private List<EAdditiveEntity> EAdditiveEntityes = new ArrayList<EAdditiveEntity>();
	public SQLiteDatabase database;
	private Object saveSync = new Object();
	public static final int MINMAX = 0;
	public static final int FAVORITES = 1;
	public static final int DANGEROUS = 2;

	public void LoadBetween(int min, int max) {
		String filter = new String();
		filter = "NUMBER>=" + String.valueOf(min) + " AND NUMBER<="
				+ String.valueOf(max) + " ORDER BY NUMBER";
		SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
				"EAdditiveEntity", 0, null);
		getEAdditiveEntityes().clear();
		// Make the query.
		try {
			Cursor managedCursor = mDatabase.query("EAdditiveEntitys", null,
					filter, null, null, null, null);
			for (managedCursor.moveToFirst(); !managedCursor.isAfterLast(); managedCursor
					.moveToNext()) {
				EAdditiveEntity eAdditiveEntity = new EAdditiveEntity();
				eAdditiveEntity.setDanger(managedCursor.getInt(managedCursor
						.getColumnIndex("DANGER")));
				eAdditiveEntity.setName(managedCursor.getString(managedCursor
						.getColumnIndex("NAME")));
				// eAdditiveEntity.setText(managedCursor.getString(managedCursor
				// .getColumnIndex("TEXT")));
				eAdditiveEntity.setNumber(managedCursor.getInt(managedCursor
						.getColumnIndex("NUMBER")));
				eAdditiveEntity.setID(managedCursor.getInt(managedCursor
						.getColumnIndex("_id")));
				getEAdditiveEntityes().add(eAdditiveEntity);
			}
			managedCursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mDatabase.close();
		}
	}

	/**
	 * @param filter
	 */
	public void LoadByTextFilter(String filter) {
		String textFilter = "LOWER(SEARCH) LIKE LOWER('%" + filter + "%')"
				+ " ORDER BY NUMBER";
		SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
				"EAdditiveEntity", 0, null);
		getEAdditiveEntityes().clear();
		// Make the query.
		try {
			Cursor managedCursor = mDatabase.query("EAdditiveEntitys", null,
					textFilter, null, null, null, null);
			for (managedCursor.moveToFirst(); !managedCursor.isAfterLast(); managedCursor
					.moveToNext()) {
				EAdditiveEntity eAdditiveEntity = new EAdditiveEntity();
				eAdditiveEntity.setDanger(managedCursor.getInt(managedCursor
						.getColumnIndex("DANGER")));
				eAdditiveEntity.setName(managedCursor.getString(managedCursor
						.getColumnIndex("NAME")));
				// eAdditiveEntity.setText(managedCursor.getString(managedCursor
				// .getColumnIndex("TEXT")));
				eAdditiveEntity.setNumber(managedCursor.getInt(managedCursor
						.getColumnIndex("NUMBER")));
				eAdditiveEntity.setID(managedCursor.getInt(managedCursor
						.getColumnIndex("_id")));
				getEAdditiveEntityes().add(eAdditiveEntity);
			}
			managedCursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mDatabase.close();
		}
	}

	public void LoadFavorites() {
		SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
				"EAdditiveEntity", 0, null);
		getEAdditiveEntityes().clear();
		// Make the query.
		try {
			// ----------------------------------------------
			SQLiteDatabase mDatabase2 = getContext().openOrCreateDatabase(
					"Favorites", 0, null);
			List<Integer> bList = new ArrayList<Integer>();
			try {
				Cursor managedCursor2 = mDatabase2.query("Favorites", null,
						null, null, null, null, null);
				for (managedCursor2.moveToFirst(); !managedCursor2
						.isAfterLast(); managedCursor2.moveToNext()) {
					if (managedCursor2.getInt(managedCursor2
							.getColumnIndex("FAVORITE")) == 1) {
						Integer i = new Integer(0);
						i = managedCursor2.getInt(managedCursor2
								.getColumnIndex("EntityID"));
						bList.add(i);
					}
				}
				managedCursor2.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mDatabase2.close();
			}
			// ----------------------------------------------
			Cursor managedCursor = mDatabase.query("EAdditiveEntitys", null,
					null, null, null, null, null);
			for (managedCursor.moveToFirst(); !managedCursor.isAfterLast(); managedCursor
					.moveToNext()) {
				EAdditiveEntity eAdditiveEntity = new EAdditiveEntity();
				eAdditiveEntity.setDanger(managedCursor.getInt(managedCursor
						.getColumnIndex("DANGER")));
				eAdditiveEntity.setName(managedCursor.getString(managedCursor
						.getColumnIndex("NAME")));
				// eAdditiveEntity.setText(managedCursor.getString(managedCursor
				// .getColumnIndex("TEXT")));
				eAdditiveEntity.setNumber(managedCursor.getInt(managedCursor
						.getColumnIndex("NUMBER")));
				eAdditiveEntity.setID(managedCursor.getInt(managedCursor
						.getColumnIndex("_id")));
				if (bList.contains(eAdditiveEntity.getID())) {
					getEAdditiveEntityes().add(eAdditiveEntity);
				}
			}
			managedCursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mDatabase.close();
		}
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context Context) {
		context = Context;
	}

	public List<EAdditiveEntity> getEAdditiveEntityes() {
		return EAdditiveEntityes;
	}

	public void setEAdditiveEntityes(List<EAdditiveEntity> eAdditiveEntityes) {
		EAdditiveEntityes = eAdditiveEntityes;
	}

	public static boolean getFavoriteByEntityId(int id) {
		try {
			SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
					"Favorites", 0, null);
			try {
				String textFilter = "EntityID =" + id;
				Cursor managedCursor = mDatabase.query("Favorites", null,
						textFilter, null, null, null, null);
				if (managedCursor.moveToFirst()) {
					return (managedCursor.getInt(managedCursor
							.getColumnIndex("FAVORITE")) == 1);
				}
				managedCursor.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mDatabase.close();
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static void addFavorite(int entityID) {
		removeFavorite(entityID);
		SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
				"Favorites", 0, null);
		ContentValues cv = new ContentValues();
		cv.put("FAVORITE", 1);
		cv.put("EntityID", entityID);
		mDatabase.insert("Favorites", null, cv);
		mDatabase.close();
	}

	public static void removeFavorite(int entityID) {
		SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
				"Favorites", 0, null);
		mDatabase.execSQL("DELETE FROM Favorites WHERE EntityID = " + entityID);
		mDatabase.close();
	}

	public void LoadByDangerous(int danger) {
		String filter = new String();
		filter = "DANGER=" + String.valueOf(danger) + " ORDER BY NUMBER";
		SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
				"EAdditiveEntity", 0, null);
		getEAdditiveEntityes().clear();
		// Make the query.
		try {
			Cursor managedCursor = mDatabase.query("EAdditiveEntitys", null,
					filter, null, null, null, null);
			for (managedCursor.moveToFirst(); !managedCursor.isAfterLast(); managedCursor
					.moveToNext()) {
				EAdditiveEntity eAdditiveEntity = new EAdditiveEntity();
				eAdditiveEntity.setDanger(managedCursor.getInt(managedCursor
						.getColumnIndex("DANGER")));
				eAdditiveEntity.setName(managedCursor.getString(managedCursor
						.getColumnIndex("NAME")));
				// eAdditiveEntity.setText(managedCursor.getString(managedCursor
				// .getColumnIndex("TEXT")));
				eAdditiveEntity.setNumber(managedCursor.getInt(managedCursor
						.getColumnIndex("NUMBER")));
				eAdditiveEntity.setID(managedCursor.getInt(managedCursor
						.getColumnIndex("_id")));
				getEAdditiveEntityes().add(eAdditiveEntity);
			}
			managedCursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mDatabase.close();
		}
	}

	public static String getTextByID(int ID) {
		String filter = new String();
		String result = "";
		filter = "_id = " + ID;
		SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
				"EAdditiveEntity", 0, null);
		// Make the query.
		try {
			Cursor managedCursor = mDatabase.query("EAdditiveEntitys", null,
					filter, null, null, null, null);
			for (managedCursor.moveToFirst(); !managedCursor.isAfterLast(); managedCursor
					.moveToNext()) {
				result = managedCursor.getString(managedCursor
						.getColumnIndex("TEXT"));
			}
			managedCursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mDatabase.close();
		}
		return result;
	}
}
