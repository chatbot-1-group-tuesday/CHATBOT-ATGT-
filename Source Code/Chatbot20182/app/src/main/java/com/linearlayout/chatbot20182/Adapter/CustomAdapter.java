package com.linearlayout.chatbot20182.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.linearlayout.chatbot20182.Model.Law;
import com.linearlayout.chatbot20182.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Law> {

    private Context context;//context chính là main activity
    private int resource;
    private List<Law> lawList;

    public CustomAdapter( Context context, int resource,  List<Law> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lawList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder  =null ;

        if(convertView==null)
        {

            //Convert view trong main để phù hợp với item_listview law
            convertView= LayoutInflater.from(context).inflate(R.layout.item_listviewlaw,parent,false);
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
            convertView.setTag(viewHolder);
        }
        else
        {

            viewHolder =(ViewHolder) convertView.getTag();
        }

        Law law = lawList.get(position);
        viewHolder.tvId.setText(String.valueOf(law.getmID()));
        viewHolder.tvName.setText(String.valueOf(law.getmName()));
        viewHolder.tvDescription.setText(String.valueOf(law.getmDescription()));


        return convertView;


    }



    //Tạo 1 view holder chứa các listview cần hiển thị
    public class ViewHolder
    {
             private TextView tvId;
             private TextView tvName;
             private TextView tvDescription;
    }
}
