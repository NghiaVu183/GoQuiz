package com.example.goquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goquiz.databinding.ActivityIngameBinding;

import java.util.ArrayList;
import java.util.List;

public class Ingame extends AppCompatActivity {
    private ActivityIngameBinding binding;
    private ArrayList<Question> filteredQuestions;
    private int countRightAns = 0;
    private int score = 0;
    public String category;
    public int level = 0;

    private int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIngameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        level = intent.getIntExtra("level",0);

        filteredQuestions = QuestionList.questionList();
        // Hàm để lấy danh sách câu hỏi dựa trên chủ đề và độ khó
        filteredQuestions = getQuestionsByCategoryAndDifficulty(category, level);
        displayQuestion();
    }
    private ArrayList<Question> getQuestionsByCategoryAndDifficulty(String category, int level){
        ArrayList<Question> filteredQuestions = new ArrayList<>();
        for (Question ques : QuestionList.questionList){
            if(ques.getCategory().equals(category) && ques.getDifficultyLevel() == level){
                filteredQuestions.add(ques);
            }
        }
        // Phương thức xáo trộn danh sách câu hỏi đã lọc
        Collections.shuffle(filteredQuestions);
        return filteredQuestions;
    }
    private void displayQuestion(){
        if(questionIndex < filteredQuestions.size()){
            // Hiển thị câu hỏi và các tùy chọn
            Question currentQuestion = filteredQuestions.get(questionIndex);
            binding.Question.setText(currentQuestion.getQuestionText());
            // Xáo trộn thứ tự các đáp án
            List<String> answerOptions = new ArrayList<>(currentQuestion.getAnswerOptions());
            Collections.shuffle(answerOptions);
            binding.AnswerA.setText(answerOptions.get(0));
            binding.AnswerB.setText(answerOptions.get(1));
            binding.AnswerC.setText(answerOptions.get(2));
            binding.AnswerD.setText(answerOptions.get(3));

            // Sự kiện cho nút trả lời
            binding.AnswerButton.setOnClickListener(v -> {
                handleAnswer(currentQuestion,answerOptions);
                binding.Answers.clearCheck();
            });
        }else{ // Hết câu hỏi
            Toast.makeText(this, "Bạn đã trả lời hết câu hỏi!" , Toast.LENGTH_SHORT).show();
            toResult();
        }
    }
    // Hàm xử lý câu trả lời
    private void handleAnswer(Question currentQuestion, List<String> answerOptions){
        // Lấy ID Radio Button được chọn
        int selectedRadioButtonId = binding.Answers.getCheckedRadioButtonId();
        // Kiểm tra xem Radio Button có được chọn không
        if(selectedRadioButtonId != -1){
            // Lấy index của Radio Button đó
            int selectedAnswerIndex = binding.Answers.indexOfChild(findViewById(selectedRadioButtonId));
            // Lấy đáp án đúng của câu hỏi hiện tại
            String correct = currentQuestion.getAnswerOptions().get(currentQuestion.getCorrectAnswerIndex()-1);
            String check = answerOptions.get(selectedAnswerIndex);
            // Kiểm tra xem người dùng trả lời đúng hay sai
            if(check.equals(correct)){
                Toast.makeText(this, "Đáp án đúng", Toast.LENGTH_SHORT).show();
                if(currentQuestion.getDifficultyLevel() == 0){
                    score += 1;
                }else{
                    score += 2;
                }
                countRightAns++;
                questionIndex++;
                displayQuestion();
            }else{
                Toast.makeText(this, "Đáp án sai", Toast.LENGTH_SHORT).show();
                toResult();
            }
        }else{
            Toast.makeText(this, "Vui lòng chọn một đáp án", Toast.LENGTH_SHORT).show();
        }
    }
    private void toResult(){
        Intent intent = new Intent(Ingame.this,Result.class);
        intent.putExtra("category", category);
        intent.putExtra("level",level);
        intent.putExtra("count",countRightAns);
        intent.putExtra("score",score);
        startActivity(intent);
    }
}