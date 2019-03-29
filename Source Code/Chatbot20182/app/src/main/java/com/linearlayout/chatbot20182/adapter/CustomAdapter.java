package com.linearlayout.chatbot20182.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linearlayout.chatbot20182.R;
import com.linearlayout.chatbot20182.model.Law;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Law> {


    private Context context;
    private int resource;
    private List<Law> Listlaw;


    public CustomAdapter(Context context, int resource, List<Law> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.Listlaw = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; //khoi tao viewholder
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_show_law, parent, false);
            viewHolder.tv_id = convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_des = convertView.findViewById(R.id.tv_des);
            viewHolder.image_url = convertView.findViewById(R.id.edit_image_url);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Law law = Listlaw.get(position);
        viewHolder.tv_id.setText(String.valueOf(law.getmId()));
        viewHolder.tv_name.setText(String.valueOf(law.getmName()));
        viewHolder.tv_des.setText(String.valueOf(law.getmDescription()));

        return convertView;
    }

    // dung de khoi tao cac textview
    public class ViewHolder {
        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_des;
        private ImageView image_url;


    }
}
