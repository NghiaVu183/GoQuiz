package com.example.goquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.goquiz.databinding.ActivityCategoriesBinding;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    private ActivityCategoriesBinding binding;
    private ArrayList<Category> categories;
    private int dokho = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        categories = new ArrayList<>();
        categories.add(new Category("Toán học", R.drawable.math, "Các câu hỏi xoay quanh số học, hình học và giải tích"));
        categories.add(new Category("Vật lý", R.drawable.physics, "Các câu hỏi về chuyển động, ánh sáng, và các lực tương tác"));
        categories.add(new Category("Hóa học", R.drawable.chemistry, "Các câu hỏi xoay quanh cấu trúc của chất và các phản ứng hóa học"));
        categories.add(new Category("Sinh học", R.drawable.biology, "Các câu hỏi về cấu trúc và chức năng của các hệ sinh thái và hệ cơ thể"));
        categories.add(new Category("Lịch sử", R.drawable.history, "Các câu hỏi về sự phát triển của con người và các sự kiện lịch sử quan trọng"));
        categories.add(new Category("Địa lý", R.drawable.geography,"Các câu hỏi xoay quanh địa hình, khí hậu, và dân cư các khu vực"));
        categories.add(new Category("Công nghệ", R.drawable.technology,"Các câu hỏi về ứng dụng kiến thức khoa học để giải quyết vấn đề thực tế"));
        categories.add(new Category("Thiên văn học", R.drawable.astronomy,"Các câu hỏi xoay quanh vật thể ngoài trái đất và cấu trúc của vũ trụ"));

        CategoriesAdapter adapter = new CategoriesAdapter(categories);
        binding.CategoriesListView.setAdapter(adapter);
        binding.CategoriesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category selected = categories.get(position);
                Intent intent = new Intent(Categories.this, Ingame.class);
                intent.putExtra("category",selected.getName());
                intent.putExtra("level",dokho);
                startActivity(intent);
            }
        });

        int total = ScorePref.saveScore(this);
        binding.score.setText(total + " pts");

        binding.level.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                dokho = 1;
            }else{
                dokho = 0;
            }
        });

        binding.QuestionsButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(Categories.this, QuestionList.class);
            startActivity(intent1);
        });
    }
}