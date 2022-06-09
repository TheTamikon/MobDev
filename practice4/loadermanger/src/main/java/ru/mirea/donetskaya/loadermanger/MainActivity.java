package ru.mirea.donetskaya.loadermanger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public final String TAG = this.getClass().getSimpleName();
    private int LoaderID = 1234;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARG_WORD, "mirea");
        getSupportLoaderManager().initLoader(LoaderID, bundle, this);
        editText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        if (i == LoaderID) {
        Toast.makeText(this, "onCreateLoader:" + i, Toast.LENGTH_SHORT).show();
        return new MyLoader(this, bundle);
    }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
        Log.d(TAG, "onLoadFinished" + s);
        Toast.makeText(this, "onLoadFinished:" + s, Toast.LENGTH_SHORT).show();
            editText.setText(s);
        }
    }
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARG_WORD, shake());
        getSupportLoaderManager().restartLoader(LoaderID, bundle, this);
    }
    public String shake() {
        String text = editText.getText().toString();
        char[] arr = text.toCharArray();
        Random rnd = new Random();
        for(int i = 0; i < arr.length; i++) {
            int index = rnd.nextInt(i + 1);
            char a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
        String result = new String(arr);
        return result;
    }
}

