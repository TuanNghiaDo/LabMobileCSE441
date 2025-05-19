package com.example.customlayout.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.customlayout.R;
import com.example.customlayout.models.Image;

import java.util.ArrayList;

//Mục đch của class này là để custom layout cho Gridview
public class MyarrayAdapter extends ArrayAdapter<Image> {  //Đây là class quan trọng nhất dùng để custom layout cho Gridview
    Activity context = null;
    ArrayList<Image> myArray = null;
    int layoutId;

    public MyarrayAdapter(Activity context, int layoutId, ArrayList<Image> myArray) {
        super(context, layoutId, myArray);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = myArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //LayoutInflater là một class (lớp) quan trọng có chức năng chính là chuyển đổi (inflate) một tệp XML layout thành các đối tượng View tương ứng trong bộ nhớ.
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        final Image myimage = myArray.get(position);

        //Dòng lệnh lấy ImageView ra để hiển thị hình ảnh
        final ImageView imgItem = (ImageView) convertView.findViewById(R.id.image_view);
        imgItem.setImageResource(myimage.getImg());

        //Dòng lệnh lấy TextView ra để hiển thị tên ảnh
        final TextView myname = (TextView) convertView.findViewById(R.id.textView1);
        myname.setText(myimage.getName());
        return convertView;
    }
}
