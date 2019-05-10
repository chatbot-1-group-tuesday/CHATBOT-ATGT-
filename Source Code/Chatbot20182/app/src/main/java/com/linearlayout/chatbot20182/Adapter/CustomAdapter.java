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
           // viewHolder.tv_image= convertView.findViewById(R.id.tv_image);
            viewHolder.tv_activate=convertView.findViewById(R.id.tv_activate);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Law law = Listlaw.get(position);
        viewHolder.tv_id.setText(String.valueOf(law.getmId()));
        viewHolder.tv_name.setText(String.valueOf(law.getmName()));
        viewHolder.tv_des.setText(String.valueOf(law.getmDescription()));
        viewHolder.tv_activate.setText(String.valueOf(law.getmActivate()));
       // Bitmap bitmap= BitmapFactory.decodeByteArray(law.getmImage(),0,law.getmImage().length );
      //  viewHolder.tv_image.setImageBitmap(bitmap);

        return convertView;
    }

    // dung de khoi tao cac textview
    public class ViewHolder {
        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_des;
        private TextView tv_activate;
      //  private ImageView tv_image;


    }
}
