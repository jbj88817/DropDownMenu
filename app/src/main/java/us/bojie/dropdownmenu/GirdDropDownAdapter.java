package us.bojie.dropdownmenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by bojiejiang on 2/2/18.
 */

public class GirdDropDownAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;
    private int mCheckItemPosition = 0;

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
        return null;
    }
}
