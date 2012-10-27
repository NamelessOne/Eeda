package ru.sigil.eeda.dangerouslevel;

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

public class DangerousAdapter extends ArrayAdapter<DangerousEntity> {
	private Context context;
	private ImageView LeftImageView;
	private ImageView RightImageView;
	private TextView Text;

	public static int width;

	private List<DangerousEntity> entities = new ArrayList<DangerousEntity>();

	public DangerousAdapter(Context context, int textViewResourceId,
			List<DangerousEntity> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.entities = objects;
	}

	public int getCount() {
		return this.entities.size();
	}

	public DangerousEntity getItem(int index) {
		return this.entities.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.determ_item_layout, parent, false);
		}
		// Get item
		DangerousEntity message = getItem(position);
		// LeftImageView = (ImageView) row
		// .findViewById(R.id.MainListViewLeftImage);
		LeftImageView = (ImageView) row.findViewById(R.id.DetermListLeftImage);
		if (message.getDanger() == 0) {
			LeftImageView.setImageResource(R.drawable.accepted);
		}
		if (message.getDanger() == 1) {
			LeftImageView.setImageResource(R.drawable.warning);
		}
		if (message.getDanger() == 2) {
			LeftImageView.setImageResource(R.drawable.banned);
		}
		RightImageView = (ImageView) row
				.findViewById(R.id.MainListViewRightImage);
		Text = (TextView) row.findViewById(R.id.MainListViewTextView);
		Text.setText(message.getName());
		// LeftImageView.setImageResource(message.getLeftImage());
		// RightImageView.setBackgroundResource(R.drawable.arrow);
		row.setTag(message);
		return row;
	}
}
