package com.smartchef.apdaters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartchef.controller.R;
import com.smartchef.utils.ImageUtil;
import com.smartchef.utils.LoadContant;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 17-May-15.
 */
public class OptionUserAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<Map<String, String>> listTittle;

    public OptionUserAdapter(Context context, ArrayList<Map<String, String>> listTittle) {
        this.context = context;
        this.listTittle = listTittle;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return listTittle.get(position);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return listTittle.size();
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_listview_useroption, null);
        }
        Map<String, String> item = listTittle.get(position);
        if (item != null) {
            TextView txtTittle = (TextView) convertView.findViewById(R.id.textViewTittle);
            TextView numberView = (TextView) convertView.findViewById(R.id.textViewNumberLook);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.itemBackgroud);
            String tittle = item.get(LoadContant.TITLE);
            String number = item.get(LoadContant.NUMBER);
            String imageResource = item.get(LoadContant.IMAGE_RESOURCE);
            txtTittle.setText(tittle);
            numberView.setText(number);
            if (tittle != null) {
                if (tittle.equals("Favorite")) {
                    Drawable drawable = context.getResources().getDrawable(R.drawable.lovefood);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    bitmap = ImageUtil.roundCornerImage(bitmap, 10);
                    imageView.setImageBitmap(bitmap);
                } else if (tittle.equals("My Collections")) {
                    Drawable drawable = context.getResources().getDrawable(R.drawable.myrecipes);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    bitmap = ImageUtil.roundCornerImage(bitmap, 10);
                    imageView.setImageBitmap(bitmap);
                }else
                    if(imageResource != null){
                        int imageSrc = Integer.parseInt(imageResource);
                        Drawable drawable = context.getResources().getDrawable(imageSrc);
                        imageView.setImageDrawable(drawable);
                    }

            }

        }
        return convertView;
    }
}
