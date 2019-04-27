package com.bulgarian.culture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = findViewById(R.id.send);
        TextView result = findViewById(R.id.result);
        SeekBar seekBar = findViewById(R.id.seekBar);

        send.setOnClickListener(view -> {
            int rand = new Random().nextInt(seekBar.getProgress());
            result.setText(String.valueOf(rand));
        });


    }
}
