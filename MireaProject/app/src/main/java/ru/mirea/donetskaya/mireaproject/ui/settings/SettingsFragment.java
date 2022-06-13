package ru.mirea.donetskaya.mireaproject.ui.settings;
import static ru.mirea.donetskaya.mireaproject.MainActivity.preferences;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ru.mirea.donetskaya.mireaproject.R;
import ru.mirea.donetskaya.mireaproject.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private String KEY_HOMEPAGE;
    private String KEY_NAME;
    private String KEY_BACKGROUNG;
    private EditText txtEditHomeP, txtEditNameP;
    View root;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        KEY_HOMEPAGE = getString(R.string.KEY_HOMEPAGE);
        KEY_NAME = getString(R.string.KEY_NAME);
        KEY_BACKGROUNG = getString(R.string.KEY_BACKGROUND);
        binding = FragmentSettingsBinding.inflate(
                inflater,
                container,
                false
        );
        root = binding.getRoot();
        txtEditHomeP = root.findViewById(R.id.txtEditHomePage);
        txtEditHomeP.setText(
                preferences
                        .getString(
                                KEY_HOMEPAGE,
                                "https://mirea.ru"
                        )
        );
        txtEditNameP = root.findViewById(R.id.txtEditName);
        txtEditNameP.setText(
                preferences
                        .getString(
                                KEY_NAME,
                                "Донецкая Ангелина Алексеевна"
                        )
        );
        root.findViewById(R.id.buttonSave).setOnClickListener(this::onClickButSave);
        root.findViewById(R.id.buttonLight).setOnClickListener(this::onClickButLight);
        root.findViewById(R.id.buttonDark).setOnClickListener(this::onClickButDark);
        String backKey = getString(R.string.KEY_BACKGROUND);
        String backColor = preferences.getString(backKey, "white");
        root.setBackgroundColor(Color.parseColor(backColor));
        return root;
    }

    public void onClickButSave(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_HOMEPAGE, txtEditHomeP.getText().toString());
        editor.putString(KEY_NAME, txtEditNameP.getText().toString());
        editor.apply();
    }
    public void onClickButLight(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_BACKGROUNG, "white");
        editor.apply();
        root.setBackgroundColor(Color.parseColor("white"));
    }
    public void onClickButDark(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_BACKGROUNG, "#373737");
        editor.apply();
        root.setBackgroundColor(Color.parseColor("#373737"));
    }
}