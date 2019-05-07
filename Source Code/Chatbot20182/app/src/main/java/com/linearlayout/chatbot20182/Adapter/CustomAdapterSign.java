package com.linearlayout.chatbot20182.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.linearlayout.chatbot20182.R;
import com.linearlayout.chatbot20182.model.Sign;

import java.util.List;

public class CustomAdapterSign extends ArrayAdapter<Sign> {

    private Context context;
    private int resource;
    private List<Sign> ListSign;


    public CustomAdapterSign(Context context, int resource, List<Sign> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.ListSign = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; //khoi tao viewholder
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.row_show_sign, parent, false);
            viewHolder.tv_id = convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_des = convertView.findViewById(R.id.tv_des);
            viewHolder.tv_image= convertView.findViewById(R.id.tv_image);
            viewHolder.tv_activate=convertView.findViewById(R.id.tv_activate);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Sign Sign = ListSign.get(position);
        viewHolder.tv_id.setText(String.valueOf(Sign.getmId()));
        viewHolder.tv_name.setText(String.valueOf(Sign.getmName()));
        viewHolder.tv_des.setText(String.valueOf(Sign.getmDescription()));
        viewHolder.tv_activate.setText(String.valueOf(Sign.getmActivate()));
        Bitmap bitmap= BitmapFactory.decodeByteArray(Sign.getmImage(),0,Sign.getmImage().length );
        viewHolder.tv_image.setImageBitmap(bitmap);

        return convertView;
    }

    // dung de khoi tao cac textview
    public class ViewHolder {
        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_des;
        private TextView tv_activate;
        private ImageView tv_image;


    }
}