package pl.leniec.pjatk_smb1.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import pl.leniec.pjatk_smb1.R;
import pl.leniec.pjatk_smb1.models.Options;

public class OptionsActivity extends Activity {
    private Options options;

    private RadioGroup colorRadio;
    private SeekBar sizeBar;
    private TextView sampleText;

    public void saveOptions(View view) {
        options.save();
    }

    public void setToDefaultOptions(View view) {
        options.setToDefaults();
        updateView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        options = new Options(getSharedPreferences("options", Context.MODE_PRIVATE));
        colorRadio = (RadioGroup)findViewById(R.id.colorRadio);
        sizeBar = (SeekBar)findViewById(R.id.sizeBar);
        sampleText = (TextView)findViewById(R.id.sampleText);

        updateView();
        updateSampleText();

        colorRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                options.setTextColor(checkedRadioButton.getText().toString());
                updateSampleText();
            }
        });

        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                options.setTextSize(progress + Options.DEFAULT_TEXT_SIZE);
                sampleText.setTextSize(options.getTextSize());
                updateSampleText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateView() {
        sizeBar.setProgress(options.getTextSize() - Options.DEFAULT_TEXT_SIZE);
        switch(options.getTextColor()) {
            case "blue":
                colorRadio.check(R.id.blue);
                break;
            case "green":
                colorRadio.check(R.id.green);
                break;
            case "yellow":
                colorRadio.check(R.id.yellow);
                break;
            case "red":
                colorRadio.check(R.id.red);
                break;
        }
    }

    private void updateSampleText() {
        sampleText.setTextSize(options.getTextSize());
        switch(options.getTextColor()) {
            case "blue":
                sampleText.setTextColor(Color.BLUE);
                break;
            case "green":
                sampleText.setTextColor(Color.GREEN);
                break;
            case "yellow":
                sampleText.setTextColor(Color.YELLOW);
                break;
            case "red":
                sampleText.setTextColor(Color.RED);
                break;
        }
    }
}
