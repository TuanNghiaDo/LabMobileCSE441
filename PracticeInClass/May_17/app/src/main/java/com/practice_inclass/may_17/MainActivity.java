package com.practice_inclass.may_17;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu); // Sử dụng tên file menu của bạn
        Log.d(TAG, "onCreateOptionsMenu called");
        return true; // true để hiển thị menu
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); // Lấy ID của item được chọn
        Log.d(TAG, "onOptionsItemSelected called for item ID: " + itemId);

        // Kiểm tra ID và thực hiện hành động tương ứng
        if (itemId == R.id.action_refresh) {
            // Xử lý khi nhấn Refresh
            Toast.makeText(this, "Đang làm mới...", Toast.LENGTH_SHORT).show();
            // Thêm code làm mới dữ liệu ở đây...
            return true; // Đã xử lý

        } else if (itemId == R.id.action_settings) {
            // Xử lý khi nhấn Settings
            Toast.makeText(this, "Mở màn hình Cài đặt...", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(this, SettingsActivity.class);
            // startActivity(intent);
            return true; // Đã xử lý

        } else if (itemId == R.id.action_help) {
            // Xử lý khi nhấn Help
            Toast.makeText(this, "Mở màn hình Trợ giúp...", Toast.LENGTH_SHORT).show();
            // Thêm code hiển thị trợ giúp ở đây...
            return true; // Đã xử lý

        } else {
            // Nếu không phải các item trên, để lớp cha xử lý (quan trọng cho nút Up/Home)
            return super.onOptionsItemSelected(item);
        }
    }

}