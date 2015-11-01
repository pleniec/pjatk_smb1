package pl.leniec.pjatk_smb1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.leniec.pjatk_smb1.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void goToList(View view) {
        startActivity(new Intent(this, ProductsActivity.class));
    }

    public void goToOptions(View view) {
        startActivity(new Intent(this, OptionsActivity.class));
    }
}
