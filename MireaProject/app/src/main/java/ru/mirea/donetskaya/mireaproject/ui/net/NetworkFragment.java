package ru.mirea.donetskaya.mireaproject.ui.net;

import static ru.mirea.donetskaya.mireaproject.MainActivity.preferences;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import ru.mirea.donetskaya.mireaproject.R;
import ru.mirea.donetskaya.mireaproject.databinding.FragmentNetworkBinding;

public class NetworkFragment extends Fragment {
    private FragmentNetworkBinding binding;
    private TextView textViewIP;
    private TextView textViewCity;
    private TextView textViewRegion;
    private TextView textViewCountry;
    private TextView textViewLocation;
    private TextView textViewOrganisation;
    private TextView textViewPostal;
    private TextView textViewTimezone;
    private TextView textViewReadme;

    String url = "https://ipinfo.io/json";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNetworkBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();
        textViewIP = root.findViewById(R.id.textViewIP);
        textViewCity = root.findViewById(R.id.textViewCity);
        textViewRegion = root.findViewById(R.id.textViewRegion);
        textViewCountry = root.findViewById(R.id.textViewCountry);
        textViewLocation = root.findViewById(R.id.textViewLocation);
        textViewOrganisation = root.findViewById(R.id.textViewOrganisation);
        textViewPostal = root.findViewById(R.id.textViewPostal);
        textViewTimezone = root.findViewById(R.id.textViewTimezone);
        textViewReadme = root.findViewById(R.id.textViewReadme);
        String backKey = getString(R.string.KEY_BACKGROUND);
        String backColor = preferences.getString(backKey, "white");
        root.setBackgroundColor(Color.parseColor(backColor));
        return root;
    }
    public void onClickNetwork(View view) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = null;
        if (connectivityManager != null) {
            networkinfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkinfo != null && networkinfo.isConnected()) {
            new DownloadPageTask().execute(url);
        } else {
            Toast.makeText(getContext(), "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }
    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewIP.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("TAG", "result: " + result);
            try {
                JSONObject responseJson = new JSONObject(result);
                String ip = responseJson.getString("ip");
                textViewIP.setText(ip);
                String city = responseJson.getString("city");
                textViewCity.setText(city);
                String region = responseJson.getString("region");
                textViewRegion.setText(region);
                String country = responseJson.getString("country");
                textViewCountry.setText(country);
                String loc = responseJson.getString("loc");
                textViewLocation.setText(loc);
                String org = responseJson.getString("org");
                textViewOrganisation.setText(org);
                String postal = responseJson.getString("postal");
                textViewPostal.setText(postal);
                String timezone = responseJson.getString("timezone");
                textViewTimezone.setText(timezone);
                String readme = responseJson.getString("readme");
                textViewReadme.setText(readme);
                //Log.d("TAG", "ip: " + ip);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                byte[] result = bos.toByteArray();
                bos.close();
                data = new String(result);
            } else {
                data = connection.getResponseMessage() + " . Error Code : " + responseCode;
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}