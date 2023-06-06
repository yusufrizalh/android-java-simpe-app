package id.inixindo.simpleandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.inixindo.simpleandroid.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 2. mendeklarasikan nilai awal
    int myNumber = 0;
    private TextView txt_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. mengenali semua komponen yang ada di layout
        txt_number = findViewById(R.id.txt_number);
        Button btn_increase = findViewById(R.id.btn_increase);
        Button btn_decrease = findViewById(R.id.btn_decrease);
        Button btn_reset = findViewById(R.id.btn_reset);
        Button btn_intent = findViewById(R.id.btn_intent);

        // 3. memberi event/listener pada komponen
        // dengan implement langsung pada komponen

        // event click listener pada btn_increase
        btn_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNumber = myNumber + 1;
                // 4. menampilkan hasil harus dalam format String
                txt_number.setText(String.valueOf(myNumber));
                // txt_number.setText(Integer.toString(myNumber));
            }
        });

        // event click listener pada btn_decrease
        // dengan implement melalui class
        btn_decrease.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_intent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_decrease:
                myNumber = myNumber - 1;
                txt_number.setText(String.valueOf(myNumber));
                break;
            case R.id.btn_reset:
                myNumber = 0;
                txt_number.setText(String.valueOf(myNumber));
                break;
            case R.id.btn_intent:
                // memanggil perintah untuk berpindah activity
                moveToSecondaryActivity();
                break;
        }
    }

    private void moveToSecondaryActivity() {
        String data = String.valueOf(txt_number.getText());
        Log.d("TAG", "moveToSecondaryActivity: " + data);   // cek nilai

        // perintah berpindah activity
        Intent myIntent = new Intent(MainActivity.this, SecondaryActivity.class);
        myIntent.putExtra("data", data);

        startActivity(myIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart Activity", Toast.LENGTH_SHORT).show();
        // reconnect server
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart Activity", Toast.LENGTH_SHORT).show();
        // perintah
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause Activity", Toast.LENGTH_SHORT).show();
        // perintah
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop Activity", Toast.LENGTH_SHORT).show();
        // disconnect server
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy Activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Toast.makeText(this, "Menu Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_capture:
                Toast.makeText(this, "Menu Capture", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_linked:
                Toast.makeText(this, "Menu Linked Devices", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_message:
                Toast.makeText(this, "Menu Starred Messages", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}