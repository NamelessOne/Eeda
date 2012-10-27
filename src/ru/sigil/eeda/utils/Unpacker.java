package ru.sigil.eeda.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Unpacker {
	private Context context;
	private AssetManager assetManager;

	public void copyAssets() {
		String[] files = null;
		try {
			files = assetManager.list("");
		} catch (IOException e) {
			Log.e("tag", e.getMessage());
		}
		for (String filename : files) {
			InputStream in = null;
			OutputStream out = null;
			try {
				File f = new File("/data/data/" + getContext().getPackageName()
						+ "/databases/");
				f.mkdirs();
				// ----------------
				in = assetManager.open(filename);
				out = new FileOutputStream("/data/data/"
						+ getContext().getPackageName()
						+ "/databases/EAdditiveEntity");
				copyFile(in, out);
				in.close();
				in = null;
				out.flush();
				out.close();
				out = null;
				// ----------------------
				SQLiteDatabase mDatabase = getContext().openOrCreateDatabase(
						"Favorites", 0, null);
				try {
					mDatabase
							.execSQL("CREATE TABLE Favorites (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
									+ "EntityID INTEGER," + "FAVORITE INTEGER)");
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally
				{
					mDatabase.close();
				}
				// ----------------------
			} catch (Exception e) {
				Log.e("tag", e.getMessage());
			}
		}
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
