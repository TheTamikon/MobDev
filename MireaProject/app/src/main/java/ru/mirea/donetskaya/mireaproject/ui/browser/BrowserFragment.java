package ru.mirea.donetskaya.mireaproject.ui.browser;
import static ru.mirea.donetskaya.mireaproject.MainActivity.preferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.mirea.donetskaya.mireaproject.R;
import ru.mirea.donetskaya.mireaproject.databinding.FragmentBrowserBinding;

public class BrowserFragment extends Fragment {

    private WebView webView;
    private String startPage = "https://www.mirea.ru/";
    private FragmentBrowserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBrowserBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();
        String keyPage = getString(R.string.KEY_HOMEPAGE);
        String homePage = preferences.getString(keyPage, startPage);
        webView = root.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(homePage);
        webView.setWebViewClient(new WebClient());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}