package pl.leniec.pjatk_smb1.models;


import android.content.SharedPreferences;

public class Options {
    static public final String DEFAULT_TEXT_COLOR = "blue";
    static public final Integer DEFAULT_TEXT_SIZE = 15;

    private SharedPreferences sharedPreferences;
    private String textColor;
    private Integer textSize;

    public Options(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        textColor = sharedPreferences.getString("textColor", DEFAULT_TEXT_COLOR);
        textSize = sharedPreferences.getInt("textSize", DEFAULT_TEXT_SIZE);
    }

    public void save() {
        sharedPreferences
                .edit()
                .putString("textColor", textColor)
                .putInt("textSize", textSize)
                .apply();
    }

    public void setToDefaults() {
        textColor = DEFAULT_TEXT_COLOR;
        textSize = DEFAULT_TEXT_SIZE;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public Integer getTextSize() {
        return textSize;
    }

    public void setTextSize(Integer textSize) {
        this.textSize = textSize;
    }
}
