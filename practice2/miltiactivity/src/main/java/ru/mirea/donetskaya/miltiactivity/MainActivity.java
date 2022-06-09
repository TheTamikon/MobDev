package ru.mirea.donetskaya.miltiactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //private Button newActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        newActivity = findViewById(R.id.button);
        View.OnClickListener ocBtnNew = new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        };

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
        buttomCancel.setOnClickListener(ocBtnCancel);*/
    }

}