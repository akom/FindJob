package com.akomsoft.findjob;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {
//    private String[] ids;
    private String[] tittles;
    private String[] locations;
    private String[] companies;
    private Activity context;

    public CustomList(Activity context, String[] ids, String[] tittles,
                      String[] locations, String[] companies) {
        super(context, R.layout.list_job, ids);
        this.context = context;
//        this.ids = ids;
        this.tittles = tittles;
        this.locations = locations;
        this.companies = companies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_job, null, true);
//        TextView textViewId = (TextView) listViewItem.findViewById(R.id.texViewId);
        TextView textViewTittle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewLocation = (TextView) listViewItem.findViewById(R.id.textViewLocation);
        TextView textViewCompany = (TextView) listViewItem.findViewById(R.id.textViewCompany);

//        textViewId.setText(ids[position]);
        textViewTittle.setText(tittles[position]);
        textViewLocation.setText(locations[position]);
        textViewCompany.setText(companies[position]);

        return listViewItem;
    }
}