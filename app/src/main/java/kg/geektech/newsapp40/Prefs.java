package kg.geektech.newsapp40;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings.GG", Context.MODE_PRIVATE);
    }

    public void saveBoardState() {
        preferences.edit().putBoolean("board_state", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("board_state", false);
    }

    public void saveAvatarName(String name) {
        preferences.edit().putString("avatar_name", name).apply();
    }

    public String isAvatarName() {
        return preferences.getString("avatar_name", "");
    }

    public void saveAvatar(Uri uri) {
        preferences.edit().putString("avatar", String.valueOf(uri)).apply();
    }

    public Uri isAvatar() {
        return Uri.parse(preferences.getString("avatar", ""));
    }

    public void cleanCash(){
        preferences.edit().clear().apply();


    }
}
