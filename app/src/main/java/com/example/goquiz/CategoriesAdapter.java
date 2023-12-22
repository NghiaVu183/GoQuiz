package com.example.goquiz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesAdapter extends BaseAdapter {
    private ArrayList<Category> categories;

    public CategoriesAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem;
        if (view == null) {
            viewItem = View.inflate(viewGroup.getContext(), R.layout.category_view, null);
        } else {
            viewItem = view;
        }
        Category category = categories.get(i);
        // Ảnh chủ đề
        ((ImageView) viewItem.findViewById(R.id.CategoriesImage)).setImageResource(category.getImage());
        // Tên chủ đề
        ((TextView) viewItem.findViewById(R.id.CategoriesName)).setText(category.getName());
        // Mô tả về chủ đề
        ((TextView) viewItem.findViewById(R.id.CategoriesDescription)).setText(String.valueOf(category.getDescription()));
        return viewItem;
    }
}
