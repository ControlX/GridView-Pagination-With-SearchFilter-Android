package com.github.controlx.catalogue.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.controlx.catalogue.R;
import com.github.controlx.catalogue.helper.ImageMapConstant;
import com.github.controlx.catalogue.helper.Utils;
import com.github.controlx.catalogue.model.Content;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Abhishek Verma on 11/27/2016.
 */

public class CatalogueGridAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private ArrayList<Content> mContentList;
    private ValueFilter mValueFilter;
    private ArrayList<Content> mContentFilterList;

    public CatalogueGridAdapter(Context context, ArrayList<Content> contentList) {
        this.mContext = context;
        this.mContentList = contentList;
        this.mContentFilterList = contentList;
        getFilter();
    }

    @Override
    public int getCount() {
        return mContentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        RecordHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.adapter_girdview_cell, parent, false);
            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.adapter_tv_poster);
            holder.imageItem = (ImageView) row.findViewById(R.id.adapter_iv_poster);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        holder.txtTitle.setText(mContentList.get(position).getName());
        holder.txtTitle.setTypeface(Utils.getTypeFaceTitillium(mContext));

        int drawable = ImageMapConstant.getDrawable(mContentList.get(position).getPosterImage());

        if (drawable != 0) {
            Picasso.with(mContext).load(drawable)
                    .placeholder(R.drawable.placeholder_for_missing_posters)
                    .resize(Utils.dpToPx(mContext, 150), 0).into(holder.imageItem);
        } else {
            Picasso.with(mContext).load(R.drawable.placeholder_for_missing_posters)
                    .placeholder(R.drawable.placeholder_for_missing_posters)
                    .resize(Utils.dpToPx(mContext, 150), 0).into(holder.imageItem);
        }

        return row;
    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
    }

    @Override
    public Filter getFilter() {
        if (mValueFilter == null) {
            mValueFilter = new ValueFilter();
        }
        return mValueFilter;
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Content> filterList = new ArrayList<Content>();
                for (int i = 0; i < mContentFilterList.size(); i++) {
                    if ((mContentFilterList.get(i).getName().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        Content contacts = new Content();
                        contacts.setName(mContentFilterList.get(i).getName());
                        contacts.setPosterImage(mContentFilterList.get(i).getPosterImage());
                        filterList.add(contacts);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mContentFilterList.size();
                results.values = mContentFilterList;
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mContentList = (ArrayList<Content>) results.values;
            notifyDataSetChanged();
        }
    }
}
