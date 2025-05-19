package com.practice_inclass.calendarnote;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefs";
    private static final String TASKS_KEY = "tasksList";
    // Ký tự đặc biệt để phân tách các mục trong danh sách khi lưu vào SharedPreferences
    // Chọn ký tự ít khả năng xuất hiện trong nội dung công việc thực tế
    private static final String ITEM_DELIMITER = "#";
    ListView lv1;
    ArrayList<String> arrayWork;
    ArrayAdapter<String> arrAdater;
    EditText edtWork, edtHour, edtMinute;
    TextView txtDate;
    Button btnAddWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = findViewById(R.id.lv1);
        edtWork = findViewById(R.id.edt_work);
        edtHour = findViewById(R.id.edt_hour);
        edtMinute = findViewById(R.id.edt_minute);
        txtDate = findViewById(R.id.tv_date);
        btnAddWork = findViewById(R.id.btn_add_work);

        arrayWork = new ArrayList<>();
        //Khai bao adapter, dua mang du lieu vao adpater
        arrAdater = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayWork);


        //Lay ngay gio he thong
        Date currentDate = Calendar.getInstance().getTime();
        //Format theo dinh dang dd/MM/yyyy
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText("Hon nay: " + simpleDateFormat.format(currentDate));

        //Thêm chức năng lưu trữ dữ liệu khi thoát chương triình
        loadTasks(); //Gọi hàm đọc dữ liệu khi khởi động app
        //Dua adapter co du lieu len listview
        lv1.setAdapter(arrAdater);
        //Su kien click nut them cong viec
        btnAddWork.setOnClickListener(new View.OnClickListener() { //onClickListener là một interface
            @Override
            public void onClick(View v) {
                //Neu 1 trong 3 edt khong co noi dung thi hien len thong bao bang hop thoai Dialog
                if (edtWork.getText().toString().equals("") || edtHour.getText().toString().equals("") || edtMinute.getText().toString().equals("")) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Infor missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    //Lấy nội dung công việc và thời gian từ EditText và đưa vào mảng Arraywork, cập nhật lại Adapter
                    String str = edtWork.getText().toString() + " - " + edtHour.getText().toString() + ":" + edtMinute.getText().toString();
                    //Để thêm dữ liệu vào trong listview chúng ta cần hai bước:
                    //Bước 1: thêm dữ liệu vào trong mảng
                    arrayWork.add(str);
                    //Bước 2: cập nhật lại adapter
                    arrAdater.notifyDataSetChanged();
                    //Lưu dữ liệu sau khi thêm
                    saveTasks();
                    edtWork.setText("");
                    edtHour.setText("");
                    edtMinute.setText("");
                }
            }
        });

        // --- THÊM CHỨC NĂNG XÓA KHI CLICK VÀO MỤC TRONG LISTVIEW ---
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // position là chỉ mục (index) của mục được click trong danh sách arrayWork

                // Hiển thị Dialog xác nhận xóa (nên có để tránh xóa nhầm)
                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc chắn muốn xóa công việc này không?\n" + arrayWork.get(position)) // Hiển thị nội dung mục sẽ xóa
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Bước 1: Xóa dữ liệu khỏi mảng arrayWork
                                arrayWork.remove(position);

                                // Bước 2: Cập nhật lại Adapter để ListView hiển thị dữ liệu mới
                                arrAdater.notifyDataSetChanged();

                                // --- THÊM CHỨC NĂNG LƯU TRỮ DỮ LIỆU: LƯU SAU KHI XÓA ---
                                saveTasks(); // Lưu lại dữ liệu sau khi xóa

                                // Thông báo đã xóa thành công (tùy chọn)
                                Toast.makeText(MainActivity.this, "Đã xóa công việc", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Không", null) // Nút Không chỉ đóng dialog
                        .show();
            }
        });
    }


    private void loadTasks() {
        // Lấy đối tượng SharedPreferences để đọc dữ liệu
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Đọc chuỗi dữ liệu từ SharedPreferences. Tham số thứ 2 là giá trị mặc định nếu không tìm thấy key
        String tasksString = prefs.getString(TASKS_KEY, null);

        // Kiểm tra xem có dữ liệu đã lưu không
        if (tasksString != null && !tasksString.isEmpty()) {
            // Tách chuỗi đã lưu thành mảng các chuỗi dựa vào ký tự phân tách
            String[] tasksArray = tasksString.split(ITEM_DELIMITER);

            // Chuyển mảng các chuỗi thành ArrayList
            arrayWork.addAll(Arrays.asList(tasksArray));

            // Lưu ý: Nếu dùng ArrayList.add() từng cái thì cũng được, nhưng addAll nhanh hơn
            // for (String task : tasksArray) {
            //     arrayWork.add(task);
            // }
        }
    }

    // --- HÀM LƯU DỮ LIỆU VÀO SharedPreferences ---
    private void saveTasks() {
        // Lấy đối tượng SharedPreferences để chỉnh sửa
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Chuyển danh sách ArrayList thành một chuỗi duy nhất bằng cách nối các phần tử với ký tự phân tách
        // Sử dụng String.join() từ Java 8 trở lên, hoặc vòng lặp/StringBuilder nếu cần tương thích cũ hơn
        String tasksString = String.join(ITEM_DELIMITER, arrayWork);

        // Lưu chuỗi vào SharedPreferences với key TASKS_KEY
        editor.putString(TASKS_KEY, tasksString);

        // Áp dụng các thay đổi (lưu dữ liệu)
        // apply() lưu không đồng bộ (nhanh hơn, khuyến khích dùng)
        // commit() lưu đồng bộ (chờ lưu xong mới chạy tiếp)
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTasks(); // Lưu dữ liệu khi activity tạm dừng
    }

    // --- (Tùy chọn) Lưu dữ liệu khi Activity bị hủy hẳn (ví dụ: khi người dùng đóng app từ Recent Apps hoặc hệ thống cần giải phóng bộ nhớ) ---
    // onPause() thường đủ, nhưng onDestory() cũng là một nơi an toàn để lưu lần cuối
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveTasks(); // Lưu dữ liệu khi activity bị hủy
    }
}
