package com.example.firebase1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase1.adapter.PlayerAdapter;
import com.example.firebase1.models.Player;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref;
    List<Player> players;
    PlayerAdapter adapter;
    RecyclerView recyclerView;
    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref = FirebaseDatabase.getInstance().getReference("players");
        players = new ArrayList<>();
        adapter = new PlayerAdapter(this, players);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("MyFirebaseApp", "onDataChange called!"); // <-- Thêm dòng này
                Log.d("MyFirebaseApp", "Snapshot exists: " + snapshot.exists()); // <-- Thêm dòng này
                Log.d("MyFirebaseApp", "Children count: " + snapshot.getChildrenCount()); // <-- Thêm dòng này
                players.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Log.d("MyFirebaseApp", "Processing child with key: " + snap.getKey()); // <-- Thêm dòng này
                    Player p = snap.getValue(Player.class);
                    players.add(p);
                }
                adapter.notifyDataSetChanged();
                Log.d("MyFirebaseApp", "notifyDataSetChanged called."); // <-- Thêm dòng này
            }

            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MyFirebaseApp", "Database Error: " + error.getMessage(), error.toException()); // <-- Thêm dòng này (Log Error)
                Toast.makeText(MainActivity.this, "Lỗi đọc dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("MyFirebaseApp", "ValueEventListener attached to 'players' node.");

        btnAdd.setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        // Tạo AlertDialog nhập tên, quê quán, tự sinh member_code, push lên Firebase
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm hội viên mới");

        // Inflate layout tùy chỉnh cho dialog (sử dụng lại dialog_edit_player.xml hoặc tạo mới)
        // Nếu dùng lại dialog_edit_player, cần ẩn hoặc sửa behavior của edtMemberCode
        // Tốt nhất nên tạo một layout riêng cho Thêm mới để dễ quản lý
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_player, null); // Cần tạo dialog_add_player.xml
        builder.setView(dialogView);

        // Ánh xạ các EditText trong dialog layout
        EditText edtMemberCode = dialogView.findViewById(R.id.edtAddMemberCode);
        EditText edtUsername = dialogView.findViewById(R.id.edtAddUsername);
        EditText edtHometown = dialogView.findViewById(R.id.edtAddHometown);
        // ... thêm các EditText khác cho các thuộc tính khác khi thêm mới

        // Cấu hình nút Save và Cancel
        builder.setPositiveButton("Thêm", (dialog, which) -> {
            // Lấy dữ liệu từ EditTexts
            String memberCode = edtMemberCode.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String hometown = edtHometown.getText().toString().trim();
            // ... lấy dữ liệu cho các trường khác

            // Kiểm tra dữ liệu bắt buộc
            if (memberCode.isEmpty() || username.isEmpty()) {
                Toast.makeText(this, "Mã và Tên hội viên không được trống", Toast.LENGTH_SHORT).show();
                return; // Không thêm nếu thiếu thông tin bắt buộc
            }

            // TODO: Kiểm tra memberCode có bị trùng trên Firebase không trước khi thêm (Tùy chọn nâng cao)

            // Tạo đối tượng Player mới
            // Điền đủ các trường, các trường chưa có thì gán giá trị mặc định (null, 0, "")
            Player newPlayer = new Player(memberCode, username, "", // avatar (để trống hoặc default)
                    "", // birthday
                    hometown,
                    "", // residence
                    0.0, // rating_single
                    0.0); // rating_double

            // Lưu đối tượng Player lên Firebase Realtime Database
            // Sử dụng memberCode làm key con dưới node "players"
            ref.child(memberCode).setValue(newPlayer)
                    .addOnSuccessListener(aVoid -> {
                        // Xử lý khi ghi dữ liệu thành công
                        Toast.makeText(MainActivity.this, "Đã thêm hội viên mới", Toast.LENGTH_SHORT).show();
                        // Dữ liệu sẽ tự động hiển thị trên RecyclerView nhờ ValueEventListener
                    })
                    .addOnFailureListener(e -> {
                        // Xử lý khi ghi dữ liệu thất bại
                        Toast.makeText(MainActivity.this, "Lỗi thêm hội viên: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            // Nếu bạn muốn Firebase tự sinh key duy nhất thay vì dùng memberCode nhập tay:
            // DatabaseReference newPlayerRef = ref.push(); // Firebase tự sinh key UID ngẫu nhiên
            // newPlayer.setMember_code(newPlayerRef.getKey()); // Gán key vừa sinh vào object Player (nếu cần)
            // newPlayerRef.setValue(newPlayer); // Lưu object vào key vừa sinh

            // Lưu ý: Với cấu trúc bạn đưa ra ("MBR001", "MBR002" làm key), bạn cần sử dụng cách 1: ref.child(memberCode).setValue(newPlayer);
            // Và bạn cần có logic để đảm bảo memberCode là duy nhất khi người dùng nhập.
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

        builder.show();

    }
}