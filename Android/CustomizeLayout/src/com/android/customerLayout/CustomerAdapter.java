package com.android.customerLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomerAdapter extends android.widget.BaseAdapter{
	
	private String[] _items;
	public LayoutInflater _inflater;
	
    static class ViewHolder {
        TextView positionText;
        TextView idText;
        TextView itemText;
    }
	
    public CustomerAdapter(Context context, String... items ) {
        _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _items = items;
    }
	
    public int getCount() {
        return _items.length;
    }

	public Object getItem(int position) {
		return _items[position];
	}

	public long getItemId(int position) {
		return position + 1;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder;
		
		if (convertView == null) {
            convertView = _inflater.inflate(R.layout.item, null);
            viewholder = new ViewHolder();

            viewholder.positionText = (TextView) convertView.findViewById( R.id.position );
            viewholder.idText = (TextView) convertView.findViewById( R.id.id );
            viewholder.itemText = (TextView) convertView.findViewById( R.id.item );
    		
            convertView.setTag(viewholder);
        } else {
            // 2.
        	viewholder = (ViewHolder) convertView.getTag();
        }
		//View view = _inflater.inflate( R.layout.item, null );
		viewholder.positionText.setText("position: " + position);
		viewholder.idText.setText("id: " + getItemId(position));
		viewholder.itemText.setText("item: " + getItem(position));
		return convertView;
	}
}
