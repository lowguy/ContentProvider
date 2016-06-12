package hyp.my0612b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
public class MyAdapter extends BaseAdapter {
    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<ItemBean> list) {
        this.mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.mPhone = (TextView) convertView.findViewById(R.id.phone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemBean bean = mList.get(position);
        viewHolder.mName.setText(bean.itemName);
        viewHolder.mPhone.setText(bean.itemPhone);
        return convertView;

    }

    class ViewHolder {
        public TextView mName;
        public TextView mPhone;
    }
}
