package ru.mirea.donetskaya.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long dateInMillis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(new Date(dateInMillis));

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(dateString);
    }

    public void onClickButton(View view){
        TextView text = (TextView) findViewById(R.id.textView2);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("key", text.getText().toString());
        startActivity(intent);
    }
}