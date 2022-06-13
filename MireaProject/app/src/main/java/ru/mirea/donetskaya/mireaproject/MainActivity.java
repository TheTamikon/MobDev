package ru.mirea.donetskaya.mireaproject;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import ru.mirea.donetskaya.mireaproject.databinding.ActivityMainBinding;
import ru.mirea.donetskaya.mireaproject.ui.calc.CalculatorFragment;
import ru.mirea.donetskaya.mireaproject.ui.hardware.HardwareFragment;
import ru.mirea.donetskaya.mireaproject.ui.net.NetworkFragment;
import ru.mirea.donetskaya.mireaproject.ui.note.RoomFragment;
import ru.mirea.donetskaya.mireaproject.ui.player.MusicService;
import ru.mirea.donetskaya.mireaproject.ui.map.MapFragment;

public class MainActivity extends AppCompatActivity implements
        GoogleMap.OnMapClickListener, SensorEventListener{

    NavController navController;

    private boolean playFlag = false;
    private boolean noteFlag = false;
    private ImageView playImage;
    private SensorManager sensorManager;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        preferences = getPreferences(MODE_PRIVATE);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_browser,
                R.id.nav_calc,
                R.id.nav_map,
                R.id.nav_set,
                R.id.nav_net,
                R.id.nav_note,
                R.id.nav_hardware,
                R.id.nav_player)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        DrawerLayout d;
        String backKey = getString(R.string.KEY_BACKGROUND);
        String backColor = preferences.getString(backKey, "white");
        d=findViewById(R.id.drawer_layout);
        d.setBackgroundColor(Color.parseColor(backColor));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNoteDialog addNote = new AddNoteDialog();
                addNote.show(getSupportFragmentManager(), "Заметка");

            }
        });

    }
    public void onSignOut(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, FireActivity.class);
        startActivity(intent);
    }
    public void onAddClicked(View v) {

        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);
        EditText toDo = (EditText) v.findViewById(R.id.txtEditNoteName);
        EditText whenDo = (EditText) v.findViewById(R.id.txtEditNoteText);
        String toDoTxt = toDo.getText().toString();
        String whenDoTxt = whenDo.getText().toString();

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof RoomFragment) {
                ((RoomFragment) fragment).on_btnSubmitClick(toDoTxt, whenDoTxt);
                Toast.makeText(getApplicationContext(), "Добавлена новая заметка", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Действие отменено", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_UI);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (findViewById(R.id.textViewPitch) != null) {
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                float gyroscope = event.values[0];
                ((TextView) findViewById(R.id.textViewGyroscope))
                        .setText("Gyroscope: " + gyroscope);
            }
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float pitch = event.values[1];
                ((TextView) findViewById(R.id.textViewPitch))
                        .setText("Pitch: " + pitch);
            }
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float accel = event.values[0];
                ((TextView) findViewById(R.id.textViewAzimuth))
                        .setText("Azimuth: " + accel);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void on_btnNumberClick_calcFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof CalculatorFragment) {
                ((CalculatorFragment) fragment).on_btnNumberClick(v);
            }
        }
    }

    public void on_btnOperationClick_calcFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof CalculatorFragment) {
                ((CalculatorFragment) fragment).on_btnOperationClick(v);
            }
        }
    }
    public void onClickPlayer(View v) {
        playImage = findViewById(R.id.play);
        if(playFlag==false) {
            playFlag=true;
            playImage.setImageResource(R.drawable.stop);
            startService(
                    new Intent(MainActivity.this, MusicService.class));
        }
        else{
            playFlag=false;
            playImage.setImageResource(R.drawable.play);
            stopService(
                    new Intent(
                            MainActivity.this,
                            MusicService.class
                    )
            );
        }
    }
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                latLng).zoom(12).build();
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        MapFragment fragment =
                (MapFragment) hostFragment.getChildFragmentManager().getFragments().get(0);

        GoogleMap mMap = fragment.getMap();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().title("Что за место?")
                .snippet("Новое место").position(latLng));
    }
    public void imageViewOnClickHW(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof HardwareFragment) {
                ((HardwareFragment) fragment).imageViewOnClick(v);
            }
        }
    }
    public void onRecordStart(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);
        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof HardwareFragment) {
                ((HardwareFragment) fragment).onRecordStartF(v);
            }
        }
    }
    public void onStopRecord(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);
        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof HardwareFragment) {
                ((HardwareFragment) fragment).onStopRecordF(v);
            }
        }
    }
    public void onClickNet(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);
        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof NetworkFragment) {
                ((NetworkFragment) fragment).onClickNetwork(v);
            }
        }
    }

    public void on_btnRemoveClick_dataRoomFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof RoomFragment) {
                ((RoomFragment) fragment).on_btnRemoveClick(v);
            }
        }
    }
}