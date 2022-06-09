package ru.mirea.donetskaya.practice3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String dateTime = intent.getStringExtra("key");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(dateTime);
    }
}
