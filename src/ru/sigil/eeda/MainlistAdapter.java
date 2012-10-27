package ru.sigil.eeda;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainlistAdapter extends ArrayAdapter<MainListEntity> {
	private Context context;
	//private ImageView LeftImageView;
	private ImageView RightImageView;
	private TextView Text;
	public static int width;

	private List<MainListEntity> entities = new ArrayList<MainListEntity>();

	public MainlistAdapter(Context context, int textViewResourceId,
			List<MainListEntity> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.entities = objects;
	}

	public int getCount() {
		return this.entities.size();
	}

	public MainListEntity getItem(int index) {
		return this.entities.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.list_item_layout, parent, false);
		}
		// Get item
		MainListEntity message = getItem(position);
		// LeftImageView = (ImageView) row
		// .findViewById(R.id.MainListViewLeftImage);
		RightImageView = (ImageView) row
				.findViewById(R.id.MainListViewRightImage);
		Text = (TextView) row.findViewById(R.id.MainListViewTextView);
		Text.setText(message.getText());
		//LeftImageView.setImageResource(message.getLeftImage());
		// RightImageView.setBackgroundResource(R.drawable.arrow);
		return row;
	}
}