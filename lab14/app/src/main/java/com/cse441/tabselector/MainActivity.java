package com.cse441.tabselector;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Ví dụ: dùng SharedPreferences để kiểm tra xem người dùng đã vào màn hình chính chưa
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_LAUNCH_COMPLETED = "firstLaunchCompleted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Logic để quyết định hiển thị layout nào
        // Ví dụ: Kiểm tra xem đây có phải lần khởi chạy đầu tiên không
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean firstLaunchCompleted = settings.getBoolean(KEY_FIRST_LAUNCH_COMPLETED, false);

        if (!firstLaunchCompleted) {
            // Đây là lần đầu hoặc người dùng chưa hoàn thành bước nào đó
            // Hiển thị layout chào mừng (activity_welcome.xml)
            setContentView(R.layout.tab1);

            Button buttonGoToMain = findViewById(R.id.buttonGoToMain);
            buttonGoToMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Đánh dấu đã hoàn thành màn hình chào mừng
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean(KEY_FIRST_LAUNCH_COMPLETED, true);
                    editor.apply();

                    // Load lại MainActivity với layout chính
                    // Hoặc bạn có thể chỉ cần load layout chính ngay tại đây
                    // và thiết lập lại các view nếu cần.
                    // Cách đơn giản nhất là gọi lại setContentView:
                    setContentView(R.layout.activity_main);
                    initializeMainLayout(); // Hàm để khởi tạo các view của activity_main
                }
            });

        } else {
            // Người dùng đã qua màn hình chào mừng, hiển thị layout chính (activity_main.xml)
            setContentView(R.layout.activity_main);
            initializeMainLayout(); // Hàm để khởi tạo các view của activity_main
        }
    }

    // Hàm này để khởi tạo các UI elements của layout activity_main.xml
    private void initializeMainLayout() {
        // Ví dụ:
        // TextView textViewMain = findViewById(R.id.textViewMain);
        // if (textViewMain != null) {
        //     textViewMain.setText("Đây là màn hình chính!");
        // }
        // ... các khởi tạo khác cho activity_main
        System.out.println("Đã vào initializeMainLayout");
    }
}