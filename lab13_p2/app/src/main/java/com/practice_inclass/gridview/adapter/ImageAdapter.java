package com.practice_inclass.gridview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] mImageIds;

    public ImageAdapter(Context context, Integer[] imageIds) {
        mContext = context;
        mImageIds = imageIds;
    }

    //Nói cho GridView (hoặc ListView) biết có bao nhiêu mục dữ liệu cần hiển thị.
    @Override
    public int getCount() {
        return mImageIds.length;
    }

    //Mục đích: Trả về mục dữ liệu cụ thể tại một vị trí (index) nhất định trong danh sách của bạn.
    //Ví dụ: Nếu bạn click vào hình ảnh thứ 3 (ở vị trí index là 2),
    //hàm này có thể trả về ID tài nguyên của hình ảnh đó hoặc đối tượng dữ liệu tương ứng.
    @Override
    public Object getItem(int position) {
        return mImageIds[position];
    }

    //Trả về một ID duy nhất cho mục dữ liệu tại vị trí position.
    //Việc này giúp GridView tối ưu hóa hiệu suất khi xử lý các thay đổi trong danh sách.
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Tạo hoặc cập nhật View để hiển thị một mục cụ thể, nếu convertView không null, thì không cần tạo mới
    //mà chỉ cần cập nhật lại nội dung của nó, viewgroup chính là GridView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);

            //Đặt kích thước cho ImageView
            //Điểu chỉnh kích thước này tùy theo mong muốn hiển thị
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200)); //200x200 pixels
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); //Cắt ảnh để lấp đầy ImageView
            imageView.setPadding(8, 8, 8, 8); //Đặt khoảng cách giữa các ảnh
        } else {
            imageView = (ImageView) convertView; //Nếu convertView không null, sử dụng lại
        }
        imageView.setImageResource(mImageIds[position]); //Đặt hình ảnh cho ImageView
        return imageView;
    }
}
