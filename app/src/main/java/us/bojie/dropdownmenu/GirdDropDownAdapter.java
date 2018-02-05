package us.bojie.dropdownmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bojiejiang on 2/2/18.
 */

public class GirdDropDownAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;
    private int mCheckItemPosition = -1;

    public GirdDropDownAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    public void setCheckItemPosition(int position) {
        mCheckItemPosition = position;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_drop_down, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }
        fillView(position, viewHolder);
        return convertView;
    }

    private void fillView(int position, ViewHolder viewHolder) {
        viewHolder.tv.setText(mList.get(position));
        if (mCheckItemPosition != -1) {
            if (mCheckItemPosition == position) {
                viewHolder.tv.setTextColor(mContext.getResources().getColor(R.color.selected));
                viewHolder.tv.setCompoundDrawablesWithIntrinsicBounds(
                        null, null,
                        mContext.getResources().getDrawable(R.mipmap.check),
                        null
                );
            } else {
                viewHolder.tv.setTextColor(mContext.getResources().getColor(R.color.unselected));
                viewHolder.tv.setCompoundDrawablesWithIntrinsicBounds(
                        null, null, null, null);
            }
        }
    }

    class ViewHolder {
        TextView tv;
    }
}
