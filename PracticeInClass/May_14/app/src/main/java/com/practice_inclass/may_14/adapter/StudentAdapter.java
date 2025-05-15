package com.practice_inclass.may_14.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice_inclass.may_14.R;
import com.practice_inclass.may_14.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private Context context;

    public StudentAdapter(Context context, List<Student> list) {
        this.context = context;
        this.studentList = list;
    }



    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.txtName.setText(student.getName());
        holder.txtAge.setText("Tuổi: " + student.getAge());
        holder.txtClassroom.setText("Lớp: " + student.getClassroom());

        // Click item
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Bạn chọn: " + student.getName(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
    //Dinh nghia lop long ben trong lop bang phuong tinh
    public static class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtAge, txtClassroom;
        //Tham chieu toi item de do len
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAge = itemView.findViewById(R.id.txt_age);
            txtClassroom = itemView.findViewById(R.id.txt_classroom);
        }
    }
}
