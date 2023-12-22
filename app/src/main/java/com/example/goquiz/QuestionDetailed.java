package com.example.goquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goquiz.databinding.ActivityDetailedQuestionBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDetailed extends AppCompatActivity {
    private ActivityDetailedQuestionBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();

        String cauhoi = intent.getStringExtra("cauhoi");
        binding.Question.setText(cauhoi);

        int dokho = intent.getIntExtra("dokho",0);
        String level = "Dễ";
        if(dokho == 1){
            level = "Khó";
        }
        binding.DoKho.setText(level);

        // Nhận các đáp án tùy chọn
        String[] tuychonn = intent.getStringArrayExtra("tuychon");
        binding.AnswerA.setText(tuychonn[0]);
        binding.AnswerB.setText(tuychonn[1]);
        binding.AnswerC.setText(tuychonn[2]);
        binding.AnswerD.setText(tuychonn[3]);

        int dapan = intent.getIntExtra("dapan",0);
        // Thay đổi background đáp án đúng
        if(dapan == 1){
            binding.AnswerA.setBackgroundResource(R.drawable.right_op);
        } else if (dapan == 2) {
            binding.AnswerB.setBackgroundResource(R.drawable.right_op);
        }else if (dapan == 3){
            binding.AnswerC.setBackgroundResource(R.drawable.right_op);
        }else if (dapan == 4){
            binding.AnswerD.setBackgroundResource(R.drawable.right_op);
        }
        binding.CorrectAnswer.setText("Đáp án đúng: " + tuychonn[dapan-1]);
    }
}
