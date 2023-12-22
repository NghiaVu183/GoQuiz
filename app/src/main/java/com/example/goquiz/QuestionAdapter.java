package com.example.goquiz;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class QuestionAdapter extends BaseAdapter {
    private List<Question> listQuestion;
    public QuestionAdapter(List<Question> listQuestion){
        this.listQuestion = listQuestion;
    }

    @Override
    public int getCount() {
        return listQuestion.size();
    }

    @Override
    public Object getItem(int position) {
        return listQuestion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listQuestion.get(position).getStt();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem; // View của 1 sản phẩm
        if(view == null){
            viewItem = View.inflate(viewGroup.getContext(),R.layout.question_view,null);
        }else {
            viewItem = view;
        }
        Question question = listQuestion.get(i);
        // Câu hỏi
        ((TextView) viewItem.findViewById(R.id.cauhoi)).setText(question.getQuestionText());
        // Thứ tự
        ((TextView) viewItem.findViewById(R.id.stt)).setText(String.valueOf(question.getStt()) + ".");
        return viewItem;
    }
}
