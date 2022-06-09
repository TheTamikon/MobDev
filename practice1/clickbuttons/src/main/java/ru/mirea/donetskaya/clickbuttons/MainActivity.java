package ru.mirea.donetskaya.clickbuttons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvOut;
    private Button buttomOk;
    private Button buttomCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut = (TextView) findViewById(R.id.tvOut);
        buttomOk = findViewById(R.id.btnOk);
        buttomCancel = findViewById(R.id.btnCancel);
        View.OnClickListener ocBtnOk = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tvOut.setText("Нажата кнопка ОК");
            }
        };
        buttomOk.setOnClickListener(ocBtnOk);
        View.OnClickListener ocBtnCancel = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tvOut.setText("Нажата кнопка Cancel");
            }
        };
        buttomCancel.setOnClickListener(ocBtnCancel);
    }
}