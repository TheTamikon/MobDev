package ru.mirea.donetskaya.mireaproject.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ru.mirea.donetskaya.mireaproject.MainActivity;
import ru.mirea.donetskaya.mireaproject.R;
import ru.mirea.donetskaya.mireaproject.databinding.FragmentMapBinding;


public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private boolean isWork = false;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 100;

    private GoogleMap mMap;
    private OnMapReadyCallback callback = new OnMapReadyCallback()  {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;
            mMap.setOnMapClickListener((MainActivity) getActivity());

            if (
                    ActivityCompat.checkSelfPermission(
                            getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(
                                    getContext(),
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
            ) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setTrafficEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            setUpMap();
        }
    };

    private void setUpMap() {
        LatLng mireaMain = new LatLng(55.670010, 37.48016);
        LatLng mireaVer = new LatLng(55.66158, 37.47761);
        LatLng mireaStav = new LatLng(45.05202, 41.91252);
        LatLng mireaFriaz = new LatLng(55.96567, 38.04891);
        LatLng mireaStrom = new LatLng(55.79351, 37.70119);
        LatLng mireaPir = new LatLng(55.73209, 37.57664);
        LatLng mireaSok = new LatLng(55.76500, 37.74195);
        LatLng mireaKol = new LatLng(55.72453, 37.63177);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(mireaMain).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.addMarker(new MarkerOptions().title("Главный кампус").snippet("Год создания: 28 мая 1947 г." +
                        "\nКоординаты: 55.670010, 37.48016" +
                        "\nАдрес: Москва, пр. Вернадского, 78").position(mireaMain));
        mMap.addMarker(new MarkerOptions().title("Кампус на Вернадского 86").snippet("Год создания: 1 июля 1900 г." +
                        "\nКоординаты: 55.66158, 37.47761" +
                        "\nАдрес: Москва, ул. Вернадского, 86").position(mireaVer));
        mMap.addMarker(new MarkerOptions().title("Филиал в Ставрополе").snippet("Год создания: 18 декабря 1996 года" +
                        "\nКоординаты: 45.05202, 41.91252" +
                        "\nАдрес: Ставрополь пр. Кулакова, д. 8").position(mireaStav));
        mMap.addMarker(new MarkerOptions().title("Филиал в Фрязино").snippet("Год создания: 1962 г." +
                        "\nКоординаты: 55.96567, 38.04891" +
                        "\nАдрес: Фрязино, ул. Вокзальная, д. 2а").position(mireaFriaz));
        mMap.addMarker(new MarkerOptions().title("Кампус на Стромынке").snippet("Год создания: 29 августа 1936 г." +
                        "\nКоординаты: 55.79351, 37.70119" +
                        "\nАдрес: Москва, ул. Стромынка, д. 20").position(mireaStrom));
        mMap.addMarker(new MarkerOptions().title("Кампус на Малой Пироговской").snippet("Год создания: -" +
                        "\nКоординаты: 55.73209, 37.57664" +
                        "\nАдрес: Москва, ул. Малая Пироговская, д. 1").position(mireaPir));
        mMap.addMarker(new MarkerOptions().title("Кампус на Соколиной Горе").snippet("Год создания: 1970 г." +
                        "\nКоординаты: 55.76500, 37.74195" +
                        "\nАдрес: Москва, 5-я ул. Соколиной Горы, д. 22").position(mireaSok));
        mMap.addMarker(new MarkerOptions().title("Колледж").snippet("Год создания: 1942 г." +
                        "\nКоординаты: 55.72453, 37.63177" +
                        "\nАдрес: Москва, 1-й Щипковский переулок, д. 23").position(mireaKol));
    }

    public GoogleMap getMap() {

        return mMap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();

        int locationPermissionStatus =
                ContextCompat.checkSelfPermission(
                        getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                );
        int secondLocationPermissionStatus =
                ContextCompat.checkSelfPermission(
                        getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                );

        if (locationPermissionStatus == PackageManager.PERMISSION_GRANTED &&
                secondLocationPermissionStatus == PackageManager.PERMISSION_GRANTED
        ) {
            isWork = true;
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    REQUEST_CODE_PERMISSION_LOCATION);
        }
        return root;
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
