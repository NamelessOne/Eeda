package ru.sigil.eeda.hundredslevel;

import java.util.ArrayList;
import java.util.List;

import ru.sigil.eeda.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HundredsLevelAdapter extends ArrayAdapter<NumbersLevel1Entity> {
	private Context context;
	// private ImageView LeftImageView;
	private ImageView RightImageView;
	private TextView Text;

	public static int width;

	private List<NumbersLevel1Entity> entities = new ArrayList<NumbersLevel1Entity>();

	public HundredsLevelAdapter(Context context, int textViewResourceId,
			List<NumbersLevel1Entity> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.entities = objects;
	}

	public int getCount() {
		return this.entities.size();
	}

	public NumbersLevel1Entity getItem(int index) {
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
		NumbersLevel1Entity message = getItem(position);
		// LeftImageView = (ImageView) row
		// .findViewById(R.id.MainListViewLeftImage);
		RightImageView = (ImageView) row
				.findViewById(R.id.MainListViewRightImage);
		Text = (TextView) row.findViewById(R.id.MainListViewTextView);
		Text.setText(message.getText());
		// LeftImageView.setImageResource(message.getLeftImage());
		// RightImageView.setBackgroundResource(R.drawable.arrow);
		row.setTag(message);
		return row;
	}
}
