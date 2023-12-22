package com.example.goquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class ScorePref {
    public static int saveScore(Context context) {
        SharedPreferences saveScore = context.getSharedPreferences("Score", Context.MODE_PRIVATE);
        return saveScore.getInt("score",0);
    }

    public static void updateScore(Context context, int score1play) {
        int score1 = saveScore(context);
        score1 += score1play;

        SharedPreferences.Editor editor = context.getSharedPreferences("Score", Context.MODE_PRIVATE).edit();
        editor.putInt("score", score1);
        editor.apply();
    }
}
