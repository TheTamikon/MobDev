package ru.mirea.donetskaya.mireaproject.ui.player;

import static ru.mirea.donetskaya.mireaproject.MainActivity.preferences;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.mirea.donetskaya.mireaproject.R;
import ru.mirea.donetskaya.mireaproject.databinding.FragmentBrowserBinding;
import ru.mirea.donetskaya.mireaproject.databinding.FragmentPlayerBinding;
import ru.mirea.donetskaya.mireaproject.ui.browser.WebClient;

public class PlayerFragment extends Fragment {

    private FragmentPlayerBinding binding;
    private Button playBtn;
    private Button stopBtn;
    MusicService service;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlayerBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();
        String backKey = getString(R.string.KEY_BACKGROUND);
        String backColor = preferences.getString(backKey, "white");
        root.setBackgroundColor(Color.parseColor(backColor));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}