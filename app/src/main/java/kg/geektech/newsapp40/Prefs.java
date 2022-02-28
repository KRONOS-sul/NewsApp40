package kg.geektech.newsapp40;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings.GG", Context.MODE_PRIVATE);
    }

    public void saveBoardState(){
        preferences.edit().putBoolean("board_state", true).apply();
    }

    public boolean isBoardShown(){
        return preferences.getBoolean("board_state", false);
    }



}
