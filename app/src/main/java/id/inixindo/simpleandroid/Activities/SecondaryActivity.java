package id.inixindo.simpleandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import id.inixindo.simpleandroid.R;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // memunculkan back button

        // menerima data dari Intent
        // String data = getIntent().getExtras().getString("data");
        String data = getIntent().getStringExtra("data");

        // menampilkan data dari Intent
        TextView txt_secondary = findViewById(R.id.txt_secondary);
        txt_secondary.setText(String.valueOf(data));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SecondaryActivity.this, MainActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}