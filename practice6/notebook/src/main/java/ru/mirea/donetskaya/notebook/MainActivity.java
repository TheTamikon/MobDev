package ru.mirea.donetskaya.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextWrite;
    private EditText editTextRead;
    private String fileName;
    private String text;
    private SharedPreferences preferences;
    private String savedText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextWrite = findViewById(R.id.editTextWrite);
        editTextRead = findViewById(R.id.editTextRead);
        preferences = getPreferences(MODE_PRIVATE);

        String filePath = preferences.getString(savedText, "Empty");
        FileInputStream fin = null;
        try {
            fin = openFileInput(filePath);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String textRead = new String(bytes);
            editTextRead.setText(textRead);
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClick(View view) {
        fileName = editTextName.getText().toString();
        text = editTextWrite.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(savedText, fileName);
        editor.apply();

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();

            Toast.makeText(this, "Текст успешно сохранён в файл " + fileName, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}