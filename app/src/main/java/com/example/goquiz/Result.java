package com.example.goquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.goquiz.databinding.ActivityResultBinding;

public class Result extends AppCompatActivity {
    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String category1 = intent.getStringExtra("category");
        int level1 = intent.getIntExtra("level",0);
        int countRight = intent.getIntExtra("count",0);
        int score1 = intent.getIntExtra("score",0);
        binding.ScoreTitle.setText("Số câu trả lời đúng: " + countRight);
        binding.Score.setText(String.valueOf(score1));

        ScorePref.updateScore(this,score1);

        binding.ReplayButton.setOnClickListener(v -> {
            Intent intentReplay = new Intent(Result.this,Ingame.class);
            intentReplay.putExtra("category", category1);
            intentReplay.putExtra("level",level1);
            startActivity(intentReplay);
        });

        binding.FinishButton.setOnClickListener(v -> {
            Intent intentFinish = new Intent(Result.this, MainActivity.class);
            startActivity(intentFinish);
        });

        binding.ShareButton.setOnClickListener(v -> {
            String shareText = "Tôi đã đạt được " + score1 + " điểm trong GoQuiz!";
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");
            intentShare.putExtra(Intent.EXTRA_TEXT,shareText);
            if (intentShare.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(intentShare, "Chia sẻ điểm số qua"));
            } else {
                // Xử lý khi không có ứng dụng chia sẻ nào khả dụng
                Toast.makeText(this, "Không tìm thấy ứng dụng để chia sẻ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}